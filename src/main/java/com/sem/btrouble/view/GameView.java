package com.sem.btrouble.view;

import java.io.IOException;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.model.Wallet;
import com.sem.btrouble.tools.GameObserver;
import com.sem.btrouble.tools.SoundObserver;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class GameView extends BasicGameState {
    private Timers timers;
    private static Controller controller;
    private static View view;
    private GameObserver observer;
    private SoundObserver soundObserver;
    private Audio wavEffect;
    private PowerUp powerObserver;
    private static Wallet wallet;

    /**
     * Initialize method of the slick2d library.
     *
     * @param gc
     *          should be the GameContainer containing the game.
     * @param sbg
     *          the reference to the StateBasedGame.
     * @throws SlickException
     *           when the game could not be initialized.
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        controller = new Controller(gc, sbg);
        observer = new GameObserver(true);
        soundObserver = new SoundObserver();
        powerObserver = new PowerUp(3);
        wallet = new Wallet();

        controller.addObserver(soundObserver);
        controller.addObserver(observer);
        controller.addObserver(powerObserver);
        controller.addObserver(wallet);

        timers = controller.getTimers();
        timers.restartTimer();

        view = new View(gc, timers);

        try {
            wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("Bubble_Trouble_Theme.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        wavEffect.playAsSoundEffect(1.0f, 1.0f, true);
        SoundStore.get().poll(0);
    }

    /**
     * Update method of the slick2d library.
     *
     * @param gc
     *          should be the GameContainer containing the game
     * @param sbg
     *          the reference to the StateBasedGame.
     * @param delta
     *          should be an integer representing the speed of the player
     * @throws SlickException
     *           when the controller could not be updated
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!timers.getCountdownRunning()) {
            controller.update(delta);
        }
    }

    /**
     * Render method of the slick2d library.
     *
     * @param gc
     *          should be the GameContainer containing the game
     * @param sbg
     *          the reference to the StateBasedGame.
     * @param graphics
     *          should be the graphics handler of the game
     * @throws SlickException
     *           when an item could not be drawn.
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) throws SlickException {
        view.draw(graphics);
    }

    public int getID() {
        return 1;
    }

    public static Controller getController() {
        return controller;
    }

    public static Wallet getWallet() { return wallet; }
}

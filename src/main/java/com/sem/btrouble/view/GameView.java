package com.sem.btrouble.view;

import com.sem.btrouble.SlickApp;
import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.model.Wallet;
import com.sem.btrouble.observering.LevelObserver;
import com.sem.btrouble.tools.SoundObserver;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class GameView extends BasicGameState implements LevelObserver {
    private Timers timers;
    private static Controller controller;
    private static View view;
    private SoundObserver soundObserver;
    private Audio wavEffect;
    private static Wallet wallet;
    private StateBasedGame sbg;

    /**
     * Initialize method of the slick2d library.
     *
     * @param gc
     *            should be the GameContainer containing the game.
     * @param sbg
     *            the reference to the StateBasedGame.
     * @throws SlickException
     *             when the game could not be initialized.
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;

        controller = new Controller(gc, sbg);
        soundObserver = new SoundObserver();
        wallet = new Wallet();

        controller.registerObserver(soundObserver);
        controller.registerObserver(SlickApp.getLogger());
        controller.registerObserver(wallet);
        controller.registerObserver(this);

        timers = controller.getTimers();
        timers.restartTimer();

        view = new View(gc, timers);

        try {
            wavEffect = AudioLoader.getAudio("WAV",
                    ResourceLoader.getResourceAsStream("Bubble_Trouble_Theme.wav"));
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
     *            should be the GameContainer containing the game
     * @param sbg
     *            the reference to the StateBasedGame.
     * @param delta
     *            should be an integer representing the speed of the player
     * @throws SlickException
     *             when the controller could not be updated
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!timers.getCountdownRunning()) {
            controller.update(delta);
        }
        if(!SlickApp.audioOn()) {
            wavEffect.stop();
        }
    }

    /**
     * Render method of the slick2d library.
     *
     * @param gc
     *            should be the GameContainer containing the game
     * @param sbg
     *            the reference to the StateBasedGame.
     * @param graphics
     *            should be the graphics handler of the game
     * @throws SlickException
     *             when an item could not be drawn.
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics)
            throws SlickException {
        view.draw(graphics);
    }

    /**
     * Get the id of the view.
     * @return the id.
     */
    public int getID() {
        return 1;
    }

    /**
     * Get the controller of the view.
     * @return the controller.
     */
    public static Controller getController() {
        return controller;
    }

    /**
     * Get the wallet of the view.
     * @return the wallet.
     */
    public static Wallet getWallet() {
        return wallet;
    }

    /**
     * Get the WavEffect of the view.
     * @return the wavEffect.
     */
    public Audio getWavEffect() {
        return wavEffect;
    }

    /**
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {
        sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
    }

    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
    }
}

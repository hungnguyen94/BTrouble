package com.sem.btrouble.view;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Timers;
import com.sem.btrouble.tools.GameObserver;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class GameView extends BasicGameState {
    private Timers timers;
    private static Controller controller;
    private static View view;
    private GameObserver observer;

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
        controller = new Controller(gc);
        observer = new GameObserver(true);

        controller.addObserver(observer);

        timers = controller.getTimers();
        timers.restartTimer();

        view = new View(gc, timers);
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
            controller.update();
            for (Player player : Model.getPlayers()) {
                player.move(gc, delta);
            }
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
}

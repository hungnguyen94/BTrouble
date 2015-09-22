package com.sem.btrouble.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class MenuView extends BasicGameState {
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
        if(gc.getInput().isKeyPressed(Input.KEY_RETURN)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
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
        graphics.drawString("Press enter to start", 300, 300);
    }

    public int getID() {
        return 0;
    }
}

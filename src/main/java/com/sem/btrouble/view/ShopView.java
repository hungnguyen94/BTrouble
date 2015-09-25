package com.sem.btrouble.view;

import com.sem.btrouble.model.Wallet;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class ShopView extends BasicGameState {
    private Image background;
    private TrueTypeFont font;

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
        background = new Image("Sprites/store1280x720.png");
        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");

            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            float mouseX = gc.getInput().getMouseX();
            float mouseY = gc.getInput().getMouseY();
            if(mouseX > 175 && mouseX < 700) {
                if (mouseY > 80 && mouseY < 155) {
                    System.out.println("bubblespeed");
                } else if (mouseY > 230 && mouseY < 320) {
                    System.out.println("time");
                } else if (mouseY > 390 && mouseY < 475) {
                    System.out.println("life");
                }
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
        graphics.setFont(font);
        background.draw(0f, 0f);
        String score = "" + GameView.getWallet().getValue();

        graphics.drawString(score, 70, 660);
    }

    public int getID() {
        return 2;
    }
}

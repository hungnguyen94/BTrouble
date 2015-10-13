package com.sem.btrouble.view;

import com.sem.btrouble.SlickApp;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class LostLevelView extends BasicGameState {
    private Image background;
    private TrueTypeFont font;

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
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        // Set objects to draw
        background = new Image("Sprites/lostlevel1280x720.png");

        loadFont();
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
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        // Press enter
        if (gc.getInput().isKeyPressed(Input.KEY_RETURN)) {
            GameView.getController().getTimers().restartTimer();
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
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
        graphics.setFont(font);
        background.draw(0f, 0f);
        drawWallet(graphics);

        graphics.drawString("You died. Press enter to restart this Level", 350,
                250);
        graphics.drawString("Press enter", 1000, 660);

        SpriteSheet livesImage = new SpriteSheet(
                "Sprites/lives_spritesheet.jpg", 381, 171);
        int lives = Model.getPlayers().get(0).getLives();
        if (lives >= 0) {
            livesImage.getSprite(lives, 0).draw(190, 670, (float) 0.286);
        }

    }

    public void drawWallet(Graphics graphics) {
        ArrayList<Player> players = Model.getPlayers();
        int sum = 0;

        for (int i = 0; i < players.size(); i++) {
            if (SlickApp.multiplayer() && SlickApp.versus()) {
                graphics.drawString(
                        "Wallet Player "
                                + (i + 1)
                                + " : "
                                + Model.getWallet(players.get(i))
                                        .getValue(), 300 + (i * 450), 450);
            }
            sum += players.get(i).getWallet().getValue();
        }
        if (!SlickApp.versus() || !SlickApp.multiplayer()) {
            graphics.drawString("Wallet: " + sum, 500, 660);
        }
    }

    /**
     * Loads the game font into a TrueTypeFont object to be used by the setFont
     * method.
     */
    private void loadFont() {
        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader
                    .getResourceAsStream("Sprites/IndieFlower.ttf");

            java.awt.Font awtFont = java.awt.Font.createFont(
                    java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the id of the view.
     * 
     * @return the id
     */
    public int getID() {
        return 4;
    }
}

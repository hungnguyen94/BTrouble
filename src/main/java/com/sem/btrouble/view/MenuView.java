package com.sem.btrouble.view;

import com.sem.btrouble.SlickApp;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class MenuView extends BasicGameState {
    private TrueTypeFont font;
    private Image background;
    private MouseOverArea audioButton;

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
        background = new Image("Sprites/menu1280x720.png");
        audioButton = new MouseOverArea(gc, background, 500, 410, 250, 240);
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
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Enter button
        if (gc.getInput().isKeyPressed(Input.KEY_RETURN)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        //Audio settings
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && audioButton.isMouseOver()) {
            SlickApp.setAudio(!SlickApp.audioOn());
        }
        //Activate audio settings
        if(!SlickApp.audioOn()) {
            GameView.getWavEffect().stop();
        } else if(!GameView.getWavEffect().isPlaying()) {
            GameView.getWavEffect().playAsMusic(1.0f, 1.0f, true);
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
        background.draw(0f, 0f);

        //Draw audio settings button
        graphics.setFont(font);
        String audioSetting = "Audio: ";
        if (SlickApp.audioOn()) {
            audioSetting = audioSetting + "on";
        } else {
            audioSetting = audioSetting + "off";
        }
        graphics.drawString(audioSetting, 500, 410);
    }

    public int getID() {
        return 0;
    }

    /**
     * Loads the game font into a TrueTypeFont object to be used by the setFont method.
     */
    private void loadFont() {
        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");

            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

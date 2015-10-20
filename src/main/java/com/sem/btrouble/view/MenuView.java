package com.sem.btrouble.view;

import com.sem.btrouble.SlickApp;
import com.sem.btrouble.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.openal.Audio;
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
    private MouseOverArea multiplayerButton;
    private MouseOverArea versusButton;
    private MouseOverArea survivalButton;

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
        audioButton = new MouseOverArea(gc, background, 500, 410, 250, 50);
        multiplayerButton = new MouseOverArea(gc, background, 500, 460, 250, 50);
        versusButton = new MouseOverArea(gc, background, 500, 510, 250, 50);
        survivalButton = new MouseOverArea(gc, background, 500, 560, 250, 50);
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
        //Settings
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if(audioButton.isMouseOver()) {
                Test.setAudio(!SlickApp.audioOn());
            } else if(multiplayerButton.isMouseOver()) {
                SlickApp.setMultiplayer(!SlickApp.multiplayer());
            } else if(versusButton.isMouseOver()) {
                SlickApp.setVersus(!SlickApp.versus());
            } else if(survivalButton.isMouseOver()) {
                SlickApp.setSurvival(!SlickApp.survival());
            }
        }

        //Activate audio settings
        GameView gameView = (GameView) sbg.getState(1);
        Audio wavEffect = gameView.getWavEffect();

        if(!SlickApp.audioOn()) {
            wavEffect.stop();
        } else if(!wavEffect.isPlaying()) {
            wavEffect.playAsMusic(1.0f, 1.0f, true);
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

        graphics.setFont(font);

        drawAudioButton(graphics);
        drawMultiplayerButton(graphics);
        drawVersusButton(graphics);
        drawSurvivalButton(graphics);
    }

    /**
     * Draws the preferences button for audio to the screen.
     * @param graphics should be the graphics handler of the game.
     */
    public void drawAudioButton(Graphics graphics) {
        String audioSetting = "Audio: ";
        if (SlickApp.audioOn()) {
            audioSetting = audioSetting + "on";
        } else {
            audioSetting = audioSetting + "off";
        }
        graphics.drawString(audioSetting, audioButton.getX(), audioButton.getY());
    }

    /**
     * Draws the preferences button for multiplayer to the screen.
     * @param graphics should be the graphics handler of the game.
     */
    public void drawMultiplayerButton(Graphics graphics) {
        String multiplayerSetting = "Multiplayer: ";
        if (SlickApp.multiplayer()) {
            multiplayerSetting = multiplayerSetting + "on";
        } else {
            multiplayerSetting = multiplayerSetting + "off";
        }
        graphics.drawString(multiplayerSetting, multiplayerButton.getX(), multiplayerButton.getY());
    }

    /**
     * Draws the preferences button for multiplayer to the screen.
     * @param graphics should be the graphics handler of the game.
     */
    public void drawVersusButton(Graphics graphics) {
        String versusSetting = "Versus: ";
        if (SlickApp.versus()) {
            versusSetting = versusSetting + "on";
        } else {
            versusSetting = versusSetting + "off";
        }
        graphics.drawString(versusSetting, versusButton.getX(), versusButton.getY());
    }

    /**
     * Draws the preferences button for multiplayer to the screen.
     * @param graphics should be the graphics handler of the game.
     */
    public void drawSurvivalButton(Graphics graphics) {
        String survivalSetting = "Survival: ";
        if (SlickApp.survival()) {
            survivalSetting = survivalSetting + "on";
        } else {
            survivalSetting = survivalSetting + "off";
        }
        graphics.drawString(survivalSetting, survivalButton.getX(), survivalButton.getY());
    }

    /**
     * Get the id of the view.
     * @return the id
     */
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

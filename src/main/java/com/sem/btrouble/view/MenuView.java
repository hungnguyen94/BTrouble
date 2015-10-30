package com.sem.btrouble.view;

import com.sem.btrouble.BTrouble;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
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
    private Audio wavEffect;

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
        try {
            wavEffect = AudioLoader.getAudio("WAV",
                    ResourceLoader.getResourceAsStream("Bubble_Trouble_Theme.wav"));
            wavEffect.playAsMusic(1.5f, 1.0f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (gc.getInput().isKeyPressed(Input.KEY_RETURN)) {
            if(BTrouble.getMultiplayer()) {
                sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
            } else {
                sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            }
        }

        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if(audioButton.isMouseOver()) {
                BTrouble.setAudio(!BTrouble.getAudioOn());
            } else if(multiplayerButton.isMouseOver()) {
                BTrouble.setMultiplayer(!BTrouble.getMultiplayer());
            } else if(versusButton.isMouseOver()) {
                BTrouble.setVersus(!BTrouble.getVersus());
            } else if(survivalButton.isMouseOver()) {
                BTrouble.setSurvival(!BTrouble.getSurvival());
            }
        }
        if(wavEffect.isPlaying() && !BTrouble.getAudioOn()) {
            wavEffect.stop();
        } else if(!wavEffect.isPlaying()) {
            wavEffect.playAsSoundEffect(1.4f, 1.f, true);
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
        graphics.setColor(Color.white);
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
        if (BTrouble.getAudioOn()) {
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
        if (BTrouble.getMultiplayer()) {
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
        if (BTrouble.getVersus()) {
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
        if (BTrouble.getSurvival()) {
            survivalSetting = survivalSetting + "on";
        } else {
            survivalSetting = survivalSetting + "off";
        }
        graphics.drawString(survivalSetting, survivalButton.getX(), survivalButton.getY());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {

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
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");

            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    inputStream);
            awtFont = awtFont.deriveFont(24f);
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

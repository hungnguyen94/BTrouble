package com.sem.btrouble;

import com.sem.btrouble.event.ExceptionEvent;
import com.sem.btrouble.model.GraphicSettings;
import com.sem.btrouble.tools.Logger;
import com.sem.btrouble.view.GameView;
import com.sem.btrouble.view.MenuView;

import com.sem.btrouble.view.ShopView;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Application running the game.
 *
 */
public class SlickApp extends StateBasedGame {

    private static GraphicSettings graphics;
    private static boolean audioOn = true;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final int DEFAULT_FRAMERATE = 60;

    /**
     * Init the Slickapp.
     * 
     * @param gamename
     *            is the name of the game
     */
    public SlickApp(String gamename) {
        super(gamename);
    }

    /**
     * Main class of the SlickApp.
     *
     * @param args
     *            should be empty.
     */
    public static void main(String[] args) {
        try {
            graphics = new GraphicSettings(true, true, 0);
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlickApp("Bubble Trouble"));
            appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
            appgc.setShowFPS(false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(DEFAULT_FRAMERATE);
            appgc.setAlwaysRender(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.log(new ExceptionEvent(ex, "initialisation of the game failed."));
        }
    }

    /**
     * Add states to the stateslist.
     * 
     * @param gc
     *            GameContainer reference
     * @throws SlickException
     *             when sprite is incorrect
     */
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MenuView());
        this.addState(new GameView());
        this.addState(new ShopView());
    }

    /**
     * Returns the graphics settings.
     * 
     * @return the graphics
     */
    public static GraphicSettings returnGraphics() {
        return graphics;
    }

    /**
     * Sets the audio setting.
     * @param audioOnSet boolean to set the audio.
     */
    public static void setAudio(boolean audioOnSet) {
        audioOn = audioOnSet;
    }

    /**
     * Get the audio setting.
     * @return a boolean that is true if the audio is on.
     */
    public static boolean audioOn() {
        return audioOn;
    }
}
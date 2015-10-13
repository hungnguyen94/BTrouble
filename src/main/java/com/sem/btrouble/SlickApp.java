package com.sem.btrouble;

import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.model.GraphicSettings;
import com.sem.btrouble.observering.EventObserver;
import com.sem.btrouble.observering.EventSubject;
import com.sem.btrouble.tools.Logger;
import com.sem.btrouble.view.GameView;
import com.sem.btrouble.view.LostLevelView;
import com.sem.btrouble.view.MenuView;
import com.sem.btrouble.view.ShopView;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Application running the game.
 *
 */
public class SlickApp extends StateBasedGame implements EventSubject {

    private static GraphicSettings graphics;
    private static boolean audioOn = true;
    private static HashMap<String,Boolean> preferences;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final int DEFAULT_FRAMERATE = 60;
    private static Logger logger;
    private ArrayList<EventObserver> observers;

    /**
     * Init the Slickapp.
     * 
     * @param gamename
     *            is the name of the game.
     */
    public SlickApp(String gamename) {
        super(gamename);

        logger = new Logger(Logger.DEFAULT_LOGGER_PATH, true);
        observers = new ArrayList<EventObserver>();
        preferences = new HashMap<String, Boolean>();
        preferences.put("audio", true);
        preferences.put("multiplayer", false);
        preferences.put("versus", true);
        preferences.put("survival", false);
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
        } catch (SlickException exception) {
            exception.printStackTrace();
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
        this.addState(new LostLevelView());
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
     * 
     * @param audio
     *            boolean to set the audio.
     */
    public static void setAudio(boolean audio) {
        preferences.put("audio", audio);
    }

    /**
     * Get the audio setting.
     * 
     * @return a boolean that is true if the audio is on.
     */
    public static Boolean audioOn() {
        return preferences.get("audio");
    }

    /**
     * Sets the multiplayer setting.
     *
     * @param multiplayer
     *            boolean to set the multiplayer.
     */
    public static void setMultiplayer(boolean multiplayer) {
        preferences.put("multiplayer", multiplayer);
    }

    /**
     * Get the multiplayer setting.
     *
     * @return a boolean that is true if the multiplayer is on.
     */
    public static Boolean multiplayer() {
        return preferences.get("multiplayer");
    }

    /**
     * Sets the versus setting.
     *
     * @param versus
     *            boolean to set the versus mode.
     */
    public static void setVersus(boolean versus) {
        preferences.put("versus", versus);
    }

    /**
     * Get the versus setting.
     *
     * @return a boolean that is true if the versus mode is on.
     */
    public static Boolean versus() {
        return preferences.get("versus");
    }

    /**
     * Sets the survival setting.
     *
     * @param survival
     *            boolean to set the survival mode.
     */
    public static void setSurvival(boolean survival) {
        preferences.put("survival", survival);
    }

    /**
     * Get the multiplayer setting.
     *
     * @return a boolean that is true if the multiplayer is on.
     */
    public static Boolean survival() {
        return preferences.get("survival");
    }

    /**
     * Returns the logger the SlickApp uses to log events.
     * @return returns the logger the SlickApp uses to log events.
     */
    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void fireEvent(GameEvent gameEvent) {
        for (EventObserver observer : observers) {
            observer.update(gameEvent);
        }
    }

    @Override
    public void registerObserver(EventObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EventObserver observer) {
        observers.remove(observer);
    }
}

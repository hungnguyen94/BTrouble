package com.sem.btrouble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.sem.btrouble.game.AbstractGame;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.view.GameState;
import com.sem.btrouble.view.LostLevelState;
import com.sem.btrouble.view.MenuView;
import com.sem.btrouble.view.MultiPlayerGameState;

/**
 * @author Hung
 */
public class BTrouble extends StateBasedGame {

    private static HashMap<String, Boolean> preferences;
    private AbstractGame game;
    private List<Player> playerList;

    /**
     * Init the Slickapp.
     *
     * @param gamename
     *            is the name of the game
     */
    public BTrouble(String gamename) {
        super(gamename);
        preferences = new HashMap<>();
        preferences.put("audio", true);
        preferences.put("multiplayer", false);
        preferences.put("versus", 
                false);
        preferences.put("survival", false);
        this.game = null;
        this.playerList = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new BTrouble("Bubble Trouble"));
            appgc.setDisplayMode(1280, 720, false);
            appgc.setShowFPS(false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
            appgc.setAlwaysRender(true);
            appgc.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MenuView());
        this.addState(new GameState());
        this.addState(new MultiPlayerGameState());
        this.addState(new LostLevelState());
    }

    /**
     * Getter for the game object.
     * @return The game object.
     */
    public AbstractGame getGame() {
        return game;
    }

    /**
     * Setter for the game object.
     * @param game The game object.
     */
    public void setGame(AbstractGame game) {
        this.game = game;
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
    public static Boolean getAudioOn() {
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
    public static Boolean getMultiplayer() {
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
    public static Boolean getVersus() {
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
    public static Boolean getSurvival() {
        return preferences.get("survival");
    }
}

package com.sem.btrouble.model;

import java.util.List;

/**
 * Created by rubenwiersma on 30-10-15.
 */
public class PlayerInfo {
    private static PlayerInfo instance = null;
    private List<Player> players;

    /**
     * PlayerInfo is a singleton with the info on the player, accessible from all the GameStates.
     */
    protected PlayerInfo() {
    }

    /**
     * Returns the PlayerInfo instance.
     * @return PlayerInfo instance
     */
    public static PlayerInfo getInstance() {
        if(instance == null) {
            instance = new PlayerInfo();
        }
        return instance;
    }

    /**
     * Set the list of players in the game.
     * @param players list of Player objects
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Get the list of players.
     * @return list of Player object
     */
    public List<Player> getPlayers() {
        return players;
    }
}

package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Level class.
 */
public class Level {
    private Room room;
    private List<Player> players;
    private List<Bubble> bubbles;

    /**
     * Constructor for the level class.
     */
    public Level() {
        room = new Room();
        players = new ArrayList<Player>();
        bubbles = new ArrayList<Bubble>();
    }

    /**
     * Adds a player to the level.
     * @param player Player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    public void restartLevel() {

    }

}

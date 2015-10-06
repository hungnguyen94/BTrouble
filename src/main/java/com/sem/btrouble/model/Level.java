package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Level class.
 */
public class Level implements Subject {
    private Room currentRoom;
    private List<Player> players;
    private List<Bubble> bubbles;

    private List<Observer> observersList;

    /**
     * Constructor for the level class.
     */
    public Level() {
        this.currentRoom = new Room();
        this.players = new ArrayList<Player>();
        this.bubbles = new ArrayList<Bubble>();
    }

    /**
     * Adds a player to the level.
     * @param player Player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }


    /**
     * Register an observer to the subject.
     *
     * @param observer Observer to be added.
     */
    public void registerObserver(Observer observer) {
        if(observer == null || observersList.contains(observer))
            return;
        observersList.add(observer);
    }

    /**
     * Remove an observer from the observersList list.
     *
     * @param observer Observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
        if(observer == null || !observersList.contains(observer))
            return;
        observersList.remove(observer);
    }

    /**
     * Method to notify the observersList about a change.
     */
    @Override
    public void notifyObserver() {
        for(Observer obj: observersList) {
            obj.update();
        }
    }
}

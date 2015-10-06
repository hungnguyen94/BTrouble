package com.sem.btrouble.model;

import com.sem.btrouble.controller.CollisionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Level class.
 */
public class Level implements Subject {
    private Room currentRoom;
    private List<Player> players;
    private CollisionHandler collisionHandler;
    private List<Observer> observersList;

    /**
     * Constructor for the level class.
     */
    public Level() {
        this.currentRoom = new Room();
        this.players = new ArrayList<Player>();
    }

    /**
     * Adds a player to the level.
     * @param player Player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Attaches the collisionHandler to the level.
     * @param collisionHandler CollisionHandler that will be added.
     */
    public void addCollisionHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }

    /**
     * Register an observer to the subject.
     *
     * @param observer Observer to be added.
     */
    @Override
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

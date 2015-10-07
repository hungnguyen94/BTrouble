package com.sem.btrouble.model;

import com.sem.btrouble.controller.CollisionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Level class.
 */
public class Level implements Subject {
    private Room room;
    private List<Player> players;
    private CollisionHandler collisionHandler;
    private List<Observer> observersList;
    private Timers timer;

    /**
     * Constructor for the level class.
     */
    public Level() {
        // Temp loading room method.
        Room r1 = new Room();
        r1.loadRoom();
        this.room = r1;
        this.players = new ArrayList<Player>();
        this.observersList = new ArrayList<Observer>();
        this.timer = new Timers(0);
        this.collisionHandler = new CollisionHandler();
        this.collisionHandler.addCollidable(room.getCollidables());
    }

    /**
     * Constructor for the level class with room parameter.
     * @param room Room that the level should be started with.
     */
    public Level(Room room) {
        this.room = room;
        this.players = new ArrayList<Player>();
        this.observersList = new ArrayList<Observer>();
        this.timer = new Timers(0);
        this.collisionHandler = new CollisionHandler();
        this.collisionHandler.addCollidable(room.getCollidables());
        timer.restartTimer();
    }

    /**
     * Adds a player to the level.
     * @param player Player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
        collisionHandler.addCollidable(players);
    }

    /**
     * Checks if any player in this level is alive.
     * @return True if there's a player alive.
     */
    private boolean playersAlive() {
        for(Player player : players) {
            if(player.isAlive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calls the move method on all objects in the level.
     */
    public synchronized void moveObjects() {
        // Don't update objects when countdown is running.
        if(timer.getCountdownRunning()) {
            System.out.println("Counting down: " + timer.getCountdownTimeLeft());
            notifyObserver();
            return;
        }
        if(timer.getLevelTimerRunning()) {
            System.out.println("Level time left: " + timer.getLevelTimeLeft());
        }
        for(Player player : players) {
            if (!collisionHandler.checkCollision(player)) {
                player.setFalling(true);
            }
            player.move();
        }
        room.moveBubbles();
        collisionHandler.checkAllCollisions();
        notifyObserver();
    }

    /**
     * Register an observer to the subject.
     *
     * @param observer Observer to be added.
     */
    @Override
    public void registerObserver(Observer observer) {
        if(observer == null || observersList.contains(observer)) {
            return;
        }
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
        if(!playersAlive()) {
            for(Observer obj: observersList) {
                obj.levelLost();
            }
        }
        if(!room.hasBubbles()) {
            for(Observer obj: observersList) {
                obj.levelWon();
            }
        }
        if(!timer.getCountdownRunning() && !timer.getLevelTimerRunning()) {
            for(Observer obj: observersList) {
                obj.levelLost();
            }
        }
    }
}

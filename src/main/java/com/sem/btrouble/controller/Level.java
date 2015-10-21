package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Drawable;
import com.sem.btrouble.model.Movable;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.LevelObserver;
import com.sem.btrouble.observering.LevelSubject;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Level class.
 */
public class Level implements LevelSubject, Drawable {
    private Room room;
    private List<Player> players;
    private List<Movable> movables;
    private List<LevelObserver> observersList;
    private boolean levelRunning;
    private Control ultimateController;
    private List<Bubble> bubbleList;
    private List<PowerUp> powerUpList;

    /**
     * Constructor for the level class with room parameter.
     * @param room Room that the level should be started with.
     */
    public Level(Room room) {
        this.room = room;
        this.players = new ArrayList<Player>();
        this.observersList = new ArrayList<LevelObserver>();
        this.movables = new CopyOnWriteArrayList<>();
        this.bubbleList = new CopyOnWriteArrayList<>();
        this.powerUpList = new CopyOnWriteArrayList<>();
        this.ultimateController = new PowerUpController(new BubbleController(new CollisionHandler(), bubbleList), powerUpList);
        this.ultimateController.addListReference(room.getCollidables());
        this.ultimateController.addListReference(movables);
    }

    /**
     * Adds a player to the level.
     * And put it on the room spawn position.
     * @param player Player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setX(room.getSpawnPositionX());
        player.setY(room.getSpawnPositionY());
        addMovable(player);
    }

    /**
     * This method adds a bubble to the list.
     * @param bubble This bubble will be added.
     */
    public void addBubble(Bubble bubble) {
        bubbleList.add(bubble);
    }

    /**
     * This method adds a collection of bubbles to the list.
     * @param bubbles These bubbles will be added.
     */
    public void addBubble(Collection<Bubble> bubbles) {
        bubbleList.addAll(bubbles);
    }

    /**
     * Adds a movable object to the level.
     * @param movable
     */
    public void addMovable(Movable movable) {
        movables.add(movable);
    }

    /**
     * Remove a movable object from the level.
     * @param movable
     */
    public void removeMovable(Movable movable) {
        movables.remove(movable);
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
        ultimateController.update();
        for(Player player : players) {
//            if (!collisionHandler.checkCollision(player)) {
//                player.setFalling(true);
//            }
        }
        for(Movable movable : movables) {
            movable.move();
            if(movable.getCollidedStatus()) {
                removeMovable(movable);
            }
        }
        notifyObserver();
    }

    /**
     * Stops the current level.
     */
    public void stop() {
        levelRunning = false;
    }

    /**
     * Starts the current level.
     */
    public void start() {
        levelRunning = true;
    }

    /**
     * Check if the level is running.
     * @return True if level is running.
     */
    public boolean isLevelRunning() {
        return levelRunning;
    }

    /**
     * Register an observer to the subject.
     *
     * @param observer Observer to be added.
     */
    @Override
    public void registerObserver(LevelObserver observer) {
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
    public void removeObserver(LevelObserver observer) {
        observersList.remove(observer);
    }

    /**
     * Method to notify the observersList about a change.
     */
    @Override
    public void notifyObserver() {
        if(!playersAlive()) {
            for(LevelObserver obj: observersList) {
                obj.levelLost();
            }
        }
        if(!room.hasBubbles()) {
            for(LevelObserver obj: observersList) {
                obj.levelWon();
            }
        }
    }

    /**
     * Draw the object.
     *
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        for(Movable movable : movables) {
            if(movable instanceof Drawable) {
                Drawable tempremovethis = (Drawable) movable;
                tempremovethis.draw(graphics);
            }
        }
        room.draw(graphics);
        ultimateController.draw(graphics);
        for(Player player : players) {
            player.draw(graphics);
        }
    }
}

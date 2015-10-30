package com.sem.btrouble.controller;

import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.PlayerInfo;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Rope;
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
public class Level implements LevelSubject {
    private Room room;
    private List<Player> players;
    private List<LevelObserver> observersList;
    private boolean levelRunning;
    private Controller mainController;
    private BubbleController bubbleController;
    private RopeController ropeController;

    /**
     * Constructor for the level class with room parameter.
     * @param room Room that the level should be started with.
     */
    public Level(Room room) {
        this.room = room;
        this.players = new CopyOnWriteArrayList<>();
        this.observersList = new ArrayList<>();

        this.bubbleController = new BubbleController(new CollisionHandler());
        this.ropeController = new RopeController(new PowerUpController(bubbleController));
        this.mainController = new BorderController(ropeController, 
                room.getMoveableWalls(), room.getMoveableFloors());

        this.mainController.addListReference(room.getCollidablesList());
        this.mainController.addListReference(players);
    }

    /**
     * Adds a player to the level.
     * And put it on the room spawn position.
     * @param player Player to be added.
     */
    public void addPlayer(Player player) {
        player.reset();
        players.add(player);
        player.setX(room.getSpawnPositionX());
        player.setY(room.getSpawnPositionY());
        PlayerInfo.getInstance().setPlayers(players);
    }

    /**
     * This method adds a collection of bubbles to the list.
     * @param bubbles These bubbles will be added.
     */
    public void addBubble(Collection<Bubble> bubbles) {
        bubbleController.addBubble(bubbles);
    }

    /**
     * Adds a rope to the ropeController.
     * @param rope rope that is added.
     */
    public void addRope(Rope rope) {
        ropeController.addRope(rope);
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
        mainController.update();
        for(Player player : players) {
            player.move();
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
     * This method will lose the level.
     */
    public void loseLevel() {
        stop();
        for(LevelObserver obj: observersList) {
            obj.levelLost();
        }
    }
    /**
     * This method will win the level.
     */
    public void winLevel() {
        stop();
        for(LevelObserver obj: observersList) {
            obj.levelWon();
        }
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
            loseLevel();
        }
        if(bubbleController.getBubblesAmount() <= 0) {
            winLevel();
        }
    }

    /**
     * Draw the object.
     *
     * @param graphics The graphics
     */
    public void draw(Graphics graphics) {
        room.draw(graphics);
        mainController.draw(graphics);
        for(Player player : players) {
            player.draw(graphics);
        }
    }
}

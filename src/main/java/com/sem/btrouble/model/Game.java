package com.sem.btrouble.model;

import java.util.List;

/**
 * Game class.
 */
public class Game implements Observer {
    private Player player;
    private Level level;
    private Runnable levelThread;
    private boolean levelRunning;

    // Temp solution to restart current room level.
    private Room tempRoom;

    /**
     * Constructor for a game.
     * @param player players in the game.
     */
    public Game(Player player) {
        this.player = player;
    }

    /**
     * Loads a new level with the given room.
     * @param room Room of the level that should be loaded.
     */
    public void loadLevel(Room room) {
        // Deep copy temp fix.
        this.tempRoom = room;
        Room deepCopiedRoom = (Room)DeepCopyList.deepCopy(room);
        this.level = new Level(deepCopiedRoom);
        level.addPlayer(player);
        level.registerObserver(this);
    }

    /**
     * Start the level in a separate thread.
     * Game is independent from the view, and can be run headless.
     */
    public void startLevel() {
        levelRunning = true;
        levelThread = new Runnable() {
            @Override
            public void run() {
                while(levelRunning) {
                    try {
                        // Run moveobjects every 1000 ms.
                        level.moveObjects();
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        levelThread.run();
    }

    /**
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {
        // If has next room, load room from loader.
        // Temp solution tempRoom.
        loadLevel(tempRoom);
        // Stops the level thread.
        levelRunning = false;
    }

    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        // Stops the level thread.
        levelRunning = false;
        if(player.hasLives()) {
            // Level restart.
            loadLevel(tempRoom);
        } else {
            // End game.
        }
    }
}

package com.sem.btrouble.model;

import java.util.ArrayList;

/**
 * Model contains all data of the game. Model is updated by the Controller and
 * used to draw the View. Model has been split into partial models such as
 * Objects and Players.
 */
public class Model {
    private static RoomIterator rooms;
    private static ArrayList<Player> players;
    private static Room roomCurrent;
    private static int currentLevel;
    private static ArrayList<PowerUp> powers = new ArrayList<PowerUp>();
    private static ArrayList<PowerUp> powersshort = new ArrayList<PowerUp>();

    private static int ROOM_WIDTH;
    private static int ROOM_HEIGHT;

    /**
     * Initializes the model. Should be done before any calls to its methods are
     * done.
     * 
     * @param width
     *            should be the width of the room.
     * @param height
     *            should be the height of the roo
     */
    public static void init(int width, int height) {
        ROOM_WIDTH = width;
        ROOM_HEIGHT = height;
        rooms = new RoomIterator();
        players = new ArrayList<Player>();
        currentLevel = 1;
        Room r = new Room();
        Room r2 = new Room();
        r.loadRoom();
        r2.loadRoom2();
        Model.addRoom(r);
        Model.addRoom(r2);
    }

    /**
     * Adds a room to the Model's room collection.
     *
     * @param room
     *            should be the Room which is added to the Model's room
     *            collection
     */
    public static void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Get the next room.
     * 
     * @return returns the next room
     */
    public static Room getNextRoom() {
        currentLevel++;
        if (rooms.hasNext()) {
            return rooms.next();
        } else {
            return null;
        }
    }

    /**
     * Returns the room which is currently active.
     *
     * @return returns the room which is currently active.
     */
    public static Room getCurrentRoom() {
        return roomCurrent;
    }

    /**
     * Returns the Model's player collection.
     *
     * @return returns an ArrayList which is the Model's player collection.
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the Model's bubble collection.
     *
     * @return returns an ArrayList which is the Model's bubble collection.
     */
    public static ArrayList<Bubble> getBubbles() {
        return new ArrayList<Bubble>(getCurrentRoom().getBubbles());
    }

    /**
     * Returns the height of the room.
     *
     * @return returns an integer representing the height of the room.
     */
    public static int getRoomHeight() {
        return ROOM_HEIGHT;
    }

    /**
     * Returns the width of the room.
     *
     * @return returns an integer representing the width of the room.
     */
    public static int getRoomWidth() {
        return ROOM_WIDTH;
    }

    /**
     * Restarts the currently active room, resets all the objects in the room
     * but preserves the players and their scores.
     */
    public static void restartRoom() {
        roomCurrent = rooms.roomRestart();
        for (Player p : players) {
            p.resetRope();
        }

        for (Player p : players) {
            p.moveTo(getCurrentRoom().getSpawnPositionX(), getCurrentRoom().getSpawnPositionY());
        }
    }
    
    public static ArrayList<PowerUp> getPowerUps() {
        return powers;
    }
    
    public static void addPowerUp(PowerUp power) {
        powers.add(power);
    }
    
    public static void clearPowerUps() {
        powers.clear();
    }
    
    public static ArrayList<PowerUp> getShortPower() {
    	return powersshort;
    }
    
    public static void addShortPowerUp(PowerUp power) {
    	powersshort.add(power);
    }
    
    public static void deleteShortPower(PowerUp power) {
    	powersshort.remove(power);
    }
    
    public static void clearShortPower() {
    	powersshort.clear();
    }

    /**
     * Adds a new player to the game.
     *
     * @param player
     *            should be the Player to be added to the game.
     */
    public static void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Get the current room.
     * 
     * @return returns the current room.
     */
    public static int getRoom_current() {
        return currentLevel;
    }
}

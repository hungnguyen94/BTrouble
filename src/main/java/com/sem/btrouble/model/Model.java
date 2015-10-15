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
    private static Timers timers;

    private static int roomWidth;
    private static int roomHeight;

    private static final int DELAY = 100;

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
        roomWidth = width;
        roomHeight = height;
        rooms = new RoomIterator();
        players = new ArrayList<Player>();
        currentLevel = 1;
        Room r = new Room();
        Room r2 = new Room();
        r.loadRoom();
        r2.loadRoom2();
        Model.addRoom(r);
        Model.addRoom(r2);
        timers = new Timers(DELAY);
    }
    
    /**
     * Get the wallet of a player.
     * @param player from who you want the wallet
     * @return the wallet
     */
    public static Wallet getWallet(Player player) {
         return player.getWallet();
    }

    /**
     * Getter for timers.
     * @return Timers object
     */
    public static Timers getTimers() {
        return timers;
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
     * Remove a player from the model.
     * @param player Player to be removed
     */
    public static void removePlayer(Player player) {
        players.remove(player);
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
        return roomHeight;
    }

    /**
     * Returns the width of the room.
     *
     * @return returns an integer representing the width of the room.
     */
    public static int getRoomWidth() {
        return roomWidth;
    }

    /**
     * Restarts the currently active room, resets all the objects in the room
     * but preserves the players and their scores.
     */
    public static void restartRoom() {
        roomCurrent = rooms.roomRestart();
        clearPowerUps();
        clearShortPower();
        getTimers().restartTimer();

        for (Player p : players) {
            p.resetRope();
        }

        for (Player p : players) {
            p.moveTo(getCurrentRoom().getSpawnPositionX(), getCurrentRoom().getSpawnPositionY());
        }
    }
    
    /**
     * Get the power ups bought in the store.
     * @return the power ups
     */
    public static ArrayList<PowerUp> getPowerUps() {
        return powers;
    }
    
    /**
     * Add a store power up.
     * @param power the power up
     */
    public static void addPowerUp(PowerUp power) {
        powers.add(power);
    }
    
    /**
     * Clear the power up bought in the store.
     */
    public static void clearPowerUps() {
        powers.clear();
    }
    
    /**
     * Get power ups received in the game.
     * @return the power ups
     */
    public static ArrayList<PowerUp> getShortPower() {
    	return powersshort;
    }
    
    /**
     * Add a power up received in the game.
     * @param power the power up
     */
    public static void addShortPowerUp(PowerUp power) {
    	powersshort.add(power);
    }
    
    /**
     * Delete a specific power up received in the game.
     * @param power the power up
     */
    public static void deleteShortPower(PowerUp power) {
    	ArrayList<PowerUp> powers = new ArrayList<PowerUp>();
    	for(int i = 0; i < powersshort.size(); i++) {
    		PowerUp listPower = powersshort.get(i);
    		if(power instanceof LifePowerUp) {
    			if(listPower instanceof SlowPowerUp || listPower instanceof TimePowerUp) {
    				powers.add(listPower);
    			}
    		} else if (power instanceof SlowPowerUp) {
    			if(listPower instanceof LifePowerUp || listPower instanceof TimePowerUp) {
    				powers.add(listPower);
    			}
    		} else if (power instanceof TimePowerUp && (listPower instanceof SlowPowerUp
    		        || listPower instanceof LifePowerUp)) {
    			powers.add(listPower);
    		}
    	}
		powersshort = powers;
    }
    
    /**
     * Remove all power ups received in the game.
     */
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

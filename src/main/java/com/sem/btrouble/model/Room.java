package com.sem.btrouble.model;

import java.util.ArrayList;

/**
 * Room contains all objects within the room (except for the players), and draws them on the screen.
 *
 */
public class Room {
    private int spawnPositionX;
    private int spawnPositionY;

	private ArrayList<Wall> walls;
	private ArrayList<Floor> floors;
	private ArrayList<Bubble> bubbles;
	
	/**
	 * Initializes the room with the objects
	 */
	public Room(){
        walls = new ArrayList<Wall>();
        floors = new ArrayList<Floor>();
        bubbles = new ArrayList<Bubble>();
        spawnPositionX = 0;
        spawnPositionY = 0;
	}

	public boolean equals(Object other) {
		if(other instanceof Room) {
			Room that = (Room) other;
			return(this.bubbles.equals(that.bubbles) && this.spawnPositionX == that.spawnPositionX &&
			this.spawnPositionY == that.spawnPositionY);
		}
		return false;
	}
	
	/**
	 * Returns the collection of bubbles within this Room.
	 * @return returns the collection of bubbles within this Room.
	 */
	public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}

	/**
	 * @return the walls
	 */
	public ArrayList<Wall> getWalls() {
		return walls;
	}

	/**
	 * @return the floor
	 */
	public ArrayList<Floor> getFloors() {
		return floors;
	}

    /**
     * Return the x coordinate of the spawn position.
     * @return - x coordinate of spawn position
     */
    public int getSpawnPositionX() {
        return spawnPositionX;
    }

    /**
     * Return the y coordinate of the spawn position.
     * @return - y coordinate of spawn position
     */
    public int getSpawnPositionY() {
        return spawnPositionY;
    }

    /**
     * Reloads the room, by calling the loadRoom method
     */
    public void reload() {
        loadRoom();
    }

    /**
     * Method to load a room with default hardcoded data
     */
    public void loadRoom() {
        spawnPositionX = 50;
        spawnPositionY = 350;
        walls.clear();
        walls.add(new Wall(0, 0, 1, 800));
        walls.add(new Wall(1123, 0, 1, 800));
        floors.clear();
        floors.add(new Floor(0, 794, 1123, 1));
        bubbles.clear();
        bubbles.add(new Bubble(3, Model.getRoomWidth() - 100, 100));
    }
}



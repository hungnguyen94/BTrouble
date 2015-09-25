package com.sem.btrouble.model;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Room contains all objects within the room (except for the players), and draws
 * them on the screen.
 *
 */
public class Room {
  private int spawnPositionX;
  private int spawnPositionY;

  private ArrayList<Wall> walls;
  private ArrayList<Floor> floors;
  private ArrayList<Bubble> bubbles;

  /**
   * Initializes the room with the objects.
   */
  public Room() {
    walls = new ArrayList<Wall>();
    floors = new ArrayList<Floor>();
    bubbles = new ArrayList<Bubble>();
    spawnPositionX = 0;
    spawnPositionY = 0;
  }
  
  /**
   * Checks whether the provided Object is the same as this Room.
   * 
   * @param other
   *          should be the Object to be checked for equality.
   * @return returns a boolean representing whether the provided Object is the
   *         same as this Room.
   */
  public boolean equals(Object other) {
    if (other instanceof Room) {
      Room that = (Room) other;
      return (this.bubbles.equals(that.bubbles) && this.spawnPositionX == that.spawnPositionX
          && this.spawnPositionY == that.spawnPositionY);
    }
    return false;
  }

  /**
   * Return all collidable objects in a room
   * @return - all collidable objects in a room
   */
  public Collection<Shape> getCollidables() {
    ArrayList<Shape> allCollidables = new ArrayList<Shape>();
    allCollidables.addAll(walls);
    allCollidables.addAll(floors);
    allCollidables.addAll(bubbles);
    return allCollidables;
  }

  /**
   * Returns the collection of bubbles within this Room.
   * 
   * @return returns the collection of bubbles within this Room.
   */
  public Collection<Bubble> getBubbles() {
    return bubbles;
  }

  public ArrayList<Wall> getWalls() {
    return walls;
  }

  public ArrayList<Floor> getFloors() {
    return floors;
  }

  /**
   * Add a bubble object to the room
   * @param b - bubble to be added
   */
  public void addBubble(Bubble b) {
    bubbles.add(b);
  }

  /**
   * Remove a bubble object from the room
   * @param b - bubble to be removed
   */
  public void removeBubble(Bubble b) {
    bubbles.remove(b);
  }

  /**
   * Return true if the room contains no bubbles
   */
  public boolean hasBubbles() {
    return !bubbles.isEmpty();
  }

  /**
   * Move all the bubbles in the room
   */
  public void moveBubbles() {
    for (Bubble b: bubbles) {
      b.move();
    }
  }

  /**
   * Return the x coordinate of the spawn position.
   * 
   * @return - x coordinate of spawn position
   */
  public int getSpawnPositionX() {
    return spawnPositionX;
  }
  
  public void setSpawnPositionX(int x) {
	  this.spawnPositionX = x;
  }
  
  public void setSpawnPositionY(int y) {
	  this.spawnPositionY = y;
  }

  /**
   * Return the y coordinate of the spawn position.
   * 
   * @return - y coordinate of spawn position
   */
  public int getSpawnPositionY() {
    return spawnPositionY;
  }

  /**
   * Reloads the room, by calling the loadRoom method.
   */
  public void reload() {
    loadRoom();
  }

  /**
   * Method to load a room with default hard coded data.
   */
  public void loadRoom() {
    spawnPositionX = Model.getRoomWidth()/2;
    spawnPositionY = 400;
    walls.clear();
    walls.add(new Wall(0, 0, 20, Model.getRoomHeight()));
    walls.add(new Wall(Model.getRoomWidth(), 0, 20, Model.getRoomHeight()));
    floors.clear();
    floors.add(new Floor(0, Model.getRoomHeight() - ((Model.getRoomHeight()/100)*14), Model.getRoomWidth(), 50));
    floors.add(new Floor(0, 0, Model.getRoomWidth(), 50));
    bubbles.clear();
    bubbles.add(new Bubble(4, Model.getRoomWidth()/2, 200));
  }
}



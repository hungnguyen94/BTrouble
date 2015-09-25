package com.sem.btrouble.model;

import java.util.ArrayList;

/**
 * Model contains all data of the game. Model is updated by the Controller and
 * used to draw the View. Model has been split into partial models such as
 * Objects and Players.
 */
public class Model {
  private static ArrayList<Room> rooms;
  private static ArrayList<Player> players;
  private static int room_current;

  private static int ROOM_WIDTH;
  private static int ROOM_HEIGHT;

  /**
   * Initializes the model. Should be done before any calls to its methods are
   * done.
   */
  public static void init(int width, int height) {
    rooms = new ArrayList<Room>();
    players = new ArrayList<Player>();
    room_current = 0;
    ROOM_WIDTH = width;
    ROOM_HEIGHT = height;
  }

  /**
   * Adds a room to the Model's room collection.
   * 
   * @param room
   *          should be the Room which is added to the Model's room collection
   */
  public static void addRoom(Room room) {
    rooms.add(room);
  }

  /**
   * Returns the room which is currently active.
   * 
   * @return returns the room which is currently active.
   */
  public static Room getCurrentRoom() {
    return rooms.get(room_current);
  }

  /**
   * Returns the Model's player collection.
   * 
   * @return returns an ArrayList which is the Model's player
   *         collection.
   */
  public static ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * Returns the Model's bubble collection.
   * 
   * @return returns an ArrayList which is the Model's bubble
   *         collection.
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
   * Restarts the currently active room, resets all the objects in the room but
   * preserves the players and their scores.
   */
  public static void restartRoom() {
    for (Player p : players) {
      p.resetRope();
    }
    getCurrentRoom().reload();
    for (Player p : players) {
      p.moveTo(getCurrentRoom().getSpawnPositionX(), getCurrentRoom().getSpawnPositionY());
    }
  }

  /**
   * Adds a new player to the game.
   * 
   * @param player
   *          should be the Player to be added to the game.
   */
  public static void addPlayer(Player player) {
    players.add(player);
  }

}

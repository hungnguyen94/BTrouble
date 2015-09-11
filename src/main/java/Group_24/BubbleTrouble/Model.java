package Group_24.BubbleTrouble;

import java.util.ArrayList;

/**
 * Model contains all data of the game. Model is updated by the Controller and used to draw the View.
 * Model has been split into partial models such as Objects and Players. 
 */
public class Model {
	private static ArrayList<Room> rooms;
	private static ArrayList<Player> players;
	private static int room_current;
	
	private static final int ROOM_WIDTH = 1123;
	private static final int ROOM_HEIGHT = 921;
	
	/**
	 * Initializes the model. Should be done before any calls to its methods are done.
	 */
	public static void init(){
		rooms = new ArrayList<Room>();
		players = new ArrayList<Player>();
		room_current = 0;
	}
	
	/**
	 * Adds a room to the Model's room collection
	 * @param room should be the Room which is added to the Model's room collection
	 */
	public static void addRoom(Room room){
		rooms.add(room);
	}
	
	/**
	 * Returns the room which is currently active.
	 * @return returns the room which is currently active.
	 */
	public static Room getCurrentRoom(){
		return rooms.get(room_current);
	}
	
	/**
	 * Returns the Model's player collection.
	 * @return returns an ArrayList<Player> which is the Model's player collection.
	 */
	public static ArrayList<Player> getPlayers(){
		return players;
	}
	
	/**
	 * Returns the Model's bubble collection.
	 * @return returns an ArrayList<Bubble> which is the Model's bubble collection.
	 */
	public static ArrayList<Bubble> getBubbles(){
		return getCurrentRoom().getBubbles();
	}
	
	/**
	 * Returns the height of the room.
	 * @return returns an integer representing the height of the room.
	 */
	public static int getRoomHeight() {
		return ROOM_HEIGHT;
	}
	
	/**
	 * Returns the width of the room.
	 * @return returns an integer representing the width of the room.
	 */
	public static int getRoomWidth() {
		return ROOM_WIDTH;
	}
	
	/**
	 * Restarts the currently active room, resets all the objects in the room but preserves the players and their scores.
	 */
	public static void restartRoom() {
		getCurrentRoom().reload();
	}
	
	/**
	 * Adds a new player to the game.
	 * @param player should be the Player to be added to the game.
	 */
	public static void addPlayer(Player player) {
		players.add(player);
	}
	
}

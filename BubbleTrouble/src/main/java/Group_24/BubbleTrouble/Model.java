package Group_24.BubbleTrouble;

import java.util.ArrayList;

public class Model {
	private static ArrayList<Room> rooms;
	private static ArrayList<Player> players;
	private static int room_current;
	
	private static final int ROOM_WIDTH = 800;
	private static final int ROOM_HEIGHT = 500;
	
	public static void init(){
		rooms = new ArrayList<Room>();
		players = new ArrayList<Player>();
		room_current = 0;
	}
	
	public static void addRoom(Room room){
		rooms.add(room);
	}
	
	public static Room getCurrentRoom(){
		return rooms.get(room_current);
	}
	
	public static ArrayList<Player> getPlayers(){
		return players;
	}
	
	public static ArrayList<Bubble> getBubbles(){
		return getCurrentRoom().getBubbles();
	}

	public static int getRoomHeight() {
		return ROOM_HEIGHT;
	}

	public static int getRoomWidth() {
		return ROOM_WIDTH;
	}
	
	public static void restartRoom() {
		getCurrentRoom().reload();
	}

	public static void addPlayer(Player player) {
		players.add(player);
	}
	
}

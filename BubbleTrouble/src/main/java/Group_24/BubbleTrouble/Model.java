package Group_24.BubbleTrouble;

import java.util.ArrayList;

public class Model {
	private static ArrayList<Room> rooms;
	private static int room_current;
	
	private static final int ROOM_WIDTH = 800;
	private static final int ROOM_HEIGHT = 500;
	
	public static void init(){
		rooms = new ArrayList<Room>();
		room_current = 0;
	}
	
	public static void addRoom(Room room){
		rooms.add(room);
	}
	
	public static Room getCurrentRoom(){
		return rooms.get(room_current);
	}
	
	public static ArrayList<Player> getPlayers(){
		return getCurrentRoom().getPlayers();
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
	
}

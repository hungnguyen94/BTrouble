package Group_24.BubbleTrouble;

import java.util.ArrayList;

/**
 * Room contains all objects within the room, and draws them on the screen.
 *
 */
public class Room {

	private ArrayList<Player> players;
	private ArrayList<Bubble> bubbles;
	
	public Room(RoomData data){
		players = data.getPlayers();
		bubbles = data.getBubbles();
	}

	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}

}



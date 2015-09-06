package Group_24.BubbleTrouble;

import java.util.ArrayList;

/**
 * Room contains all objects within the room (except for the players), and draws them on the screen.
 *
 */
public class Room {
	
	private RoomData data;
	private int startingposition;

	private ArrayList<Bubble> bubbles;
	
	/**
	 * Constructs a new Room using the data contained by a RoomData data container.
	 * @param data should be a RoomData object containing the data which should be loaded into the Room.
	 */
	public Room(RoomData data){
		this.data = data;
		this.reload();
	}
	
	/**
	 * Returns the collection of bubbles within this Room.
	 * @return returns the collection of bubbles within this Room.
	 */
	public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}
	
	/**
	 * Reloads the room, loads the initial data into the Room and places the Players in the room without touching the Players themself. 
	 */
	public void reload() {
		for(Player player: Model.getPlayers()){
			player.moveTo(startingposition);
		}
		startingposition = data.getStartingposition();
		bubbles = data.getBubbles();
	}

}



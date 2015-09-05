package Group_24.BubbleTrouble;

import java.util.ArrayList;

/**
 * Room contains all objects within the room, and draws them on the screen.
 *
 */
public class Room {
	
	private RoomData data;
	private boolean active;
	private int startingposition;

	private ArrayList<Bubble> bubbles;
	
	public Room(RoomData data){
		this.data = data;
		this.reload();
	}
	
	public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void activate(){
		this.active = true;
	}
	
	public void deactivate(){
		this.active = false;
	}

	public void reload() {
		for(Player player: Model.getPlayers()){
			player.moveTo(startingposition);
		}
		startingposition = data.getStartingposition();
		bubbles = data.getBubbles();
	}

}



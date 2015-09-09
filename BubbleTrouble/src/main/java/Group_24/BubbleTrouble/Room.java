package Group_24.BubbleTrouble;

import java.util.ArrayList;
import java.lang.Object;

/**
 * Room contains all objects within the room (except for the players), and draws them on the screen.
 *
 */
public class Room {
	
	private RoomData data;
	private int startingposition;

	private ArrayList<Wall> walls;
	private ArrayList<Floor> floors;
	
	private ArrayList<Bubble> bubbles;
	
	/**
	 * Constructs a new Room using the data contained by a RoomData data container.
	 * @param data should be a RoomData object containing the data which should be loaded into the Room.
	 */
	public Room(RoomData data){
		this.data = data;
		
		walls = new ArrayList<Wall>();
		walls.add(new Wall(0, 0, 10, 800));
		walls.add(new Wall(843, 0, 10, 800));
		
		floors = new ArrayList<Floor>();
		floors.add(new Floor(0, 596, 800, 2 ));
		this.reload();
	}
	
	public boolean equals(Object other) {
		boolean res = false;
		if(other instanceof Room) {
			Room that = (Room) other;
			if(this.data.equals(that.data) && this.startingposition == that.startingposition && this.walls.equals(that.walls) &&
					 this.floors.equals(that.floors) && this.bubbles.equals(that.bubbles)) {
				res = true;
			}
		}
		return res;
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

}



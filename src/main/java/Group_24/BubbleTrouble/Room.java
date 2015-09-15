package Group_24.BubbleTrouble;

import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Room contains all objects within the room (except for the players), and draws them on the screen.
 *
 */
public class Room {
	
	private RoomData data;

    private int spawnPositionX;
    private int spawnPositionY;

	private ArrayList<Rectangle> walls;
	private ArrayList<Rectangle> floors;
	private ArrayList<Bubble> bubbles;
	
	/**
	 * Initializes the room with the objects
	 */
	public Room(){
		walls = new ArrayList<Rectangle>();
		floors = new ArrayList<Rectangle>();
        bubbles = new ArrayList<Bubble>();
		loadRoom();
	}
	
	public boolean equals(Object other) {
		if(other instanceof Room) {
			Room that = (Room) other;
			return(this.data.equals(that.data) && this.bubbles.equals(that.bubbles) && this.spawnPositionX == that.spawnPositionX &&
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
	 * Reloads the room, loads the initial data into the Room and places the Players in the room without touching the Players themself. 
	 */
	public void reload() {
        loadRoom();
	}

	/**
	 * @return the walls
	 */
	public ArrayList<Rectangle> getWalls() {
		return walls;
	}

	/**
	 * @return the floor
	 */
	public ArrayList<Rectangle> getFloors() {
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
     * Method to load a room with default data
     */
    public void loadRoom() {
        spawnPositionX = 50;
        spawnPositionY = 350;
        walls.clear();
        walls.add(new Rectangle(0, 0, 1, 800));
        walls.add(new Rectangle(1123, 0, 1, 800));
        floors.clear();
        floors.add(new Rectangle(0, 794, 1123, 1));
        bubbles.clear();
        bubbles.add(new Bubble(3, Model.getRoomWidth() - 100, 100));
    }
}



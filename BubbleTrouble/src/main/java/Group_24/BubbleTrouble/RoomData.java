package Group_24.BubbleTrouble;

import java.util.ArrayList;
import java.lang.Object;

/**
 * RoomData contains all the initial data of a room. Getting the data gives you a copy of the original data.
 *
 */
public class RoomData {
	private ArrayList<Bubble> bubbles;
	private int startingposition;
	
	public RoomData(ArrayList<Bubble> bubbles){
		this.bubbles = bubbles;
		this.startingposition = 50;
	}
	
	public RoomData(String file){
		// TODO load roomdata from file
	}
	
	public boolean equals(Object other) {
		boolean res = false;
		if(other instanceof RoomData) {
			RoomData that = (RoomData) other;
			if(this.bubbles.equals(that.bubbles) && this.startingposition == that.startingposition) {
				res = true;
			}
		}
		return res;
	}
	
	/**
	 * getBubbles creates a list with a copy of every oiginal bubble, so that it doesn't touch the original data. 
	 * @return returns a copy of the list of bubbles.
	 */
	public ArrayList<Bubble> getBubbles() {
		ArrayList<Bubble> res = new ArrayList<Bubble>();
		for(Bubble bubble: bubbles){
			res.add(new Bubble(bubble.getSize(), bubble.getX(), bubble.getY()));
		}
		return res;
	}
	
	/**
	 * Get the horizontal starting position of the player in the room.
	 * @return returns the horizontal starting position of the player.
	 */
	public int getStartingposition() {
		return startingposition;
	}
}

package Group_24.BubbleTrouble;

import java.util.ArrayList;

public class RoomData {
	private ArrayList<Bubble> bubbles;
	private int startingposition;
	
	public RoomData(ArrayList<Bubble> bubbles){
		this.bubbles = bubbles;
		this.startingposition = 10;
	}
	
	public RoomData(String file){
		// TODO load roomdata from file
	}

	public ArrayList<Bubble> getBubbles() {
		ArrayList<Bubble> res = new ArrayList<Bubble>();
		for(Bubble bubble: bubbles){
			res.add(new Bubble(bubble.getSize(), bubble.getX(), bubble.getY()));
		}
		return res;
	}

	public int getStartingposition() {
		return startingposition;
	}
}

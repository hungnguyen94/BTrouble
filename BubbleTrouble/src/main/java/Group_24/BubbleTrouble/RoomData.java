package Group_24.BubbleTrouble;

import java.util.ArrayList;

public class RoomData {
	private ArrayList<Player> players;
	private ArrayList<Bubble> bubbles;
	
	public RoomData(ArrayList<Player> players, ArrayList<Bubble> bubbles){
		this.players = players;
		this.bubbles = bubbles;
	}
	
	public RoomData(String file){
		// TODO load roomdata from file
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}
}

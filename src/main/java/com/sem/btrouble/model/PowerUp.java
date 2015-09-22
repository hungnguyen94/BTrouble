package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.sem.btrouble.event.BubbleEvent;

public class PowerUp implements Observer {
	
	private int type;
	
	public PowerUp(int type) {
		this.type = type;
	}
	
	public void update(Observable observable, Object arg) {
		if(arg instanceof BubbleEvent) {
			BubbleEvent event = (BubbleEvent) arg;
			if(event.getId() == BubbleEvent.COLLISION_ROPE && type == 1) {
				slowBubbles(.3f);
			}
		}
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void givePower() {
		switch(type) {
		case(0) :
			giveExtraLife();
			break;
		case(1) :
			slowBubbles(.3f);
			break;
		case(2) :
			break;
		default :
			break;
		}
	}
	
	public void slowBubbles(float speed) {
		ArrayList<Bubble> bubbles = Model.getBubbles();
		for(int i = 0; i < bubbles.size(); i++) {
			bubbles.get(i).setAY(speed);
		}
	}

	public void giveExtraLife() {
		ArrayList<Player> players = Model.getPlayers();
		players.get(0).addLife();
	}
	
	public void erasePower() {
		switch(type) {
		case(0) :
			break;
		case(1) :
			setType(3);
		case(2) :
			break;
		default :
			break;
		}
	}

}

package com.sem.btrouble.model;

import java.util.Observable;
import java.util.Observer;

import com.sem.btrouble.event.BubbleEvent;

public class Wallet implements Observer {

    private int value;

    public Wallet() {
	value = 0;
    }

    public int getValue() {
	return value;
    }

    public void increaseValue(int extra) {
	value += extra;
    }

    public void decreaseValue(int less) {
	value -= less;
    }

    public void update(Observable observable, Object arg) {
	if (arg instanceof BubbleEvent) {
	    BubbleEvent event = (BubbleEvent) arg;
	    if (event.getId() == BubbleEvent.COLLISION_ROPE) {
		int value = event.getSubject().getSize();
		increaseValue(value * 1000);
	    }
	}
    }

}

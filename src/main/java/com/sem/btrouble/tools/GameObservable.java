package com.sem.btrouble.tools;

import java.util.Observable;

import com.sem.btrouble.event.GameEvent;

public class GameObservable extends Observable {

    protected void fireEvent(GameEvent gameEvent) {
	setChanged();
	notifyObservers(gameEvent);
    }
}

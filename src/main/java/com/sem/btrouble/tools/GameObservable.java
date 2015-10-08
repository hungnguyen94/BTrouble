package com.sem.btrouble.tools;

import java.util.Observable;

import com.sem.btrouble.event.GameEvent;

/**
 * Class which fires an event when a game event happens.
 * @author Martin
 *
 */
public class GameObservable extends Observable {

    /**
     * Fire the event.
     * @param gameEvent the event
     */
    protected void fireEvent(GameEvent gameEvent) {
        setChanged();
        notifyObservers(gameEvent);
    }
}

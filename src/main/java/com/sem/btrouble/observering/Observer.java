package com.sem.btrouble.observering;

import com.sem.btrouble.event.Event;

/**
 * Observes the events thrown in the game.
 */
public interface Observer {
    
    /**
     * Method called when the observed object fires an event.
     * @param event the GameEvent the subject fired.
     */
    void update(Event event);
    
}

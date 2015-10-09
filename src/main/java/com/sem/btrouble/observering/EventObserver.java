package com.sem.btrouble.observering;

import com.sem.btrouble.event.GameEvent;

/**
 * Observes the events thrown in the game.
 */
public interface EventObserver {
    
    /**
     * Method called when the observed object fires an event, writing the event
     * to the log file.
     * @param observable object to observe
     * @param arg the event
     */
    void update(GameEvent event);
    
}

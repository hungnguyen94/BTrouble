package com.sem.btrouble.observering;

import com.sem.btrouble.event.GameEvent;

/**
 * Observes the events thrown in the game.
 */
public interface EventObserver {
    
    /**
     * Method called when the observed object fires an event.
     * @param event the GameEvent the subject fired.
     */
    void update(GameEvent event);
    
}

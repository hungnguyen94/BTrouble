package com.sem.btrouble.observering;

import com.sem.btrouble.event.GameEvent;

/**
 * Subject interface. Observable classes should implement this.
 */
public interface EventSubject {
    
    /**
     * Method to notify the observers about a change.
     * @param gameEvent the event that the observers should be notified with.
     */
    void fireEvent(GameEvent gameEvent);
    
    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    void registerObserver(EventObserver observer);

    /**
     * Method to notify the observers about a change.
     * @param observer the obser to be removed.
     */
    void removeObserver(EventObserver observer);
    
}

package com.sem.btrouble.observering;

import com.sem.btrouble.event.Event;

/**
 * Subject interface. Observable classes should implement this.
 */
public interface Subject {
    
    /**
     * Method to notify the observers about a change.
     * @param gameEvent the event that the observers should be notified with.
     */
    void fireEvent(Event gameEvent);
    
    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    void registerObserver(Observer observer);

    /**
     * Method to notify the observers about a change.
     * @param observer the obser to be removed.
     */
    void removeObserver(Observer observer);
    
}

package com.sem.btrouble.observering;

/**
 * Subject interface. Observable classes should implement this.
 */
public interface LevelSubject {

    /**
     * Method to notify the observers about a change.
     */
    void notifyObserver();
    
    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    void registerObserver(LevelObserver observer);

    /**
     * Method to notify the observers about a change.
     * @param observer the obser to be removed.
     */
    void removeObserver(LevelObserver observer);
    
}

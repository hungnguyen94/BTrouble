package com.sem.btrouble.observering;

/**
 * Subject interface. Observable classes should implement this.
 */
public interface LevelSubject {

    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    void registerObserver(LevelObserver observer);

    /**
     * Remove an observer from the observers list.
     * @param observer Observer to be removed.
     */
    void removeObserver(LevelObserver observer);

    /**
     * Method to notify the observers about a change.
     */
    void notifyObserver();
}

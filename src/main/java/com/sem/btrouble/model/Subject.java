package com.sem.btrouble.model;

/**
 * Subject interface. Observable classes should implement this.
 */
public interface Subject {

    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    public void registerObserver(Observer observer);

    /**
     * Remove an observer from the observers list.
     * @param observer Observer to be removed.
     */
    public void removeObserver(Observer observer);

    /**
     * Method to notify the observers about a change.
     */
    public void notifyObserver();
}

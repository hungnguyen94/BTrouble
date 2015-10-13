package com.sem.btrouble.observering;

public interface Subject<Observer> {
    
    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    void registerObserver(Observer observer);

    /**
     * Remove an observer from the observers list.
     * @param observer Observer to be removed.
     */
    void removeObserver(Observer observer);

    /**
     * Method to notify the observers about a change.
     */
    void notifyObserver();
}

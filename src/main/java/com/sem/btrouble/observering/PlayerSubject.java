package com.sem.btrouble.observering;

public interface PlayerSubject {
    
    /**
     * Register an observer to the subject.
     * @param observer Observer to be added.
     */
    void registerObserver(PlayerObserver observer);

    /**
     * Remove an observer from the observers list.
     * @param observer Observer to be removed.
     */
    void removeObserver(PlayerObserver observer);

    /**
     * Method to notify the observers about a change.
     */
    void notifyObserver();
}

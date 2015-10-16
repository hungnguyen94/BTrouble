package com.sem.btrouble.observering;

/**
 * Observer interface. Observers should implement this.
 */
public interface LevelObserver {

   /**
     * This method is called when the observer is notified about a update.
     * @param subject The observable subject.
     * @param arg Arguments that can be passed.
     */
    void update(LevelSubject subject, Object arg);

    /**
     * This method is called when a level is won.
     */
    void levelWon();

    /**
     * This method is called when a level is lost.
     */
    void levelLost();

}

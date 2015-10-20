package com.sem.btrouble.observering;

/**
 * Observer interface to observe the Level.
 */
public interface LevelObserver {

    /**
     * Method called when the level is won.
     */
    void levelWon();

    /**
     * Method called when the level is lost.
     */
    void levelLost();
}

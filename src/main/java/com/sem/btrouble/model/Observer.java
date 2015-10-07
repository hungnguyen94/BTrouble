package com.sem.btrouble.model;

/**
 * Observer interface. Observers should implement this.
 */
public interface Observer {

//    /**
//     * This method is called when the observer is notified about a update.
//     */
//    void update();

    /**
     * This method is called when a level is won.
     */
    void levelWon();

    /**
     * This method is called when a level is lost.
     */
    void levelLost();

}

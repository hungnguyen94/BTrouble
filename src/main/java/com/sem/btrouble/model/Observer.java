package com.sem.btrouble.model;

import java.util.List;

/**
 * Observer interface. Observers should implement this.
 */
public interface Observer {

    /**
     * This method is called when the observer is notified about a update.
     */
    void update(List<Drawable> drawables);

    /**
     * This method is called when a level is won.
     */
    void levelWon();

    /**
     * This method is called when a level is lost.
     */
    void levelLost();

}

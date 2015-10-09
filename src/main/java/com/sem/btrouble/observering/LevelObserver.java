package com.sem.btrouble.observering;

/**
 * Observer interface. Observers should implement this.
 */
public interface LevelObserver {

    /**
     * This method is called when the observer is notified about a update.
     * @param drawables the objects that should be drawn.
     */
    //void update(List<Drawable> drawables);

    /**
     * This method is called when a level is won.
     */
    void levelWon();

    /**
     * This method is called when a level is lost.
     */
    void levelLost();

}

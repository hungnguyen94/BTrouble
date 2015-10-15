package com.sem.btrouble.observering;

/**
 * Observe the player.
 * @author Martin
 *
 */
public interface PlayerObserver {
    
    /**
     * The player has shot a rope.
     */
    void shotaRope();
    
    /**
     * The player has lost a life.
     */
    void lostaLife();
    
    /**
     * The player has gained a life.
     */
    void gainedaLife();
    
    /**
     * The player has collided with a bubble.
     */
    void collidedWithBubble();
    
    /**
     * The player collided on the left side.
     */
    void collidedLeft();
    
    /**
     * The player collided on the right side.
     */
    void collidedRight();
    
    /**
     * ??.
     */
    void poppedaBubble();
    
}

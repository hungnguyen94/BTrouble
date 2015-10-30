package com.sem.btrouble.model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class which generates a random power up.
 * @author Martin
 *
 */
public final class PowerUpFactory {
    
    public static final float POWERUP_PROBABILITY = .1f;
    
    public static CopyOnWriteArrayList<Bubble> bubbleList;
    
    public static void init(CopyOnWriteArrayList<Bubble> bubbleList2){
        bubbleList = bubbleList2;
    }
    
    /**
     * Generate a random power up.
     * @param x x position
     * @param y y position
     * @param random the random number
     * @return the power up
     */
	public static PowerUp generate(float x, float y, double random) {
        if((random -= POWERUP_PROBABILITY) < 0) {
            return new StayRopePowerUp(x, y);
        }
        if((random -= POWERUP_PROBABILITY) < 0) {
            return new LifePowerUp(x, y);
        }
        if((random -= POWERUP_PROBABILITY) < 0) {
            return new SlowBubblesPowerUp(x, y, bubbleList);
        }
        
        return null;
	}
}

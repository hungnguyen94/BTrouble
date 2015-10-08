package com.sem.btrouble.model;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Superclass for all power ups.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public abstract class PowerUp extends Rectangle {
	
	private boolean falling;
	private float vy;
	private float ay;
	
	/**
	 * Construct power up bought in the store.
	 */
	public PowerUp() {
		super(-50, -50, 50, 100);
	}
	
	/**
	 * Construct power up received in the game.
	 * @param xpos x position
	 * @param ypos y position
	 */
	public PowerUp(float xpos, float ypos) {
		super(xpos, ypos, 50, 100);
		this.falling = true;
		this.vy = 2;
		this.ay = .3f;
	}

	/**
	 * Activate the power up.
	 */
    public abstract void activate();
    
    /**
     * Reset the power up.
     */
    public abstract void reset();
    
    /**
     * Draw the power up.
     * @throws SlickException occurs when image is invalid
     */
    public abstract void draw() throws SlickException;
    
    /**
     * Return if the power up is falling.
     * @return boolean
     */
    public boolean isFalling() {
        return falling;
    }
    
    /**
     * Set the power up to falling.
     * @param falling boolean
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    /**
     * Let the power up fall.
     */
    public void fall() {
    	float y = getY();
        float changeY = y + vy;
        setY(changeY);
        vy += ay;
    }
    
    /**
     * Let the power up move.
     */
    public void move() {
        if (isFalling()) {
            fall();
        }
        else {
            vy = 0;
        }    
    }
    
}

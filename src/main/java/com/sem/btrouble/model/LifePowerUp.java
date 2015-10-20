package com.sem.btrouble.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Representing the Power up for an extra life.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class LifePowerUp extends PowerUp {
    
	private Image playerIdle;
	
	/**
	 * Construct the power up bought in the shop.
	 */
	public LifePowerUp() {
		super();
		activate();
	}
	
	/**
	 * Construct the power up received from a bubble.
	 * @param xpos x position
	 * @param ypos y position
	 */
    public LifePowerUp(float xpos, float ypos) {
        super(xpos, ypos);
    }
    
    /**
     * Test if two object are equal of this instance.
     * @param other the object to compare with
     * @return a boolean
     */
    public boolean equals(Object other) {
        if(other instanceof LifePowerUp) {
            LifePowerUp that = (LifePowerUp) other;
            return this.isFalling() == that.isFalling() 
                    && Math.abs(this.x - that.x) == 0
                    && Math.abs(this.y - that.y) == 0
                    && Math.abs(this.getVelocityY() - that.getVelocityY()) == 0
                    && Math.abs(this.getAccelerationY() - that.getAccelerationY()) == 0;
        } else {
            return false;
        }
    }
    
    /**
     * Activate the power up.
     */
    public void activate() {
        ArrayList<Player> players = Model.getPlayers();
        if(players.get(0).getLives() < 5) {
            players.get(0).addLife();
        }
    }
    
    /**
     * Reset the power up.
     */
    public void reset() {
    }


    /**
     * Draw the power up.
     * @param graphics The graphics
     */
    public void draw(Graphics graphics) {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_life.png");
            }
            playerIdle.draw(getX(), getY(), 50, 100);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }      
    }
}

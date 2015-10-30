package com.sem.btrouble.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Representing the Power up for an extra life.
 * @author Martin
 *
 */
public class LifePowerUp extends PlayerPowerUp implements Movable {
    
    private static final long serialVersionUID = 1L;
    private Image lifePowerUpImage;
	
	/**
	 * Construct the power up bought in the shop.
	 */
	public LifePowerUp() {
		super();
	}
	
	/**
	 * Construct the power up received from a bubble.
	 * @param xpos x position
	 * @param ypos y position
	 */
    public LifePowerUp(float xpos, float ypos) {
        super(xpos, ypos, 1);
    }

    /**
     * Apply the powerUp to the Player.
     *
     * @param player Player to apply the powerUp to.
     */
    @Override
    public void activate(Player player) {
        player.addLife();
        setCollided(true);
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
     * Reset the power up.
     */
    public void reset() {

    }


    /**
     * Draw the power up.
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        try {
            if (lifePowerUpImage == null) {
                lifePowerUpImage = new Image("Sprites/powerup_life.png");
            }
            lifePowerUpImage.draw(getX(), getY(), 50, 100);
        } catch (SlickException e) {
            e.printStackTrace();
        }      
    }
}

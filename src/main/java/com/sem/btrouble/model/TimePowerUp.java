package com.sem.btrouble.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.view.GameView;

/**
 * Represents power up which slows down the time.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class TimePowerUp extends PowerUp {
    
	private Image playerIdle;
	
	/**
	 * Construct power up bought in the store.
	 */
	public TimePowerUp() {
		super();
		activate();
	}
	
	/**
	 * Construct power up received in the game.
	 * @param xpos x position
	 * @param ypos y position
	 */
    public TimePowerUp(float xpos, float ypos) {
        super(xpos, ypos);
    }
    
    /**
     * Test if two object are equal of this instance.
     * @param other the object to compare with
     * @return a boolean
     */
    public boolean equals(Object other) {
        if(other instanceof TimePowerUp) {
            TimePowerUp that = (TimePowerUp) other;
            return this.isFalling() == that.isFalling() 
                    && Math.abs(this.x - that.x) == 0
                    && Math.abs(this.y - that.y) == 0
                    && Math.abs(this.getVY() - that.getVY()) == 0
                    && Math.abs(this.getAY() - that.getAY()) == 0;
        } else {
            return false;
        }
    }
    
    /**
     * Active power up bought in the store.
     */
    public void activate() {
        GameView.getController().getTimers().setLevelTimerCounter(10000);
    }
    
    /**
     * Activate power up received in the game.
     */
    public void activateShort() {
    	GameView.getController().getTimers().restartTimerWithoutCountdown();
    }
    
    /**
     * Reset the power up.
     */
    public void reset() {
        GameView.getController().getTimers().setLevelTimerCounter(100);
    }
    
    /**
     * Draw the power up.
     * @param graphics The graphics
     */
    public void draw(Graphics graphics) {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_time.png");
            }
            playerIdle.draw(getX(), getY(), 50, 100);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

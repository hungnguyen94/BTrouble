package com.sem.btrouble.model;

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
     * @throws SlickException occurs when image is invalid
     */
    public void draw() throws SlickException {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_time.png");
            }
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        playerIdle.draw(getX(), getY(), 50, 100);
    }


}

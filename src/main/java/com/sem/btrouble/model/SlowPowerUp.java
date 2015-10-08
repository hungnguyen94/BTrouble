package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.event.BubbleEvent;

/**
 * Represents power up for slow bubbles.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class SlowPowerUp extends PowerUp implements Observer{
    
    private boolean on;
	private Image playerIdle;
    
	/**
	 * Construct power up from shop.
	 */
    public SlowPowerUp() {
    	super();
    	on = false;
    	activate();
    }
    
    /**
     * Construct power up received in the game.
     * @param xpos x position
     * @param ypos y position
     */
    public SlowPowerUp(float xpos, float ypos) {
        super(xpos, ypos);
        on = false;
    }
    
    /**
     * Update the speed of the bubbles.
     * @param observable object to observe
     * @param arg the event
     */
    public void update(Observable observable, Object arg) {
      if (arg instanceof BubbleEvent) {
          BubbleEvent event = (BubbleEvent) arg;
          if (event.getId() == BubbleEvent.COLLISION_ROPE && on) {
              slowBubbles(.3f);
          }
      }
    }
    
    /**
     * Activate the power up.
     */
    public void activate() {
        on = true;
        slowBubbles(.3f);
    }
    
    /**
     * Reset the power ups.
     */
    public void reset() {
        on = false;
    }
    
    /**
     * Slow down the bubbles.
     * @param speed speed of bubbles
     */
    public void slowBubbles(float speed) {
      ArrayList<Bubble> bubbles = Model.getBubbles();
      for (int i = 0; i < bubbles.size(); i++) {
          bubbles.get(i).setAY(speed);
      }
    }
    
    /**
     * Draw the power up.
     * @throws SlickException occurs when image is invalid
     */
    public void draw() throws SlickException {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_slow.png");
            }
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        playerIdle.draw(getX(), getY(), 50, 100);
    }

}

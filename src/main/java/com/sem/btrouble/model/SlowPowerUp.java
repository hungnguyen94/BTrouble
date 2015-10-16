package com.sem.btrouble.model;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.Event;
import com.sem.btrouble.observering.Observer;

/**
 * Represents power up for slow bubbles.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class SlowPowerUp extends PowerUp implements Observer {
    
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
     * Test if two object are equal of this instance.
     * @param other the object to compare with
     * @return a boolean
     */
    public boolean equals(Object other) {
        if(other instanceof SlowPowerUp) {
            SlowPowerUp that = (SlowPowerUp) other;
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
     * Update the speed of the bubbles.
     * @param observable object to observe
     * @param arg the event
     */
    public void update(Event event) {
      if(event == BubbleEvent.COLLISION_ROPE && on){
          slowBubbles(.3f);
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
     * Return if the power is on or off.
     * @return boolean
     */
    public boolean getOn() {
        return on;
    }
    
    /**
     * Slow down the bubbles.
     * @param speed speed of bubbles
     */
    public void slowBubbles(float speed) {
      ArrayList<Bubble> bubbles = Model.getBubbles();
      for (int i = 0; i < bubbles.size(); i++) {
          bubbles.get(i).setAccelerationY(speed);
      }
    }
    
    /**
     * Draw the power up.
     * @param graphics The graphics
     */
    public void draw(Graphics graphics) {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_slow.png");
            }
            playerIdle.draw(getX(), getY(), 50, 100);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

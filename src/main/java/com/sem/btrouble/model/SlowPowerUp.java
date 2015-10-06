package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.event.BubbleEvent;

public class SlowPowerUp extends PowerUp implements Observer{
    
    private boolean on;
	private Image playerIdle;
    
    public SlowPowerUp() {
    	super();
    	on = false;
    	activate();
    }
    
    public SlowPowerUp(float xpos, float ypos) {
        super(xpos, ypos);
        on = false;
    }
    
    public void update(Observable observable, Object arg) {
      if (arg instanceof BubbleEvent) {
          BubbleEvent event = (BubbleEvent) arg;
          if (event.getId() == BubbleEvent.COLLISION_ROPE && on == true) {
              slowBubbles(.3f);
          }
      }
    }
    
    public void activate() {
        on = true;
        slowBubbles(.3f);
    }
    
    public void reset() {
        on = false;
    }
    
    public void slowBubbles(float speed) {
      ArrayList<Bubble> bubbles = Model.getBubbles();
      for (int i = 0; i < bubbles.size(); i++) {
          bubbles.get(i).setAY(speed);
      }
    }
    
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

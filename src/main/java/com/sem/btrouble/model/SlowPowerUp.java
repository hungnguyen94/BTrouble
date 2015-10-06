package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.sem.btrouble.event.BubbleEvent;

public class SlowPowerUp implements PowerUp, Observer{
    
    boolean on;
    
    public SlowPowerUp() {
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

}

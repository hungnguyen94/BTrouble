package com.sem.btrouble.model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.view.GameView;

public class TimePowerUp extends PowerUp {
    
	private Image playerIdle;
	
	public TimePowerUp() {
		super();
		activate();
	}
	
    public TimePowerUp(float xpos, float ypos) {
        super(xpos, ypos);
    }
    
    public void activate() {
        GameView.getController().getTimers().setLevelTimerCounter(10000);
    }
    
    public void activateShort() {
    	GameView.getController().getTimers().restartTimerWithoutCountdown();
    }
    
    public void reset() {
        GameView.getController().getTimers().setLevelTimerCounter(100);
    }
    
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

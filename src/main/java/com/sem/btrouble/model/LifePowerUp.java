package com.sem.btrouble.model;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LifePowerUp extends PowerUp {
    
	private Image playerIdle;
	
	public LifePowerUp() {
		super();
		activate();
	}
	
    public LifePowerUp(float xpos, float ypos) {
        super(xpos, ypos);
    }
    
    public void activate() {
        ArrayList<Player> players = Model.getPlayers();
        players.get(0).addLife();
    }
    
    public void reset() {
        return;
    }
    
    public void draw() throws SlickException {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_life.png");
            }
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        playerIdle.draw(getX(), getY(), 50, 100);
    }

}

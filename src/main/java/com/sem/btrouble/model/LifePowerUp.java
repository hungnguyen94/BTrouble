package com.sem.btrouble.model;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
     * Activate the power up.
     */
    public void activate() {
        ArrayList<Player> players = Model.getPlayers();
        players.get(0).addLife();
    }
    
    /**
     * Reset the power up.
     */
    public void reset() {
    }


    /**
     * Draw the power up.
     */
    public void draw(Graphics graphics) {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_life.png");
                playerIdle.draw(getX(), getY(), 50, 100);
            }
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }      
    }

}

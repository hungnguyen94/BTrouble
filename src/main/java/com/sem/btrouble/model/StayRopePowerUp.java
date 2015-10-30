package com.sem.btrouble.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Powerup that makes the rope not disappear.
 */
public class StayRopePowerUp extends PlayerPowerUp {

    private static final long serialVersionUID = 1L;
    private Image lifePowerUpImage;

    public StayRopePowerUp(float x, float y, int expirationTime) {
        super(x, y, expirationTime);
    }
    
    public boolean equals(Object other) {
    	if(other instanceof StayRopePowerUp) {
    		StayRopePowerUp that = (StayRopePowerUp) other;
    		return this.getX() == that.getX() && this.getY() == that.getY();
    	} else {
    		return false;
    	}
    }

    /**
     * Apply the powerUp to the Player.
     * Starts the expiration timer to remove the
     * powerup when it runs out.
     * @param player Player to apply the powerUp to.
     */
    @Override
    public void activate(final Player player) {
        player.getWallet().addPowerUp(this);
        startExpirationTimer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getWallet().removePowerUp(StayRopePowerUp.this);
            }
        });
    }

    /**
     * Reset the power up.
     */
    @Override
    public void reset() {

    }

    /**
     * Draw the object.
     *
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        try {
            if (lifePowerUpImage == null) {
                lifePowerUpImage = new Image("Sprites/powerup_rope.png");
            }
            lifePowerUpImage.draw(getX(), getY(), 40, 100);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

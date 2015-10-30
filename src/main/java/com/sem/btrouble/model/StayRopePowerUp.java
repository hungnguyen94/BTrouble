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

    private Image lifePowerUpImage;

    /**
     * Construct the power up bought in the shop.
     */
    public StayRopePowerUp(int expirationTime) {
        super(expirationTime);
    }

    /**
     * Creates a new StayRopePowerUp object.
     * @param x the x location where it will spawn
     * @param y the y location where it will spawn
     * @param expirationTime expiration time for the powerup
     */
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
                lifePowerUpImage = new Image("Sprites/powerup_life.png");
            }
            lifePowerUpImage.draw(getX(), getY(), 50, 100);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

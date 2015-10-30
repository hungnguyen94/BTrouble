package com.sem.btrouble.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Powerup that makes the rope not disappear.
 */
public class StayRopePowerUp extends PlayerPowerUp {

    private static final long serialVersionUID = 1L;
    private static final int expirationTime = 5000;
    private Image lifePowerUpImage;

    /**
     * Construct the power up bought in the shop.
     * @param expirationTime expiration time for the powerup
     */
    public StayRopePowerUp(int expirationTime) {
        super(expirationTime);
    }

    /**
     * Creates a new StayRopePowerUp object.
     * @param x the x location where it will spawn
     * @param y the y location where it will spawn
     */
    public StayRopePowerUp(float x, float y) {
        super(x, y);
    }
    
    /**
     * Compares to stayRopePowerUp objects.
     * @param other the object to compare with
     * @return a boolean
     */
    public boolean equals(Object other) {
    	if(other instanceof StayRopePowerUp) {
    		StayRopePowerUp that = (StayRopePowerUp) other;
    		return this.getX() == that.getX() && this.getY() == that.getY();
    	} else {
    		return false;
    	}
    }
    
    /**
     * HashCode because of implemented equals method.
     * 
     * @return hashCode
     */
    public int hashCode() {
        return 42; // any arbitrary constant will do
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

    @Override
    public void activate(final Player collider) {
        collider.getWallet().addPowerUp(this);
        startExpirationTimer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collider.getWallet().removePowerUp(StayRopePowerUp.this);
            }
        });
    }

    /**
     * Starts the expiration timer. If the actionListener is run, the powerup is
     * expired.
     */
    protected void startExpirationTimer(ActionListener actionListener) {
        Timer timer = new Timer(0, actionListener);
        timer.setInitialDelay(expirationTime);
        timer.setRepeats(false);
        timer.start();
    }
    
}

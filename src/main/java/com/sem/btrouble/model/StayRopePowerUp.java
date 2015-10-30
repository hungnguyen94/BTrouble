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
    private static final int expirationTime = 10000;
    private Image lifePowerUpImage;

    public StayRopePowerUp(float x, float y) {
        super(x, y);
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

package com.sem.btrouble.model;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.Event;
import com.sem.btrouble.observering.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a wallet.
 * 
 * @author Martin
 *
 */
public class Wallet implements Observer {

    private int value;
    private List<PowerUp> powerUpList;

    /**
     * Construct a wallet with value zero.
     */
    public Wallet() {
        this.value = 0;
        this.powerUpList = new ArrayList<>();
    }

    /**
     * Get the value of the wallet.
     * 
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks to see whether this Wallet equals another Object.
     * @param other the other object to check against.
     * @return boolean that represents the equality
     */
    public boolean equals(Object other) {
        if(other instanceof Wallet) {
            Wallet that = (Wallet) other;
            return this.value == that.value;
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
     * Get power ups of the wallet.
     * @return the power ups.
     */
    public List<PowerUp> getPowerUps() {
    	return this.powerUpList;
    }

    /**
     * Adds a powerUp to the wallet.
     * @param bubblePowerUp This powerup will be added.
     */
    public void addPowerUp(PowerUp bubblePowerUp) {
        removePowerUp(containsPowerUp(bubblePowerUp.getClass()));
        powerUpList.add(bubblePowerUp);
    }

    /**
     * removes a powerUp from the wallet.
     * @param powerUp This powerup will be removed.
     */
    public void removePowerUp(PowerUp powerUp) {
        powerUpList.remove(powerUp);
    }

    /**
     * Check if the list contains a powerup.
     * @param powerUp Powerup
     * @return True if the wallet has the powerup.
     */
    public PowerUp containsPowerUp(Class<? extends PowerUp> powerUp) {
        for(PowerUp playerPowerUp : powerUpList) {
            if(playerPowerUp.getClass() == powerUp) {
                return playerPowerUp;
            }
        }
        return null;
    }

    /**
     * Set the value of the wallet.
     * @param value The value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Increase the value.
     * 
     * @param extra
     *            the extra amount
     */
    public void increaseValue(int extra) {
        value += extra;
    }

    /**
     * Decrease the value.
     * 
     * @param less
     *            the less amount
     */
    public void decreaseValue(int less) {
        value -= less;
    }

    /**
     * Update the wallet.
     * @param event The event which happened
     */
    @Override
    public void update(Event event) {
        if (event == BubbleEvent.COLLISION_ROPE) {
            // int value = bubbleEvent.getSubject().getSize();
            increaseValue(1000);
        }
    }

}

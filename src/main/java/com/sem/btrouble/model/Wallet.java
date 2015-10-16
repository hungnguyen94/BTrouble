package com.sem.btrouble.model;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.observering.EventObserver;

/**
 * Represents a wallet.
 * @author Martin
 *
 */
public class Wallet implements EventObserver {

    private int value;

    /**
     * Construct a wallet with value zero.
     */
    public Wallet() {
        value = 0;
    }

    /**
     * Get the value of the wallet.
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
    public boolean equals(Object other) {
        if(other instanceof Wallet) {
            Wallet that = (Wallet) other;
            return this.value == that.value;
        } else {
            return false;
        }
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
     * @param extra the extra amount
     */
    public void increaseValue(int extra) {
        value += extra;
    }

    /**
     * Decrease the value.
     * @param less the less amount
     */
    public void decreaseValue(int less) {
        value -= less;
    }

    /**
     * Update the wallet.
     * @param event The event which happened
     */
    @Override
    public void update(GameEvent event) {
        if (event instanceof BubbleEvent) {
            BubbleEvent bubbleEvent = (BubbleEvent) event;
            if (event.getId() == BubbleEvent.COLLISION_ROPE) {
                int value = bubbleEvent.getSubject().getSize();
                increaseValue(value * 1000);
            }
        }
    }

}

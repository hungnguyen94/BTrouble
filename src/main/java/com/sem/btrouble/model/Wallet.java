package com.sem.btrouble.model;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.Event;
import com.sem.btrouble.observering.Observer;

/**
 * Represents a wallet.
 * 
 * @author Martin
 *
 */
public class Wallet implements Observer {

    private int value;

    /**
     * Construct a wallet with value zero.
     */
    public Wallet() {
        value = 0;
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
     * 
     * @param observable
     *            object to observe
     * @param arg
     *            the event
     */
    @Override
    public void update(Event event) {
        if (event == BubbleEvent.COLLISION_ROPE) {
            // int value = bubbleEvent.getSubject().getSize();
            increaseValue(1000);
        }
    }

}

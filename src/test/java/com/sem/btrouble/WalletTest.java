package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sem.btrouble.model.Wallet;

/**
 * Class which tests the Wallet class.
 * 
 * @author Martin
 *
 */
public class WalletTest {

    private Wallet wallet = new Wallet();

    /**
     * Test the increaseValue method.
     */
    @Test
    public void increaseValueTest() {
	wallet.increaseValue(10);
	assertEquals(10, wallet.getValue());
    }

    /**
     * Test the decreaseValue method.
     */
    @Test
    public void decreaseValueTest() {
	wallet.decreaseValue(10);
	assertEquals(-10, wallet.getValue());
    }

}

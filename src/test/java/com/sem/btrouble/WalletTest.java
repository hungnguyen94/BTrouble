package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.when;

import com.sem.btrouble.model.LifePowerUp;
import com.sem.btrouble.model.PlayerPowerUp;
import com.sem.btrouble.model.Wallet;

/**
 * Class which tests the Wallet class.
 * 
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WalletTest {

	@Mock private PlayerPowerUp power;
    private Wallet wallet = new Wallet();
    private Wallet compare = new Wallet();

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
    
    @Test
    public void setValueTest() {
    	wallet.setValue(10);
    	assertEquals(10, wallet.getValue());
    }
    
    @Test
    public void equalsTrueTest() {
    	assertTrue(wallet.equals(compare));
    }
    
    @Test
    public void equalsFalseValueTest() {
    	compare.setValue(10);
    	assertFalse(wallet.equals(compare));
    }
    
    @Test
    public void equalsOtherTest() {
    	assertFalse(wallet.equals(new String("Test")));
    }
    
    @Test
    public void addPowerUpTest() {
    	wallet.addPowerUp(power);
    	assertTrue(wallet.getPowerUps().contains(power));
    }
    
    @Test
    public void removePowerUpTest() {
    	wallet.addPowerUp(power);
    	wallet.removePowerUp(power);
    	assertTrue(wallet.getPowerUps().isEmpty());
    }
    
    @Test
    public void containsPowerUpTrueTest() {
    	wallet.addPowerUp(power);
    	assertEquals(wallet.containsPowerUp(power.getClass()), power);
    }
    
    @Test
    public void containsPowerUpFalseTest() {
    	wallet.addPowerUp(power);
    	assertEquals(wallet.containsPowerUp(LifePowerUp.class), null);
    }

}

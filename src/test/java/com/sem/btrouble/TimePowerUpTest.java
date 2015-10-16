package com.sem.btrouble;

//import static org.junit.Assert.assertEquals;

/**
 * Test the Time power up.
 * @author Martin
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.model.TimePowerUp;
//import com.sem.btrouble.view.GameView;

public class TimePowerUpTest extends PowerUpTest {

    private TimePowerUp power;
    
    /**
     * Set up the object.
     */
    @Before
    public void setUp() {
        power = new TimePowerUp(1, 1);
        setPower(power);
    }
    
    /**
     * Test the activate method.
     */
    @Test
    public void activateTest() {
        //power.activate();
        //assertEquals(10000, GameView.getController().getTimers().getLevelTimerCounter());
    }
    
    /**
     * Test the reset method.
     */
    @Test
    public void resetTest() {
        
    }
    
    @Test
    public void equalsTrueTest() {
        assertTrue(power.equals(power));
    }
    
    @Test
    public void equalsOtherTest() {
        assertFalse(power.equals(new String("power")));
    }
    
    @Test
    public void equalsFalseFallingTest() {
        TimePowerUp power2 = new TimePowerUp(1, 1);
        power2.setFalling(false);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseXTest() {
        TimePowerUp power2 = new TimePowerUp(2, 1);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseYTest() {
        TimePowerUp power2 = new TimePowerUp(1, 2);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseVYTest() {
        TimePowerUp power2 = new TimePowerUp(1, 1);
        power2.setVY(10);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseAYTest() {
        TimePowerUp power2 = new TimePowerUp(1, 1);
        power2.setAY(10);
        assertFalse(power.equals(power2));
    }

}

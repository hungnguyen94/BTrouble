package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.model.PowerUp;

/**
 * Class which test the PowerUp class.
 * 
 * @author Martin
 *
 */
public abstract class PowerUpTest {
    
    private PowerUp power;
    
    /**
     * Set up the power up.
     */
    @Before
    public abstract void setUp();
    
    /**
     * Set the power up object.
     * @param power the power up object
     */
    public void setPower(PowerUp power) {
        this.power = power;
    }

    /**
     * Test the activate method.
     */
    @Test
    public abstract void activateTest();
    
    /**
     * Test the reset method.
     */
    @Test
    public abstract void resetTest();
    
    /**
     * Test the setFalling and isFalling method.
     */
    @Test
    public void setFallingTest() {
        power.setFalling(false);
        assertFalse(power.isFalling());
    }
    
    /**
     * Test the fall method.
     */
    @Test
    public void fallTest() {
        float firstY = power.getY();
        float vy = power.getVY();
        float ay = power.getAY();
        power.fall();
        assertEquals(firstY + vy, power.getY(), 0);
        assertEquals(vy + ay, power.getVY(), 0);
    }
    
    /**
     * Test the move method with true falling.
     */
    @Test
    public void moveTrueTest() {
        float y = power.getY();
        float vy = power.getVY();
        power.move();
        assertEquals(y + vy, power.getY(), 0);
    }
    
    /**
     * Test the move method with false falling.
     */
    @Test
    public void moveFalseTest() {
        power.setFalling(false);
        power.move();
        assertEquals(0, power.getVY(), 0);
    }
    
    

}

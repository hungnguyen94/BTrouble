package com.sem.btrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.sem.btrouble.model.PlayerPowerUp;

import org.junit.Before;
import org.junit.Test;

/**
 * Class which test the PlayerPowerUp class.
 * 
 * @author Martin
 *
 */
public abstract class PowerUpTest {
    
    private PlayerPowerUp power;
    
    /**
     * Set up the power up.
     */
    @Before
    public abstract void setUp();
    
    /**
     * Set the power up object.
     * @param power the power up object
     */
    public void setPower(PlayerPowerUp power) {
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
    
    @Test
    public void setVelocityYTest() {
    	power.setVelocityY(10);
    	assertEquals(10, power.getVelocityY(), 0);
    }
    
    @Test
    public void setAccelerationYTest() {
    	power.setAccelerationY(10);
    	assertEquals(10, power.getAccelerationY(), 0);
    }
    
    @Test
    public void setFallingTest() {
    	power.setFalling(false);
    	assertFalse(power.isFalling());
    }
    
    @Test
    public void setCollidedTest() {
    	power.setCollided(true);
    	assertTrue(power.getCollidedStatus());
    }
    
    @Test
    public void fallTest(){
    	float y = power.getY();
    	float velocityY = power.getVelocityY();
    	power.fall();
    	assertEquals(y + velocityY, power.getY(), 0);
    	assertEquals(velocityY + power.getAccelerationY(), power.getVelocityY(), 0);
    }
    
    @Test
    public void moveFalseTest() {
    	power.setFalling(false);
    	power.move();
    	assertEquals(0, power.getVelocityY(), 0);
    }
    
    @Test
    public void moveTrueTest() {
    	float y = power.getY();
    	float velocityY = power.getVelocityY();
    	power.setFalling(true);
    	power.move();
    	assertEquals(y + velocityY, power.getY(), 0);
    	assertEquals(velocityY + power.getAccelerationY(), power.getVelocityY(), 0);
    }

}

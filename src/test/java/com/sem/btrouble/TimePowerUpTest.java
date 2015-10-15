package com.sem.btrouble;

import static org.junit.Assert.*;

//import static org.junit.Assert.assertEquals;

/**
 * Test the Time power up.
 * @author Martin
 */
import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.TimePowerUp;
//import com.sem.btrouble.view.GameView;
import com.sem.btrouble.view.GameView;

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
        System.out.println(PlayerEvent.valueOf("COLLISION_BUBBLE"));
        System.out.println(BubbleEvent.COLLISION_CEILING);
    }
    
    /**
     * Test the reset method.
     */
    @Test
    public void resetTest() {
        
    }

}

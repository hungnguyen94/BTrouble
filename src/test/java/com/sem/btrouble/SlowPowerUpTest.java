package com.sem.btrouble;

import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

//import java.util.ArrayList;

//import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

//import com.sem.btrouble.model.Bubble;
//import com.sem.btrouble.model.Model;
//import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.SlowPowerUp;

/**
 * Test the Slow Power up.
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SlowPowerUpTest extends PowerUpTest {

    //@Mock private Bubble bubble;
    //@Mock private Room room;
    private SlowPowerUp power;
    
    /**
     * Set up the power up.
     */
    @Before
    public void setUp() {
//        Model.init(1, 1);
//        Model.addRoom(room);
        power = new SlowPowerUp(1, 1);
        setPower(power);
//        ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
//        bubbles.add(bubble);
//        when(room.getBubbles()).thenReturn(bubbles);
    }
    
    /**
     * Test the activate method.
     */
    @Test
    public void activateTest() {
        //power.activate();
        //assertTrue(power.getOn());
        //verify(bubble).setAY(.3f);
    }
    
    /**
     * Test the reset method.
     */
    @Test
    public void resetTest() {
        power.reset();
        assertFalse(power.getOn());
    }

}

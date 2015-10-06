package com.sem.btrouble;

import static org.junit.Assert.assertEquals;

import com.sem.btrouble.model.Timers;
import org.junit.Test;

/**
 * Class which test the Timers class.
 * 
 * @author Martin
 *
 */
public class TimersTest {

    private Timers timer = new Timers(1);

    /**
     * Test the getLevelTimeLeft method.
     */
    @Test
    public void getLevelTimeLeftTest() {
	assertEquals(timer.getLevelTimeLeft(), 500 * 100);
    }

    /**
     * Test the getLevelMaxDuration method.
     */
    @Test
    public void getLevelMaxDuration() {
	assertEquals(timer.getLevelMaxDuration(), 500 * 100);
    }

    /**
     * Test the getCountdownTimeLeft method.
     */
    @Test
    public void getCountdownTimeLeft() {
	assertEquals(timer.getCountdownTimeLeft(), 100 * 30);
    }

    /**
     * Test the getCountdownMaxDuration method.
     */
    @Test
    public void getCountdownMaxDuration() {
	assertEquals(timer.getCountdownMaxDuration(), 100 * 30);
    }

}

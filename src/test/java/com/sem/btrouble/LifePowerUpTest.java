package com.sem.btrouble;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sem.btrouble.model.LifePowerUp;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test the Life Powerup.
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LifePowerUpTest extends PowerUpTest {

    @Mock private Player player;
    private LifePowerUp power;
    
    /**
     * Set up the power up.
     */
    @Before
    public void setUp() {
        Model.init(1, 1);
        Model.addPlayer(player);
        power = new LifePowerUp(1, 1);
        setPower(power);
    }
    
    /**
     * Test the activate method.
     */
    @Test
    public void activateTest() {
        power.activate();
        verify(player).addLife();
    }
    
    /**
     * Test the reset method.
     */
    @Test
    public void resetTest() {
        power.reset();
        verifyNoMoreInteractions(player);
    }
    
    /**
     * Test the other constructor.
     * Used suppressWarning because life has to made to pass the test.
     */
    @Test
    public void constructTest() {
        @SuppressWarnings("unused")
        LifePowerUp life = new LifePowerUp();
        verify(player).addLife();
    }

}

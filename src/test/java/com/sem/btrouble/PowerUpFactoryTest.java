package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sem.btrouble.model.LifePowerUp;
import com.sem.btrouble.model.PowerUpFactory;
import com.sem.btrouble.model.StayRopePowerUp;

public class PowerUpFactoryTest {

	@Test
	public void stayPowerTest() {
		assertEquals(PowerUpFactory.generate(1, 1, 0.05), new StayRopePowerUp(1, 1, 5000));
	}
	
	@Test
	public void lifePowerTest() {
		assertEquals(PowerUpFactory.generate(1, 1, 0.15), new LifePowerUp(1, 1));
	}
	
	@Test
	public void nullPowerTest() {
		assertEquals(PowerUpFactory.generate(1, 1, 0.25), null);
	}

}

package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.model.StayRopePowerUp;

public class StayRopePowerUpTest extends PowerUpTest {

	private StayRopePowerUp power;
	
	@Before
	public void setUp() {
		power = new StayRopePowerUp(1, 1);
		setPower(power);
	}
	
	@Test
	public void equalsTrueTest() {
		StayRopePowerUp compare = new StayRopePowerUp(1, 1);
		assertTrue(compare.equals(power));
	}
	
	@Test
	public void equalsFalseXTest() {
		StayRopePowerUp compare = new StayRopePowerUp(2, 1);
		assertFalse(compare.equals(power));
	}
	
	@Test
	public void equalsFalseYTest() {
		StayRopePowerUp compare = new StayRopePowerUp(1, 2);
		assertFalse(compare.equals(power));
	}
	
	@Test
	public void equalsOtherTest() {
		assertFalse(power.equals(new String("Test")));
	}

	@Override
	public void activateTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetTest() {
		// TODO Auto-generated method stub
		
	}

}

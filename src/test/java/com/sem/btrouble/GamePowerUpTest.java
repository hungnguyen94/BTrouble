package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sem.btrouble.model.GamePowerUp;

public abstract class GamePowerUpTest {

	private GamePowerUp power;
	
	@Before
	public abstract void setUp();
	
	public void setObject(GamePowerUp power) {
		this.power = power;
	}
	
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
	public abstract void activateTest();
	
	@Test
	public abstract void resetTest();
	
	@Test
	public void setFallingTest() {
		power.setFalling(false);
		assertFalse(power.isFalling());
	}
	
	@Test
	public void moveFalseTest() {
		power.setFalling(false);
		power.move();
		assertEquals(0, power.getVelocityY(), 0);
	}
	
	@Test
	public void moveTrueTest() {
		float velocityY = power.getVelocityY();
		power.setFalling(true);
		power.move();
		assertEquals(velocityY + power.getAccelerationY(), power.getVelocityY(), 0);
	}
	
	@Test
	public void fallTest() {
		float y = power.getY();
		float velocityY = power.getVelocityY();
		power.fall();
		assertEquals(y + velocityY, power.getY(), 0);
		assertEquals(velocityY + power.getAccelerationY(), power.getVelocityY(), 0);
	}
	
	@Test
	public void setCollidedTest() {
		power.setCollided(true);
		assertTrue(power.getCollidedStatus());
	}

}

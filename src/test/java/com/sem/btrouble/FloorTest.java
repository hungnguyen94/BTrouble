package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sem.btrouble.model.Floor;

public class FloorTest {
	
	private Floor floor = new Floor(1, 1, 1, 1);

	@Test
	public void moveRightTest() {
		floor.moveRight();
		assertEquals(1.1, floor.getX(), 0.01);
	}
	
	@Test
	public void moveLeftTest() {
		floor.moveLeft();
		assertEquals(0.9, floor.getX(), 0.01);
	}
	
	@Test
	public void moveUpTest() {
		floor.moveUp();
		assertEquals(0.9, floor.getY(), 0.01);
	}
	
	@Test
	public void moveDownTest() {
		floor.moveDown();
		assertEquals(1.1, floor.getY(), 0.01);
	}

}

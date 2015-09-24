package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sem.btrouble.model.Floor;

public class FloorTest {
	
	private Floor floor = new Floor(1, 1, 1, 1);

	@Test
	public void moveRightTest() {
		floor.moveRight();
		assertEquals(2, floor.getX(), 0);
	}
	
	@Test
	public void moveLeftTest() {
		floor.moveLeft();
		assertEquals(0, floor.getX(), 0);
	}
	
	@Test
	public void moveUpTest() {
		floor.moveUp();
		assertEquals(2, floor.getY(), 0);
	}
	
	@Test
	public void moveDownTest() {
		floor.moveDown();
		assertEquals(0, floor.getY(), 0);
	}

}

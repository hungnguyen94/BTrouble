package com.sem.btrouble;

import static org.junit.Assert.*;

import com.sem.btrouble.model.Rope;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class RopeTest {

	private Rope rope;
	
	@Before
	public void setUp() throws SlickException {
		rope = new Rope(1, 1);
	}
	
	@Test
	public void getDYTest() {
		assertEquals(5, rope.getDY());
	}
	
	@Test
	public void equalsTrueTest() {
		assertTrue(rope.equals(rope));
	}
	
	@Test
	public void equalsFalseXTest() {
		assertFalse(rope.equals(new Rope(2, 1)));
	}
	
	@Test
	public void equalsFalseYTest() {
		assertFalse(rope.equals(new Rope(1, 2)));
	}
	
	@Test
	public void equalsFalseDYTest() {
		Rope rope2 = new Rope(1, 1);
		rope2.move();
		assertFalse(rope.equals(rope2));
	}
	
	@Test
	public void equalsOtherTest() {
		String string = new String("rope");
		assertFalse(rope.equals(string));
	}
	
	@Test
	public void moveYTest() {
		float y = rope.getY();
		rope.move();
		assertEquals((y-10), rope.getY(), 0);
	}
	
	@Test
	public void moveDYTest() {
		rope.move();
		assertEquals(0, rope.getDY());
	}
	
	@Test
	public void moveNoDYTest() {
		Rope rope2 = new Rope(20, 20);
		rope2.move();
		assertEquals(5, rope2.getDY(), 0);
	}

}

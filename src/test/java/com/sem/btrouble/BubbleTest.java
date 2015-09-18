package com.sem.btrouble;

import static org.junit.Assert.*;

import com.sem.btrouble.model.Bubble;
import org.junit.Test;

import com.sem.btrouble.event.BubbleEvent;

public class BubbleTest {
	
	private Bubble bubble1 = new Bubble(1, 1, 1);
	private Bubble bubble2 = new Bubble(2, 2, 2, 2, 2);

	@Test
	public void getSize1Test() {
		assertEquals(1, bubble1.getSize());
	}
	
	@Test
	public void getSize2Test() {
		assertEquals(2, bubble2.getSize());
	}
	
	@Test
	public void getVx1Test() {
		assertEquals(3f, bubble1.getVx(), 0);
	}
	
	@Test
	public void getVx2Test() {
		assertEquals(2, bubble2.getVx(), 0);
	}
	
	@Test
	public void getVy1Test() {
		assertEquals(0, bubble1.getVy(), 0);
	}
	
	@Test
	public void getVy2Test() {
		assertEquals(2, bubble2.getVy(), 0);
	}
	
	@Test
	public void collideFloorTest() {
		double vy = bubble1.getVy();
		bubble1.bubbleEvent(new BubbleEvent(bubble1, BubbleEvent.COLLISION_FLOOR, "Collided with floor"));
		assertEquals(-vy, bubble1.getVy(), 0);
	}
	
	@Test
	public void collideWallTest() {
		double vx = bubble1.getVx();
		bubble1.bubbleEvent(new BubbleEvent(bubble1, BubbleEvent.COLLISION_WALL, "Collided with wall"));
		assertEquals(-vx, bubble1.getVx(), 0);
	}
	
	@Test
	public void equalsTrueTest() {
		assertTrue(bubble1.equals(bubble1));
	}
	
	@Test
	public void equalsFalseSizeTest() {
		assertFalse(bubble1.equals(new Bubble(2, 1, 1)));
	}
	
	@Test
	public void equalsFalseXTest() {
		assertFalse(bubble1.equals(new Bubble(1, 2, 1)));
	}

	@Test
	public void equalsFalseYTest() {
		assertFalse(bubble1.equals(new Bubble(1, 1, 2)));
	}
	
	@Test
	public void equalsFalseVXTest() {
		assertFalse(bubble2.equals(new Bubble(2, 2, 2, 1, 2)));
	}
	
	@Test
	public void equalsFalseVYTest() {
		assertFalse(bubble2.equals(new Bubble(2, 2, 2, 2, 1)));
	}
	
	@Test
	public void equalsOtherTest() {
		String string = new String("bubble");
		assertFalse(bubble1.equals(string));
	}
	
}

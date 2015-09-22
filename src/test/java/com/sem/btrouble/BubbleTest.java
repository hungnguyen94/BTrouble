package com.sem.btrouble;

import static org.junit.Assert.*;

import com.sem.btrouble.model.Bubble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.sem.btrouble.event.BubbleEvent;

@RunWith(MockitoJUnitRunner.class)
public class BubbleTest {
	
	@Mock private BubbleEvent event;
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
	public void collideDefault() {
		Bubble bubble = bubble1;
		when(event.getId()).thenReturn(5);
		bubble1.bubbleEvent(event);
		assertEquals(bubble, bubble1);
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
	
	@Test
	public void setAYTest() {
		bubble1.setAY(1);
		assertEquals(1, bubble1.getAY(), 0);
	}
	
	@Test
	public void bounceYTest() {
		double vy = bubble1.getVy();
		bubble1.bounceY();
		assertEquals(-vy, bubble1.getVy(), 0);
	}
	
	@Test
	public void bounceXTest() {
		double vx = bubble1.getVx();
		bubble1.bounceX();
		assertEquals(-vx, bubble1.getVx(), 0);
	}
	
	@Test
	public void bounceXTrueTest() {
		double vx = bubble1.getVx();
		bubble1.bounceX(true);
		assertEquals(-vx, bubble1.getVx(), 0);
	}
	
	@Test
	public void bounceXFalseTest() {
		double vx = bubble1.getVx();
		bubble1.bounceX(false);
		assertEquals(vx, bubble1.getVx(), 0);
	}
	
	@Test
	public void bounceYTrueTest() {
		double vy = bubble1.getVy();
		bubble1.bounceY(true);
		assertEquals(-vy, bubble1.getVy(), 0);
	}
	
	@Test
	public void bounceYFalseTest() {
		double vy = bubble1.getVy();
		bubble1.bounceY(false);
		assertEquals(vy, bubble1.getVy(), 0);
	}
	
	@Test
	public void bounceYFloorTest() {
		bubble1.bounceYFloor();
		assertEquals(-Math.abs(11+2*(bubble1.getSize())), bubble1.getVy(), 0);
	}
	
	@Test
	public void toStringTest() {
		assertEquals("Bubble{size=1, x=-9.0, y=-9.0, vx=3.0, vy=0.0, ay=0.4}", bubble1.toString());
	}
	
	@Test
	public void moveXTest() {
		double x = bubble1.getCenterX() + bubble1.getVx();
		bubble1.move();
		assertEquals(x-10, bubble1.getX(), 0);
	}
	
	@Test
	public void moveYTest() {
		double y = bubble1.getCenterY() + bubble1.getVy() + .4f;
		bubble1.move();
		assertEquals(y-10, bubble1.getY(), 0.000001);
	}
	
}

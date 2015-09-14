package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.junit.Test;

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
	public void getVX1Test() {
		assertEquals(3f, bubble1.getVX(), 0);
	}
	
	@Test
	public void getVX2Test() {
		assertEquals(2, bubble2.getVX(), 0);
	}
	
	@Test
	public void getVY1Test() {
		assertEquals(0, bubble1.getVY(), 0);
	}
	
	@Test
	public void getVY2Test() {
		assertEquals(2, bubble2.getVY(), 0);
	}
	
	@Test
	public void collideFloorTest() {
		double vy = bubble1.getVY();
		bubble1.bubbleEvent(new BubbleEvent(BubbleEvent.COLLISION_FLOOR));
		assertEquals(-vy, bubble1.getVY(), 0);
	}
	
	@Test
	public void collideWallTest() {
		double vx = bubble1.getVX();
		bubble1.bubbleEvent(new BubbleEvent(BubbleEvent.COLLISION_WALL));
		assertEquals(-vx, bubble1.getVX(), 0);
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

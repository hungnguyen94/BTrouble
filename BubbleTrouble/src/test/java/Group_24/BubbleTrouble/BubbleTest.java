package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.lang.Object;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BubbleTest extends ObjectTest{
	
	@Mock private Controller controller;
	private Bubble bubble = new Bubble(3, 1, 1);
	private int x = 1;
	private int y = 1;
	private double vy = 0;
	private double vx;
	private int size = 3;

	
	@Before
	public void setUp() {
		setObject(bubble);
		setX(x);
		setY(y);
		vx = bubble.getVX();
	}
	
	
	@Test
	@Override
	public void getHeightTest() {
		assertEquals(bubble.getHeight(), size * 10);
	}
	
	@Test
	@Override
	public void getWidthTest() {
		assertEquals(bubble.getWidth(), size * 10);
	}
	
	@Test
	public void getSizeTest() {
		assertEquals(bubble.getSize(), size);
	}

	@Test
	public void collideFloorTest() {
		bubble.collide(0);
		assertEquals(bubble.getVY(), -vy, 0);
	}
	
	@Test
	public void collideWallTest() {
		bubble.collide(1);
		assertEquals(bubble.getVX(), -vx, 0);
	}
	
	/*@Test
	public void collideDefaultTest() {
		bubble.collide(3);
		assertEquals(bubble, new Bubble(size, x, y));
	}*/
	
	/*@Test
	public void collideRopeTest() {
		bubble.collide(2);
		assertEquals(size--, bubble.getSize());
	}*/
	
	@Test
	public void equalsTrueTest() {
		Bubble bubble = new Bubble(1, 1, 1);
		assertTrue(this.bubble.equals(bubble));
	}
	
	
	@Test
	public void equalsFalseTest() {
		Bubble bubble = new Bubble(1, 2, 1);
		assertFalse(this.bubble.equals(bubble));
	}
	
	@Test
	public void equalsOtherTest() {
		int bubble = 1;
		assertFalse(this.bubble.equals(bubble));
	}
	

}

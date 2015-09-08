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
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJunitRunner;

//@RunWith(Parameterized.class)
//@RunWith(MockitoJUnitRunner.class)
public class BubbleTest extends ObjectTest{
	
	//@Mock private Room room;
	private Bubble bubble = new Bubble(1, 1, 1);
	private int x = 1;
	private int y = 1;
	private int size = 1;

	/*public BubbleTest(Bubble bubble, int x, int y, int size) {
		this.bubble = new Bubble(1, 1, 1);
		this.x = x;
		this.y = y;
		this.size = size;
	}*/
	
	@Before
	public void setUp() {
		setObject(bubble);
		setX(x);
		setY(y);
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
	public void splitSizeTest() {
		//bubble.split();
		//assertEquals(bubble.getSize(), size--);
	}
	
	/*@Parameters
	public static Collection<Object[]> data() {
		Object[][] values = {
				{new Bubble(2, 1, 1), 1, 1, 2},
				{new Bubble(2, 0, 0, 1, 1), 0, 0, 2}
		};
		return Arrays.asList(values);
	}*/

}
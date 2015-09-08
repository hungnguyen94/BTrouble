package Group_24.BubbleTrouble;

import java.awt.Color;
import java.awt.Graphics2D;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Class which tests the floor.
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FloorTest extends ObjectTest {
	
	@Mock Graphics2D graphics;
	@Mock View view;
	private Floor floor = new Floor(1, 1, 1, 1);
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * Set up the variables in the object test class
	 */
	@Before
	public void setUp() {
		x = floor.getX();
		y = floor.getY();
		width = floor.getWidth();
		height = floor.getHeight();
		setObject(floor);
		setX(x);
		setY(y);
		setWidth(height);
		setHeight(width);
	}
	
	/**
	 * Test the drawObject method.
	 */
	@Test
	public void drawObjectTest() {
		floor.drawObject(graphics, view);
		verify(graphics).setColor(Color.blue);
		verify(graphics).fillRect(floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());
	}

}

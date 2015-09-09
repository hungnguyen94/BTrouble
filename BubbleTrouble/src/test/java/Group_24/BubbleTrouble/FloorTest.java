package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

public class FloorTest extends ObjectTest {

	private Floor floor;
	
	@Before
	public void setUp() {
		this.floor = new Floor(1, 1, 1, 1);
		setObject(floor);
		setX(1);
		setY(1);
		setWidth(1);
		setHeight(1);
	}
	
	@Test
	public void getSpriteTest() {
		//assertTrue(floor.getSprite().equals(new Rectangle(1, 1, 1, 1)));

	}

}

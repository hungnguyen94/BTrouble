package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.geom.Rectangle;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WallTest extends ObjectTest{

	private Wall wall;
	Rectangle sprite;
	
	@Before
	public void setUp() {
		sprite = new Rectangle(1, 1, 1, 1);
		wall = new Wall(1, 1, 1, 1);
		setObject(wall);
		setX(1);
		setY(1);
		setWidth(1);
		setHeight(1);
	}
	
	@Test
	public void getSprite() {
		//assertEquals(sprite, wall.getSprite());
	}

}

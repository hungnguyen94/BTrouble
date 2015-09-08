package Group_24.BubbleTrouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import Group_24.BubbleTrouble.Object;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public abstract class ObjectTest {
	
	private Object object;
	private int x;
	private int y;
	private int height;
	private int width;
	
	@Before
	public abstract void setUp() throws SlickException;
	
	public void setObject(Object o) {
		object = o;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	@Test
	public void getXTest() {
		assertEquals(object.getX(), x);
	}
	
	@Test
	public void getYTest() {
		assertEquals(object.getY(), y);
	}
	
	@Test
	public void getHeightTest() {
		assertEquals(object.getHeight(), height);
	}
	
	@Test
	public void getWidthTest() {
		assertEquals(object.getWidth(), width);
	}
	
	@Test
	public void collidesWithTrueTest() {
		Object collider = object;
		assertTrue(collider.collidesWith(object));
	}
	
	/*@Test
	public void collidesWithFalseTest() {
		Object collider = object;
		collider.setX(11);
		assertFalse(collider.getX() == object.getX());
	}*/
	

}

package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public abstract class SpriteTest {

	private Sprite sprite;
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	@Test
	public void setVisibilityTest1() {
		sprite.setVisible(true);
		assertTrue(sprite.isVisible());
	}
	
	@Test
	public void setVisibilityTest2() {
		sprite.setVisible(false);
		assertFalse(sprite.isVisible());
	}

}

package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnimationTest {

	private Animation animation;
	
	@Before
	public void setUp() {
		animation = new Animation();
	}
	
	@Test
	public void updateNegativeDelayTest() {
		animation.setDelay(-1);
		animation.update();
		assertEquals(animation, new Animation());
	}

}

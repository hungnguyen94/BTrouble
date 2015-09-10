package Group_24.BubbleTrouble;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Matchers.anyString;
import static org.lwjgl.opengl.GL11.glInitNames;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RopeTest {

	private Rope rope;
	@Mock Image sprite;
	
	@Before
	public void setUp() throws SlickException {
		//rope = new Rope(1, 1);
		//Image spy = spy(new Image(anyString()));
        //doReturn(1).when(spy).Image(anyString());
	}
	
	/*@Test
	public void existTrueTest() {
		rope.setExists(true);
		rope.getExists(true);
	}
	
	@Test
	public void isVisibleTest() {
		rope.isVisible();
		verify(sprite).isDestroyed();
	}*/

}

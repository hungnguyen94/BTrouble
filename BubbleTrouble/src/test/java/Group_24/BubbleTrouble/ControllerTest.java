package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	@Mock GameContainer container;
	@Mock Player player;
	
	@Before
	public void setUp() throws SlickException {
		Controller.startNewGame(container);
	}
	


}

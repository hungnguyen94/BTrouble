package Group_24.BubbleTrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class PlayerTest extends ObjectTest{

	private Player player;
	private int lives;
	
	@Before
	public void setUp() throws SlickException {
		//player = new Player(1, 1);
		setObject(player);
		setX(1);
		setY(1);
		setHeight(0);
		setWidth(0);
		lives = player.getLives();
	}
	/*
	@Test
	public void addLife() {
		player.addLife();
		assertEquals(player.getLives(), lives++);
	}*/

}

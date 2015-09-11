package Group_24.BubbleTrouble;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.SlickException;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
	Thread gameThread;
	
	@Before
	public void setUp() throws SlickException, InterruptedException {
		gameThread = new Thread(new Runnable() {
			
			public void run() {
				try{
					SlickApp.main(null);
				}
				catch(Exception e){}
			}
		});
		gameThread.start();
		
		Thread.sleep(2000);
	}
	
	@SuppressWarnings("deprecation")
	@After
	public void endTest() throws SlickException {
		Controller.endGame("test ended");
		gameThread.suspend();
	}
	
	@Test
	public void gameStartsTest() throws SlickException, InterruptedException{
		Assert.assertFalse(Model.getPlayers().isEmpty());
	}
	
	@Test
	public void bubblesMoveOnUpdateTest() throws SlickException{
		float pos1 = Model.getBubbles().get(0).getX();
		Controller.update();
		float pos2 = Model.getBubbles().get(0).getX();
		Assert.assertNotEquals(pos1, pos2);
	}
	
	@Test
	public void playerCanLoseLifeTest(){
		Player player = Model.getPlayers().get(0);
		int livesBefore = player.getLives();
		Controller.loseLife(player);
		int livesAfter = player.getLives();
		Assert.assertNotEquals(livesBefore, livesAfter);
	}
	
	@Test
	public void addsBubbleAfterUpdateTest() throws SlickException{
		int bubblesBefore = Model.getBubbles().size();
		Controller.addBubble(new Bubble(5, 100, 100));
		int bubblesAfter = Model.getBubbles().size();
		Assert.assertEquals(bubblesBefore, bubblesAfter);
		Controller.update();
		bubblesAfter = Model.getBubbles().size();
		Assert.assertNotEquals(bubblesBefore, bubblesAfter);
	}
	
	@Test
	public void removesBubbleAfterUpdateTest() throws SlickException{
		Bubble bubble = new Bubble(5, 100, 100);
		Controller.addBubble(bubble);
		Controller.update();
		
		int bubblesBefore = Model.getBubbles().size();
		Controller.removeBubble(bubble);
		int bubblesAfter = Model.getBubbles().size();
		Assert.assertEquals(bubblesBefore, bubblesAfter);
		Controller.update();
		bubblesAfter = Model.getBubbles().size();
		Assert.assertNotEquals(bubblesBefore, bubblesAfter);
	}
	
	@Test
	public void playerIsDeadTest() throws SlickException{
		Player player = Model.getPlayers().get(0);
		while(player.getLives() > 0){
			player.loseLife();
		}
		Controller.loseLife(player);
		Assert.assertTrue(player.getLives() == -1);
	}
	
	@Test
	public void noBubblesTest() throws SlickException{
		for(Bubble b : Model.getBubbles()){
			Controller.removeBubble(b);
		}
		Controller.update();
		assertTrue(Model.getBubbles().isEmpty());
		
	}
	
	@Test
	public void playerRightWallCollisionTest() throws SlickException{
		Player player = Model.getPlayers().get(0);
		int pos = 0;
		
		for(int i = 0; i < Model.getRoomWidth()*2; i ++){
			player.moveTo((int) player.getX() + 1, (int) player.getY());
			Controller.update();
			pos = (int) player.getX();
		}
		assertTrue(pos < Model.getRoomWidth());
	}
	
	@Test
	public void playerLeftWallCollisionTest() throws SlickException{
		Player player = Model.getPlayers().get(0);
		int pos = 0;
		
		for(int i = 0; i < Model.getRoomWidth()*2; i ++){
			player.moveTo((int) player.getX() - 1, (int) player.getY());
			Controller.update();
			pos = (int) player.getX();
		}
		assertTrue(pos < Model.getRoomWidth());
	}
	
}

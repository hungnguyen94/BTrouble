package Group_24.BubbleTrouble;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import java.util.ArrayList;

/**
 * Controller, recalculates the Model, on request of the view.
 */
public class Controller {
	
	private static final int REWARD_BUBBLE = 100;
    private static ArrayList<Bubble> newBubbles;
    private static ArrayList<Bubble> oldBubbles;
    private static GameContainer gc;
    private static Timers timers;

    /**
     * Starts a new game by loading data into the room and adding the players.
     */
    public static void startNewGame(GameContainer container) throws SlickException {
        timers = new Timers(100);

    	newBubbles = new ArrayList<Bubble>();
		oldBubbles = new ArrayList<Bubble>();
        gc = container;

    	Model.init();
    	Model.addRoom(new Room());
    	Model.addPlayer(new Player(0, 0));
        Model.restartRoom();
    }

    public static Timers getTimers() {
        return timers;
    }

	/**
	 * Updates the model, should be done on request of the view.
	 */
	public static void update() throws SlickException {

		for(Bubble bubble: Model.getBubbles()) {
			
			for (Rectangle floor: Model.getCurrentRoom().getFloors())
				if (bubble.intersects(floor))
					bubble.collide(CollisionEvent.TYPE_FLOOR);
            
            // Collision detection for walls
            for (Rectangle wall: Model.getCurrentRoom().getWalls()) {
            	if (bubble.intersects(wall))
            		bubble.collide(CollisionEvent.TYPE_WALL);
            }

            for(Player player : Model.getPlayers()){

                // Check if timer has run out.
                if (getTimers().getLevelTimeLeft() <= 0) {
                    loseLife(player);
                }
            	
	            // CollisionEvent detection for bubble against player
	            if (player.intersects(bubble)) {
	                loseLife(player);
	            }

                // Collision detection for walls
                for (Rectangle wall: Model.getCurrentRoom().getWalls()) {
                    if (player.intersects(wall)) {
                        int playerX = (int) player.getX() + ((int)(player.getWidth()/2));
                        if (playerX > wall.getX()) {
                            player.setLeftBlocked(true);
                        } else
                        if (playerX <= wall.getX()) {
                            player.setRightBlocked(true);
                        }
                    }
                }

	            for (Rope rope : player.getRopes()) {
	                if (bubble.intersects(rope)) {
	                    bubble.collide(CollisionEvent.TYPE_ROPE);
	                    player.increaseScore(REWARD_BUBBLE);
	                    player.resetRope();
	                }
	            }
            }
        }
        
        // calculate movements
        updateRopes();
    	updateBubble();
	}
	
	/**
	 * Lets the player lose a life
	 * @param player should be the player who lost a life
	 */
	public static void loseLife(Player player) {
		player.loseLife();
        if(!player.hasLives()){
        	endGame("Game over...");
        } else {
        	Model.restartRoom();
        	player.resetRope();
            Controller.getTimers().restartTimer();
        }
	}
	
	/**
	 * Updates the bubble, checks whether bubbles should be removed or not, and calculates the new position of each bubble.
	 */
	private static void updateBubble() {
        for(Bubble bubble: newBubbles) {
            Model.getBubbles().add(bubble);
        }
        newBubbles.clear();
        for(Bubble bubble: oldBubbles) {
        	Model.getBubbles().remove(bubble);
        }
        oldBubbles.clear();
        if (Model.getBubbles().isEmpty()) endGame("You won the game!");
        for(Bubble bubble: Model.getBubbles())
            bubble.move();
    }

    /**
     * adds a bubble to the game
     * @param bubble Bubble to add to the room
     */
    public static void addBubble(Bubble bubble) {
        newBubbles.add(bubble);
    }

    /**
     * removes a bubble from the game
     * @param bubble the bubble to remove
     */
    public static void removeBubble(Bubble bubble) {
        oldBubbles.add(bubble);
    }
	
    /**
     * Updates the ropes, calculates the new position of the rope and removes the rope if it hits the ceiling. 
     */
    private static void updateRopes() throws SlickException {
    	for(Player player: Model.getPlayers()){
    	    for (int i = 0; i < player.getRopes().size(); i++) {
    	      player.getRopes().get(i).move();
    	      if (player.getRopes().get(i).getY() <= 0) {
    	        player.getRopes().remove(i);
    	      }
    	    }
	    }
    }
    
    /**
     * Ends the game by stopping the view, shows a message. 
     * @param message should be a String containing the message which is shown.
     */
	public static void endGame(String message) {
		gc.exit();
    }
	
}

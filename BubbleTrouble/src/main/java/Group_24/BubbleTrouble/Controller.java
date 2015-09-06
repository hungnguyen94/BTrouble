package Group_24.BubbleTrouble;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller {
	
	private static final int REWARD_BUBBLE = 100;
	private static View view;
	private static ArrayList<Bubble> newBubbles;
    private static ArrayList<Bubble> oldBubbles;
	
	public static void init(){
		newBubbles = new ArrayList<Bubble>();
		oldBubbles = new ArrayList<Bubble>();
		
		startNewGame();
	}
	
	public static View getView() {
		return view;
	}

	public static void update(){
		for(Bubble bubble: Model.getBubbles()) {
            // collision detection for bubble against floor
            if (bubble.getY() + bubble.getWidth() >= Model.getRoomHeight()) {
                bubble.collide(Collision.TYPE_FLOOR);
            }
            // collision detection for bubble against left wall
            if (bubble.getX() <= 0) {
                bubble.collide(Collision.TYPE_WALL);
            }
            // collision detection for bubble against right wall
            if (bubble.getX() + bubble.getWidth() >= Model.getRoomWidth()) {
                bubble.collide(Collision.TYPE_WALL);
            }
            
            for(Player player : Model.getPlayers()){
	            // collision detection for bubble against player
	            if (player.collidesWith(bubble)) {
	                loseLife(player);
	            }
	
	            for (Rope rope : player.getRopes()) {
	                if (bubble.collidesWith(rope)) {
	                    bubble.collide(Collision.TYPE_ROPE);
	                    player.increaseScore(REWARD_BUBBLE);
	                    player.resetRope();
	                }
	            }
            }
        }
        
        // calculate movements
        updateRopes();
    	updateBubble();
    	updatePlayer();
	}
	
	private static void loseLife(Player player) {
		player.loseLife();
        if(!player.hasLives()){
        	endGame("Game over...");
        } else {
        	Model.restartRoom();
        	player.resetRope();
        	view.start();
        }
		
	}
	
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
     * @param size size of the bubble
     * @param x horizontal position of the bubble
     * @param y vertical position of the bubble
     * @param vx horizontal speed of the bubble
     * @param vy vertical speed of the bubble
     */
    public static void addBubble(int size, int x, int y, double vx, double vy) {
        Bubble newBubble = new Bubble(size, x, y, vx, vy);
        newBubbles.add(newBubble);
    }

    /**
     * removes a bubble from the game
     * @param bubble the bubble to remove
     */
    public static void removeBubble(Bubble bubble) {
        oldBubbles.add(bubble);
    }
	
    private static void updateRopes() {
    	for(Player player: Model.getPlayers()){
	        ArrayList<Rope> ropes = player.getRopes();
	
	        for (int i = 0; i < ropes.size(); i++) {
	
	            Rope rope = (Rope) ropes.get(i);
	
	            if (rope.isVisible()) {
	            	rope.move();
	            } else {
	                ropes.clear();
	            }
	        }
    	}
    }

    private static void updatePlayer() {
        for(Player player: Model.getPlayers()){
        	player.move();
        }
    }
	
    public static void startNewGame(){
    	// checks if the view is inactive
    	if(view == null || !view.isActive()){
    		view = new View();
    		
    		// TODO could write data import from file
    		Model.init();
    		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    		bubbles.add(new Bubble(3, 100, 50));
    		RoomData data = new RoomData(bubbles);
    		Model.addRoom(new Room(data));
    		
    		Model.addPlayer(new Player(10, Model.getRoomHeight()-65));
    	}
    	
    }
    
	public static void endGame(String message) {
		view.pause();
		view.showMessage(message);
    }
	
	public static void pauseGame(){
		if(view.isActive()){
			view.pause();
			view.showMessage("game paused");
			view.start();
		}
	}

	public static void keyReleased(KeyEvent e) {
		for(Player player: Model.getPlayers()){
			player.keyReleased(e);
		}
	}
	
	public static void keyPressed(KeyEvent e) {
		for(Player player: Model.getPlayers()){
			player.keyPressed(e);
		}
		
		int key = e.getKeyCode();

	    switch (key) {
		case KeyEvent.VK_PAUSE: pauseGame();
			break;

		default:
			break;
		}
	}
	
}

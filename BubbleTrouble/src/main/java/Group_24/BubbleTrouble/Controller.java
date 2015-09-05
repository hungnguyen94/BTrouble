package Group_24.BubbleTrouble;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller {
	
	private static View view;
	private static ArrayList<Bubble> newBubbles;
    private static ArrayList<Bubble> oldBubbles;
	
	public static void init(){
		newBubbles = new ArrayList<Bubble>();
		oldBubbles = new ArrayList<Bubble>();
		
		startGame();
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
	                endGame("Game over...");
	            }
	
	            for (Rope rope : player.getRopes()) {
	                if (bubble.collidesWith(rope)) {
	                    bubble.collide(Collision.TYPE_ROPE);
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
	
    public static void startGame(){
    	if(view == null || view.isRunning()){
    		view = new View();
    		
    		Model.init();
    		ArrayList<Player> players = new ArrayList<Player>();
    		players.add(new Player(10, Model.getRoomHeight()-65));
    		ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    		bubbles.add(new Bubble(3, 100, 50));
    		RoomData data = new RoomData(players, bubbles);
    		Model.addRoom(new Room(data));
    	}
    	
    }
    
	public static void endGame(String message) {
		view.stopGame();
		view.showMessage(message);
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
	}
}

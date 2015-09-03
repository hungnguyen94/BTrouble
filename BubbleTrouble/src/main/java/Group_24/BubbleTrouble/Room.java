package Group_24.BubbleTrouble;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

/**
 * Room contains all objects within the room, and draws them on the screen.
 *
 */
@SuppressWarnings("serial")
public class Room extends JPanel implements ActionListener {

	private Player player;
	private ArrayList<Bubble> bubbles;
    private static ArrayList<Bubble> newBubbles;
    private static ArrayList<Bubble> oldBubbles;
	private Timer timer;
    private final int DELAY = 10;
	
	private final int ROOM_WIDTH = 800;
	private final int ROOM_HEIGHT = 500;
	
	public Room(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		player = new Player(10, ROOM_HEIGHT-65);
		bubbles = new ArrayList<Bubble>();
        newBubbles = new ArrayList<Bubble>();
        oldBubbles = new ArrayList<Bubble>();
        Bubble initBubble = new Bubble(3, 100, 50);
		bubbles.add(initBubble);
		
		setPreferredSize(new Dimension(ROOM_WIDTH, ROOM_HEIGHT));
		
		timer = new Timer(DELAY, this);
        timer.start(); 
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
   
		player.drawObject(g2d, this);
        for(Bubble bubble: bubbles)
		    bubble.drawObject(g2d, this);
		player.drawRopes(g2d, this);
	}
	
	public void actionPerformed(ActionEvent e) {

        for(Bubble bubble: bubbles) {
            // collision detection for bubble against floor
            if (bubble.getY() + bubble.getWidth() >= ROOM_HEIGHT) {
                bubble.collide(Collision.TYPE_FLOOR);
            }
            // collision detection for bubble against left wall
            if (bubble.getX() <= 0) {
                bubble.collide(Collision.TYPE_WALL);
            }
            // collision detection for bubble against right wall
            if (bubble.getX() + bubble.getWidth() >= ROOM_WIDTH) {
                bubble.collide(Collision.TYPE_WALL);
            }

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
        
        // calculate movements and repaint
        updateRopes();
    	updateBubble();
    	updatePlayer();
        
        repaint();
    }
	
	private void updateRopes() {

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

    private void updatePlayer() {
        player.move();
    }
    
    private void updateBubble() {
        for(Bubble bubble: newBubbles) {
            bubbles.add(bubble);
        }
        newBubbles.clear();
        for(Bubble bubble: oldBubbles) {
            bubbles.remove(bubble);
        }
        oldBubbles.clear();
        if (bubbles.isEmpty()) endGame("You won the game!");
        for(Bubble bubble: bubbles)
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

    private void endGame(String message) {
        JOptionPane.showMessageDialog(this, message);
        timer.stop();
    }
	
	private class TAdapter extends KeyAdapter {

	    @Override
	    public void keyReleased(KeyEvent e) {
	        player.keyReleased(e);
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	    	player.keyPressed(e);
	    }
	}
}



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
public class Room extends JPanel implements ActionListener{
	
	private Player player;
	private Bubble bubble;
	private Timer timer;
    private final int DELAY = 10;
	
	private final int ROOM_WIDTH = 300;
	private final int ROOM_HEIGHT = 300;
	
	public Room(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		player = new Player(10, ROOM_HEIGHT-65);
		bubble = new Bubble(5, 100, 50);
		
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
		bubble.drawObject(g2d, this);
		player.drawRopes(g2d, this);
	}
	
	public void actionPerformed(ActionEvent e) {
        
        // collision detection for bubble against floor
        if(bubble.getY() + bubble.getWidth() >= ROOM_HEIGHT){
        	bubble.collide(Collision.TYPE_FLOOR);
        }
        // collision detection for bubble against left wall
        if(bubble.getX() <= 0){
        	bubble.collide(Collision.TYPE_WALL);
        }
        // collision detection for bubble against right wall
        if(bubble.getX() + bubble.getWidth() >= ROOM_WIDTH){
        	bubble.collide(Collision.TYPE_WALL);
        }
        
        // collision detection for bubble against player
        if(player.collidesWith(bubble)){
        	JOptionPane.showMessageDialog(this, "Game over...");
        	timer.stop();
        }
        
        for(Rope rope: player.getRopes()){
        	if(bubble.collidesWith(rope)){
        		bubble.collide(Collision.TYPE_ROPE);
        		player.resetRope();
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

        bubble.move();
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



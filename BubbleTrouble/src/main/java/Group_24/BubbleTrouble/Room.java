package Group_24.BubbleTrouble;

import javax.swing.*;
import java.awt.event.*;
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
    private Color drawingcolor = Color.white;
	
	private final int ROOM_WIDTH = 300;
	private final int ROOM_HEIGHT = 300;
	
	public Room(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		player = new Player();
		bubble = new Bubble(10, 5, 100, 100);
		
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
   
		g2d.setColor(drawingcolor);
		g2d.drawRect(player.getX(), ROOM_HEIGHT-player.getHeight(), player.getWidth(), player.getHeight());
		g2d.setColor(drawingcolor);
		g2d.drawOval(bubble.getX(), bubble.getY(), bubble.getRealSize(), bubble.getRealSize());
	}
	
	public void actionPerformed(ActionEvent e) {
        
        // collision detection for bubble against floor
        if(bubble.getY() + bubble.getRealSize() >= ROOM_HEIGHT){
        	bubble.collide(Bubble.COLLISIONTYPE_FLOOR);
        }
        // collision detection for bubble against left wall
        if(bubble.getX() <= 0){
        	bubble.collide(Bubble.COLLISIONTYPE_WALL);
        }
        // collision detection for bubble against right wall
        if(bubble.getX() + bubble.getRealSize() >= ROOM_WIDTH){
        	bubble.collide(Bubble.COLLISIONTYPE_WALL);
        }
        
        // collision detection for bubble against player
        double dist_x = Math.abs(bubble.getX() + .5 * bubble.getRealSize() - (player.getX() + .5 * player.getWidth())) - .5 * bubble.getRealSize() - .5 * player.getWidth();
        double dist_y = Math.abs(bubble.getY() + (.5 * bubble.getRealSize()) - ROOM_HEIGHT + player.getHeight()) - .5 * bubble.getRealSize();
        
        if(dist_x <= 0 && dist_y <= 0){
        	drawingcolor = Color.red;
        	timer.stop();
        }
        
        // calculate movements and repaint
        player.move();
        bubble.move();
        repaint();
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



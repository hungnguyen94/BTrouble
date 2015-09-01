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
	private Timer timer;
    private final int DELAY = 10;
	
	private final int ROOM_WIDTH = 300;
	private final int ROOM_HEIGHT = 300;
	
	public Room(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		player = new Player();
		
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
   
		g2d.setColor(Color.white);
		g2d.drawRect(player.getX(), ROOM_HEIGHT-player.getHeight(), player.getWidth(), player.getHeight());
	}
	
	public void actionPerformed(ActionEvent e) {
        
        player.move();
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



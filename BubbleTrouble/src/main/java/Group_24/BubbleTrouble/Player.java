package Group_24.BubbleTrouble;

import java.awt.event.*;

/**
 * Player class, containing all the data about the player.
 *
 */
public class Player {
	private int x;
	private int dx;
	
	private int width;
	private int height;
	
	public Player(){
		this.x = 10;
		this.width = 30;
		this.height = 50;
	}
	
	public void move(){
		x += dx;
	}
	
	public int getX(){
		return x;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key){
			case KeyEvent.VK_LEFT: dx = -1; break;
			case KeyEvent.VK_RIGHT: dx = 1; break;
			default: return;
		}
	}
	
	public void keyReleased(KeyEvent e) {
	        
        int key = e.getKeyCode();

		switch(key){
			case KeyEvent.VK_LEFT: dx = 0; break;
			case KeyEvent.VK_RIGHT: dx = 0; break;
			default: return;
		}
	
	}
}

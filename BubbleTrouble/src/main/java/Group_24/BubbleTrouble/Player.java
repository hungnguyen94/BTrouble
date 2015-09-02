package Group_24.BubbleTrouble;

import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Player class, containing all the data about the player.
 *
 */
public class Player extends Sprite{
	private int dx;
	
	private ArrayList<Rope> ropes;
	
	public Player(int x, int y){
		super(x, y);
		init("Sprites/Player.png");

	}
	
	@Override
	protected void init(String img) {
		loadImage(img);
		
		ropes = new ArrayList<Rope>();
		getImageDimensions();
	}

	public ArrayList<Rope> getRopes() {
		return ropes;
	}
	
	public void move(){
		x += dx;
	}
	
    public void fire() {
        ropes.add(new Rope((this.getX() + this.getWidth() / 2), this.getY()));
    }
    
    public void drawRopes(Graphics2D g, Room room){
    	ArrayList<Rope> rs = this.getRopes();

        for (Object r1 : rs) {
            Rope r = (Rope) r1;
            g.drawImage(r.getImage(), r.getX() - r.getWidth() / 2,
                    r.getY(), room);
        }   
    }
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key){
			case KeyEvent.VK_SPACE: fire(); break;
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

	public void resetRope() {
		ropes = new ArrayList<Rope>();
	}
}

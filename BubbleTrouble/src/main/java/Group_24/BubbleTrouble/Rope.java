package Group_24.BubbleTrouble;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rope {
	private int dy;
	private int x;
	private int y;
	private boolean exists = false;
	private final int INITIAL_ROPESPEED = 2;
	private Image sprite;
	
    public Rope(int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        this.dy = INITIAL_ROPESPEED;
        sprite = new Image("Sprites/rope.png");
    }
    
    public void draw() {
      sprite.draw(x, y);
    }
    
    public void setExists(boolean exist) {
      this.exists = exist;
    }
    
    public boolean getExists(boolean exist) {
      return this.exists;
    }
    
    public int getY() {
      return this.y;
    }

    public void move() {
        this.y -= dy;
        
        if (this.y <= 0) {
            dy = 0;
        }
    }
}

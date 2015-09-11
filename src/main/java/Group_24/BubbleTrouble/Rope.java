package Group_24.BubbleTrouble;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Rope extends Rectangle {
	private int dy;
	private boolean exists = false;
	private final int INITIAL_ROPESPEED = 5;
	private Image sprite;
	
    public Rope(float x, float y) throws SlickException {
        super(x, y, 2f, 790f);
        this.dy = INITIAL_ROPESPEED;
        sprite = new Image("Sprites/rope.png");
    }
    
    public boolean equals(Object other) {
    	if(other instanceof Rope) {
    		Rope that = (Rope) other;
    		return(this.x == that.x && this.y == that.y && this.exists == that.exists);
    	}
    	return false;
    }
    
    public void draw() {
      sprite.draw(x - (int)(sprite.getWidth() / 2), y);
    }

    public void move() {
        setY(getY()-2*dy);
        
        if (getY() <= 0) {
            dy = 0;
        }
    }
}

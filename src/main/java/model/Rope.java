package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("serial")
public class Rope extends Rectangle {
	private int dy;
	private final int INITIAL_ROPESPEED = 5;
	private Image sprite;
	
    public Rope(float x, float y) {
        super(x, y, 2f, 790f);
        this.dy = INITIAL_ROPESPEED;
       // sprite = new Image("Sprites/rope.png");
    }
    
    public boolean equals(Object other) {
    	if(other instanceof Rope) {
    		Rope that = (Rope) other;
    		return(this.x == that.x && this.y == that.y && this.dy == that.dy);
    	}
    	return false;
    }
    
    public int getDY() {
    	return dy;
    }
    
    public void draw() throws SlickException {
      sprite = new Image("Sprites/rope.png");
      sprite.draw(x - (int)(sprite.getWidth() / 2), y);
    }

    public void move() {
        setY(getY()-2*dy);
        
        if (getY() <= 0) {
            dy = 0;
        }
    }
}

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
        super(x, y, 60f, 790f);
        this.dy = INITIAL_ROPESPEED;
        sprite = new Image("Sprites/rope.png");
    }
    
    public void draw() {
      sprite.draw(x, y);
    }

    public void move() {
        setY(getY()-dy);
        
        if (getY() <= 0) {
            dy = 0;
        }
    }
}

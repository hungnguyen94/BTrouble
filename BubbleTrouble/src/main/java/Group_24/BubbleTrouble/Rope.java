package Group_24.BubbleTrouble;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rope extends Object {
	private int dy;
    private Image sprite;
	private final int INITIAL_ROPESPEED = 3;

    public Rope(int x, int y) throws SlickException {
        super(x, y);
        sprite = new Image("Sprites/Rope.gif");
        this.dy = INITIAL_ROPESPEED;
    }

    public void move() throws SlickException {
        y -= dy;
        
        if (y <= 0) {
            sprite.destroy();
        }
    }

    public boolean isVisible() {
        return !sprite.isDestroyed();
    }

    public void draw() {
        sprite.draw(x, y);
    }
}

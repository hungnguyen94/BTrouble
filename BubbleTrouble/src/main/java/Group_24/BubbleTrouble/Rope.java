package Group_24.BubbleTrouble;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rope extends Sprite{
	private int dy;
	private final int INITIAL_ROPESPEED = 3;
	private Image rope;
	
    public Rope(int x, int y) throws SlickException {
        super(x, y);
        this.dy = INITIAL_ROPESPEED;
        rope = new Image("Sprites/Rope.gif");
    }
    
    protected void init(String img) {
        loadImage(img);  
        
        getImageDimensions();
    }
    
    public void draw() {
      rope.draw(x, y);
    }

    public void move() {
        y -= dy;
        
        if (y <= 0) {
            this.setVisible(false);
        }
    }
}

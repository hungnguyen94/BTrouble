package Group_24.BubbleTrouble;

public class Rope extends Sprite{
	private int dy;
	private final int INITIAL_ROPESPEED = 5;

    public Rope(int x, int y) {
        super(x, y);
        this.dy = INITIAL_ROPESPEED;
        init("Sprites/Rope.png");
    }
    
    protected void init(String img) {
        loadImage(img);  
        
        getImageDimensions();
    }

    public void move() {
        y -= dy;
        
        if (y < 0) {
            this.setVisible(false);
        }
    }
}

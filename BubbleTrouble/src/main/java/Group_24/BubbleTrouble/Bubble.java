package Group_24.BubbleTrouble;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bubble extends Object {
	private int size;
	
	// actual size of a level one bubble in the game in pixels.
	private final double GAME_SIZE = 10;
	
	// speed (pixels / step)
	private double vx;
	private double vy;
	
	// acceleration (pixels / step^2)
	private double ay;
	
	// gravity
	private final double G = .01;
	// starting speed in horizontal direction
	private final double INITIAL_HORIZONTAL_SPEED = .5;
	
	/**
	 * Bubble class, containing all the data about the bubble.
	 * 
	 * @param size of the bubble.
	 * @param x horizontal starting position of the bubble in the room
	 * @param y vertical starting position of the bubble in the room
	 */
	public Bubble(int size, int x, int y) {
		super(x, y);
		
		this.size = size;
		this.ay = G;
		this.vx = INITIAL_HORIZONTAL_SPEED;
	}

    public Bubble(int size, int x, int y, int vx) {
        super(x, y);

        this.size = size;
        this.ay = G;
        this.vx = vx;
    }

	public int getSize() {
		return size;
	}
	
	@Override
	public int getWidth() {
		return (int) (size * GAME_SIZE);
	}
	
	@Override
	public int getHeight() {
		return this.getWidth();
	}
	
	public void move() {
		this.vy += ay;
		
		this.x += vx;
		this.y += vy;
	}
	
	public void collide(int type){
		switch(type){
			case Collision.TYPE_FLOOR: vy = -vy; break;
			case Collision.TYPE_WALL: vx = -vx; break;
			case Collision.TYPE_ROPE: split(); break;
			default: return;
		}
	}

	/**
	 * Splits the bubble in two with a smaller size of each
	 */
	public void split() {
        size--;
        int newSize = (int) size;
        int newX = (int) x;
        int newY = (int) y;
        int newVx = (int) -vx;
        Room.addBubble(newSize, newX, newY, newVx);
	}

	@Override
	public void drawObject(Graphics2D g, Room r) {
		//g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getWidth());
		g.setColor(Color.white);
		g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getWidth());
	}
}

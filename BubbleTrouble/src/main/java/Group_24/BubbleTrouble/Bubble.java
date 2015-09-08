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
	private final double G = .05;
	// starting speed in horizontal direction
	private final double INITIAL_HORIZONTAL_SPEED = .5;
    // factor of acceleration that the bubbles go up with when hit with a rope
    private final int HIT_SPEED_FACTOR = 50;
	
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

    /**
     * Bubble constructor with the ability to give initial speed as a variable
     * @param size of the bubble.
     * @param x horizontal starting position of the bubble in the room
     * @param y vertical starting position of the bubble in the room
     * @param vx horizontal starting speed of the bubble
     * @param vy vertical starting speed of the bubble
     */
    public Bubble(int size, int x, int y, double vx, double vy) {
        super(x, y);

        this.size = size;
        this.ay = G;
        this.vx = vx;
        this.vy = vy;
    }
    
    /**
     * Returns the size of the bubble in steps.
     * @return returns an integer representing the size of the bubble in steps.
     */
	public int getSize() {
		return size;
	}
	
	/**
     * Returns the actual diameter of the bubble, overrides the superclass method.
     * @return returns an integer representing the actual diameter of the bubble.
     */
	@Override
	public int getWidth() {
		return (int) (size * GAME_SIZE);
	}
	
	/**
     * Returns the actual diameter of the bubble, overrides the superclass method.
     * @return returns an integer representing the actual diameter of the bubble.
     */
	@Override
	public int getHeight() {
		return this.getWidth();
	}
	
	/**
	 * Calculates the next location of the Bubble.
	 */
	public void move() {
		this.vy += ay;
		
		this.x += vx;
		this.y += vy;
	}
	
	/**
	 * Should be called when a Bubble collides.
	 * @param type should be a integer holding the collision type, contained by Collision.[type]. 
	 */
	public void collide(int type){
		switch(type){
			case CollisionEvent.TYPE_FLOOR: vy = -vy; break;
			case CollisionEvent.TYPE_WALL: vx = -vx; break;
			case CollisionEvent.TYPE_ROPE: split(); break;
			default: return;
		}
	}

	/**
	 * Splits the bubble in two with a smaller size of each
	 */
	public void split() {
		// reduce size
		size--;
		if (size != 0) {
			// give upward speed
			vy = -ay * HIT_SPEED_FACTOR;
			// casting to integers
			int newSize = (int) size;
			int newX = (int) x;
			int newY = (int) y;
			// add an extra bubble to the game
			Controller.addBubble(newSize, newX, newY, -vx, vy);
		} else {
			Controller.removeBubble(this);
		}
	}
}

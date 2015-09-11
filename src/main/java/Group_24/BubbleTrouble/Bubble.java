package Group_24.BubbleTrouble;

import org.newdawn.slick.geom.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bubble extends Circle {
	private int size;
	
	// actual size of a level one bubble in the game in pixels.
	private final float GAME_SIZE = 10;
	
	// speed (pixels / step)
	private float vx;
	private float vy;
	
	// acceleration (pixels / step^2)
	private float ay;
	
	// gravity
	private final float G = .4f;
	// starting speed in horizontal direction
	private final float INITIAL_HORIZONTAL_SPEED = 3f;
    // factor of acceleration that the bubbles go up with when hit with a rope
    private final int HIT_SPEED_FACTOR = 30;
	
	/**
	 * Bubble class, containing all the data about the bubble.
	 * 
	 * @param size of the bubble.
	 * @param x horizontal starting position of the bubble in the room
	 * @param y vertical starting position of the bubble in the room
	 */
	public Bubble(int size, float x, float y) {
		super(x, y, size*10f);
		
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
    public Bubble(int size, float x, float y, float vx, float vy) {
        super(x, y, size*10f);

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
    
    public double getVX() {
    	return vx;
    }
    
    public double getVY() {
    	return vy;
    }
	
	/**
	 * Calculates the next location of the Bubble.
	 */
	public void move() {
		this.vy += ay;
		
		float newX = getCenterX() + vx;
		float newY = getCenterY() + vy;

        setCenterX(newX);
        setCenterY(newY);
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
		setRadius(size*10f);
		if (size != 0) {
			// give upward speed
			vy = -ay * HIT_SPEED_FACTOR;
			// add an extra bubble to the game
            Bubble newBubble = new Bubble(size, x, y, -vx, vy);
			Controller.addBubble(newBubble);
		} else {
			Controller.removeBubble(this);
		}
	}
	
	public boolean equals(Shape other) {
		if(other instanceof Bubble) {
			Bubble that = (Bubble) other;
			return (this.size == that.size && this.x == that.x && this.y == that.y && this.vx == that.vx && this.vy == that.vy);
		}
		return false;
	}
}

package com.sem.btrouble.model;

import org.newdawn.slick.geom.Circle;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.view.SlickApp;

@SuppressWarnings("serial")
public class Bubble extends Circle {
	private int size;
	
	// actual size of a level one bubble in the game in pixels.
	private static final float GAME_SIZE = 10f;
	
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
		super(x, y, size*GAME_SIZE);
		
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
     * Invert the y direction on collision
     */
    public void bounceY() {
        vy = -vy;
    }

    /**
     * Invert the x direction on collision
     */
    public void bounceX() {
        vx = -vx;
    }
	
	/**
	 * Should be called when a Bubble collides.
	 * @param type should be a integer holding the collision type, contained by Collision.[type]. 
	 */
	public void bubbleEvent(BubbleEvent event){
		switch(event.getId()){
			case BubbleEvent.COLLISION_FLOOR: vy = -vy; break;
			case BubbleEvent.COLLISION_WALL: vx = -vx; break;
			case BubbleEvent.COLLISION_ROPE: split(); break;
			default: return;
		}
	}

	/**
	 * Splits the bubble in two with a smaller size of each
	 */
	public void split() {
		// reduce size
		size--;
		setRadius(size*GAME_SIZE);
		if (size > 0) {
			// give upward speed
			vy = -ay * HIT_SPEED_FACTOR;
			// add an extra bubble to the game
            Bubble newBubble = new Bubble(size, x, y, -vx, vy);
			SlickApp.getController().addBubble(newBubble);
		} else {
		  SlickApp.getController().removeBubble(this);
		}
	}
	
	public boolean equals(Object other) {
		if(other instanceof Bubble) {
			Bubble that = (Bubble) other;
			return (this.size == that.size && this.x == that.x && this.y == that.y && this.vx == that.vx && this.vy == that.vy);
		}
		return false;
	}
}

package Group_24.BubbleTrouble;

import java.awt.Graphics2D;

/**
 * Object which can be held by the Model, and contains data about an object.
 *
 */
public abstract class Object {
	protected double x;
    protected double y;
    
    protected double width;
    protected double height;
    
    /**
     * Constructs a new Object at a position.
     * @param x should be an integer representing the horizontal position of the object.
     * @param y should be an integer representing the vertical position of the object.
     */
	public Object(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
     * Constructs a new Object at a position with a certain size.
     * @param x should be an integer representing the horizontal position of the object.
     * @param y should be an integer representing the vertical position of the object.
     * @param width should be an integer representing the width of the object.
     * @param height should be an integer representing the height of the object.
     */
	public Object(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the horizontal position of the object
	 * @return returns an integer representing the horizontal position of the object
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * Returns the vertical position of the object
	 * @return returns an integer representing the vertical position of the object
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * Returns the width of the object
	 * @return returns an integer representing the width of the object
	 */
	public int getWidth() {
		return (int) width;
	}
	
	/**
	 * Returns the height of the object
	 * @return returns an integer representing the height of the object
	 */
	public int getHeight() {
		return (int) height;
	}
	
	/**
	 * Returns whether this object collides with another Object that.
	 * @param that should be an Object of which the collision should be checked.
	 * @return returns a boolean representing whether this object collides with another Object that. 
	 */
	// TODO dit zou cleaner kunnen met bijvoorbeeld een bounding box
	public boolean collidesWith(Object that){
		boolean xCol = Math.abs(
				(that.getX() + .5 * that.getWidth()) - 
				(this.getX() + .5 * this.getWidth())
			) - .5 * that.getWidth() - .5 * this.getWidth() <= 0;
		boolean yCol = Math.abs(
				(that.getY() + .5 * that.getHeight()) - 
				(this.getY() + .5 * this.getHeight())
			) - .5 * that.getHeight() - .5 * this.getHeight() <= 0;
		
		return xCol && yCol;
	}
	
	/**
	 * Draws the object, should be implemented by the child classes.
	 * @param g should be the graphics object in which the Object is drawn.
	 * @param v should be the view in which the Object is drawn.
	 */
	public abstract void drawObject(Graphics2D g, View v);
}

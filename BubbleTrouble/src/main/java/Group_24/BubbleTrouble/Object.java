package Group_24.BubbleTrouble;

import java.awt.Graphics2D;

public abstract class Object {
	protected double x;
    protected double y;
    
    protected double width;
    protected double height;
    
	public Object(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Object(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return (int) width;
	}

	public int getHeight() {
		return (int) height;
	}
	
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

	public abstract void drawObject(Graphics2D g, Room r);
}

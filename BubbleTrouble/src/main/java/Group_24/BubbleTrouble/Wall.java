package Group_24.BubbleTrouble;

import java.awt.*;

/**
 * Created by hung.
 */
public class Wall extends Object {

    /**
     * Constructor for Wall class
     * @param x - x-coordinate of the top left position of the wall.
     * @param y - y-coordicate of the top left position of the wall.
     * @param width - The width of the wall.
     * @param height - The height of the wall.
     */
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Draw the wall with a rectangle.
     */
 	@Override
	public void drawObject(Graphics2D g, View v) {
		g.setColor(Color.GREEN);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}

package Group_24.BubbleTrouble;

import java.awt.*;

/**
 * Created by hung.
 */
public class Floor extends Object {

    public Floor(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


	/**
     * Draw the floor with a rectangle.
     */
 	@Override
	public void drawObject(Graphics2D g, View v) {
		g.setColor(Color.blue);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}

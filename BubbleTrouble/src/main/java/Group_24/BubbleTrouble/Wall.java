package Group_24.BubbleTrouble;

import org.newdawn.slick.geom.*;

/**
 * Created by hung.
 */
public class Wall extends Object {

    private Rectangle sprite;

    /**
     * Constructor for Wall class
     * @param x - x-coordinate of the top left position of the wall.
     * @param y - y-coordicate of the top left position of the wall.
     * @param width - The width of the wall.
     * @param height - The height of the wall.
     */
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        sprite = new Rectangle(x, y, width, height);
    }

    public Rectangle getSprite() {
        return sprite;
    }

}

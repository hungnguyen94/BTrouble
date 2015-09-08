package Group_24.BubbleTrouble;

import org.newdawn.slick.geom.*;

/**
 * Created by hung.
 */
public class Floor extends Object {

    private Rectangle sprite;

    public Floor(int x, int y, int width, int height) {
        super(x, y, width, height);
        sprite = new Rectangle(x, y, width, height);
    }

    public Rectangle getSprite() {
        return sprite;
    }

}

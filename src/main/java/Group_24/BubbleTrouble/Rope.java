package Group_24.BubbleTrouble;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("serial")
public class Rope extends Rectangle {
  private int dy;
  private static final int INITIAL_ROPESPEED = 5;
  private Image sprite;

  /**
   * Constructs a new rope, at the given position and with the standard initial
   * rope speed.
   * 
   * @param xpos
   *          should be a float, representing the horizontal position of the
   *          rope in the game.
   * @param ypos
   *          should be a float, representing the vertical position of the rope
   *          in the game.
   */
  public Rope(float xpos, float ypos) {
    super(xpos, ypos, 2f, 790f);
    this.dy = INITIAL_ROPESPEED;
    // sprite = new Image("Sprites/rope.png");
  }

  /**
   * Get the vertical speed of the rope.
   * 
   * @return returns an integer representing the vertical speed of the rope
   */
  public int getDY() {
    return dy;
  }

  /**
   * Draws the rope.
   * 
   * @throws SlickException
   *           when the image of the rope is not found
   */
  public void draw() throws SlickException {
    sprite = new Image("Sprites/rope.png");
    sprite.draw(x - (int) (sprite.getWidth() / 2), y);
  }

  /**
   * Moves the rope to it's next position.
   */
  public void move() {
    setY(getY() - 2 * dy);

    if (getY() <= 0) {
      dy = 0;
    }
  }
}

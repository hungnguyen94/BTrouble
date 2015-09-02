package Group_24.BubbleTrouble;

import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Player class, containing all the data about the player.
 *
 */
public class Player extends Sprite{
  private int dx;
  private final int PLAYER_SPEED = 5;

  private ArrayList<Rope> ropes;

  /**
   * Constructor for the Player class.
   * @param x x value for the Player from the sprite class.
   * @param y y value for the Player from the sprite class.
   */
  public Player(int x, int y){
    super(x, y);
    init("Sprites/Player.png");
  }

  @Override
  protected void init(String img) {
    loadImage(img);

    ropes = new ArrayList<Rope>();
    getImageDimensions();
  }

  public ArrayList<Rope> getRopes() {
    return ropes;
  }

  /**
   * Function which allows the Player to move.
   */
  public void move() {
    x += dx;
  }

  /**
   * Function which allows the player to fire.
   */
  public void fire() {
    int yvalue = this.getY();
    
    for (int i = 32; i < 321; i += 32) {
      ropes.add(new Rope((this.getX() + this.getWidth() / 2), yvalue));
      yvalue += 32;
    }
  }
  
  /**
   * Function which draws all the ropes from the ArrayList.
   * @param graphics Graphics2D element.
   * @param room The room to draw to.
   */
  public void drawRopes(Graphics2D graphics, Room room) {
    ArrayList<Rope> rs = this.getRopes();

    for (Object r1 : rs) {
      Rope rope = (Rope) r1;
      graphics.drawImage(rope.getImage(), rope.getX() - rope.getWidth() / 2,
          rope.getY(), room);
    }   
  }

  /**
   * This function handles the keyboard control.
   * @param e KeyEvent to handle the keyboard.
   */
  public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    switch (key) {
      case KeyEvent.VK_SPACE: fire(); break;
      case KeyEvent.VK_LEFT: dx = -1 * PLAYER_SPEED; break;
      case KeyEvent.VK_RIGHT: dx = 1 * PLAYER_SPEED; break;
      default: return;
    }
  }
  
  /**
   * This functions handles the release of keys.
   * @param e the KeyEvent to handle the keyboard.
   */
  public void keyReleased(KeyEvent e) {

    int key = e.getKeyCode();

    switch (key) {
      case KeyEvent.VK_LEFT: dx = 0; break;
      case KeyEvent.VK_RIGHT: dx = 0; break;
      default: return;
    }

  }
  
  /**
   * This functions deletes all the rope elements from the room.
   */
  public void resetRope() {
    ropes = new ArrayList<Rope>();
  }
}
package Group_24.BubbleTrouble;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Player class, containing all the data about the player.
 *
 */
public class Player extends Object {
    private int dx;
    private int lives;
    private int score;

    private SpriteSheet walkSheet;
    private Animation walkAnimation;
    private Image playerIdle;
    private float x = 100f;
    private float y = 350f;
    private boolean facingLeft = true;
    private boolean idle = true;
    
    private static final int PLAYER_SPEED = 5;
    private static final int INITIAL_LIVES = 2;
    private static final int INITIAL_SCORE = 0;

    private ArrayList<Rope> ropes;

    /**
     * Constructor for the Player class.
     * @param x x value for the Player from the sprite class.
     * @param y y value for the Player from the sprite class.
     */
    public Player(int x, int y) throws SlickException {
        super(x, y);
        playerIdle = new Image("Sprites/playerIdle.png");
        walkSheet = new SpriteSheet("Sprites/PlayerWalk.png", 102, 148);
        walkAnimation = new Animation(walkSheet, 150);
        ropes = new ArrayList<Rope>();
        lives = INITIAL_LIVES;
        score = INITIAL_SCORE;
    }

    public ArrayList<Rope> getRopes() {
        return ropes;
    }

    public void addLife(){
        lives ++;
    }

    public void loseLife(){
        lives --;
    }

    public boolean hasLives(){
        return lives >= 0;
    }

    public int getLives(){
        return lives;
    }

    public int getScore() {
        return score;
    }

    /**
     * @return the dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * @param dx the dx to set
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void increaseScore(int amount) {
        score += amount;
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

    public void draw() {
      if (!idle) {
        walkAnimation.getCurrentFrame().getFlippedCopy(facingLeft, false).draw(x, y);
    } else {
        playerIdle.getFlippedCopy(facingLeft, false).draw(x, y);
    }
    }

    /**
     * Function which draws all the ropes from the ArrayList.
     * @param graphics Graphics2D element.
     * @param view The room to draw to.
     */
    public void drawRopes() {
        ArrayList<Rope> rs = this.getRopes();

        for (Object r1 : rs) {
            Rope rope = (Rope) r1;
            //r1.draw();
        }
    }
    
    public void move(GameContainer container, int delta) {
      Input input = container.getInput();
      
//      for(Floor floor: Model.getCurrentRoom().getFloors()) {
//        if(!this.collidesWith(floor))
//            y += 1;
//      }
      
      if (input.isKeyDown(Input.KEY_LEFT))
      {
        idle = false;
        facingLeft = true;
        walkAnimation.update(delta);
          x -= delta * 0.1f;
      }
      else if (input.isKeyDown(Input.KEY_RIGHT))
      {
        idle = false;
        facingLeft = false;
        walkAnimation.update(delta);
          x += delta * 0.1f;
      } else {
        idle = true;
      }
    }

    /**
     * This functions deletes all the rope elements from the room.
     */
    public void resetRope() {
        ropes = new ArrayList<Rope>();
    }

    public void moveTo(int x) {
        this.x = x;
        this.dx = 0;
    }
}

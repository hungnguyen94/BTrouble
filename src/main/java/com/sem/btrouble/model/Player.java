package com.sem.btrouble.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Player class, containing all the data about the player.
 *
 */
@SuppressWarnings("serial")
public class Player extends Rectangle {
  private int lives;
  private int score;

  private SpriteSheet walkSheet;
  private Animation walkAnimation;
  private Image playerIdle;
  private boolean facingLeft = true;
  private boolean idle = true;

  private boolean leftBlocked;
  private boolean rightBlocked;
  private boolean alive;
  private boolean falling;

  // Gravity attributes
  private float vy;
  private float ay = .2f;

  private static final int PLAYER_SPEED = 3;
  private static final int INITIAL_LIVES = 5;
  private static final int INITIAL_SCORE = 0;

  private ArrayList<Rope> ropes;

  /**
   * Constructor for the Player class.
   *
   * @param xpos
   *          x value for the Player from the sprite class.
   * @param ypos
   *          y value for the Player from the sprite class.
   */
  public Player(float xpos, float ypos) {
    super(xpos, ypos, 60f, 175f);
    ropes = new ArrayList<Rope>();
    lives = INITIAL_LIVES;
    score = INITIAL_SCORE;
    vy = 2;
    rightBlocked = false;
    leftBlocked = false;
    alive = true;
    falling = true;
    try {
      playerIdle = new Image("Sprites/idle.png");
      walkSheet = new SpriteSheet("Sprites/player_spritesheet.png", 100, 175);
    } catch (SlickException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    walkAnimation = new Animation(walkSheet, 20);
  }

  /**
   * Checks whether the provided Object is the same as this Player.
   *
   * @param other
   *          should be the Object to be checked for equality.
   * @return returns a boolean representing whether the provided Object is the
   *         same as this Player.
   */
  public boolean equals(Object other) {
    if (other instanceof Player) {
      Player that = (Player) other;
      return (this.x == that.x && this.y == that.y && this.ropes.equals(that.ropes)
          && this.facingLeft == that.facingLeft && this.idle == that.idle
          && this.lives == that.lives && this.score == that.score && this.vy == that.vy
          && this.rightBlocked == that.rightBlocked && this.leftBlocked == that.leftBlocked);
    }
    return false;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public boolean isFalling() {
    return falling;
  }

  public void setFalling(boolean falling) {
    this.falling = falling;
  }

  public ArrayList<Rope> getRopes() {
    return ropes;
  }

  public void addLife() {
    lives++;
  }

  public void loseLife() {
    lives--;
  }

  public boolean hasLives() {
    return lives >= 0;
  }

  public int getLives() {
    return lives;
  }

  public int getScore() {
    return score;
  }

  public void increaseScore(int amount) {
    score += amount;
  }

  /**
   * Add a rope to the player
   */
  public void moveRopes() {
    for(Rope r: ropes) {
      r.move();
    }
  }

  /**
   * Remove collided ropes
   */
  public Collection<Shape> removeCollidedRopes() {
    LinkedHashSet<Shape> collidedRopes = new LinkedHashSet<Shape>();
    for(Rope r: ropes) {
      if(r.isCollided()) {
        collidedRopes.add(r);
      }
    }
    ropes.removeAll(collidedRopes);
    return collidedRopes;
  }

  /**
   * Function which allows the player to fire.
   * True if the rope succesfully fires
   * @param r - rope to be added
   */
  public boolean fire(Rope r) {
    if (ropes.size() <= 0) {
      ropes.add(r);
      return true;
    }
    return false;
  }

  /**
   * Draws the player on the screen.
   *
   * @throws SlickException
   *           when the player could not be drawn.
   */
  public void draw() throws SlickException {
    // Render the sprite at an offset.
    int playerX = (int) (x - ((walkSheet.getWidth() / walkSheet.getHorizontalCount()) - getWidth()) / 2);
    if (!idle) {
      walkAnimation.getCurrentFrame().getFlippedCopy(facingLeft, false).draw(playerX, y);
    } else {
      playerIdle.getFlippedCopy(facingLeft, false).draw(playerX, y);
    }
    for (int i = 0; i < ropes.size(); i++) {
      ropes.get(i).draw();
    }
  }

  /**
   * Moves the Player the provided amount of pixels to the right.
   *
   */
  public void move() {
    if(isFalling())
      fall();
    else
      vy = 0;

    idle = true;
  }

  /**
   * Move the player to the left
   * @param delta - speed
   */
  public void moveLeft(int delta) {
    if(!leftBlocked) {
      rightBlocked = false;
      leftBlocked = false;
      idle = false;
      facingLeft = true;
      walkAnimation.update(delta);
      x -= delta * 0.15f * PLAYER_SPEED;
    }
  }

  /**
   * Move the player to the right
   * @param delta - speed
   */
  public void moveRight(int delta) {
    if (!rightBlocked) {
      rightBlocked = false;
      leftBlocked = false;
      idle = false;
      facingLeft = false;
      walkAnimation.update(delta);
      x += delta * 0.15f * PLAYER_SPEED;
    }
  }


  public void setLeftBlocked(boolean leftBlocked) {
    this.leftBlocked = leftBlocked;
  }

  public void setRightBlocked(boolean rightBlocked) {
    this.rightBlocked = rightBlocked;
  }

  /**
   * This functions deletes all the rope elements from the room.
   */
  public void resetRope() {
    ropes.clear();
  }

  /**
   * Move the player to the specified coordinates.
   *
   * @param xpos
   *          - x-coordinate
   * @param ypos
   *          - y-coordinate
   */
  public void moveTo(int xpos, int ypos) {
    this.x = xpos;
    this.y = ypos;
    falling = true;
  }

  /**
   * Slowly fall down vertically
   */
  public void fall() {
    y += vy;
    vy += ay;
  }

}

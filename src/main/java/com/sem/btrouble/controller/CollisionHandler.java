package com.sem.btrouble.controller;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.tools.GameObservable;
import com.sem.btrouble.model.Rope;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class to handle collisions
 */
public class CollisionHandler extends GameObservable {

  private Collection<Shape> collidables;
  private final int sideLeft = 1;
  private final int sideRight = 2;
  private final int sideTop = 3;
  private final int sideBottom = 4;

  /**
   * Draw hitboxes of all objects in collidables
   * 
   * @param g
   *          - graphics handler from Slick2D
   */
  public void hitboxDraw(Graphics g) {
    for (Shape s : collidables) {
      g.setColor(Color.red);
      g.setLineWidth(2);
      g.draw(s);
      g.setLineWidth(1);
      // g.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
    }
  }

  /**
   * Use set to prevent duplicates.
   */
  public CollisionHandler() {
    collidables = new HashSet<Shape>();
  }

  /**
   * Add a collidable object to the list
   * 
   * @param c
   *          - collidable object
   */
  public void addCollidable(Shape c) {
    collidables.add(c);
  }

  /**
   * Add collection of collidable objects to the list
   * 
   * @param c
   *          - collection of collidable objects
   */
  public void addCollidable(Collection<? extends Shape> c) {
    collidables.addAll(c);
  }

  /**
   * Remove all collidable objects that are in c
   * 
   * @param c
   *          - collection of collidable objects
   */
  public void removeCollidable(Collection<? extends Shape> c) {
    collidables.removeAll(c);
  }

  /**
   * Remove a collidable object from the list
   * 
   * @param c
   *          - collidable object
   */
  public void removeCollidable(Shape c) {
    collidables.remove(c);
  }

    /**
     * Get size of list of collidable objects
     */
    public int getSize() {
        return collidables.size();
    }

  /**
   * Check if you collide with any object
   * 
   * @param self
   *          - object that is checking for collision
   */
  public boolean checkCollision(Shape self) {
      boolean collided = false;
      // Removes all null references. It's an hashset, so duplicates aren't
      // possible.
      collidables.remove(null);

      if (self == null)
          return collided;

        // Iterate over a shallow cloned set, since you can't change the set while iterating.
      HashSet<Shape> collidablesClone = new HashSet<Shape>(collidables);
      for(Shape collidee: collidablesClone) {
          if(self != collidee && self.intersects(collidee)) {
              collided = true;
              onCollide(self, collidee);
          }
      }
      return collided;
  }

  /**
   * Check collision for every shapes in the collection
   * 
   * @param colliders
   *          - collection of shapes
   * @return - true if collision
   */
  public boolean checkCollision(Collection<? extends Shape> colliders) {
    boolean collided = false;
    // Removes all null references. It's an hashset, so duplicates aren't
    // possible.
    collidables.remove(null);

    // Iterate over a shallow cloned set, since you can't change the set while
    // iterating.
    Collection<Shape> collidersClone = new HashSet<Shape>(colliders);
    for (Shape self : collidersClone) {
      if (checkCollision(self)) {
        collided = true;
      }
    }
    return collided;
  }

  /**
   * Actions on a collide
   * 
   * @param collider
   *          - the collider
   * @param collidee
   *          - the object being collided in
   */
  private void onCollide(Shape collider, Shape collidee) {
    if (collider instanceof Player) {
      playerCollide((Player) collider, collidee);
    }

    if (collider instanceof Bubble) {
      bubbleCollide((Bubble) collider, collidee);
    }

    if (collider instanceof Rope) {
      ropeCollide((Rope) collider, collidee);
    }
  }

  /**
   * Actions when the player collides with another shape
   * 
   * @param player
   *          - player that is colliding
   * @param collidee
   *          - shape the player collides with
   */
  private void playerCollide(Player player, Shape collidee) {
    if (collidee instanceof Bubble) {
      fireEvent(new PlayerEvent(player, PlayerEvent.COLLISION_BUBBLE, "Collided with bubble"));
      player.setAlive(false);
    }

    if (collidee instanceof Wall) {
      switch (checkSideX(player, collidee)) {
        case sideLeft:
          fireEvent(
              new PlayerEvent(player, PlayerEvent.COLLISION_RIGHTWALL, "Collided with right wall"));
          player.setRightBlocked(true);
          break;
        case sideRight:
          player.setLeftBlocked(true);
          fireEvent(
              new PlayerEvent(player, PlayerEvent.COLLISION_LEFTWALL, "Collided with left wall"));
          break;
      }
    }

    if (collidee instanceof Floor) {
      player.setFalling(false);
    }
  }

  /**
   * Actions when the bubble collides with another shape
   * 
   * @param bubble
   *          - bubble that is colliding
   * @param collidee
   *          - shape the bubble collides with
   */
  private void bubbleCollide(Bubble bubble, Shape collidee) {
    if (collidee instanceof Wall) {
      switch (checkSideX(bubble, collidee)) {
        case sideLeft:
          fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_WALL, "Collided with wall"));
          bubble.bounceX(true);
          break;
        case sideRight:
          fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_WALL, "Collided with wall"));
          bubble.bounceX(false);
          break;
      }
    }
    if (collidee instanceof Floor) {
      // If floor is under bounce up with constant speed, else bounce normally
      switch (checkSideY(bubble, collidee)) {
        case sideTop:
          fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_FLOOR, "Collided with floor"));
          bubble.bounceYFloor();
          break;
        case sideBottom:
          fireEvent(
              new BubbleEvent(bubble, BubbleEvent.COLLISION_CEILING, "Collided with ceiling"));
          bubble.bounceY(false);
          break;
      }
    }
    if (collidee instanceof Rope) {
      fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_ROPE, "Collided with rope"));
      Rope that = (Rope) collidee;
      bubble.split();
      that.setCollided(true);
    }
    if (collidee instanceof Bubble) {
      Bubble that = (Bubble) collidee;
      switch (checkSideX(bubble, collidee)) {
        case sideLeft:
          bubble.bounceX(true);
          that.bounceX(false);
          break;
        case sideRight:
          bubble.bounceX(false);
          that.bounceX(true);
          break;
      }
      switch (checkSideY(bubble, collidee)) {
        case sideTop:
          bubble.bounceY(true);
          that.bounceY(false);
          break;
        case sideBottom:
          bubble.bounceY(false);
          that.bounceY(true);
          break;
      }
    }
  }

  /**
   * Actions when the rope collides with another shape
   * 
   * @param rope
   *          - rope that is colliding
   * @param collidee
   *          - shape the rope collides with
   */
  private void ropeCollide(Rope rope, Shape collidee) {
    if (collidee instanceof Wall) {
      rope.setCollided(true);
    }

    if (collidee instanceof Floor) {
      rope.setCollided(true);
    }

    if (collidee instanceof Bubble) {
      rope.setCollided(true);
    }
  }

  /**
   * Return which side the collision occurs for X-axis
   * 
   * @param collider
   *          - mover
   * @param collidee
   *          - colliding shape
   * @return - integer representing the side
   */
  private int checkSideX(Shape collider, Shape collidee) {
    // Collide on right side
    if (collider.getCenterX() > collidee.getCenterX()) {
      return sideRight;
    }
    // Collide on left side
    if (collider.getCenterX() < collidee.getCenterX()) {
      return sideLeft;
    }
    return 0;
  }

  /**
   * Return which side the collision occurs for Y-axis
   * 
   * @param collider
   *          - mover
   * @param collidee
   *          - colliding shape
   * @return - integer representing the side
   */
  private int checkSideY(Shape collider, Shape collidee) {
    // Collide on top side
    if (collider.getCenterY() < collidee.getCenterY()) {
      return sideTop;
    }
    // Collide on bottom side
    if (collider.getCenterY() > collidee.getCenterY()) {
      return sideBottom;
    }
    return 0;
  }
}

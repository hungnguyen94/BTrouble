package com.sem.btrouble.controller;

import com.sem.btrouble.tools.GameObservable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class to handle collisions.
 */
public class CollisionHandler extends GameObservable {

    private Collection<Collidable> collidables;

    /**
     * Draw hitboxes of all objects in collidables.
     *
     * @param g
     *            - graphics handler from Slick2D
     */
    public void hitboxDraw(Graphics g) {
        for (Collidable s : collidables) {
            Shape shape = (Shape) s;
            g.setColor(Color.red);
            g.setLineWidth(2);
            g.draw(shape);
            g.setLineWidth(1);
            // g.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
        }
    }

    /**
     * Use set to prevent duplicates.
     */
    public CollisionHandler() {
        collidables = new HashSet<Collidable>();
    }

    /**
     * Add a collidable object to the list.
     *
     * @param c
     *            - collidable object
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Add collection of collidable objects to the list.
     *
     * @param c
     *            - collection of collidable objects
     */
    public void addCollidable(Collection<? extends Collidable> c) {
        collidables.addAll(c);
    }

    /**
     * Remove all collidable objects that are in c.
     *
     * @param c
     *            - collection of collidable objects
     */
    public void removeCollidable(Collection<? extends Collidable> c) {
        collidables.removeAll(c);
    }

    /**
     * Remove a collidable object from the list.
     *
     * @param c
     *            - collidable object
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Get size of list of collidable objects.
     * 
     * @return - The number of colliable objects
     */
    public int getSize() {
        return collidables.size();
    }

    /**
     * Check if you collide with any object.
     *
     * @param self
     *            - object that is checking for collision
     * @return - true if Collidable has collided
     */
    public boolean checkCollision(Collidable self) {
        boolean collided = false;
        // Removes all null references. It's an hashset, so duplicates aren't
        // possible.
        collidables.remove(null);

        if (self == null) {
            return false;
        }

        // Iterate over a shallow cloned set, since you can't change the set
        // while iterating.
        HashSet<Collidable> collidablesClone = new HashSet<Collidable>(collidables);
        for (Collidable collidee : collidablesClone) {
            if (self != collidee && self.intersectsCollidable(collidee)) {
                // If there is no corresponding CollisionAction for this collision, skip it.
                CollisionAction selfAction = self.getCollideActions().get(collidee.getClass());
                CollisionAction collideeAction = collidee.getCollideActions().get(self.getClass());
                if(selfAction != null) {
                    selfAction.onCollision(collidee);
                }
                if(collideeAction != null) {
                    collideeAction.onCollision(self);
                }
                collided = (selfAction != null || collideeAction != null);
            }
        }
        return collided;
    }

    /**
     * Checks collisions for all objects in the collisionhandler.
     */
    public void checkAllCollisions() {
        checkCollision(collidables);
    }

    /**
     * Check collision for every Collidables in the collection.
     *
     * @param colliders
     *            - collection of Collidables
     * @return - true if collision
     */
    public boolean checkCollision(Collection<? extends Collidable> colliders) {
        boolean collided = false;
        // Removes all null references. It's an hashset, so duplicates aren't
        // possible.
        collidables.remove(null);

        // Iterate over a shallow cloned set, since you can't change the set
        // while
        // iterating.
        Collection<Collidable> collidersClone = new HashSet<Collidable>(colliders);
        for (Collidable self : collidersClone) {
            if (checkCollision(self)) {
                collided = true;
            }
        }
        return collided;
    }

    /**
     * Return which side the collision occurs for X-axis.
     * @param c1 collider object.
     * @param c2 collidee object.
     * @return CollisionSide enum representing the side
     */
    public static CollisionSide checkCollisionSideX(Collidable c1, Collidable c2) {
        // Collide on right side
        if (c1.getCenterX() > c2.getCenterX()) {
            return CollisionSide.RIGHT;
        }
        // Collide on left side
        if (c1.getCenterX() < c2.getCenterX()) {
            return CollisionSide.LEFT;
        }
        return CollisionSide.NONE;
    }

    /**
     * Return which side the collision occurs for Y-axis.
     * @param c1 collider object.
     * @param c2 collidee object.
     * @return CollisionSide enum representing the side
     */
    public static CollisionSide checkCollisionSideY(Collidable c1, Collidable c2) {
        // Collide on top side
        if (c1.getCenterY() < c2.getCenterY()) {
            return CollisionSide.TOP;
        }
        // Collide on bottom side
        if (c1.getCenterY() > c2.getCenterY()) {
            return CollisionSide.BOTTOM;
        }
        return CollisionSide.NONE;
    }
}

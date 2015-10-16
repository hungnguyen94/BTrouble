package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Class to handle collisions.
 */
public class CollisionHandler implements Drawable {

    private Collection<Collidable> collidables;

    /**
     * Use set to prevent duplicates.
     */
    public CollisionHandler() {
        collidables = new CopyOnWriteArraySet<Collidable>();
    }

    /**
     * Add a collidable object to the list.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Add collection of collidable objects to the list.
     *
     * @param c collection of collidable objects
     */
    public void addCollidable(Collection<? extends Collidable> c) {
        collidables.addAll(c);
    }

    /**
     * Remove all collidable objects that are in c.
     *
     * @param c collection of collidable objects
     */
    public void removeCollidable(Collection<? extends Collidable> c) {
        collidables.removeAll(c);
    }

    /**
     * Remove a collidable object from the list.
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Get size of list of collidable objects.
     *
     * @return The number of colliable objects
     */
    public int getSize() {
        return collidables.size();
    }

    /**
     * Check if you collide with any object.
     *
     * @param self object that is checking for collision.
     * @return true if Collidable has collided.
     */
    public boolean checkCollision(Collidable self) {
        boolean collided = false;
        // Removes all null references. It's an set, so duplicates aren't
        // possible.
        collidables.remove(null);

        if (self == null) {
            return false;
        }

        for (Collidable collidee : collidables) {
//            if (self != collidee && collisionCheckAABB(self, collidee)) {
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
                collided = true;
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
     * @param colliders collection of Collidables.
     * @return true if collision.
     */
    public boolean checkCollision(Collection<? extends Collidable> colliders) {
        boolean collided = false;
        // Removes all null references. It's an set, so duplicates aren't
        // possible.
        collidables.remove(null);

        // Iterate over a shallow cloned set, since you can't change the set
        // while iterating.
        Collection<Collidable> collidersClone = new CopyOnWriteArraySet<Collidable>(colliders);
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

    /**
     * Use Axis-Aligned Bounding Box collision detection.
     * @param c1 collidable to check collision with c2.
     * @param c2 collidable to check collision with c1.
     * @return True if they collided.
     */
    private boolean collisionCheckAABB(Collidable c1, Collidable c2) {
        float c1minX = c1.getX();
        float c1maxX = c1.getX() + c1.getWidth();
        float c2minX = c2.getX();
        float c2maxX = c2.getX() + c2.getWidth();

        float c1minY = c1.getY();
        float c1maxY = c1.getY() + c1.getHeight();
        float c2minY = c2.getY();
        float c2maxY = c2.getY() + c2.getHeight();

        return c1maxX > c2minX
                && c1minX < c2maxX
                && c1maxY > c2minY
                && c1minY < c2maxY;
    }

    /**
     * Draw the object.
     *
     * @param graphics Graphics handler
     */
    @Override
    public void draw(Graphics graphics) {

        for(Collidable collidable : collidables) {
            graphics.setColor(Color.red);
            graphics.drawRect(collidable.getX(), collidable.getY(),
//                    Math.abs(collidable.getCenterX() - collidable.getX()),
//                    Math.abs(collidable.getCenterY() - collidable.gCenteretY())
                    collidable.getWidth(), collidable.getHeight()
            );
            graphics.setColor(Color.green);
            graphics.drawRect(collidable.getCenterX()+1 - (collidable.getWidth()/2),
                    collidable.getCenterY()+1 - (collidable.getWidth()/2),
                    collidable.getWidth(), collidable.getHeight()
            );
        }
    }
}

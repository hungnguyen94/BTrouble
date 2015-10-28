package com.sem.btrouble.controller;

import com.sem.btrouble.model.Drawable;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Class to handle collisions.
 */
public class CollisionHandler implements Drawable, Controller {

    private Collection<Collidable> collidables;
    private Collection<Collection<? extends Collidable>> collidableListsReference;

    /**
     * Use set to prevent duplicates.
     */
    public CollisionHandler() {
        collidables = new CopyOnWriteArraySet<Collidable>();
        collidableListsReference = new ArrayList<>();
    }

    /**
     * Adds a reference to a list.
     * These lists are used to check for collisions. A reference to
     * the list itself is used, instead of a (shallow) copy. Changes in a list
     * will automatically be reflected in the implementing class.
     * @param collidableCollection Collection of collidables.
     */
    public void addListReference(Collection<? extends Collidable> collidableCollection) {
        collidableListsReference.add(collidableCollection);
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
        collidables.remove(null);

        if (self == null) {
            return false;
        }

        for (Collidable collidee : collidables) {
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
        for(Collection<? extends Collidable> collidableList : collidableListsReference) {
            for(Collidable collidable : collidableList) {
                if(collidable.getCollidedStatus()) {
                    collidableList.remove(collidable);
                    collidables.remove(collidable);
                }
            }
            addCollidable(collidableList);
        }
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
     * Draw the object.
     *
     * @param graphics Graphics handler
     */
    @Override
    public void draw(Graphics graphics) {

//        for(Collidable collidable : collidables) {
//            graphics.setColor(Color.red);
//            graphics.drawRect(collidable.getX(), collidable.getY(),
////                    Math.abs(collidable.getCenterX() - collidable.getX()),
////                    Math.abs(collidable.getCenterY() - collidable.gCenteretY())
//                    collidable.getWidth(), collidable.getHeight()
//            );
//            graphics.setColor(Color.green);
//            graphics.drawRect(collidable.getCenterX()+1 - (collidable.getWidth()/2),
//                    collidable.getCenterY()+1 - (collidable.getHeight()/2),
//                    collidable.getWidth(), collidable.getHeight()
//            );
//        }
    }

    /**
     * Update method for the Controller.
     */
    @Override
    public void update() {
        checkAllCollisions();
    }
}

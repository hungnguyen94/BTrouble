package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.model.Rope;
import org.newdawn.slick.geom.Shape;

import java.util.Collection;
import java.util.HashSet;


/**
 * Class to handle collisions
 */
public class CollisionHandler {

    private Collection<Shape> collidables;

    /**
     * Use set to prevent duplicates.
     */
    public CollisionHandler() {
        collidables = new HashSet<Shape>();
    }

    /**
     * Add a collidable object to the list
     * @param c - collidable object
     */
    public void addCollidable(Shape c) {
        collidables.add(c);
    }

    /**
     * Add collidable objects to the list
     * @param c - list of collidable objects
     */
    public void addCollidable(Collection<Shape> c) {
        collidables.addAll(c);
    }

    /**
     * Remove all collidable objects that are in c
     * @param c - list of collidable objects
     */
    public void removeCollidable(Collection<Shape> c) {
        collidables.removeAll(c);
    }

    /**
     * Remove a collidable object from the list
     * @param c - collidable object
     */
    public void removeCollidable(Shape c) {
        collidables.remove(c);
    }



    /**
     * Check if you collide with any object
     * @param self - object that is checking for collision
     */
    public boolean checkCollision(Shape self) {
        boolean collided = false;
        collidables.remove(null);

        if(self == null)
            return collided;

        // Iterate over a shallow cloned set, since you can't change the set while iterating.
        HashSet<Shape> collidablesClone = new HashSet<Shape>(collidables);
        for(Shape collidee: collidablesClone) {
            if(self.intersects(collidee) && self != collidee) {
                collided = true;
                onCollide(self, collidee);
            }
        }
        return collided;
    }

    /**
     * Actions on a collide
     * @param collider - the collider
     * @param collidee - the object being collided in
     */
    private void onCollide(Shape collider, Shape collidee) {
        if(collider instanceof Player) {
            playerCollide((Player) collider, collidee);
        }

        if(collider instanceof Bubble) {
            bubbleCollide((Bubble) collider, collidee);
        }

        if(collider instanceof Rope) {
            ropeCollide((Rope) collider, collidee);
        }
    }

    /**
     * Actions when the player collides with another shape
     * @param player - player that is colliding
     * @param collidee - shape the player collides with
     */
    private void playerCollide(Player player, Shape collidee) {
        if(collidee instanceof Bubble) {
            player.setAlive(false);
        }

        if(collidee instanceof Wall) {
            Wall that = (Wall) collidee;
            int playerX = (int) player.getX() + ((int) (player.getWidth() / 2));
            if (playerX > that.getX()) {
                player.setLeftBlocked(true);
            } else if (playerX <= that.getX()) {
                player.setRightBlocked(true);
            }
        }

        if(collidee instanceof Floor) {
            player.setFalling(false);
        }
    }

    /**
     * Actions when the bubble collides with another shape
     * @param bubble - bubble that is colliding
     * @param collidee - shape the bubble collides with
     */
    private void bubbleCollide(Bubble bubble, Shape collidee) {
        if(collidee instanceof Wall) {
            bubble.bounceX();
        }
        if(collidee instanceof Floor) {
            bubble.bounceY();
        }
        if(collidee instanceof Rope) {
            Rope that = (Rope) collidee;
            bubble.split();
        }
    }

    /**
     * Actions when the rope collides with another shape
     * @param rope - rope that is colliding
     * @param collidee - shape the rope collides with
     */
    private void ropeCollide(Rope rope, Shape collidee) {
        if(collidee instanceof Wall) {
            Wall that = (Wall) collidee;
            rope.setCollided(true);
        }
        if(collidee instanceof Bubble) {
            Bubble that = (Bubble) collidee;
            rope.setCollided(true);
        }
    }

}

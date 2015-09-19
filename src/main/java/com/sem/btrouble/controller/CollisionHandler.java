package com.sem.btrouble.controller;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.model.Rope;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * Class to handle collisions
 */
public class CollisionHandler {

    private Collection<Shape> collidables;

    public Collection<Shape> getCollidables() {
        return collidables;
    }

    public void hitboxDraw(Graphics g) {
        for(Shape s: collidables) {
            g.setColor(Color.red);
            g.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
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
     * @param c - collidable object
     */
    public void addCollidable(Shape c) {
        collidables.add(c);
    }

    /**
     * Add collection of collidable objects to the list
     * @param c - collection of collidable objects
     */
    public void addCollidable(Collection<? extends Shape> c) {
        collidables.addAll(c);
    }

    /**
     * Remove all collidable objects that are in c
     * @param c - collection of collidable objects
     */
    public void removeCollidable(Collection<? extends Shape> c) {
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
     * Check collision for every shapes in the collection
     * @param colliders - collection of shapes
     * @return - true if collision
     */
    public boolean checkCollision(Collection<? extends Shape> colliders) {
        boolean collided = false;
        collidables.remove(null);

        // Iterate over a shallow cloned set, since you can't change the set while iterating.
        Collection<Shape> collidersClone = new HashSet<Shape>(colliders);
        for(Shape self: collidersClone) {
            if(checkCollision(self)) {
                collided = true;
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
            bubbleCollide((Bubble)collider, collidee);
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
            that.setCollided(true);
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

        if(collidee instanceof Floor) {
            Floor that = (Floor) collidee;
            rope.setCollided(true);
        }

        if(collidee instanceof Bubble) {
            Bubble that = (Bubble) collidee;
            rope.setCollided(true);
        }
    }
}

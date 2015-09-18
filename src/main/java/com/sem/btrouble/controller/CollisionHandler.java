package com.sem.btrouble.controller;

import com.sem.btrouble.model.*;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle collisions
 */
public class CollisionHandler {

    private List<Shape> collidables;

    public CollisionHandler() {
        collidables = new ArrayList<Shape>();
    }

    /**
     * Add a collidable object to the list
     * @param c - collidable object
     */
    public void addCollidable(Shape c) {
        collidables.add(c);
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
    public void checkCollision(Shape self) {
        for(Shape collidee: collidables) {
            if(self.intersects(collidee) && self != collidee) {
                onCollide(self, collidee);
            }
        }
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
            player.loseLife();
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
            Floor that = (Floor) collidee;
            player.stopFalling();
        } else {
            player.fall();
        }
    }

    /**
     * Actions when the bubble collides with another shape
     * @param bubble - bubble that is colliding
     * @param collidee - shape the bubble collides with
     */
    private void bubbleCollide(Bubble bubble, Shape collidee) {
        if(collidee instanceof Wall) {
            Wall that = (Wall) collidee;
            bubble.bounceX();
        }
        if(collidee instanceof Floor) {
            Floor that = (Floor) collidee;
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
        }
        if(collidee instanceof Bubble) {
            Bubble that = (Bubble) collidee;
        }
    }

}

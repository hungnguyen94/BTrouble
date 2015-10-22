package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a rope.
 * @author Martin
 *
 */
public class StayRope extends Rope {

    private boolean moveFlag;

    /**
     * Constructs a new rope at the given position.
     *
     * @param xpos  should be a float representing the horizontal position of the
     *              rope.
     * @param ypos  should be a float representing the vertical position of the
     *              rope.
     * @param player Owner of the rope.
     */
    public StayRope(float xpos, float ypos, Player player) {
        super(xpos, ypos, player);
        this.moveFlag = true;
    }

    /**
     * Calculates the next position of the Rope.
     */
    @Override
    public void move() {
        if (moveFlag) {
            super.move();
        }
    }

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        Map<Class<? extends Collidable>, CollisionAction> collisionActionMap 
            = new HashMap<Class<? extends Collidable>, CollisionAction>();

        // Method called on Floor collision.
        collisionActionMap.put(Floor.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                StayRope.this.moveFlag = false;
            }
        });

        // Method called on Wall collision.
        collisionActionMap.put(Wall.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                StayRope.this.moveFlag = false;
            }
        });

        // Method called on Bubble collision.
        collisionActionMap.put(Bubble.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                setCollided(true);
                StayRope.this.moveFlag = false;
            }
        });

        return collisionActionMap;
    }
}
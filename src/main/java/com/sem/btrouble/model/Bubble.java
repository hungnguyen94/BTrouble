package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import com.sem.btrouble.controller.CollisionHandler;
import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.view.GameView;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Bubble is a model, which represents the bubbles in the game.
 *
 */
@SuppressWarnings("serial")
public class Bubble extends Circle implements Drawable, Collidable {
    private int size;

    // actual size of a level one bubble in the game in pixels.
    private static final float GAME_SIZE = 10f;

    // speed (pixels / step)
    private float vx;
    private float vy;

    // acceleration (pixels / step^2)
    private float ay;

    // gravity
    private static final float GRAVITY = .4f;
    // starting speed in horizontal direction
    private static final float INITIAL_HORIZONTAL_SPEED = 3f;
    // factor of acceleration that the bubbles go up with when hit with a rope
    private static final int HIT_SPEED_FACTOR = 30;
    //
    private static final int BUBBLE_SCORE = 1000;

    /**
     * Bubble class, containing all the data about the bubble.
     * 
     * @param size
     *            of the bubble.
     * @param xpos
     *            horizontal starting position of the bubble in the room
     * @param ypos
     *            vertical starting position of the bubble in the room
     */
    public Bubble(int size, float xpos, float ypos) {
        super(xpos, ypos, size * GAME_SIZE);

        this.size = size;
        this.ay = GRAVITY;
        this.vx = INITIAL_HORIZONTAL_SPEED;
    }

    /**
     * Bubble constructor with the ability to give initial speed as a variable.
     * 
     * @param size
     *            of the bubble.
     * @param xpos
     *            horizontal starting position of the bubble in the room
     * @param ypos
     *            vertical starting position of the bubble in the room
     * @param vx
     *            horizontal starting speed of the bubble
     * @param vy
     *            vertical starting speed of the bubble
     */
    public Bubble(int size, float xpos, float ypos, float vx, float vy) {
        super(xpos, ypos, size * GAME_SIZE);

        this.size = size;
        this.ay = GRAVITY;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Returns the size of the bubble in steps.
     * 
     * @return returns an integer representing the size of the bubble in steps.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the velocity of the bubble in x direction.
     * 
     * @return returns a double representing the velocity of the bubble in x
     *         direction.
     */
    public double getVx() {
        return vx;
    }

    /**
     * Returns the velocity of the bubble in y direction.
     * 
     * @return returns a double representing the velocity of the bubble in y
     *         direction.
     */
    public double getVy() {
        return vy;
    }

    /**
     * Sets the acceleration of the bubble in y direction.
     * 
     * @param ay
     *            should be an integer representing the acceleration of the
     *            bubble in y direction.
     */
    public void setAY(float ay) {
        this.ay = ay;
    }

    /**
     * Returns the acceleration of the bubble in y direction.
     * 
     * @return returns a double representing the acceleration of the bubble in y
     *         direction.
     */
    public float getAY() {
        return ay;
    }

    /**
     * Calculates the next location of the Bubble.
     */
    public void move() {
        this.vy += ay;

        float newX = getCenterX() + vx;
        float newY = getCenterY() + vy;

        setCenterX(newX);
        setCenterY(newY);
    }

    /**
     * Should be called when a Bubble collides.
     * 
     * @param event
     *            should be a BubbleEvent representing an event in the game.
     */
    public void bubbleEvent(BubbleEvent event) {
        switch (event.getId()) {
        case BubbleEvent.COLLISION_FLOOR:
            vy = -vy;
            break;
        case BubbleEvent.COLLISION_WALL:
            vx = -vx;
            break;
        case BubbleEvent.COLLISION_ROPE:
            split();
            break;
        default:
            return;
        }
    }

    /**
     * Splits the bubble in two with a smaller size of each.
     */
    public void split() {
        // reduce size
        size--;
        setRadius(size * GAME_SIZE);
        if (size > 0) {
            // give upward speed
            vy = -Math.abs(ay) * HIT_SPEED_FACTOR;
            vx = Math.abs(vx);
            // add an extra bubble to the game
            Bubble leftBubble = new Bubble(size, x, y, -vx, vy);
            Bubble rightBubble = new Bubble(size, x, y, vx, vy);
            GameView.getController().addBubble(leftBubble);
            GameView.getController().addBubble(rightBubble);
            GameView.getController().removeBubble(this);
            PowerUp power = PowerUpGenerator.generate(x, y);
            if(power != null) {
            	Model.addShortPowerUp(power);
            }
        } else {
            GameView.getController().removeBubble(this);
        }
    }

    /**
     * Checks whether the provided Object is the same as this Bubble.
     * 
     * @param other
     *            should be the Object to be checked for equality.
     * @return returns a boolean representing whether the provided Object is the
     *         same as this Bubble.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Bubble) {
            Bubble that = (Bubble) other;
            return this.size == that.size && Math.abs(this.x - that.x) == 0 
                    && Math.abs(this.y - that.y) == 0
                    && Math.abs(this.vx - that.vx) == 0 && Math.abs(this.vy - that.vy) == 0;
        }
        return false;
    }

    private final int arbitraryconstant = 42;

    @Override
    public int hashCode() {
        assert false : "hashcode not designed";
        return arbitraryconstant;
    }

    /**
     * Invert the y direction on collision.
     */
    public void bounceY() {
        vy = -vy;
    }

    /**
     * Invert the x direction on collision.
     */
    public void bounceX() {
        vx = -vx;
    }

    /**
     * Bounce to left or right direction on collision.
     * 
     * @param left
     *            - bounce to the left
     */
    public void bounceX(boolean left) {
        if (left) {
            vx = -Math.abs(vx);
        } else {
            vx = Math.abs(vx);
        }
    }

    /**
     * Bounce to up or down direction on collision.
     * 
     * @param up
     *            - bounce up
     */
    public void bounceY(boolean up) {
        if (up) {
            vy = -Math.abs(vy);
        } else {
            vy = Math.abs(vy);
        }
    }

    private final int bounceconstant = 11;

    /**
     * Bounce up on collision with floor.
     */
    public void bounceYFloor() {
        vy = -Math.abs(bounceconstant + 2 * (size));
    }

    @Override
    public String toString() {
        return "Bubble{" + "size=" + size + ", x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy
                + ", ay=" + ay + '}';
    }

    /**
     * Draws the object.
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fill(this);
        graphics.draw(this);
    }

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        Map<Class<? extends Collidable>, CollisionAction> collisionActionMap =
                new HashMap<Class<? extends Collidable>, CollisionAction>();

        // Method called on Wall collision
        collisionActionMap.put(Wall.class, new WallCollision());

        // Method called on Floor collision
        collisionActionMap.put(Floor.class, new FloorCollision());

        // Method called on Bubble collision
        collisionActionMap.put(Bubble.class, new BubbleCollision());

        // Method called on Rope collision
        collisionActionMap.put(Rope.class, new RopeCollision());
        return collisionActionMap;
    }

    /**
     * Class to method on collision with Wall.
     */
    private class WallCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable wall) {
            switch(CollisionHandler.checkCollisionSideX(Bubble.this, wall)) {
                case LEFT:
                    bounceX(true);
                    break;
                case RIGHT:
                    bounceX(false);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Class to call method on collision with Floor.
     */
    private class FloorCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable floor) {
            switch(CollisionHandler.checkCollisionSideY(Bubble.this, floor)) {
                case TOP:
                    bounceYFloor();
                    break;
                case BOTTOM:
                    bounceY();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Class to call method on collision with bubble.
     */
    private class BubbleCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable b) {
            Bubble bubble = (Bubble) b;
            switch(CollisionHandler.checkCollisionSideX(Bubble.this, bubble)) {
                case LEFT:
                    Bubble.this.bounceX(true);
                    bubble.bounceX(false);
                    break;
                case RIGHT:
                    Bubble.this.bounceX(false);
                    bubble.bounceX(true);
                    break;
                default:
                    break;
            }
            switch(CollisionHandler.checkCollisionSideY(Bubble.this, bubble)) {
                case TOP:
                    Bubble.this.bounceY(true);
                    bubble.bounceY(false);
                    break;
                case BOTTOM:
                    Bubble.this.bounceY(false);
                    bubble.bounceY(true);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Class to call method on collision with rope.
     */
    private class RopeCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            split();
            Rope rope = (Rope) collider;
            rope.setCollided(true);
            GameView.getWallet().increaseValue(BUBBLE_SCORE);
        }
    }

    /**
     * Checks for intersection with another Collidable.
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return intersects((Shape) collidable);
    }
}

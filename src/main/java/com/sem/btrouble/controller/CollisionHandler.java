package com.sem.btrouble.controller;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.LifePowerUp;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.PowerUp;
import com.sem.btrouble.model.TimePowerUp;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.tools.GameObservable;
import com.sem.btrouble.model.Rope;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class to handle collisions.
 */
public class CollisionHandler extends GameObservable {

    private Collection<Shape> collidables;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_TOP = 3;
    private static final int SIDE_BOTTOM = 4;

    /**
     * Draw hitboxes of all objects in collidables.
     *
     * @param g
     *            - graphics handler from Slick2D
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
     * Add a collidable object to the list.
     *
     * @param c
     *            - collidable object
     */
    public void addCollidable(Shape c) {
        collidables.add(c);
    }

    /**
     * Add collection of collidable objects to the list.
     *
     * @param c
     *            - collection of collidable objects
     */
    public void addCollidable(Collection<? extends Shape> c) {
        collidables.addAll(c);
    }

    /**
     * Remove all collidable objects that are in c.
     *
     * @param c
     *            - collection of collidable objects
     */
    public void removeCollidable(Collection<? extends Shape> c) {
        collidables.removeAll(c);
    }

    /**
     * Remove a collidable object from the list.
     *
     * @param c
     *            - collidable object
     */
    public void removeCollidable(Shape c) {
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
     * @return - true if shape has collided
     */
    public boolean checkCollision(Shape self) {
        boolean collided = false;
        // Removes all null references. It's an hashset, so duplicates aren't
        // possible.
        collidables.remove(null);

        if (self == null) {
            return false;
        }

        // Iterate over a shallow cloned set, since you can't change the set
        // while iterating.
        HashSet<Shape> collidablesClone = new HashSet<Shape>(collidables);
        for (Shape collidee : collidablesClone) {
            if (self != collidee && self.intersects(collidee)) {
                collided = true;
                onCollide(self, collidee);
            }
        }
        return collided;
    }

    /**
     * Check collision for every shapes in the collection.
     *
     * @param colliders
     *            - collection of shapes
     * @return - true if collision
     */
    public boolean checkCollision(Collection<? extends Shape> colliders) {
        boolean collided = false;
        // Removes all null references. It's an hashset, so duplicates aren't
        // possible.
        collidables.remove(null);

        // Iterate over a shallow cloned set, since you can't change the set
        // while
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
     * Actions on a collide.
     *
     * @param collider
     *            - the collider
     * @param collidee
     *            - the object being collided in
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

        if (collider instanceof Wall) {
            wallCollide((Wall) collider, collidee);
        }
        
        if (collider instanceof PowerUp) {
            powerCollide((PowerUp) collider, collidee);
        }
    }
    
    private void powerCollide(PowerUp power, Shape collidee) {
    	if (collidee instanceof Floor) {
    		power.setFalling(false);
    		power.setY(collidee.getY() - power.getHeight());
    	}
    	
        if (collidee instanceof Player) {
        	ArrayList<Player> players = Model.getPlayers();
        	Player player = null;
        	for(Player otherPlayer: players) {
        		if(collidee.equals(otherPlayer)) {
        			player = (Player) collidee;
        		}
        	}
        	if(!(power instanceof LifePowerUp && player.getLives() == 5)
        			&& !(power instanceof TimePowerUp)){
        		power.activate();
        	}
        	if(power instanceof TimePowerUp) {
        		TimePowerUp timePower = (TimePowerUp) power;
        		timePower.activateShort();
        	}
        	Model.deleteShortPower(power);
        	System.out.println(Model.getShortPower());
        }
    }

    /**
     * Actions when the wall collides with another shape.
     *
     * @param wall
     *            - wall that is colliding
     * @param collidee
     *            - shape the wall collides with
     */
    private void wallCollide(Wall wall, Shape collidee) {
        if (collidee instanceof Wall) {
            wall.changeDirection();
            Wall that = (Wall) collidee;
            that.changeDirection();
        }
    }

    /**
     * Actions when the player collides with another shape.
     *
     * @param player
     *            - player that is colliding
     * @param collidee
     *            - shape the player collides with
     */
    private void playerCollide(Player player, Shape collidee) {
        if (collidee instanceof Bubble) {
            fireEvent(
                    new PlayerEvent(player, PlayerEvent.COLLISION_BUBBLE, "Collided with bubble"));
            player.setAlive(false);
        }

        if (collidee instanceof Wall) {
            switch (checkSideX(player, collidee)) {
            case SIDE_LEFT:
                fireEvent(new PlayerEvent(player, PlayerEvent.COLLISION_RIGHTWALL,
                        "Collided with right wall"));
                player.setRightBlock(true);
                break;
            case SIDE_RIGHT:
                player.setLeftBlock(true);
                fireEvent(new PlayerEvent(player, PlayerEvent.COLLISION_LEFTWALL,
                        "Collided with left wall"));
                break;
            default:
                break;
            }
        }

        if (collidee instanceof Floor) {
            player.setFalling(false);
            player.setY(collidee.getY() - player.getHeight());
        }
        

    }

    /**
     * Actions when the bubble collides with another shape.
     *
     * @param bubble
     *            - bubble that is colliding
     * @param collidee
     *            - shape the bubble collides with
     */
    private void bubbleCollide(Bubble bubble, Shape collidee) {
        if (collidee instanceof Wall) {
            switch (checkSideX(bubble, collidee)) {
            case SIDE_LEFT:
                fireEvent(
                        new BubbleEvent(bubble, BubbleEvent.COLLISION_WALL, "Collided with wall"));
                bubble.bounceX(true);
                break;
            case SIDE_RIGHT:
                fireEvent(
                        new BubbleEvent(bubble, BubbleEvent.COLLISION_WALL, "Collided with wall"));
                bubble.bounceX(false);
                break;
            default:
                break;
            }
        }
        if (collidee instanceof Floor) {
            // If floor is under bounce up with constant speed, else bounce
            // normally
            switch (checkSideY(bubble, collidee)) {
            case SIDE_TOP:
                fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_FLOOR,
                        "Collided with floor"));
                bubble.bounceYFloor();
                break;
            case SIDE_BOTTOM:
                fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_CEILING,
                        "Collided with ceiling"));
                bubble.bounceY(false);
                break;
            default:
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
            case SIDE_LEFT:
                bubble.bounceX(true);
                that.bounceX(false);
                break;
            case SIDE_RIGHT:
                bubble.bounceX(false);
                that.bounceX(true);
                break;
            default:
                break;
            }
            switch (checkSideY(bubble, collidee)) {
            case SIDE_TOP:
                bubble.bounceY(true);
                that.bounceY(false);
                break;
            case SIDE_BOTTOM:
                bubble.bounceY(false);
                that.bounceY(true);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Actions when the rope collides with another shape.
     *
     * @param rope
     *            - rope that is colliding
     * @param collidee
     *            - shape the rope collides with
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
     * Return which side the collision occurs for X-axis.
     *
     * @param collider
     *            - mover
     * @param collidee
     *            - colliding shape
     * @return - integer representing the side
     */
    private int checkSideX(Shape collider, Shape collidee) {
        // Collide on right side
        if (collider.getCenterX() > collidee.getCenterX()) {
            return SIDE_RIGHT;
        }
        // Collide on left side
        if (collider.getCenterX() < collidee.getCenterX()) {
            return SIDE_LEFT;
        }
        return 0;
    }

    /**
     * Return which side the collision occurs for Y-axis.
     *
     * @param collider
     *            - mover
     * @param collidee
     *            - colliding shape
     * @return - integer representing the side
     */
    private int checkSideY(Shape collider, Shape collidee) {
        // Collide on top side
        if (collider.getCenterY() < collidee.getCenterY()) {
            return SIDE_TOP;
        }
        // Collide on bottom side
        if (collider.getCenterY() > collidee.getCenterY()) {
            return SIDE_BOTTOM;
        }
        return 0;
    }
}

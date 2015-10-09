package com.sem.btrouble.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import com.sem.btrouble.controller.CollisionHandler;
import com.sem.btrouble.observering.PlayerObserver;
import com.sem.btrouble.observering.PlayerSubject;

/**
 * Player class, containing all the data about the player.
 *
 */
@SuppressWarnings("serial")
public class Player extends Rectangle implements Drawable, Collidable, PlayerSubject {
    private int lives;
    private int score;

    private SpriteSheet walkSheet;
    private Animation walkAnimation;
    private Image playerIdle;
    private boolean facingLeft = true;
    private boolean idle = true;

    private boolean leftBlocked;
    private boolean rightBlocked;
    private boolean alive;
    private boolean falling;
    
    private boolean lostaLife;
    private boolean gainedaLife;
    private boolean poppedaBubble;
    private boolean shotaRope;

    // Gravity attributes
    private float vy;
    private float ay = .3f;

    private static final int PLAYER_SPEED = 3;
    private static final int INITIAL_LIVES = 5;
    private static final int INITIAL_SCORE = 0;

    private ArrayList<Rope> ropes;
    
    private ArrayList<PlayerObserver> observers;

    /**
     * Constructor for the Player class.
     *
     * @param xpos
     *            x value for the Player from the sprite class.
     * @param ypos
     *            y value for the Player from the sprite class.
     */
    public Player(float xpos, float ypos) {
        super(xpos, ypos, 50f, 160f);
        ropes = new ArrayList<Rope>();
        lives = INITIAL_LIVES;
        score = INITIAL_SCORE;
        vy = 2;
        rightBlocked = false;
        leftBlocked = false;
        alive = true;
        falling = true;
        
        lostaLife = false;
        gainedaLife = false;
        poppedaBubble = false;
        shotaRope = false;
        this.observers = new ArrayList<PlayerObserver>();
    }

    /**
     * Checks whether the provided Object is the same as this Player.
     *
     * @param other
     *            should be the Object to be checked for equality.
     * @return returns a boolean representing whether the provided Object is the
     *         same as this Player.
     */
    public boolean equals(Object other) {
        if (other instanceof Player) {
            Player that = (Player) other;
            return Math.abs(this.x - that.x) == 0 && Math.abs(this.y - that.y) == 0 
                    && this.ropes.equals(that.ropes)
                    && this.facingLeft == that.facingLeft && this.idle == that.idle
                    && this.lives == that.lives && this.score == that.score 
                    && Math.abs(this.vy - that.vy) == 0
                    && this.rightBlocked == that.rightBlocked
                    && this.leftBlocked == that.leftBlocked;
        }
        return false;
    }
    
    /**
     * HashCode because of implemented equals method.
     * @return hashCode
     */
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    /**
     * Return if player is right blocked.
     * @return boolean
     */
    public boolean getRightBlocked() {
        return rightBlocked;
    }

    /**
     * Return if the player is left blocked.
     * @return boolean
     */
    public boolean getLeftBlocked() {
        return leftBlocked;
    }

    /**
     * Set the player to right blocked.
     * @param block boolean
     */
    public void setRightBlock(boolean block) {
        this.rightBlocked = block;
    }

    /**
     * Set the player to left blocked.
     * @param block boolean
     */
    public void setLeftBlock(boolean block) {
        this.leftBlocked = block;
    }

    /**
     * Return if the player is alive.
     * @return boolean
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set the player alive.
     * @param alive boolean
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Return if the player is falling.
     * @return boolean
     */
    public boolean isFalling() {
        return falling;
    }

    /**
     * Set the player to falling.
     * @param falling boolean
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * Return the ropes which the player has shot.
     * @return the ropes
     */
    public ArrayList<Rope> getRopes() {
        return ropes;
    }

    /**
     * Add a life to the player.
     */
    public void addLife() {
        this.gainedaLife = true;
        notifyObservers();
        lives++;
    }

    /**
     * Remove a life of the player.
     */
    public void loseLife() {
        this.lostaLife = true;
        notifyObservers();
        lives--;
    }

    /**
     * Return if the player has lives.
     * @return boolean
     */
    public boolean hasLives() {
        return lives >= 0;
    }

    /**
     * Get the amount of lives of the player.
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Get the score of the player.
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Increase the score of the player.
     * @param amount how many the score increases
     */
    public void increaseScore(int amount) {
        this.poppedaBubble = true;
        notifyObservers();
        score += amount;
    }

    /**
     * Return the vertical speed of the player.
     * @return vertical speed
     */
    public double getVy() {
        return vy;
    }

    /**
     * Add a rope to the player.
     */
    public void moveRopes() {
        for (Rope r : ropes) {
            r.move();
        }
    }

    /**
     * Remove collided ropes.
     * @return Collection without collided ropes
     */
    public Collection<Collidable> removeCollidedRopes() {
        Collection<Collidable> collidedRopes = new ArrayList<Collidable>();
        for (Rope r : ropes) {
            if (r.isCollided()) {
                collidedRopes.add(r);
            }
        }
        ropes.removeAll(collidedRopes);
        return collidedRopes;
    }

    /**
     * Function which allows the player to fire. 
     * True if the rope succesfully fires.
     * 
     * @param r
     *            - rope to be added
     * @return boolean
     */
    public boolean fire(Rope r) {
        this.shotaRope = true;
        notifyObservers();
        if (ropes.size() <= 0) {
            ropes.add(r);
            return true;
        }
        return false;
    }

    /**
     * Draws the player on the screen.
     *
     * @throws SlickException
     *             when the player could not be drawn.
     */
    @Override
    public void draw(Graphics graphics) {
        try {
            if (playerIdle == null && walkSheet == null && walkAnimation == null) {
                playerIdle = new Image("Sprites/idle.png");
                walkSheet = new SpriteSheet("Sprites/player_spritesheet.png", 100, 175);
                walkAnimation = new Animation(walkSheet, 20);
            }
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Render the sprite at an offset.
        int playerX = (int) (x
                - ((walkSheet.getWidth() / walkSheet.getHorizontalCount()) - getWidth()) / 2);
        if (!idle) {
            walkAnimation.getCurrentFrame().getFlippedCopy(facingLeft, false).draw(playerX, y - 15);
        } else {
            playerIdle.getFlippedCopy(facingLeft, false).draw(playerX, y - 15);
        }
        for (int i = 0; i < ropes.size(); i++) {
            ropes.get(i).draw(graphics);
        }
    }

    /**
     * Moves the Player the provided amount of pixels to the right.
     *
     */
    public void move() {
        if (isFalling()) {
            fall();
        } else {
            vy = 0;
        }
        idle = true;
    }

    /**
     * Move the player to the left.
     * 
     * @param delta
     *            - speed
     */
    public void moveLeft(int delta) {
        if (!leftBlocked) {
            rightBlocked = false;
            leftBlocked = false;
            idle = false;
            facingLeft = true;
            walkAnimation.update(delta);
            x -= delta * 0.15f * PLAYER_SPEED;
        }
    }

    /**
     * Move the player to the right
     * 
     * @param delta
     *            - speed
     */
    public void moveRight(int delta) {
        if (!rightBlocked) {
            rightBlocked = false;
            leftBlocked = false;
            idle = false;
            facingLeft = false;
            walkAnimation.update(delta);
            x += delta * 0.15f * PLAYER_SPEED;
        }
    }

    /**
     * This functions deletes all the rope elements from the room.
     */
    public void resetRope() {
        ropes.clear();
    }

    /**
     * Move the player to the specified coordinates.
     *
     * @param xpos
     *            - x-coordinate
     * @param ypos
     *            - y-coordinate
     */
    public void moveTo(int xpos, int ypos) {
        this.x = xpos;
        this.y = ypos;
        falling = true;
    }

    /**
     * Slowly fall down vertically.
     */
    public void fall() {
        y += vy;
        vy += ay;
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

        // Method called on Bubble collision.
        collisionActionMap.put(Bubble.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                setAlive(false);
            }
        });

        // Method called on Wall collision
        collisionActionMap.put(Wall.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                switch (CollisionHandler.checkCollisionSideX(Player.this, collider)) {
                    case LEFT:
                        setRightBlock(true);
                        break;
                    case RIGHT:
                        setLeftBlock(true);
                        break;
                    default:
                        break;
                }
            }
        });

        // Method called on Floor collision.
        collisionActionMap.put(Floor.class, new CollisionAction() {
            @Override
            public void onCollision(Collidable collider) {
                setFalling(false);
                setY(collider.getY() - getHeight());
            }
        });

        return collisionActionMap;
    }

    /**
     * Checks for intersection with another Collidable.
     * @param collidable Check if this collidable intersects with that collidable.
     * @return True if this object intersects with collidable.
     */
    @Override
    public boolean intersectsCollidable(Collidable collidable) {
        return this.intersects((Shape) collidable);
    }

    @Override
    public void registerObserver(PlayerObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(PlayerObserver observer) {
        observers.add(observer);

    }

    @Override
    public void notifyObservers() {
        for(PlayerObserver observer: observers){
            if(lostaLife){
                observer.lostaLife();
            }
            if(gainedaLife){
                observer.gainedaLife();
            }
            if(poppedaBubble){
                observer.poppedaBubble();
            }
            if(shotaRope){
                observer.shotaRope();
            }
        }
        lostaLife = false;
        gainedaLife = false;
        poppedaBubble = false;
        shotaRope = false;
    }
}

package com.sem.btrouble.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Player class, containing all the data about the player.
 *
 */
@SuppressWarnings("serial")
public class Player extends Rectangle {
    private int lives;
    private int score;

    private SpriteSheet walkSheet;
    private Animation walkAnimation;
    private Image playerIdle;
    private boolean facingLeft = true;
    private boolean idle = true;

    private boolean leftBlocked;
    private boolean rightBlocked;

    // Gravity attributes
    private float vy;
    private float ay = .5f;

    private static final int PLAYER_SPEED = 3;
    private static final int INITIAL_LIVES = 5;
    private static final int INITIAL_SCORE = 0;

    private ArrayList<Rope> ropes;

    /**
     * Constructor for the Player class.
     * @param x x value for the Player from the sprite class.
     * @param y y value for the Player from the sprite class.
     */
    public Player(float x, float y){
        super(x, y, 60f, 175f);
        ropes = new ArrayList<Rope>();
        lives = INITIAL_LIVES;
        score = INITIAL_SCORE;
        vy = 2;
        rightBlocked = false;
        leftBlocked = false;
    }
    
    public boolean equals(Object other) {
    	if(other instanceof Player) {
    		Player that = (Player) other;
    		return(this.x == that.x && this.y == that.y && this.ropes.equals(that.ropes) && this.facingLeft == that.facingLeft && this.idle == that.idle && this.lives == that.lives &&
    				this.score == that.score && this.vy == that.vy && this.rightBlocked == that.rightBlocked && this.leftBlocked == that.leftBlocked);
    	}
    	return false;
    }

    public ArrayList<Rope> getRopes() {
        return ropes;
    }

    public void addLife(){
        lives ++;
    }

    public void loseLife(){
        lives --;
    }

    public boolean hasLives(){
        return lives >= 0;
    }

    public int getLives(){
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    /**
     * Function which allows the player to fire.
     * @throws SlickException
     */
    public void fire() throws SlickException {
        if (ropes.size() <= 0)
            ropes.add(new Rope(getX() + (int)(getWidth()/2), getY()));
    }

    public void draw() throws SlickException {
        // Render the sprite at an offset.
        playerIdle = new Image("Sprites/idle.png");
        walkSheet = new SpriteSheet("Sprites/player_spritesheet.png", 100, 175);
        walkAnimation = new Animation(walkSheet, 20);
        int playerX = (int)(x - ((walkSheet.getWidth()/walkSheet.getHorizontalCount()) - getWidth()) / 2);
        if (!idle) {
            walkAnimation.getCurrentFrame().getFlippedCopy(facingLeft, false).draw(playerX, y);
        } else {
            playerIdle.getFlippedCopy(facingLeft, false).draw(playerX, y);
        }
        for (int i = 0; i < ropes.size(); i++) {
            ropes.get(i).draw();
        }
    }

    public void move(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        for(Rectangle floor: Model.getCurrentRoom().getFloors()) {
            if(!this.intersects(floor)) {
                y += vy;
                this.vy += ay;
            } else {
                vy = 0;
            }
        }

        idle = true;
        if (input.isKeyDown(Input.KEY_LEFT) && !leftBlocked)
        {
            rightBlocked = false;
            leftBlocked = false;
            idle = false;
            facingLeft = true;
            walkAnimation.update(delta);
            //if(!stuck)
                x -= delta * 0.15f * PLAYER_SPEED;
        }
        else if (input.isKeyDown(Input.KEY_RIGHT) && !rightBlocked)
        {
            rightBlocked = false;
            leftBlocked = false;
            idle = false;
            facingLeft = false;
            walkAnimation.update(delta);
            //if(!stuck)
                x += delta * 0.15f * PLAYER_SPEED;
        }
        if (input.isKeyPressed(Input.KEY_SPACE))
        {
            idle = true;
            fire();
        }
    }

    public void setLeftBlocked(boolean leftBlocked) {
        this.leftBlocked = leftBlocked;
    }

    public void setRightBlocked(boolean rightBlocked) {
        this.rightBlocked = rightBlocked;
    }

    /**
     * This functions deletes all the rope elements from the room.
     */
    public void resetRope() {
        ropes = new ArrayList<Rope>();
    }

    /**
     * Move the player to the specified coordinates.
     * @param x - x-coordinate
     * @param y - y-coordinate
     */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Stop moving vertically
     */
    public void stopFalling() {
        vy = 0;
    }

    /**
     * Slowly fall down vertically
     */
    public void fall() {
        vy = 2;
    }

}

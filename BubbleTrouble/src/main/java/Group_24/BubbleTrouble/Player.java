package Group_24.BubbleTrouble;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Player class, containing all the data about the player.
 *
 */
public class Player extends Object {
    private int dx;
    private int lives;
    private int score;
    private Image sprite;

    private static final int PLAYER_SPEED = 5;
    private static final int INITIAL_LIVES = 2;
    private static final int INITIAL_SCORE = 0;

    private ArrayList<Rope> ropes;

    /**
     * Constructor for the Player class.
     * @param x x value for the Player from the sprite class.
     * @param y y value for the Player from the sprite class.
     */
    public Player(int x, int y) throws SlickException {
        super(x, y);
        sprite = new Image("Sprites/Player.png");
        ropes = new ArrayList<Rope>();
        lives = INITIAL_LIVES;
        score = INITIAL_SCORE;
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

    /**
     * @return the dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * @param dx the dx to set
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    /**
     * Function which allows the Player to move.
     */
    public void move() {
        x += dx;
        for(Floor floor: Model.getCurrentRoom().getFloors()) {
            if(!this.collidesWith(floor))
                y += 1;
        }
    }

    /**
     * Function which allows the player to fire.
     */
    public void fire() throws SlickException {
        int yvalue = this.getY();

        for (int i = 32; i < 321; i += 32) {
            ropes.add(new Rope((this.getX() + this.getWidth() / 2), yvalue));
            yvalue += 32;
        }
    }

    public void draw() throws SlickException {
        sprite.draw(x, y);
        drawRopes();
    }

    /**
     * Function which draws all the ropes from the ArrayList.
     */
    public void drawRopes() throws SlickException {
        ArrayList<Rope> rs = this.getRopes();

        for (Rope rope : rs) {
            rope.draw();
        }
    }

    /**
     * Handles the keyboard control.
     * @param action the action to take; -1 is left, 0 is fire, 1 is right and 2 is do nothing
     */
    public void action(int action) throws SlickException {
        switch (action) {
            case -1: dx = -1 * PLAYER_SPEED; break;
            case 0: fire(); break;
            case 1: dx = 1 * PLAYER_SPEED; break;
            case 2: dx = 0; break;
            default: return;
        }
    }

    /**
     * This functions deletes all the rope elements from the room.
     */
    public void resetRope() {
        ropes = new ArrayList<Rope>();
    }

    public void moveTo(int x) {
        this.x = x;
        this.dx = 0;
    }
}

package Group_24.BubbleTrouble;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

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

	private SpriteSheet walkSheet;
	private Animation walkAnimation;
	private Image playerIdle;
	private float x = 100f;
	private float y = 421f;
	private boolean facingLeft = true;
	private boolean idle = true;

	private static final int PLAYER_SPEED = 5;
	private static final int INITIAL_LIVES = 2;
	private static final int INITIAL_SCORE = 0;

	private ArrayList<Rope> ropes;

	/**
	 * Constructor for the Player class.
	 * 
	 * @param x
	 *            x value for the Player from the sprite class.
	 * @param y
	 *            y value for the Player from the sprite class.
	 */
	public Player(int x, int y) throws SlickException {
		super(x, y);
		playerIdle = new Image("Sprites/idle.png");
		walkSheet = new SpriteSheet("Sprites/walkAnimation.png", 100, 175);
		walkAnimation = new Animation(walkSheet, 100);
		ropes = new ArrayList<Rope>();
		lives = INITIAL_LIVES;
		score = INITIAL_SCORE;
	}

	public boolean equals(Object other) {
    	boolean res = false;
    	if(other instanceof Player) {
    		Player that = (Player) other;
    		if(this.x == that.x && this.y == that.y && this.lives == that.lives && this.score == that.score
    				&& this.walkAnimation.equals(that.walkAnimation) && this.ropes.equals(that.ropes) && this.dx == that.dx && this.facingLeft == that.facingLeft &&
    				this.idle == that.idle) {
    			res = true;
    		}
    	}
    	return res;
    }

	public ArrayList<Rope> getRopes() {
		return ropes;
	}

	public void addLife() {
		lives++;
	}

	public void loseLife() {
		lives--;
	}

	public boolean hasLives() {
		return lives >= 0;
	}

	public int getLives() {
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
	 * @param dx
	 *            the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	public void increaseScore(int amount) {
		score += amount;
	}

	/**
	 * Function which allows the player to fire.
	 * 
	 * @throws SlickException
	 */
	public void fire() throws SlickException {
		int yvalue = this.getY();

		for (int i = 32; i < 321; i += 32) {
			ropes.add(new Rope((this.getX() + this.getWidth() / 2), yvalue));
			yvalue += 32;
		}
	}

	/**
	 * Handles the keyboard control.
	 * 
	 * @param action
	 *            the action to take; -1 is left, 0 is fire, 1 is right and 2 is
	 *            do nothing
	 */
	public void action(int action) throws SlickException {
		switch (action) {
		case -1:
			dx = -1 * PLAYER_SPEED;
			break;
		case 0:
			fire();
			break;
		case 1:
			dx = 1 * PLAYER_SPEED;
			break;
		case 2:
			dx = 0;
			break;
		default:
			return;
		}
		if (ropes.size() == 0) {
			Rope rope = new Rope((int) x, 596);
			ropes.add(rope);
		}
	}

	public void draw() throws SlickException {
		if (!idle) {
			walkAnimation.getCurrentFrame().getFlippedCopy(facingLeft, false)
					.draw(x, y);
		} else {
			playerIdle.getFlippedCopy(facingLeft, false).draw(x, y);
		}
		for (int i = 0; i < ropes.size(); i++) {
			ropes.get(i).draw();
		}
	}

	public void move(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		// for(Floor floor: Model.getCurrentRoom().getFloors()) {
		// if(!this.collidesWith(floor))
		// y += 1;
		// }

		if (input.isKeyDown(Input.KEY_LEFT)) {
			idle = false;
			facingLeft = true;
			walkAnimation.update(delta);
			x -= delta * 0.15f;
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			idle = false;
			facingLeft = false;
			walkAnimation.update(delta);
			x += delta * 0.15f;
		} else if (input.isKeyPressed(Input.KEY_SPACE)) {
			idle = true;
			fire();
		} else {
			idle = true;
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

package Group_24.BubbleTrouble;

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
public class Player extends Rectangle {
    private int lives;
    private int score;

    private SpriteSheet walkSheet;
    private Animation walkAnimation;
    private Image playerIdle;
    private boolean facingLeft = true;
    private boolean idle = true;

    // Gravity attributes
    private float vy;
    private float ay = .5f;

    private static final int PLAYER_SPEED = 3;
    private static final int INITIAL_LIVES = 2;
    private static final int INITIAL_SCORE = 0;

    private ArrayList<Rope> ropes;

    /**
     * Constructor for the Player class.
     * @param x x value for the Player from the sprite class.
     * @param y y value for the Player from the sprite class.
     */
    public Player(float x, float y) throws SlickException {
        super(x, y, 100f, 175f);
        playerIdle = new Image("Sprites/idle.png");
        walkSheet = new SpriteSheet("Sprites/player_spritesheet.png", 100, 175);
        walkAnimation = new Animation(walkSheet, 40);
        ropes = new ArrayList<Rope>();
        lives = INITIAL_LIVES;
        score = INITIAL_SCORE;
        vy = 2;
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
        ropes.add(new Rope(getX(), getY()));
    }

    public void draw() throws SlickException {
        if (!idle) {
            walkAnimation.getCurrentFrame().getFlippedCopy(facingLeft, false).draw(x, y);
        } else {
            playerIdle.getFlippedCopy(facingLeft, false).draw(x, y);
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

//        boolean stuck = false;
//        for(Rectangle wall: Model.getCurrentRoom().getWalls()) {
//            if(this.intersects(wall))
//                stuck = true;
//        }

        if (input.isKeyDown(Input.KEY_LEFT))
        {
            idle = false;
            facingLeft = true;
            walkAnimation.update(delta);
            //if(!stuck)
                x -= delta * 0.15f * PLAYER_SPEED;
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            idle = false;
            facingLeft = false;
            walkAnimation.update(delta);
            //if(!stuck)
                x += delta * 0.15f * PLAYER_SPEED;
        }
        else if (input.isKeyPressed(Input.KEY_SPACE))
        {
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
    }
}

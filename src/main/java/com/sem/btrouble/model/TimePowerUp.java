package com.sem.btrouble.model;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionAction;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.view.GameView;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents power up which slows down the time.
 * @author Martin
 *
 */
@SuppressWarnings("serial")
public class TimePowerUp extends PowerUp {
    
	private Image playerIdle;
	
	/**
	 * Construct power up bought in the store.
	 */
	public TimePowerUp() {
		super();
		activate();
	}
	
	/**
	 * Construct power up received in the game.
	 * @param xpos x position
	 * @param ypos y position
	 */
    public TimePowerUp(float xpos, float ypos) {
        super(xpos, ypos);
    }
    
    /**
     * Active power up.
     */
    public void activate() {
        Timers timers = GameView.getController().getTimers();
        timers.addAdditionalTime(50);
    }

    
    /**
     * Reset the power up.
     */
    public void reset() {
        GameView.getController().getTimers().resetAdditionalTime();
    }
    
    /**
     * Draw the power up.
     */
    public void draw(Graphics graphics) {
        try {
            if (playerIdle == null) {
                playerIdle = new Image("Sprites/powerup_time.png");
            }
            playerIdle.draw(getX(), getY(), 50, 100);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Every collidable should return a Map with all CollisionActions
     * that collidable should process. To prevent class checking, simply
     * use the class as the key, and a CollisionAction instance as value.
     *
     * @return A map of all actions this collidable can do on a collision.
     */
    @Override
    public Map<Class<? extends Collidable>, CollisionAction> getCollideActions() {
        Map<Class<? extends Collidable>, CollisionAction> collisionActionMap =
                new HashMap<Class<? extends Collidable>, CollisionAction>();

        // Method called on collision with Floor.
        collisionActionMap.put(Floor.class, new FloorCollision());

        // Method called on collision with Player.
        collisionActionMap.put(Player.class, new PlayerCollision());

        return collisionActionMap;
    }

    /**
     * Class to call method on collision with floor.
     */
    private class FloorCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            setFalling(false);
            setY(collider.getY() - getHeight());
        }
    }

    /**
     * Class to call method on collision with player.
     */
    private class PlayerCollision implements CollisionAction {
        @Override
        public void onCollision(Collidable collider) {
            GameView.getController().getTimers().increaseLevelTimerCounter(50);
            Model.deleteShortPower(TimePowerUp.this);
        }
    }
}

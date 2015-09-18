package com.sem.btrouble.controller;

import com.sem.btrouble.event.BubbleEvent;
import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Rope;
import com.sem.btrouble.model.Timers;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Observable;


/**
 * Controller, recalculates the Model, on request of the view.
 */
public class Controller extends Observable {

  private static final int REWARD_BUBBLE = 100;

  private ArrayList<Bubble> newBubbles;
  private ArrayList<Bubble> oldBubbles;
  private GameContainer gc;
  private CollisionHandler collisionHandler;
  private static Timers timers;

  /**
   * Starts a new game by loading data into the room and adding the players.
   */
  public Controller(GameContainer container) throws SlickException {
    timers = new Timers(100);
    this.gc = container;
    newBubbles = new ArrayList<Bubble>();
    oldBubbles = new ArrayList<Bubble>();

    collisionHandler = new CollisionHandler();
    Model.init();
    Room r = new Room();
    Model.addRoom(r);
    r.loadRoom();
    Player p = new Player(0, 0);
    Model.addPlayer(p);
    collisionHandler.addCollidable(p);
    Model.restartRoom();
    collisionHandler.addCollidable(r.getCollidables());
    fireEvent(new ControllerEvent(this, ControllerEvent.GAMESTART, "Game started"));
  }

  public Timers getTimers() {
    return timers;
  }

  /**
   * Updates the model, should be done on request of the view.
   */
  public void update(int delta) throws SlickException {
    // Create a shallow clone to prevent changes to the list while iterating.
    ArrayList<Bubble> bubblesClone = new ArrayList<Bubble>(Model.getCurrentRoom().getBubbles());
    for(Bubble bubble: bubblesClone) {
      collisionHandler.checkCollision(bubble);
    }

    for(Player player: Model.getPlayers()) {
      if(!collisionHandler.checkCollision(player))
        player.setFalling(true);
      if (!player.isAlive())
        loseLife(player);
      for(Rope rope: player.getRopes()) {
        collisionHandler.checkCollision(rope);
      }
    }




/*    for (Bubble bubble : Model.getBubbles()) {

      for (Rectangle floor : Model.getCurrentRoom().getFloors()) {
        if (bubble.intersects(floor)) {
          fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_FLOOR, "Collided with floor"));
          bubble.bubbleEvent(
              new BubbleEvent(bubble, BubbleEvent.COLLISION_FLOOR, "Collided with floor"));
        }
      }

      // Collision detection for walls
      for (Rectangle wall : Model.getCurrentRoom().getWalls()) {
        if (bubble.intersects(wall)) {
          fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_WALL, "Collided with wall"));
          bubble.bubbleEvent(
              new BubbleEvent(bubble, BubbleEvent.COLLISION_WALL, "Collided with wall"));
        }
      }

      for (Player player : Model.getPlayers()) {

        // Check if timer has run out.
        if (this.getTimers().getLevelTimeLeft() <= 0) {
          fireEvent(new ControllerEvent(this, ControllerEvent.OUTOFTIME, "Out of time"));
          loseLife(player);
        }

        // BubbleEvent detection for bubble against player
        if (player.intersects(bubble)) {
          loseLife(player);
        }

        // Collision detection for walls
        for (Rectangle wall : Model.getCurrentRoom().getWalls()) {
          if (player.intersects(wall)) {
            int playerX = (int) player.getX() + ((int) (player.getWidth() / 2));
            if (playerX > wall.getX()) {
              fireEvent(new PlayerEvent(player, PlayerEvent.COLLISION_LEFTWALL,
                  "Collided with left wall"));
              player.setLeftBlocked(true);
            } else if (playerX <= wall.getX()) {
              fireEvent(new PlayerEvent(player, PlayerEvent.COLLISION_RIGHTWALL,
                  "Collided with right wall"));
              player.setRightBlocked(true);
            }
          }
        }

        for (Rope rope : player.getRopes()) {
          if (bubble.intersects(rope)) {
            fireEvent(new BubbleEvent(bubble, BubbleEvent.COLLISION_ROPE, "Collided with rope"));
            bubble.bubbleEvent(
                new BubbleEvent(bubble, BubbleEvent.COLLISION_ROPE, "Collided with rope"));
            fireEvent(new PlayerEvent(player, PlayerEvent.POPBUBBLE, "Popped a bubble"));
            player.increaseScore(REWARD_BUBBLE);
            player.resetRope();
          }
        }
      }
    }*/

    for (Player p: Model.getPlayers()) {
      p.move();
    }
    processInput(delta);
    // calculate movements
    updateRopes();
    updateBubble();
  }

  /**
   * Move the player on key presses
   * @param delta - milliseconds between frames
   */
  public void processInput(int delta) {
    Input input = gc.getInput();
    Player p1 = Model.getPlayers().get(0);

    if (input.isKeyDown(Input.KEY_LEFT)) {
      p1.moveLeft(delta);
    } else if (input.isKeyDown(Input.KEY_RIGHT)) {
      p1.moveRight(delta);
    }

    if (input.isKeyPressed(Input.KEY_SPACE)) {
      Rope r = new Rope(p1.getX() + (int) (p1.getWidth() / 2), p1.getY());
      if(p1.fire(r))
        collisionHandler.addCollidable(r);
    }
  }

  /**
   * Lets the player lose a life.
   * 
   * @param player
   *          should be the player who lost a life
   */
  public void loseLife(Player player) {
    fireEvent(new PlayerEvent(player, PlayerEvent.LIFE_LOST, "Lost a life"));
    player.loseLife();
    if (!player.hasLives()) {
      endGame("Game over...");
    } else {
      collisionHandler.removeCollidable(Model.getCurrentRoom().getCollidables());
      restartRoom();
      player.setAlive(true);
    }
  }

  private void restartRoom() {
    fireEvent(new ControllerEvent(this, ControllerEvent.RESTARTROOM, "Room restarted"));
    Model.restartRoom();
    getTimers().restartTimer();
    collisionHandler.addCollidable(Model.getCurrentRoom().getCollidables());
  }

  private void fireEvent(GameEvent gameEvent) {
    setChanged();
    notifyObservers(gameEvent);
  }

  /**
   * Updates the bubble, checks whether bubbles should be removed or not, and
   * calculates the new position of each bubble.
   */
  private void updateBubble() {
    if (!Model.getCurrentRoom().hasBubbles()) {
      endGame("You won the game!");
    }
    Model.getCurrentRoom().moveBubbles();
  }

  /**
   * Adds a bubble to the game.
   * 
   * @param bubble
   *          Bubble to add to the room
   */
  public void addBubble(Bubble bubble) {
    Model.getCurrentRoom().addBubble(bubble);
    collisionHandler.addCollidable(bubble);
  }

  /**
   * Removes a bubble from the game.
   * 
   * @param bubble
   *          the bubble to remove
   */
  public void removeBubble(Bubble bubble) {
    Model.getCurrentRoom().removeBubble(bubble);
    collisionHandler.removeCollidable(bubble);
  }

  /**
   * Updates the ropes, calculates the new position of the rope and removes the
   * rope if it hits the ceiling.
   */
  private void updateRopes() throws SlickException {
    for (Player player : Model.getPlayers()) {
      player.moveRopes();
      collisionHandler.removeCollidable(player.removeCollidedRopes());
    }

  }

  /**
   * Ends the game by stopping the view, shows a message.
   * 
   * @param message
   *          should be a String containing the message which is shown.
   */
  public void endGame(String message) {
    fireEvent(new ControllerEvent(this, ControllerEvent.GAMEOVER, "Game over"));
    gc.exit();
  }

}

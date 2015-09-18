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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

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
  private Timers timers;

  /**
   * Starts a new game by loading data into the room and adding the players.
   */
  public Controller(GameContainer container) throws SlickException {
    timers = new Timers(100);

    newBubbles = new ArrayList<Bubble>();
    oldBubbles = new ArrayList<Bubble>();
    gc = container;

    Model.init();
    Model.addRoom(new Room());
    Model.addPlayer(new Player(0, 0));
    Model.restartRoom();
    fireEvent(new ControllerEvent(this, ControllerEvent.GAMESTART, "Game started"));
  }

  public Timers getTimers() {
    return timers;
  }

  /**
   * Updates the model, should be done on request of the view.
   */
  public void update() throws SlickException {

    for (Bubble bubble : Model.getBubbles()) {

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
    }

    // calculate movements
    updateRopes();
    updateBubble();
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
      restartRoom();
    }
  }

  private void restartRoom() {
    fireEvent(new ControllerEvent(this, ControllerEvent.RESTARTROOM, "Room restarted"));
    Model.restartRoom();
    this.getTimers().restartTimer();
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
    for (Bubble bubble : newBubbles) {
      Model.getBubbles().add(bubble);
    }
    newBubbles.clear();
    for (Bubble bubble : oldBubbles) {
      Model.getBubbles().remove(bubble);
    }
    oldBubbles.clear();
    if (Model.getBubbles().isEmpty()) {
      endGame("You won the game!");
    }
    for (Bubble bubble : Model.getBubbles()) {
      bubble.move();
    }
  }

  /**
   * Adds a bubble to the game.
   * 
   * @param bubble
   *          Bubble to add to the room
   */
  public void addBubble(Bubble bubble) {
    newBubbles.add(bubble);
  }

  /**
   * Removes a bubble from the game.
   * 
   * @param bubble
   *          the bubble to remove
   */
  public void removeBubble(Bubble bubble) {
    oldBubbles.add(bubble);
  }

  /**
   * Updates the ropes, calculates the new position of the rope and removes the
   * rope if it hits the ceiling.
   */
  private void updateRopes() throws SlickException {
    for (Player player : Model.getPlayers()) {
      for (int i = 0; i < player.getRopes().size(); i++) {
        player.getRopes().get(i).move();
        if (player.getRopes().get(i).getY() <= 0) {
          player.getRopes().remove(i);
        }
      }
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

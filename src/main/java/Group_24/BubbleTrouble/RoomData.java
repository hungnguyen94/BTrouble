package Group_24.BubbleTrouble;

import java.util.ArrayList;

/**
 * RoomData contains all the initial data of a room. Getting the data gives you
 * a copy of the original data.
 *
 */
public class RoomData {
  private ArrayList<Bubble> bubbles;
  private int startingPositionX;
  private int startingPositionY;

  /**
   * Constructs a new RoomData data container.
   * @param bubbles should be an ArrayList containing the bubbles in the room.
   */
  public RoomData(ArrayList<Bubble> bubbles) {
    this.bubbles = bubbles;
    this.startingPositionX = 50;
    this.startingPositionY = 350;
  }

  public RoomData(String file) {
    // TODO load roomdata from file
  }

  /**
   * Returns whether two rooms are the same.
   */
  public boolean equals(Object other) {
    if (other instanceof RoomData) {
      RoomData that = (RoomData) other;
      return (this.bubbles.equals(that.bubbles));
    }
    return false;
  }

  /**
   * getBubbles creates a list with a copy of every oiginal bubble, so that it
   * doesn't touch the original data.
   * 
   * @return returns a copy of the list of bubbles.
   */
  public ArrayList<Bubble> getBubbles() {
    ArrayList<Bubble> res = new ArrayList<Bubble>();
    for (Bubble bubble : bubbles) {
      res.add(new Bubble(bubble.getSize(), bubble.getX(), bubble.getY()));
    }
    return res;
  }

  /**
   * Get the horizontal starting position of the player in the room.
   * 
   * @return returns the horizontal starting position of the player.
   */
  public int getStartingPositionX() {
    return startingPositionX;
  }

  /**
   * Get the vertical starting position of the player in the room.
   * 
   * @return - returns the vertical starting position of the player.
   */
  public int getStartingPositionY() {
    return startingPositionY;
  }
}

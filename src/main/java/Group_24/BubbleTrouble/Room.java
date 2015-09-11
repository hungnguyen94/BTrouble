package Group_24.BubbleTrouble;

import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Room contains all objects within the room (except for the players), and draws
 * them on the screen.
 *
 */
public class Room {

  private RoomData data;
  private int startingPositionX;
  private int startingPositionY;

  private ArrayList<Rectangle> walls;
  private ArrayList<Rectangle> floors;

  private ArrayList<Bubble> bubbles;

  /**
   * Constructs a new Room using the data contained by a RoomData data
   * container.
   * 
   * @param data
   *          should be a RoomData object containing the data which should be
   *          loaded into the Room.
   */
  public Room(RoomData data) {
    this.data = data;

    walls = new ArrayList<Rectangle>();
    walls.add(new Rectangle(0, 0, 1, 800));
    walls.add(new Rectangle(1123, 0, 1, 800));

    floors = new ArrayList<Rectangle>();
    floors.add(new Rectangle(0, 794, 1123, 1));
    this.reload();
  }

  /**
   * Returns whether two rooms are the same.
   */
  public boolean equals(Object other) {
    if (other instanceof Room) {
      Room that = (Room) other;
      return (this.data.equals(that.data) && this.bubbles.equals(that.bubbles)
          && this.startingPositionX == that.startingPositionX
          && this.startingPositionY == that.startingPositionY);
    }
    return false;
  }

  /**
   * Returns the collection of bubbles within this Room.
   * 
   * @return returns the collection of bubbles within this Room.
   */
  public ArrayList<Bubble> getBubbles() {
    return bubbles;
  }

  /**
   * Reloads the room, loads the initial data into the Room and places the
   * Players in the room without touching the Players themself.
   */
  public void reload() {
    startingPositionX = data.getStartingPositionX();
    startingPositionY = data.getStartingPositionY();
    for (Player player : Model.getPlayers()) {
      player.moveTo(startingPositionX, startingPositionY);
    }
    bubbles = data.getBubbles();
  }

  public ArrayList<Rectangle> getWalls() {
    return walls;
  }

  public ArrayList<Rectangle> getFloors() {
    return floors;
  }

}

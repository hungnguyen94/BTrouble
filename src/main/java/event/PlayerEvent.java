package event;

import model.Player;

public class PlayerEvent extends GameEvent {

  public static final int COLLISION_BUBBLE = 0;
  public static final int COLLISION_LEFTWALL = 1;
  public static final int COLLISION_RIGHTWALL = 2;
  public static final int POPBUBBLE = 3;
  public static final int LIFE_LOST = 4;
  public static final int LIFE_GAINED = 5;
  
  public PlayerEvent(Object subject, int id, String message) {
    super(subject, id, message);
  }
  
  @Override
  public Player getSubject() {
    return (Player) super.getSubject();
  }

  @Override
  public String toString() {
    return "<PlayerEvent: " + this.getMessage() + ">";
  }
}

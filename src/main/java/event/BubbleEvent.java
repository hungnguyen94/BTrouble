package event;

import model.Bubble;

/**
 * CollisionEvent, holds different kinds of collisions.
 *
 */
public class BubbleEvent extends GameEvent {
  
  public static final int COLLISION_FLOOR = 0;
	public static final int COLLISION_WALL = 1;
	public static final int COLLISION_ROPE = 2;
  
  public BubbleEvent(Bubble subject, int id, String message) {
    super(subject, id, message);
  }
  
  @Override
  public Bubble getSubject(){
    return (Bubble) super.getSubject();
  }
  
  public String toString() {
    return "<BubbleEvent: " + this.getMessage() + ">";
  }
}

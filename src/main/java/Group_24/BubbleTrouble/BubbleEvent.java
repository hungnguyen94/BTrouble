package Group_24.BubbleTrouble;

/**
 * CollisionEvent, holds different kinds of collisions.
 *
 */
public class BubbleEvent extends GameEvent {
  public static final int COLLISION_FLOOR = 0;
	public static final int COLLISION_WALL = 1;
	public static final int COLLISION_ROPE = 2;
	
	public BubbleEvent(int id) {
    super(id);
  }
	
  @Override
  public String toString() {
    String res = "<BubbleEvent: ";
    switch(this.getType()){
      case COLLISION_FLOOR:
        res += "Collided with floor";
        break;
      case COLLISION_WALL:
        res += "Collided with wall";
        break;
      case COLLISION_ROPE:
        res += "Collided with rope";
        break;
      default:
        res += "Type unknown";
    }
    
    return res + ">";
  }
}

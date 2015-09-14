package Group_24.BubbleTrouble;

public class PlayerEvent extends GameEvent {

  public static final int COLLISION_BUBBLE = 0;
  public static final int COLLISION_LEFTWALL = 1;
  public static final int COLLISION_RIGHTWALL = 2;
  public static final int POPBUBBLE = 3;
  public static final int LIFE_LOST = 4;
  public static final int LIFE_GAINED = 5;
  
  public PlayerEvent(int type) {
    super(type);
  }

  @Override
  public String toString() {
    String res = "<PlayerEvent: ";
    switch(this.getType()){
      case COLLISION_BUBBLE:
        res += "Collided with bubble";
        break;
      case COLLISION_LEFTWALL:
        res += "Collided with left wall";
        break;
      case COLLISION_RIGHTWALL:
        res += "Collided with right wall";
        break;
      case POPBUBBLE:
        res += "Popped a bubble";
        break;
      case LIFE_LOST:
        res += "Lost a life";
        break;
      case LIFE_GAINED:
        res += "Gained a life";
        break;
      default:
        res += "Type unknown";
    }
    
    return res + ">";
  }

}

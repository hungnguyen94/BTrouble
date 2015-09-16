package event;

public class ControllerEvent extends GameEvent{

  public static final int GAMEOVER = 0;
  public static final int GAMESTART = 1;
  public static final int NEXTROOM = 2;
  public static final int RESTARTROOM = 3;
  public static final int OUTOFTIME = 4;

  public ControllerEvent(int id){
    super(id);
  }
  
  public String toString() {
    String res = "<ControllerEvent: ";

    switch (this.getType())
      {
      case GAMEOVER:
        res += "Game over";
        break;
      case GAMESTART:
        res += "Game started";
        break;
      case NEXTROOM:
        res += "Next room";
        break;
      case RESTARTROOM:
        res += "Room restarted";
        break;
      case OUTOFTIME:
        res += "Out of time";
        break;
      default:
        res += "Type unknown";
      }

    return res + ">";
  }
}

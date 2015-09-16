package event;

import controller.Controller;

public class ControllerEvent extends GameEvent{

  public static final int GAMEOVER = 0;
  public static final int GAMESTART = 1;
  public static final int NEXTROOM = 2;
  public static final int RESTARTROOM = 3;
  public static final int OUTOFTIME = 4;
  public static final int GAMEWON = 5;
  public static final int GAMELOST = 6; 
  
  public ControllerEvent(Controller controller, int id, String message){
    super(controller, id, message);
  }
  
  public String toString() {
    return "<ControllerEvent: " + this.getMessage() + ">";
  }
}

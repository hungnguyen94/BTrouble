package com.sem.btrouble;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.sem.btrouble.controller.Controller;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;

import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class ControllerTest {
    
  SlickApp application = mock(SlickApp.class);
  AppGameContainer appgc = mock(AppGameContainer.class);
  Bubble bubble = mock(Bubble.class);
  Player player = mock(Player.class);
    
  @Test
  public void addBubbleTest() throws SlickException {
    Controller controller = new Controller(appgc, application);
    int bubblesBefore = Model.getBubbles().size();
    controller.addBubble(bubble);
    int bubblesAfter = Model.getBubbles().size();
    assertFalse(bubblesBefore == bubblesAfter);
  }
    
  @Test
  public void removeBubbleTest() throws SlickException {
    Controller controller = new Controller(appgc, application);
    // Make sure there is at least one bubble in the Model
    controller.addBubble(bubble);
    int bubblesBefore = Model.getBubbles().size();
    controller.removeBubble(bubble);
    int bubblesAfter = Model.getBubbles().size();
    assertFalse(bubblesBefore == bubblesAfter);
  }
    
  @Test
  public void exitTest() throws SlickException {
    Controller controller = new Controller(appgc, application);
    appgc.start();
    controller.endGame("GameOver");
    verify(appgc).exit();
  }
    
}
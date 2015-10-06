package com.sem.btrouble.model;

import com.sem.btrouble.view.GameView;

public class TimePowerUp implements PowerUp {
    
    public TimePowerUp() {
        
    }
    
    public void activate() {
        GameView.getController().getTimers().setLevelTimerCounter(10000);
    }
    
    public void reset() {
        GameView.getController().getTimers().setLevelTimerCounter(100);
    }

}

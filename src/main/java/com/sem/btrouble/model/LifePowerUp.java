package com.sem.btrouble.model;

import java.util.ArrayList;

public class LifePowerUp implements PowerUp {
    
    public LifePowerUp() {
        
    }
    
    public void activate() {
        ArrayList<Player> players = Model.getPlayers();
        players.get(0).addLife();
    }
    
    public void reset() {
        return;
    }

}

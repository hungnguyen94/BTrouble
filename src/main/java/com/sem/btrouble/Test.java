package com.sem.btrouble;

import com.sem.btrouble.model.Game;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;

/**
 * Created by hung on 7-10-15.
 */
public class Test {

    public static void main(String[] args) {
        Player player = new Player(1f, 1f);
        Game game = new Game(player);
        Room room = new Room();
        room.loadRoom();
        game.loadLevel(room);
        game.startLevel();
    }

}

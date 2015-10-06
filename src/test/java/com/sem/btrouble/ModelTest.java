package com.sem.btrouble;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Class which tests the model class.
 * 
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ModelTest extends Model {

    @Mock
    private Room room;
    @Mock
    private Player player;

    /**
     * Set up the model.
     */
    @Before
    public void setUp() {
	Model.init(1280, 720);
	Model.getNextRoom();
	Model.getNextRoom();
    }

    /**
     * Test the getRoomHeight method.
     */
    @Test
    public void getRoomHeightTest() {
	assertEquals(Model.getRoomHeight(), 720);
    }

    /**
     * Test the getRoomWidth method.
     */
    @Test
    public void getRoomWidthTest() {
	assertEquals(Model.getRoomWidth(), 1280);
    }

    /*	*//**
	   * Test the addRoom method.
	   *//*
	     * @Test public void addRoomTest() { Model.addRoom(room);
	     * //Model.getNextRoom(); Model.restartRoom();
	     * assertEquals(Model.getCurrentRoom(), room); }
	     */

    /*	*//**
	   * Test the getBubbles method.
	   *//*
	     * @Test public void getBubblesTest() { ArrayList<Bubble> bubbles =
	     * new ArrayList<Bubble>(); Model.addRoom(room);
	     * when(room.getBubbles()).thenReturn(bubbles);
	     * assertEquals(Model.getBubbles(), bubbles); }
	     */

    /**
     * Test the addPlayer method.
     */
    @Test
    public void addPlayerTest() {
	ArrayList<Player> players = new ArrayList<Player>();
	players.add(player);
	Model.addPlayer(player);
	assertEquals(players, Model.getPlayers());
    }

    /*	*//**
	   * Test the restartRoom method.
	   *//*
	     * @Test public void restartRoomTest() { Model.addRoom(room);
	     * Model.restartRoom(); assertEquals(room, Model.getCurrentRoom());
	     * }
	     */

}

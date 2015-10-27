//package com.sem.btrouble;
//
//import com.sem.btrouble.model.PlayerPowerUp;
//import com.sem.btrouble.model.Model;
//import com.sem.btrouble.model.Player;
//import com.sem.btrouble.model.Room;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Class which tests the model class.
// *
// * @author Martin
// *
// */
//@RunWith(MockitoJUnitRunner.class)
//public class ModelTest extends Model {
//
//    @Mock
//    private Room room;
//    @Mock
//    private Player player;
//    @Mock
//    private PlayerPowerUp power;
//
//    /**
//     * Set up the model.
//     */
//    @Before
//    public void setUp() {
//        Model.init(1280, 720);
//        Model.getNextRoom();
//        Model.getNextRoom();
//    }
//
//    /**
//     * Test the getRoomHeight method.
//     */
//    @Test
//    public void getRoomHeightTest() {
//        assertEquals(Model.getRoomHeight(), 720);
//    }
//
//    /**
//     * Test the getPowerUps method.
//     */
//    @Test
//    public void getPowerUpsTest() {
//        assertEquals(new ArrayList<PlayerPowerUp>(), Model.getPowerUps());
//    }
//
//    /**
//     * Test the addPowerUps method.
//     */
//    @Test
//    public void addPowerUpsTest() {
//        Model.addPowerUp(power);
//        ArrayList<PlayerPowerUp> powers = new ArrayList<PlayerPowerUp>();
//        powers.add(power);
//        assertEquals(powers, Model.getPowerUps());
//    }
//
//    /**
//     * Test the clearPowerUps method.
//     */
//    @Test
//    public void clearPowerUpsTest() {
//        Model.addPowerUp(power);
//        ArrayList<PlayerPowerUp> powers = new ArrayList<PlayerPowerUp>();
//        powers.add(power);
//        Model.clearPowerUps();
//        assertEquals(new ArrayList<PlayerPowerUp>(), Model.getPowerUps());
//    }
//
//    /**
//     * Test the clearShortPower method.
//     */
//    @Test
//    public void clearShortPowerTest() {
//        Model.addShortPowerUp(power);
//        ArrayList<PlayerPowerUp> powers = new ArrayList<PlayerPowerUp>();
//        powers.add(power);
//        Model.clearShortPower();
//        assertEquals(new ArrayList<PlayerPowerUp>(), Model.getShortPower());
//    }
//
//    /**
//     * Test the getRoomWidth method.
//     */
//    @Test
//    public void getRoomWidthTest() {
//        assertEquals(Model.getRoomWidth(), 1280);
//    }
//
//    /*	*//**
//           * Test the addRoom method.
//           *//*
//             * @Test public void addRoomTest() { Model.addRoom(room);
//             * //Model.getNextRoom(); Model.restartRoom();
//             * assertEquals(Model.getCurrentRoom(), room); }
//             */
//
//    /*	*//**
//           * Test the getBubbles method.
//           *//*
//             * @Test public void getBubblesTest() { ArrayList<Bubble> bubbles =
//             * new ArrayList<Bubble>(); Model.addRoom(room);
//             * when(room.getBubbles()).thenReturn(bubbles);
//             * assertEquals(Model.getBubbles(), bubbles); }
//             */
//
//    /**
//     * Test the addPlayer method.
//     */
//    @Test
//    public void addPlayerTest() {
//        ArrayList<Player> players = new ArrayList<Player>();
//        players.add(player);
//        Model.addPlayer(player);
//        assertEquals(players, Model.getPlayers());
//    }
//
//    /*	*//**
//           * Test the restartRoom method.
//           *//*
//             * @Test public void restartRoomTest() { Model.addRoom(room);
//             * Model.restartRoom(); assertEquals(room, Model.getCurrentRoom());
//             * }
//             */
//
//}

//package com.sem.btrouble;
//
//import com.sem.btrouble.controller.Collidable;
//import com.sem.btrouble.model.Bubble;
//import com.sem.btrouble.model.Model;
//import com.sem.btrouble.model.Player;
//import com.sem.btrouble.model.Room;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.Collection;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.verify;
//
///**
// * Class which test the Room class.
// * 
// * @author Martin
// *
// */
//@RunWith(MockitoJUnitRunner.class)
//public class RoomTest {
//
//    @Mock
//    private Player player;
//    @Mock
//    private Bubble bubble;
//    private Room room;
//
//    /**
//     * Set up the model and room object.
//     */
//    @Before
//    public void setUp() {
//        room = new Room();
//        Model.init(1280, 720);
//        Model.addPlayer(player);
//        Model.addRoom(room);
//    }
//
//    /**
//     * Test the getter of spawn position X.
//     */
//    @Test
//    public void testGetSpawnPositionX() {
//        assertTrue(room.getSpawnPositionX() == 0);
//        Model.restartRoom();
//        assertFalse(Model.getCurrentRoom().getSpawnPositionX() == 0);
//    }
//
//    /**
//     * Test the getter of spawn position Y.
//     */
//    @Test
//    public void testGetSpawnPositionY() {
//        assertTrue(room.getSpawnPositionY() == 0);
//        Model.restartRoom();
//        assertFalse(Model.getCurrentRoom().getSpawnPositionY() == 0);
//    }
//
//    /**
//     * Test the getter of the list of bubbles.
//     */
//    @Test
//    public void testGetBubbles() {
//        assertTrue(room.getBubbles().isEmpty());
//        room.loadRoom();
//        assertFalse(room.getBubbles().isEmpty());
//    }
//
//    /**
//     * Test the hasBubbles method with outcome false.
//     */
//    @Test
//    public void hasBubblesFalseTest() {
//        assertFalse(room.hasBubbles());
//    }
//
//    /**
//     * Test the hasBubbles method with outcome true.
//     */
//    @Test
//    public void hasBubblesTrueTest() {
//        room.addBubble(bubble);
//        assertTrue(room.hasBubbles());
//    }
//
//    /**
//     * Test the moveBubbles method.
//     */
//    @Test
//    public void moveBubblesTest() {
//        room.addBubble(bubble);
//        room.moveBubbles();
//        verify(bubble).move();
//    }
//
//    /**
//     * Test the removeBubbles method.
//     */
//    @Test
//    public void removeBubbleTest() {
//        room.addBubble(bubble);
//        room.removeBubble(bubble);
//        assertFalse(room.hasBubbles());
//    }
//
//    /**
//     * Test the equals method with another type.
//     */
//    @Test
//    public void equalsOtherTest() {
//        assertFalse(room.equals(new String("test")));
//    }
//
//    /**
//     * Test the equals method with a false bubble.
//     */
//    @Test
//    public void equalsBubbleTest() {
//        room.addBubble(bubble);
//        Room room2 = new Room();
//        assertFalse(room.equals(room2));
//    }
//    
//    /**
//     * Test the equals method with a false x.
//     */
//    @Test
//    public void equalsXTest() {
//        Room room2 = new Room();
//        room2.setSpawnPositionX(10);
//        assertFalse(room.equals(room2));
//    }
//    
//    /**
//     * Test the equals method with a false y.
//     */
//    @Test
//    public void equalsYTest() {
//        Room room2 = new Room();
//        room2.setSpawnPositionY(10);
//        assertFalse(room.equals(room2));
//    }
//
//    /**
//     * Test the equals method with the copy method.
//     */
//    @Test
//    public void equalsTest() {
//        Room room2 = room.copyRoom();
//        assertTrue(room.equals(room2));
//    }
//
//    /**
//     * Test the getCollidables method.
//     */
//    @Test
//    public void getCollidablesTest() {
//        room.addBubble(bubble);
//        Collection<Collidable> collection = room.getCollidables();
////        assertEquals(1, collection.size());
////        assertTrue(collection.contains(bubble));
//    }
//
//}

package com.sem.btrouble;

import com.sem.btrouble.controller.Collidable;
import com.sem.btrouble.controller.CollisionHandler;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.model.Rope;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.geom.Shape;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Test for the CollisionHandler
 */
@RunWith(MockitoJUnitRunner.class)
public class CollisionHandlerTest {
    @Mock
    private Bubble bubble;
    @Mock
    private Player player;
    @Mock
    private Floor floor;
    @Mock
    private Wall wall;
    @Mock
    private Rope rope;

    private CollisionHandler collisionHandler;

    /**
     * setUp the collisionHandler.
     */
    @Before
    public void setUp() {
        collisionHandler = new CollisionHandler();
        assertNotNull(collisionHandler);
    }

    /**
     * Test the collision between a player and a bubble.
     */
    @Test
    public void playerCollideBubbleTest() {
        when(player.intersectsCollidable(bubble)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(player);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersectsCollidable(bubble);
        verify(player).getCollideActions();
    }

    /**
     * Test the collision between the right side of a wall and a player.
     */
    @Test
    public void playerCollideWallSideRightTest() {
        when(player.getCenterX()).thenReturn(-1f);
        when(wall.getCenterX()).thenReturn(1f);
        when(player.intersectsCollidable(wall)).thenReturn(true);
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersectsCollidable(wall);
        verify(player).getCollideActions();
    }

    /**
     * Test the collision between the left side of a wall and a player.
     */
    @Test
    public void playerCollideWallSideLeftTest() {
        when(player.getCenterX()).thenReturn(1f);
        when(wall.getCenterX()).thenReturn(-1f);
        when(player.intersectsCollidable(wall)).thenReturn(true);
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersectsCollidable(wall);
        verify(player).getCollideActions();
    }

    /**
     * Test the collision between the floor and a player.
     */
    @Test
    public void playerCollideFloorTest() {
        when(player.intersectsCollidable(floor)).thenReturn(true);
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(floor);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersectsCollidable(floor);
        verify(player).getCollideActions();
    }

    /**
     * Tests the collision between the left side of a wall and a bubble.
     */
    @Test
    public void bubbleCollideWallLeftTest() {
        when(bubble.getCenterX()).thenReturn(1f);
        when(wall.getCenterX()).thenReturn(-1f);
        when(bubble.intersectsCollidable(wall)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersectsCollidable(wall);
        verify(bubble).getCollideActions();
    }

    /**
     * Tests the collision between the right side of a wall and a bubble.
     */
    @Test
    public void bubbleCollideWallRightTest() {
        when(bubble.getCenterX()).thenReturn(-1f);
        when(wall.getCenterX()).thenReturn(1f);
        when(bubble.intersectsCollidable(wall)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersectsCollidable(wall);
        verify(bubble).getCollideActions();
    }

    /**
     * Test the collision between the floor and a bubble.
     */
    @Test
    public void bubbleCollideFloorTest() {
        when(bubble.intersectsCollidable(floor)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(floor);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersectsCollidable(floor);
        verify(bubble).getCollideActions();
    }

    /**
     * Tests the collision between a bubble and a rope.
     */
    @Test
    public void bubbleCollideRopeTest() {
        when(bubble.intersectsCollidable(rope)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(rope);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersectsCollidable(rope);
        verify(bubble).getCollideActions();
    }

    /**
     * Test the addCollidables method.
     */
    @Test
    public void addCollidablesTest() {
        Collection<Collidable> collidables = new HashSet<Collidable>();
        collidables.add(player);
        collidables.add(bubble);
        collisionHandler.addCollidable(collidables);
        assertEquals(collisionHandler.getSize(), collidables.size());
        collisionHandler.addCollidable(bubble);
        // Duplicates should not be added, since it's a set
        assertEquals(collisionHandler.getSize(), collidables.size());
    }

    /**
     * Tests the removeCollidables method.
     */
    @Test
    public void removeCollidablesTest() {
        Collection<Collidable> collidables = new HashSet<Collidable>();
        collidables.add(player);
        collidables.add(bubble);
        collidables.add(wall);
        collisionHandler.addCollidable(collidables);
        assertEquals(collisionHandler.getSize(), collidables.size());

        Collection<Collidable> removedCollidables = new HashSet<Collidable>();
        removedCollidables.add(bubble);
        removedCollidables.add(wall);
        collisionHandler.removeCollidable(removedCollidables);
        assertEquals(collisionHandler.getSize(), collidables.size() - removedCollidables.size());
    }

    /**
     * Test addCollidable method.
     */
    @Test
    public void addCollidableTest() {
        assertTrue(collisionHandler.getSize() == 0);
        collisionHandler.addCollidable(player);
        assertTrue(collisionHandler.getSize() > 0);
    }

    /**
     * Test removeCollidable method.
     */
    @Test
    public void removeCollidableTest() {
        collisionHandler.addCollidable(player);
        assertTrue(collisionHandler.getSize() > 0);
        collisionHandler.removeCollidable(player);
        assertTrue(collisionHandler.getSize() == 0);
    }

    /**
     * Test the checkCollision method with outcome false.
     */
    @Test
    public void checkCollisionTestFalse() {
        collisionHandler.addCollidable(player);
        assertFalse(collisionHandler.checkCollision(player));
    }

    /**
     * Test the checkCollision method with outcome true.
     */
    @Test
    public void checkCollisionTestTrue() {
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(bubble);
        when(player.intersectsCollidable(bubble)).thenReturn(true);
        when(bubble.intersectsCollidable(player)).thenReturn(true);
        assertTrue(collisionHandler.checkCollision(player));
        assertTrue(collisionHandler.checkCollision(bubble));
    }

    /**
     * Test the checkCollisionList method.
     */
    @Test
    public void checkCollisionListTest() {
        Collection<Collidable> listCollidables = new HashSet<Collidable>();
        listCollidables.add(player);
        listCollidables.add(bubble);
        collisionHandler.addCollidable(listCollidables);
        when(player.intersectsCollidable(bubble)).thenReturn(true);
        when(bubble.intersectsCollidable(player)).thenReturn(true);
        assertTrue(collisionHandler.checkCollision(listCollidables));
    }

}

package com.sem.btrouble;

import com.sem.btrouble.controller.CollisionHandler;
import com.sem.btrouble.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.newdawn.slick.geom.Shape;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by hung on 22-9-15.
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

    private float colliderX = 1;
    private float colliderY = 1;

    private float collideeX = 1;
    private float collideeY = 1;

    private CollisionHandler collisionHandler;

    @Before
    public void CollisionHandlerTest() {
        collisionHandler = new CollisionHandler();
        assertNotNull(collisionHandler);
    }

    @Test
    public void PlayerCollideBubbleTest() {
        when(player.intersects(bubble)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(player);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersects(bubble);
        verify(player).setAlive(false);
    }

    @Test
    public void PlayerCollideWallSideRightTest() {
        when(player.getCenterX()).thenReturn(-1f);
        when(wall.getCenterX()).thenReturn(1f);
        when(player.intersects(wall)).thenReturn(true);
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersects(wall);
        verify(player).setRightBlocked(true);
    }

    @Test
    public void PlayerCollideWallSideLeftTest() {
        when(player.getCenterX()).thenReturn(1f);
        when(wall.getCenterX()).thenReturn(-1f);
        when(player.intersects(wall)).thenReturn(true);
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersects(wall);
        verify(player).setLeftBlocked(true);
    }

    @Test
    public void PlayerCollideFloorTest() {
        when(player.intersects(floor)).thenReturn(true);
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(floor);
        assertTrue(collisionHandler.checkCollision(player));
        verify(player).intersects(floor);
        verify(player).setFalling(false);
    }

    @Test
    public void BubbleCollideWallLeftTest() {
        when(bubble.getCenterX()).thenReturn(1f);
        when(wall.getCenterX()).thenReturn(-1f);
        when(bubble.intersects(wall)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersects(wall);
        verify(bubble).bounceX(false);
    }

    @Test
    public void BubbleCollideWallRightTest() {
        when(bubble.getCenterX()).thenReturn(-1f);
        when(wall.getCenterX()).thenReturn(1f);
        when(bubble.intersects(wall)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(wall);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersects(wall);
        verify(bubble).bounceX(true);
    }

    @Test
    public void BubbleCollideFloorTest() {
        when(bubble.intersects(floor)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(floor);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersects(floor);
    }

    @Test
    public void BubbleCollideRopeTest() {
        when(bubble.intersects(rope)).thenReturn(true);
        collisionHandler.addCollidable(bubble);
        collisionHandler.addCollidable(rope);
        assertTrue(collisionHandler.checkCollision(bubble));
        verify(bubble).intersects(rope);
        verify(bubble).split();
        verify(rope).setCollided(true);
    }

    @Test
    public void AddCollidablesTest() {
        Collection<Shape> collidables = new HashSet<Shape>();
        collidables.add(player);
        collidables.add(bubble);
        collisionHandler.addCollidable(collidables);
        assertEquals(collisionHandler.getSize(), collidables.size());
        collisionHandler.addCollidable(bubble);
        // Duplicates should not be added, since it's a set
        assertEquals(collisionHandler.getSize(), collidables.size());
    }

    @Test
    public void RemoveCollidablesTest() {
        Collection<Shape> collidables = new HashSet<Shape>();
        collidables.add(player);
        collidables.add(bubble);
        collidables.add(wall);
        collisionHandler.addCollidable(collidables);
        assertEquals(collisionHandler.getSize(), collidables.size());

        Collection<Shape> removedCollidables = new HashSet<Shape>();
        removedCollidables.add(bubble);
        removedCollidables.add(wall);
        collisionHandler.removeCollidable(removedCollidables);
        assertEquals(collisionHandler.getSize(), collidables.size() - removedCollidables.size());
    }

    @Test
    public void AddCollidableTest() {
        assertTrue(collisionHandler.getSize() == 0);
        collisionHandler.addCollidable(player);
        assertTrue(collisionHandler.getSize() > 0);
    }

    @Test
    public void RemoveCollidableTest() {
        collisionHandler.addCollidable(player);
        assertTrue(collisionHandler.getSize() > 0);
        collisionHandler.removeCollidable(player);
        assertTrue(collisionHandler.getSize() == 0);
    }

    @Test
    public void checkCollisionTestFalse() {
        collisionHandler.addCollidable(player);
        assertFalse(collisionHandler.checkCollision(player));
    }

    @Test
    public void checkCollisionTestTrue() {
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(bubble);
        when(player.intersects(bubble)).thenReturn(true);
        when(bubble.intersects(player)).thenReturn(true);
        assertTrue(collisionHandler.checkCollision(player));
        assertTrue(collisionHandler.checkCollision(bubble));
    }

    @Test
    public void checkCollisionListTest() {
        Collection<Shape> listCollidables = new HashSet<Shape>();
        listCollidables.add(player);
        listCollidables.add(bubble);
        collisionHandler.addCollidable(listCollidables);
        when(player.intersects(bubble)).thenReturn(true);
        when(bubble.intersects(player)).thenReturn(true);
        assertTrue(collisionHandler.checkCollision(listCollidables));
    }

}

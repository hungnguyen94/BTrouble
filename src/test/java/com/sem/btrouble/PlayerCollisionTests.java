package com.sem.btrouble;

import com.sem.btrouble.controller.CollisionHandler;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Wall;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hung on 16-10-15.
 */
public class PlayerCollisionTests {
    CollisionHandler collisionHandler;

//    @Mock
    Player player;
    @Mock
    Bubble bubble;
    @Spy
    Floor floor;
    @Spy
    Wall wall;

    private static final float CENTER_X = 9f;
    private static final float CENTER_Y = 11f;
    private static final float HEIGHT = 14f;
    private static final float WIDTH = 15f;

    @Before
    public void setUp() {
        collisionHandler = new CollisionHandler();
        player = new Player(0, 0);
        collisionHandler.addCollidable(player);
    }

    @Test
    public void playerBubbleCollision() {
        assertTrue(player.isAlive());
        player.getCollideActions().get(Bubble.class).onCollision(bubble);
        assertFalse(player.isAlive());
    }


    @Test
    public void playerFloorCollision() {
        floor = Mockito.spy(new Floor(0, 0, HEIGHT, WIDTH));
        assertTrue(player.isFalling());
        player.getCollideActions().get(Floor.class).onCollision(floor);
        Mockito.verify(floor).getCenterY();
        assertFalse(player.isFalling());
        float newCenter = floor.getCenterY() - (floor.getHeight() + player.getHeight()) / 2;
        assertTrue(player.getCenterY() == newCenter);
    }

    @Test
    public void playerWallCollisionRight() {
        wall = Mockito.spy(new Wall(-10, 10, HEIGHT, WIDTH));
        assertFalse(player.getLeftBlocked());
        player.getCollideActions().get(Wall.class).onCollision(wall);
        Mockito.verify(wall, Mockito.times(2)).getCenterX();
        assertTrue(player.getLeftBlocked());
        float newCenterX = wall.getCenterX() + (wall.getWidth() + player.getWidth()) / 2;
        assertTrue(player.getCenterX() == newCenterX);
    }

    @Test
    public void playerWallCollisionLeft() {
        wall = Mockito.spy(new Wall(100, 10, HEIGHT, WIDTH));
        assertFalse(player.getRightBlocked());
        player.getCollideActions().get(Wall.class).onCollision(wall);
        Mockito.verify(wall, Mockito.times(3)).getCenterX();
        assertTrue(player.getRightBlocked());
        float newCenterX = wall.getCenterX() - (wall.getWidth() + player.getWidth()) / 2;
        assertTrue(player.getCenterX() == newCenterX);
    }


}

package com.sem.btrouble;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.model.Room;
import com.sem.btrouble.tools.DataLoader;

/**
 * Test cases for the data loader.
 * @author Christian
 *
 */
public class DataLoaderTest {
    
    private DataLoader dataloader;
    
    /**
     * Initialize the dataloader.
     * @throws SlickException if dataloader cannot be initialized.
     */
    @Before
    public void init() throws SlickException {
        dataloader = new DataLoader(DataLoader.STANDARD_LOCATION);
    }
    
    /**
     * Test if the dataloader does not contain a non existing file.
     */
    @Test
    public void testNonExistingFile(){
        dataloader = new DataLoader("random");
        assertFalse(dataloader.hasRoom(0));
    }
    
    /**
     * Test if the dataloader has a room.
     */
    @Test
    public void testDataFileHasRoom() {
        assertTrue(dataloader.hasRoom(0));
    }
    
    /**
     * Test if the data rooms have bubbles.
     */
    @Test
    public void testDataRoomsHaveBubbles() {
        int currentLevel = 0;
        while(dataloader.hasRoom(currentLevel)){
            Room room = dataloader.loadRoom(0);
            assertTrue(room.getBubbles().size() > 0);
            currentLevel++;
        }   
    }
    
    /**
     * Test if the data rooms have floors.
     */
    @Test
    public void testDataRoomsHaveFloors() {
        int currentLevel = 0;
        while(dataloader.hasRoom(currentLevel)){
            Room room = dataloader.loadRoom(0);
            assertTrue(room.getFloors().size() > 0);
            currentLevel++;
        }   
    }
    
    /**
     * Test if the data rooms have walls.
     */
    @Test
    public void testDataRoomsHaveWalls() {
        int currentLevel = 0;
        while(dataloader.hasRoom(currentLevel)){
            Room room = dataloader.loadRoom(0);
            assertTrue(room.getWalls().size() > 0);
            currentLevel++;
        }   
    }
    
    /**
     * Test if the dataloader handles 'null' rooms.
     */
    @Test
    public void testParseRoomNodeNull(){
        Room room = dataloader.parseRoom(null);
        assertTrue(room.getBubbles().size() == 0);
    }
    
    /**
     * Test if the dataloader handles out of bounds indices.
     */
    @Test
    public void loadRoomWrongIndex(){
        Room room = dataloader.loadRoom(-1);
        assertTrue(room.getBubbles().size() == 0);
    }
    
}
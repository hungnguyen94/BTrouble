package com.sem.btrouble;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.w3c.dom.Node;

import com.sem.btrouble.model.Room;
import com.sem.btrouble.tools.DataLoader;

public class DataLoaderTest {
    
    DataLoader dataloader;
    
    @Before
    public void init() throws SlickException {
        dataloader = new DataLoader(DataLoader.STANDARD_LOCATION);
    }
    
    @Test
    public void testNonExistingFile(){
        dataloader = new DataLoader("random");
        assertFalse(dataloader.hasRoom(0));
    }
    
    @Test
    public void testDataFileHasRoom() {
        assertTrue(dataloader.hasRoom(0));
    }
    
    @Test
    public void testDataRoomsHaveBubbles() {
        int currentLevel = 0;
        while(dataloader.hasRoom(currentLevel)){
            Room room = dataloader.loadRoom(0);
            assertTrue(room.getBubbles().size() > 0);
            currentLevel ++;
        }   
    }
    
    @Test
    public void testDataRoomsHaveFloors() {
        int currentLevel = 0;
        while(dataloader.hasRoom(currentLevel)){
            Room room = dataloader.loadRoom(0);
            assertTrue(room.getFloors().size() > 0);
            currentLevel ++;
        }   
    }
    
    @Test
    public void testDataRoomsHaveWalls() {
        int currentLevel = 0;
        while(dataloader.hasRoom(currentLevel)){
            Room room = dataloader.loadRoom(0);
            assertTrue(room.getWalls().size() > 0);
            currentLevel ++;
        }   
    }
    
    @Test
    public void testParseRoomNodeNull(){
        Room room = dataloader.parseRoom(null);
        assertTrue(room.getBubbles().size() == 0);
    }
    
    @Test
    public void loadRoomWrongIndex(){
        Room room = dataloader.loadRoom(-1);
        assertTrue(room.getBubbles().size() == 0);
    }
    
}

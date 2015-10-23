package com.sem.btrouble;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.tools.DataLoader;
import com.sem.btrouble.view.GameView;

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
        Room room = dataloader.loadRoom(0);
        System.out.println(room.getBubbles().size());
    }
    
    
}

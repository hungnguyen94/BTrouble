package com.sem.btrouble.tools;

import java.io.File;
import java.util.ArrayList;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Model;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;

public class DataLoader {
  static File file;
  
  public static void init(){
    file = new File("gamedata");
  }
  
  public static Room getRoom(int id){
    ArrayList<Wall> walls = new ArrayList<Wall>();
    ArrayList<Floor> floors = new ArrayList<Floor>();
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    
    
    if(id == 0){
      walls.add(new Wall(0, 0, 20, 800));
      walls.add(new Wall(1103, 0, 20, 800));
      floors.add(new Floor(0, 794, 1123, 50));
      floors.add(new Floor(0, 0, 1123, 50));
      bubbles.add(new Bubble(1, Model.getRoomWidth()/2, 200));
    }
    
    if(id != 0){
      walls.add(new Wall(0, 0, 20, 800));
      walls.add(new Wall(1103, 0, 20, 800));
      floors.add(new Floor(0, 794, 1123, 50));
      floors.add(new Floor(0, 0, 1123, 50));
      bubbles.add(new Bubble(1, Model.getRoomWidth()/2, 200));
    }
    
    return new Room(id, walls, floors, bubbles);
  }
}

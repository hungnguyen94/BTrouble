package com.sem.btrouble.tools;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;

public class DataLoader {
  private static File file;
  private static ArrayList<Room> rooms;
  static NodeList roomList;

  public static void main(String[] args) {
    init();
  }

  public static void init() {
    file = new File("src/main/resources/gamedata.xml");
    rooms = new ArrayList<Room>();

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = factory.newDocumentBuilder();

      Document data = db.parse(file);
      data.getDocumentElement().normalize();
      roomList = data.getElementsByTagName("Room");
      
      for(int i = 0; i < roomList.getLength(); i ++){
        rooms.add(getRoom(i));
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static float[] getAttributesByNames(Node node, String[] names) {
    float[] res = new float[names.length];
    for (int i = 0; i < names.length; i++) {
      res[i] = Float.parseFloat(node.getAttributes().getNamedItem(names[i]).getTextContent());
    }
    return res;
  }

  public static Room getRoom(int roomNr) {
    ArrayList<Wall> walls = new ArrayList<Wall>();
    ArrayList<Floor> floors = new ArrayList<Floor>();
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();

    Node roomNode = roomList.item(roomNr);
    
    NodeList roomData = roomNode.getChildNodes();
    String[] attr_names;
    float[] attr;
    
    for (int j = 0; j < roomData.getLength(); j++) {
      Node roomElement = roomData.item(j);
      switch (roomElement.getNodeName()) {
        case "Wall":
          attr_names = new String[] { "x", "y", "width", "height" };
          attr = getAttributesByNames(roomElement, attr_names);
          walls.add(new Wall(attr[0], attr[1], attr[2], attr[3]));
          break;
        case "Floor":
          attr_names = new String[] { "x", "y", "width", "height" };
          attr = getAttributesByNames(roomElement, attr_names);
          floors.add(new Floor(attr[0], attr[1], attr[2], attr[3]));
          break;
        case "Bubble":
          attr_names = new String[] { "size", "x", "y" };
          attr = getAttributesByNames(roomElement, attr_names);
          bubbles.add(new Bubble((int) attr[0], attr[1], attr[2]));
          break;
      }
    }

    return new Room(walls, floors, bubbles);
  }

  public static ArrayList<Room> getRooms() {
    ArrayList<Room> res = new ArrayList<Room>();
    for (int i = 0; i < rooms.size(); i++) {
      res.add(getRoom(i));
    }
    return res;
  }
}

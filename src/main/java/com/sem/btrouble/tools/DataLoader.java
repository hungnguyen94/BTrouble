package com.sem.btrouble.tools;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private File file;
    private DocumentBuilderFactory factory;
    public static final String STANDARD_LOCATION = "src/main/resources/data.xml";

    public DataLoader(String file) {
        this.file = new File(file);
        factory = DocumentBuilderFactory.newInstance();
    }

    public Room loadRoom(int index) {
        Room room = new Room();
        try {
            Element levelElement = loadFileData("Level", index);
            room = parseRoom(levelElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }
    
    public Element loadFileData(String nodeName, int index) throws ParserConfigurationException, SAXException, IOException, ParseException{
        if(!hasRoom(index)) {
            index = 0;
        }
        Element res = null;
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document data = builder.parse(file);
        data.getDocumentElement().normalize();

        Node item = data.getElementsByTagName("Level").item(index);
        if (item.getNodeType() == Node.ELEMENT_NODE) {
            res = (Element) item;
        } else {
            throw new ParseException("Room node should be an element", -3);
        }
        return res;
    }

    public Room parseRoom(Element levelData) {
        Room room = new Room();
        
        NamedNodeMap attributes = levelData.getAttributes();
        int spawnX = Integer.parseInt(attributes.getNamedItem("spawnX").getNodeValue());
        int spawnY = Integer.parseInt(attributes.getNamedItem("spawnY").getNodeValue());
        String background = attributes.getNamedItem("background").getNodeValue();

        ArrayList<Wall> walls = parseWalls(levelData);
        ArrayList<Floor> floors = parseFloors(levelData);
        ArrayList<Floor> movableFloors = parseMovableFloors(levelData);
        floors.addAll(movableFloors);

        room = new Room(walls, floors, spawnX, spawnY, background);
        room.addMovables(movableFloors);

        return room;
    }

    public ArrayList<Floor> parseMovableFloors(Element levelData) {
        NodeList floorData = levelData.getElementsByTagName("MovableFloor");
        ArrayList<Floor> floors = new ArrayList<Floor>();
        NamedNodeMap attributes;

        for (int i = 0; i < floorData.getLength(); i++) {
            attributes = floorData.item(i).getAttributes();

            float x = Float.parseFloat(attributes.getNamedItem("x").getNodeValue());
            float y = Float.parseFloat(attributes.getNamedItem("y").getNodeValue());
            float width = Float.parseFloat(attributes.getNamedItem("width").getNodeValue());
            float height = Float.parseFloat(attributes.getNamedItem("height").getNodeValue());

            floors.add(new Floor(x, y, width, height));
        }
        return floors;
    }

    public ArrayList<Wall> parseWalls(Element levelData) {
        NodeList wallData = levelData.getElementsByTagName("Wall");
        ArrayList<Wall> walls = new ArrayList<Wall>();
        NamedNodeMap attributes;

        for (int i = 0; i < wallData.getLength(); i++) {
            attributes = wallData.item(i).getAttributes();

            float x = Float.parseFloat(attributes.getNamedItem("x").getNodeValue());
            float y = Float.parseFloat(attributes.getNamedItem("y").getNodeValue());
            float width = Float.parseFloat(attributes.getNamedItem("width").getNodeValue());
            float height = Float.parseFloat(attributes.getNamedItem("height").getNodeValue());
            walls.add(new Wall(x, y, width, height));
        }
        return walls;
    }

    public ArrayList<Floor> parseFloors(Element levelData) {
        NodeList floorData = levelData.getElementsByTagName("Floor");
        ArrayList<Floor> floors = new ArrayList<Floor>();
        NamedNodeMap attributes;

        for (int i = 0; i < floorData.getLength(); i++) {
            attributes = floorData.item(i).getAttributes();

            float x = Float.parseFloat(attributes.getNamedItem("x").getNodeValue());
            float y = Float.parseFloat(attributes.getNamedItem("y").getNodeValue());
            float width = Float.parseFloat(attributes.getNamedItem("width").getNodeValue());
            float height = Float.parseFloat(attributes.getNamedItem("height").getNodeValue());

            floors.add(new Floor(x, y, width, height));
        }
        return floors;
    }

    public ArrayList<Bubble> parseBubbles(Element levelData) {
        NodeList bubbleData = levelData.getElementsByTagName("Bubble");
        ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
        NamedNodeMap attributes;

        for (int i = 0; i < bubbleData.getLength(); i++) {
            attributes = bubbleData.item(i).getAttributes();
            int size = Integer.parseInt(attributes.getNamedItem("size").getNodeValue());
            float x = Float.parseFloat(attributes.getNamedItem("x").getNodeValue());
            float y = Float.parseFloat(attributes.getNamedItem("y").getNodeValue());
            bubbles.add(new Bubble(size, x, y));
        }
        return bubbles;
    }
    
    public List<Bubble> loadBubbles(int index) {
        if(!hasRoom(index)) {
            index = 0;
        }
        List<Bubble> bubbles = new ArrayList<Bubble>();
        try {
            Element levelElement = loadFileData("Level", index);
            bubbles = parseBubbles(levelElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bubbles;
    }

    public boolean hasRoom(int currentLevel) {
        boolean res = false;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document data = builder.parse(file);
            data.getDocumentElement().normalize();
            res = data.getElementsByTagName("Level").getLength() > currentLevel;
        } catch (Exception e) {
            res = false;
        }

        return res;
    }

}
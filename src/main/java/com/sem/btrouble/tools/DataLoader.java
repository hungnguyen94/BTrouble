package com.sem.btrouble.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;

public class DataLoader {
    private File file;
    private DocumentBuilderFactory factory;
    public static final String STANDARD_LOCATION = "src/main/resources/data.xml";

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {
        DataLoader loader = new DataLoader("src/main/resources/data.xml");
        loader.loadRoom(0);
    }

    public DataLoader(String file) {
        this.file = new File(file);
        factory = DocumentBuilderFactory.newInstance();
    }

    public Room loadRoom(int index) {
        Room room = new Room();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document data = builder.parse(file);
            data.getDocumentElement().normalize();

            room = parseRoom(data.getElementsByTagName("Level").item(index));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    private Room parseRoom(Node item) {
        Room room = new Room();
        try {
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element levelData = (Element) item;

                NamedNodeMap attributes = levelData.getAttributes();
                // int roomHeight =
                // Integer.parseInt(attributes.getNamedItem("roomHeight").getNodeValue());
                // int roomWidth =
                // Integer.parseInt(attributes.getNamedItem("roomWidth").getNodeValue());
                int spawnX = Integer.parseInt(attributes.getNamedItem("spawnX").getNodeValue());
                int spawnY = Integer.parseInt(attributes.getNamedItem("spawnY").getNodeValue());
                String background = attributes.getNamedItem("background").getNodeValue();

                ArrayList<Wall> walls = parseWalls(levelData);
                ArrayList<Floor> floors = parseFloors(levelData);
                ArrayList<Bubble> bubbles = parseBubbles(levelData);

                room = new Room(walls, floors, bubbles, spawnX, spawnY, background);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    private ArrayList<Wall> parseWalls(Element levelData) {
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

    private ArrayList<Floor> parseFloors(Element levelData) {
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

    private ArrayList<Bubble> parseBubbles(Element levelData) {
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

    public boolean hasRoom(int currentLevel) {
        boolean res = false;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document data = builder.parse(file);
            data.getDocumentElement().normalize();
            res = data.getElementsByTagName("Level").item(10) != null;
        } catch (Exception e) {
            res = false;
        }

        return res;
    }

}

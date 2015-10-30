package com.sem.btrouble.tools;

import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.model.Wall;
import com.sem.btrouble.observering.Direction;
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

/**
 * 
 * @author Chris(tian)
 * This class generates rooms according to the XML data.
 *
 */
public class DataLoader {
    private File file;
    private DocumentBuilderFactory factory;
    public static final String STANDARD_LOCATION = "src/main/resources/data.xml";

    /**
     * Constructor for the Dataloader class.
     * @param file This is the file that has to be imported.
     */
    public DataLoader(String file) {
        this.file = new File(file);
        factory = DocumentBuilderFactory.newInstance();
    }

    /**
     * Parsers the room from the XML file.
     * @param index The index'th room to be loaded.
     * @return the room that has been parsed.
     */
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

    public Element loadFileData(String nodeName, int index) throws ParserConfigurationException, SAXException, IOException, ParseException {
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

    /**
     * Read all the objects from the current node.
     * @param item the node of the current room.
     * @return return the parsed room.
     */
    public Room parseRoom(Node item) {
        Room room = new Room();
        try {
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element levelData = (Element) item;

                NamedNodeMap attributes = levelData.getAttributes();
                int spawnX = Integer.parseInt(attributes.getNamedItem("spawnX").getNodeValue());
                int spawnY = Integer.parseInt(attributes.getNamedItem("spawnY").getNodeValue());
                String background = attributes.getNamedItem("background").getNodeValue();

                ArrayList<Wall> walls = parseWalls(levelData);
                ArrayList<Floor> floors = parseFloors(levelData);
                ArrayList<Wall> movableWalls = parseMovableWalls(levelData);
                ArrayList<Floor> movableFloors = parseMovableFloors(levelData);

                room = new Room(walls, floors, spawnX, spawnY, background);
                room.addMovableFloors(movableFloors);
                room.addMovableWalls(movableWalls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    /**
     * This function parses all the movable floors.
     * @param levelData the data for the movable floors.
     * @return a dataset of movable floors.
     */
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
            float speed = Float.parseFloat(attributes.getNamedItem("speed").getNodeValue());
            Direction direction = Direction.stringToDirection(attributes.getNamedItem("direction").getNodeValue());

            floors.add(new Floor(x, y, width, height, speed, direction));
        }
        return floors;
    }

    /**
     * This function parses all the movable walls.
     * @param levelData the data for the movable walls.
     * @return a dataset of movable walls.
     */
    public ArrayList<Wall> parseMovableWalls(Element levelData) {
        NodeList wallData = levelData.getElementsByTagName("MovableWall");
        ArrayList<Wall> walls = new ArrayList<>();
        NamedNodeMap attributes;

        for (int i = 0; i < wallData.getLength(); i++) {
            attributes = wallData.item(i).getAttributes();

            float x = Float.parseFloat(attributes.getNamedItem("x").getNodeValue());
            float y = Float.parseFloat(attributes.getNamedItem("y").getNodeValue());
            float width = Float.parseFloat(attributes.getNamedItem("width").getNodeValue());
            float height = Float.parseFloat(attributes.getNamedItem("height").getNodeValue());
            float speed = Float.parseFloat(attributes.getNamedItem("speed").getNodeValue());
            Direction direction = Direction.stringToDirection(
                    attributes.getNamedItem("direction").getNodeValue());

            walls.add(new Wall(x, y, width, height, speed, direction));
        }
        return walls;
    }

    /**
     * This functions parses all the walls in the data.
     * @param levelData the data containing all the walls.
     * @return a dataset of walls.
     */
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

    /**
     * This functions parses all the floors from the data.
     * @param levelData the data containing all the floors.
     * @return a dataset containing all the floors.
     */
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

    /**
     * This function parses all the bubbles from the data.
     * @param levelData the data containing all the bubbles.
     * @return a dataset of bubbles.
     */
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
    /**
     * This function parses the bubbles from a 
     * specific level. 
     * @param index Index of the level.
     * @return A list containing bubbles.
     */
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

    /**
     * This function checks if a certain level exists in the XML file.
     * @param currentLevel level to check.
     * @return true if it exists, if not false.
     */
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
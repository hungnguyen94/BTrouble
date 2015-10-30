package com.sem.btrouble.controller;

import com.sem.btrouble.model.Floor;
import com.sem.btrouble.model.Wall;
import org.newdawn.slick.Graphics;

import java.util.List;

/**
 * @author Hung
 */
public class BorderController extends ControllerDecorator {

    private List<Wall> wallList;
    private List<Floor> floorList;
    private Controller controller;

    /**
     * Constructor for the controller decorator.
     *
     * @param controller controller to be decorated.
     * @param wallList The list of walls
     * @param floorList The list of floors
     */
    public BorderController(Controller controller, List<Wall> wallList, List<Floor> floorList) {
        super(controller);
        this.controller = controller;
        this.wallList = wallList;
        this.floorList = floorList;
        controller.addListReference(this.wallList);
        controller.addListReference(this.floorList);
    }

    /**
     * Moves the walls and floors.
     */
    public void update() {
        for(Wall wall : wallList) {
            wall.move();
        }
        for(Floor floor : floorList) {
            floor.move();
        }
        controller.update();
    }

    /**
     * Draw the objects.
     *
     * @param graphics The graphics
     */
    @Override
    public void draw(Graphics graphics) {
        for(Wall wall : wallList) {
            wall.draw(graphics);
        }
        for(Floor floor : floorList) {
            floor.draw(graphics);
        }
        controller.draw(graphics);
    }
}

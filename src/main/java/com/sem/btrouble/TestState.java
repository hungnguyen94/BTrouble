//package com.sem.btrouble;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.newdawn.slick.GameContainer;
//import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Image;
//import org.newdawn.slick.Input;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.TrueTypeFont;
//import org.newdawn.slick.state.BasicGameState;
//import org.newdawn.slick.state.StateBasedGame;
//import org.newdawn.slick.util.ResourceLoader;
//
//import com.sem.btrouble.model.Drawable;
//import com.sem.btrouble.model.Game;
//import com.sem.btrouble.model.Player;
//import com.sem.btrouble.model.Room;
//import com.sem.btrouble.observering.LevelObserver;
//
///**
// * Test state
// */
//public class TestState extends BasicGameState implements LevelObserver {
//    private TrueTypeFont font;
//    private List<Drawable> drawables;
//    private Image background;
//    private Game game;
//    private Player player;
//
//    /**
//     * Initialize method of the slick2d library.
//     *
//     * @param gc
//     *            should be the GameContainer containing the game.
//     * @param sbg
//     *            the reference to the StateBasedGame.
//     * @throws SlickException
//     *             when the game could not be initialized.
//     */
//    @Override
//    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//        // load font from a .ttf file
//        try {
//            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");
//            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
//                    inputStream);
//            awtFont = awtFont.deriveFont(24f); // set font size
//            font = new TrueTypeFont(awtFont, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        drawables = new ArrayList<Drawable>();
//        background = new Image("Sprites/background1280x720.png");
//
//        player = new Player(1f, 1f);
//        newGame(player);
//    }
//
//    /**
//     * Loads a new game.
//     * @param player Player in the game.
//     */
//    private void newGame(Player player) {
//        game = new Game(player, this);
//        Room room = new Room();
//        room.loadRoom();
//        game.loadLevel(room);
//    }
//
//    /**
//     * Update method of the slick2d library.
//     *
//     * @param gc
//     *            should be the GameContainer containing the game
//     * @param sbg
//     *            the reference to the StateBasedGame.
//     * @param delta
//     *            should be an integer representing the speed of the player
//     * @throws SlickException
//     *             when the controller could not be updated
//     */
//    @Override
//    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
//        Input input = gc.getInput();
//        if (input.isKeyDown(Input.KEY_LEFT)) {
//            player.moveLeft(delta);
//        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
//            player.moveRight(delta);
//        }
//
//        game.updateGame();
//    }
//
//    /**
//     * Render method of the slick2d library.
//     *
//     * @param gc
//     *            should be the GameContainer containing the game
//     * @param sbg
//     *            the reference to the StateBasedGame.
//     * @param graphics
//     *            should be the graphics handler of the game
//     * @throws SlickException
//     *             when an item could not be drawn.
//     */
//    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics)
//            throws SlickException {
//        background.draw(0f, 0f);
//        draw(graphics);
//        System.out.println("Draw in TestState");
//        System.out.println("Player coordinates: " + player.getCenterX() + ", " + player.getCenterY());
//    }
//
//    /**
//     * Draws all the elements to the screen.
//     *
//     * @param graphics
//     *            Graphics object from Slick2D
//     * @throws SlickException
//     */
//    public void draw(Graphics graphics) throws SlickException {
//        for(Drawable drawable : drawables) {
//            drawable.draw(graphics);
//        }
//    }
//
//
//    @Override
//    public int getID() {
//        return 0;
//    }
//
//    /**
//     * This method is called when the observer is notified about a update.
//     */
//    @Override
//    public void update(List<Drawable> drawables) {
//        this.drawables = drawables;
//        System.out.println("Updated drawables");
//    }
//
//    /**
//     * This method is called when a level is won.
//     */
//    @Override
//    public void levelWon() {
//
//    }
//
//    /**
//     * This method is called when a level is lost.
//     */
//    @Override
//    public void levelLost() {
//
//    }
//}

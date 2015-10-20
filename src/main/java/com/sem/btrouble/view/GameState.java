package com.sem.btrouble.view;

import com.sem.btrouble.BTrouble;
import com.sem.btrouble.controller.AbstractGame;
import com.sem.btrouble.controller.MultiPlayerGame;
import com.sem.btrouble.controller.MultiPlayerSurvivalGame;
import com.sem.btrouble.controller.SinglePlayerGame;
import com.sem.btrouble.controller.SinglePlayerSurvivalGame;
import com.sem.btrouble.model.Drawable;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.Direction;
import com.sem.btrouble.observering.LevelObserver;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Test state.
 */
public class GameState extends BasicGameState implements LevelObserver {
    private TrueTypeFont font;
    private List<Drawable> drawables;
    private Image background;
    private AbstractGame game;
    private Player player;
    private Player secondPlayer;

    private StateBasedGame stateBasedGame;

    /**
     * Initialize method of the slick2d library.
     *
     * @param gc should be the GameContainer containing the game.
     * @param sbg the reference to the StateBasedGame.
     * @throws SlickException when the game could not be initialized.
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.stateBasedGame = sbg;
        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        drawables = new ArrayList<Drawable>();
        background = new Image("Sprites/background1280x720.png");
    }

    /**
     * Loads a new game.
     */
    private void newGame() {
        Room room = new Room();
        room.loadRoom();
        if(BTrouble.getMultiplayer()) {
            if(BTrouble.getMultiplayer()) {
                if(BTrouble.getSurvival()) {
                    game = new MultiPlayerSurvivalGame(room, this);
                } else {
                    game = new MultiPlayerGame(room, this);
                }
                secondPlayer = new Player(2f, 2f);
                game.addPlayer(secondPlayer);
            }
        } else {
            if(BTrouble.getSurvival()) {
                game = new SinglePlayerSurvivalGame(room, this);
            } else {
                game = new SinglePlayerGame(room, this);
            }
        }
        player = new Player(1f, 1f);
        game.addPlayer(player);
        game.startLevel();
    }

    /**
     * Update method of the slick2d library.
     *
     * @param gc should be the GameContainer containing the game
     * @param sbg the reference to the StateBasedGame.
     * @param delta should be an integer representing the speed of the player
     * @throws SlickException when the controller could not be updated
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_LEFT)) {
            game.movePlayer(player, Direction.LEFT, delta);
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            game.movePlayer(player, Direction.RIGHT, delta);
        }
        if(input.isKeyPressed(Input.KEY_SPACE)) {
            game.fireRope(player);
        }
        if(BTrouble.getMultiplayer()) {
            if (input.isKeyDown(Input.KEY_A)) {
                game.movePlayer(secondPlayer, Direction.LEFT, delta);
            } else if (input.isKeyDown(Input.KEY_D)) {
                game.movePlayer(secondPlayer, Direction.RIGHT, delta);
            }
            if(input.isKeyPressed(Input.KEY_W)) {
                game.fireRope(secondPlayer);
            }
        }
        game.updateGame();
    }

    /**
     * Render method of the slick2d library.
     *
     * @param gc should be the GameContainer containing the game
     * @param sbg the reference to the StateBasedGame.
     * @param graphics should be the graphics handler of the game
     * @throws SlickException when an item could not be drawn.
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics)
            throws SlickException {
        background.draw(0f, 0f);
        draw(graphics);
    }

    /**
     * Draws all the elements to the screen.
     *
     * @param graphics Graphics object from Slick2D
     */
    public void draw(Graphics graphics) {
//        for(Drawable drawable : drawables) {
//            drawable.draw(graphics);
//        }
        game.draw(graphics);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        newGame();
    }

    @Override
    public int getID() {
        return 1;
    }

    /**
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {

    }

    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
    }
}

package com.sem.btrouble.view;

import com.sem.btrouble.BTrouble;
import com.sem.btrouble.game.AbstractGame;
import com.sem.btrouble.game.SinglePlayerGame;
import com.sem.btrouble.game.SinglePlayerSurvivalGame;
import com.sem.btrouble.model.Bubble;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.Room;
import com.sem.btrouble.observering.Direction;
import com.sem.btrouble.observering.LevelObserver;
import com.sem.btrouble.tools.DataLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
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
    private AbstractGame game;
    private Player player;
    private StateBasedGame stateBasedGame;
    private DataLoader dataloader;
    private int currentLevel;
    

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
        this.currentLevel = 0;
        this.dataloader = new DataLoader(DataLoader.STANDARD_LOCATION); 
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
        player = new Player(1f, 1f);
    }

    /**
     * Loads a new game.
     */
    private void newGame() {
        Room room = dataloader.loadRoom(currentLevel);

        if(BTrouble.getSurvival()) {
            game = new SinglePlayerSurvivalGame(room, this);
        } else {
            game = new SinglePlayerGame(room, this);
        }

        List<Bubble> blist = new ArrayList<>();

        blist.add(new Bubble(3, 100, 100));
        blist.add(new Bubble(3, 200, 200));
        blist.add(new Bubble(3, 500, 150));

        game.spawnBubbles(blist);
        game.addPlayer(player);
        game.startGame();
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
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
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
        draw(graphics);
        drawLives(graphics);
    }

    /**
     * Draw lives on screen.
     * @param graphics Graphics handler.
     */
    public void drawLives(Graphics graphics) {
        SpriteSheet livesImage = null;
        graphics.setColor(Color.white);
        try {
            livesImage = new SpriteSheet("Sprites/lives_spritesheet.jpg", 381, 171);
            livesImage.getSprite(player.getLives(), 0).draw(190, 670, (float) 0.286);
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws all the elements to the screen.
     *
     * @param graphics Graphics object from Slick2D
     */
    public void draw(Graphics graphics) {
        game.draw(graphics);
        drawWallet(graphics);
    }

    /**
     * Draws the wallet.
     * @param graphics graphics
     */
    private void drawWallet(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(String.valueOf(player.getWallet().getValue()), 1000, 660);
    }

    /**
     * This is run when this state is entered.
     * @param container gamecontainer
     * @param game statebasedgame
     * @throws SlickException exception from slick2d
     */
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        if(this.game == null || !this.game.isLevelRunning()) {
            newGame();
        }
    }

    /**
     * Return the id of this state.
     * @return state id
     */
    @Override
    public int getID() {
        return 1;
    }

    /**
     * This method is called when a level is won.
     */
    @Override
    public void levelWon() {
        stateBasedGame.enterState(0, new FadeInTransition(Color.gray), new BlobbyTransition(Color.red));
        currentLevel ++;
    }

    /**
     * This method is called when a level is lost.
     */
    @Override
    public void levelLost() {
        stateBasedGame.enterState(4, new FadeOutTransition(Color.white), new FadeInTransition(Color.black));
    }
}

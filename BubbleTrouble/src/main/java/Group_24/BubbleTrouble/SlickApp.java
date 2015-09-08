package Group_24.BubbleTrouble;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

import javax.swing.*;

/**
 * Application running the game.
 *
 */
@SuppressWarnings("serial")
public class SlickApp extends BasicGame
{

    private Image player;
    private static final int REWARD_BUBBLE = 100;
    private static ArrayList<Bubble> newBubbles;
    private static ArrayList<Bubble> oldBubbles;

	public SlickApp(String gamename) {
        super(gamename);
    }

    public void init(GameContainer gc) throws SlickException {
        newBubbles = new ArrayList<Bubble>();
        oldBubbles = new ArrayList<Bubble>();
        player = new Image("sprites/Player.png");

        // TODO could add import RoomData from file
        Model.init();
        ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
        bubbles.add(new Bubble(3, 100, 50));
        RoomData data = new RoomData(bubbles);
        Model.addRoom(new Room(data));

        Model.addPlayer(new Player(100, Model.getRoomHeight() / 2));
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        for(Bubble bubble: Model.getBubbles()) {

            for(Floor floor: Model.getCurrentRoom().getFloors())
                if(bubble.getY() + bubble.getWidth() >= floor.getY())
                    bubble.collide(CollisionEvent.TYPE_FLOOR);

            // CollisionEvent detection for bubble against floor
            if (bubble.getY() + bubble.getWidth() >= Model.getRoomHeight()) {
                bubble.collide(CollisionEvent.TYPE_FLOOR);
            }

            // Collision detection for walls
            for(Wall wall: Model.getCurrentRoom().getWalls()) {
                if(bubble.getX() <= (wall.getX() + wall.getWidth()))
                    bubble.collide(CollisionEvent.TYPE_WALL);
                if(bubble.getX() + bubble.getWidth() >= wall.getX())
                    bubble.collide(CollisionEvent.TYPE_WALL);
            }

            // CollisionEvent detection for bubble against room left
            if (bubble.getX() <= 0) {
                bubble.collide(CollisionEvent.TYPE_WALL);
            }
            // CollisionEvent detection for bubble against room right
            if (bubble.getX() + bubble.getWidth() >= Model.getRoomWidth()) {
                bubble.collide(CollisionEvent.TYPE_WALL);
            }

            for(Player player : Model.getPlayers()){

                // Collision detection on walls.
                for(Wall wall: Model.getCurrentRoom().getWalls()) {
                    if(player.collidesWith(wall))
                        player.setDx(-1*player.getDx());
                }

                // CollisionEvent detection for bubble against player
                if (player.collidesWith(bubble)) {
                    loseLife(player, gc);
                }

                for (Rope rope : player.getRopes()) {
                    if (bubble.collidesWith(rope)) {
                        bubble.collide(CollisionEvent.TYPE_ROPE);
                        player.increaseScore(REWARD_BUBBLE);
                        player.resetRope();
                    }
                }
            }
        }

        // calculate movements
        updateRopes();
        updateBubble(gc);
        updatePlayer();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("Test", 10, 30);
        player.draw(100, 300);
    }


    public static void main(String[] args) {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlickApp("Bubble Trouble"));
            appgc.setDisplayMode(800, 500, false);
            //appgc.setShowFPS(false);
            appgc.setAlwaysRender(true);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SlickApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lets the player lose a life
     * @param player should be the player who lost a life
     */
    private static void loseLife(Player player, GameContainer gc) {
        player.loseLife();
        if(!player.hasLives()){
            endGame("Game over...", gc);
        } else {
            Model.restartRoom();
            player.resetRope();
        }

    }

    /**
     * Updates the bubble, checks whether bubbles should be removed or not, and calculates the new position of each bubble.
     */
    private static void updateBubble(GameContainer gc) {
        for(Bubble bubble: newBubbles) {
            Model.getBubbles().add(bubble);
        }
        newBubbles.clear();
        for(Bubble bubble: oldBubbles) {
            Model.getBubbles().remove(bubble);
        }
        oldBubbles.clear();
        if (Model.getBubbles().isEmpty()) endGame("You won the game!", gc);
        for(Bubble bubble: Model.getBubbles())
            bubble.move();
    }

    /**
     * adds a bubble to the game
     * @param size size of the bubble
     * @param x horizontal position of the bubble
     * @param y vertical position of the bubble
     * @param vx horizontal speed of the bubble
     * @param vy vertical speed of the bubble
     */
    public static void addBubble(int size, int x, int y, double vx, double vy) {
        Bubble newBubble = new Bubble(size, x, y, vx, vy);
        newBubbles.add(newBubble);
    }

    /**
     * removes a bubble from the game
     * @param bubble the bubble to remove
     */
    public static void removeBubble(Bubble bubble) {
        oldBubbles.add(bubble);
    }

    /**
     * Updates the ropes, calculates the new position of the rope and removes the rope if it hits the ceiling.
     */
    private static void updateRopes() {
        for(Player player: Model.getPlayers()){
            ArrayList<Rope> ropes = player.getRopes();

            for (int i = 0; i < ropes.size(); i++) {

                Rope rope = (Rope) ropes.get(i);

                if (rope.isVisible()) {
                    rope.move();
                } else {
                    ropes.clear();
                }
            }
        }
    }

    /**
     * Updates the player, calculates the new position.
     */
    private static void updatePlayer() {
        for(Player player: Model.getPlayers()){
            player.move();
        }
    }

    /**
     * Ends the game by stopping the view, shows a message.
     * @param message should be a String containing the message which is shown.
     */
    public static void endGame(String message, GameContainer gc) {
        gc.pause();
    }
}
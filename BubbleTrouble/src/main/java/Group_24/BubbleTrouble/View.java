package Group_24.BubbleTrouble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The view generates everything the user sees, and lets the user interact with the game.
 * The view hands the input from the user over to the Controller. The Controller updates the Model, and the view shows the user the data from the Model.
 */
@SuppressWarnings("serial")
public class View extends JPanel implements ActionListener {
	
	private Timer timer;
    private final int DELAY = 10;
	
    /**
     * Constructs a new view, and activates it.
     */
	public View(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		setPreferredSize(new Dimension(Model.getRoomWidth(), Model.getRoomHeight()));
		
		timer = new Timer(DELAY, this);
        start();
	}
	
	/**
	 * Returns whether this view is active by returning whether the timers is running;
	 * @return returns a boolean which represents whether this view is active;
	 */
	public boolean isActive() {
		return timer.isRunning();
	}
	
	/**
	 * Paints the view by calling this.doDrawing(Graphics g);
	 */
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
	
	/**
	 * Draws the objects on the screen.
	 * @param g should be the Graphics-object used to draw the components.
	 */
	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(Player player : Model.getPlayers()){
			player.drawObject(g2d, this);
			player.drawRopes(g2d, this);
		}
		for(Bubble bubble: Model.getBubbles())
		    bubble.drawObject(g2d, this);
		
		g2d.drawString("lives: [ " + Model.getPlayers().get(0).getLives() + " ], score: [ " + Model.getPlayers().get(0).getScore() + " ]", 5, 15);
	}
	
	/**
	 * Shows a message to the player.
	 * @param message should be a String containing the message.
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	private class TAdapter extends KeyAdapter {

	    @Override
	    public void keyReleased(KeyEvent e) {
	        Controller.keyReleased(e);
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	    	Controller.keyPressed(e);
	    }
	}
	
	/**
	 * Updates the controller and repaints this view each each time a action (e.g. a step of the timer) is performed.
	 */
	public void actionPerformed(ActionEvent e) {
		Controller.update();
		repaint();
	}
	
	/**
	 * Pauses the game by stopping the timer.
	 */
	public void pause(){
		timer.stop();
	}
	
	/**
	 * Starts the game by starting the timer.
	 */
	public void start(){
		timer.start();
	}
}

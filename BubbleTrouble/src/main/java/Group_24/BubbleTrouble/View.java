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

@SuppressWarnings("serial")
public class View extends JPanel implements ActionListener {
	
	private Timer timer;
    private final int DELAY = 10;
    private boolean running;
	
	public View(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		setPreferredSize(new Dimension(Model.getRoomWidth(), Model.getRoomHeight()));
		
		timer = new Timer(DELAY, this);
		running = false;
        startGame();
	}
	
	public boolean isRunning() {
		return running;
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
	
	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(Player player : Model.getPlayers()){
			player.drawObject(g2d, this);
	        for(Bubble bubble: Model.getBubbles())
			    bubble.drawObject(g2d, this);
			player.drawRopes(g2d, this);
		}
	}
	
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

	public void actionPerformed(ActionEvent e) {
		Controller.update();
		repaint();
	}
	
	public void stopGame(){
		timer.stop();
		running = false;
	}
	
	public void startGame(){
		timer.start();
		running = true;
	}
}

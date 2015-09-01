package Group_24.BubbleTrouble;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener{
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private final int SCREEN_WIDTH = 300;
	private final int SCREEN_HEIGHT = 300;
	
	public Board(){
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

	private void doDrawing(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(10, 10, 50, 50);
	}
}

class TAdapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT)) {
        	
        }

        if ((key == KeyEvent.VK_RIGHT)) {
        	
        }

        if ((key == KeyEvent.VK_SPACE)) {
            // TODO add shooting option
        }
    }
}


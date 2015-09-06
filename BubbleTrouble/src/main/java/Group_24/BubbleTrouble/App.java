package Group_24.BubbleTrouble;

import java.awt.EventQueue;
import javax.swing.*;

/**
 * Application running the game.
 *
 */
@SuppressWarnings("serial")
public class App extends JFrame
{
	/**
	 * Constructs a new Application, running the game
	 */
	public App() {
		
		Controller.startNewGame();
        add(Controller.getView());
        
        setResizable(false);
        pack();
        
        setTitle("Bubble Trouble");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {                
                JFrame app = new App();
                app.setVisible(true);                
            }
        });
    }
}

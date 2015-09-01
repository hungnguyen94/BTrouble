package Group_24.BubbleTrouble;

import java.awt.EventQueue;
import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App extends JFrame
{
	public App() {

        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Bubble Trouble");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {                
                JFrame ex = new App();
                ex.setVisible(true);                
            }
        });
    }
}

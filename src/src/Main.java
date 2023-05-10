import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Main extends JFrame
{
	//Make the Game Frame 
	public static void main(String[]args)
	{
		JFrame window=new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("The Legend of Zelda");
		
		GamePanel gamePanel=new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}
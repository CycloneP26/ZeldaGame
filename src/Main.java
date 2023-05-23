import javax.swing.JFrame;

public class Main extends JFrame
{
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
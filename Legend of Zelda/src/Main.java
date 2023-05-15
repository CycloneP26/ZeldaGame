import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

public class Main extends JFrame
{
    public static void main(String[]args)
    {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(768, 768);
        window.setResizable(false);
        window.setTitle("The Legend of Zelda");

        GamePanel gamePanel = new GamePanel();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(gamePanel);
        window.add(bottomPanel, BorderLayout.SOUTH);
        

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}

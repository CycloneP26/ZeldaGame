import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

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
        
        // Create a panel with a FlowLayout to center the components horizontally
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(gamePanel);
        
        // Add the panel to the SOUTH region of the JFrame
        window.add(bottomPanel, BorderLayout.SOUTH);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}

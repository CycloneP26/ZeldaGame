import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(768, 768);
        window.setResizable(false);
        window.setTitle("The Legend of Zelda");

        GamePanel gamePanel = new GamePanel();

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(720, 144));
        topPanel.setBorder(new EmptyBorder(16, 0, 0, 0)); // Add padding to move the gray box down

        // Level Box
        JPanel levelBox = new JPanel();
        levelBox.setBackground(Color.GRAY);
        levelBox.setPreferredSize(new Dimension(240, 96));
        topPanel.add(levelBox);

        // Rupees and Keys Panel
        JPanel rupeesAndKeysPanel = new JPanel(new GridLayout(2, 1));
        rupeesAndKeysPanel.setBackground(Color.BLACK);

        ImageIcon rupeesIcon = new ImageIcon(Main.class.getResource("/objects/Rupee.png"));
        JLabel rupeesLabel = new JLabel("RUPEES: ", rupeesIcon, JLabel.LEFT);
        rupeesLabel.setForeground(Color.WHITE);
        rupeesLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        rupeesAndKeysPanel.add(rupeesLabel);

        ImageIcon keysIcon = new ImageIcon(Main.class.getResource("/objects/Key.png"));
        JLabel keysLabel = new JLabel("KEYS: ", keysIcon, JLabel.LEFT);
        keysLabel.setForeground(Color.WHITE);
        keysLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        rupeesAndKeysPanel.add(keysLabel);

        topPanel.add(rupeesAndKeysPanel);

        // Health Label
        ImageIcon healthIcon = new ImageIcon(Main.class.getResource("/objects/Heart.png"));
        JLabel healthLabel = new JLabel("<html><div style='text-align: center;'><span style='vertical-align: text-bottom;'>-LIFE-</span><br><img src='" + healthIcon + "'></div></html>", JLabel.CENTER);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Courier New", Font.PLAIN, 36));
        topPanel.add(healthLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        window.add(mainPanel);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}

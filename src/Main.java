import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
        HUD hudPanel = new HUD();

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(768, 64));
        topPanel.setLayout(new GridLayout(2, 4, 20, 0));

        JLabel levelLabel = new JLabel("LEVEL - ");
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(new Font("Courier New", Font.PLAIN, 30));
        topPanel.add(levelLabel);

        ImageIcon rupeesIcon = new ImageIcon("Rupee.png");
        JLabel rupeesLabel = new JLabel("RUPEES: ", rupeesIcon, JLabel.LEFT);
        rupeesLabel.setForeground(Color.WHITE);
        rupeesLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        topPanel.add(rupeesLabel);

        ImageIcon healthIcon = new ImageIcon("Heart.png");
        JLabel healthLabel = new JLabel("LIFE: ", healthIcon, JLabel.LEFT);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        topPanel.add(healthLabel);

        ImageIcon keysIcon = new ImageIcon("Key.png");
        JLabel keysLabel = new JLabel("KEYS: ", keysIcon, JLabel.LEFT);
        keysLabel.setForeground(Color.WHITE);
        keysLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        topPanel.add(keysLabel);

        topPanel.add(hudPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setLayout(new BorderLayout());

        int tileSize = gamePanel.getTileSize();
        int panelHeight = gamePanel.getScreenHeight() - tileSize; // Adjust the panel height

        bottomPanel.setPreferredSize(new Dimension(gamePanel.getScreenWidth(), panelHeight));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(gamePanel.getScreenWidth(), tileSize)); // Empty panel to create space
        emptyPanel.setBackground(null);
        bottomPanel.add(emptyPanel, BorderLayout.NORTH);
        bottomPanel.add(gamePanel, BorderLayout.CENTER);

        window.add(topPanel, BorderLayout.NORTH);
        window.add(bottomPanel, BorderLayout.CENTER);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}

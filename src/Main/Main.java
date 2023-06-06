package Main;

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
    private static JLabel keysLabelStatic;
    private static JLabel rupeesLabelStatic;
    private static int keysCount = 0;
    private static int rupeesCount = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.runGame();
    }

    public void runGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(768, 757);
        setResizable(false);
        setTitle("The Legend of Zelda");

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

        ImageIcon rupeesIcon = new ImageIcon(getClass().getResource("/objects/Rupee.png"));
        JLabel rupeesLabel = new JLabel("RUPEES: " + rupeesCount, rupeesIcon, JLabel.LEFT);
        rupeesLabel.setForeground(Color.WHITE);
        rupeesLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        rupeesAndKeysPanel.add(rupeesLabel);

        ImageIcon keysIcon = new ImageIcon(getClass().getResource("/objects/Key.png"));
        JLabel keysLabel = new JLabel("KEYS: " + keysCount, keysIcon, JLabel.LEFT);
        keysLabel.setForeground(Color.WHITE);
        keysLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
        rupeesAndKeysPanel.add(keysLabel);

        keysLabelStatic = keysLabel; // Set the static variable
        rupeesLabelStatic = rupeesLabel; // Set the static variable

        topPanel.add(rupeesAndKeysPanel);

        // Health Label
        ImageIcon healthIcon = new ImageIcon(getClass().getResource("/objects/Heart.png"));
        JLabel healthLabel = new JLabel("<html><div style='text-align: center;'><span style='vertical-align: text-bottom;'>-LIFE-</span><br><img src='" + healthIcon + "'></div></html>", JLabel.CENTER);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Courier New", Font.PLAIN, 36));
        topPanel.add(healthLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel.startGameThread();
    }

    public static void updateKeysCount(int count) {
        keysCount = count;
        keysLabelStatic.setText("KEYS: " + keysCount);
    }

    public static void updateRupeesCount(int count) {
        rupeesCount = count;
        rupeesLabelStatic.setText("RUPEES: " + rupeesCount);
    }

}

package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//The Main class makes the entire frame, and the hud. 
//it uses various methods and drawings to make the hud and change the things that can be changed
//@author Ivan Xiao

public class Main extends JFrame {
    private static JLabel keysLabelStatic;
    private static JLabel rupeesLabelStatic;
    private static int keysCount = 0;
    private static int rupeesCount = 0;
    private static int heartCount = 3; //# of hearts
    private JLabel healthLabel; //#health label
    private static boolean hasSword = false;
    
    /**
     * The main method creates an instance of the Main class and runs the game.
     *
     * @param args Command-line arguments
     */
    
    public static void main(String[] args) {
        Main main = new Main();
        main.runGame();
    }

    /**
     * The runGame method sets up the main frame, size, and the HUD panels.
     * It adds the game panel and other components to the frame.
     */
    
    public void runGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(768, 774);
        setResizable(false);
        setTitle("The Legend of Zelda");

        GamePanel gamePanel = new GamePanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(new EmptyBorder(16, 0, 0, 0));


        // Level Box
        JPanel levelBox = new JPanel();
        levelBox.setBackground(Color.GRAY);
        levelBox.setPreferredSize(new Dimension(240, 96));
        topPanel.add(levelBox, BorderLayout.WEST);

        // Rupees and Keys Panel
        JPanel rupeesAndKeysPanel = new JPanel();
        rupeesAndKeysPanel.setBackground(Color.BLACK);
        rupeesAndKeysPanel.setLayout(new BoxLayout(rupeesAndKeysPanel, BoxLayout.X_AXIS));

     // Labels Panel
        JPanel labelsPanel = new JPanel();
        labelsPanel.setBackground(Color.BLACK);
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

        keysLabelStatic = createLabel("KEYS: " + keysCount, "/objects/Key.png");
        rupeesLabelStatic = createLabel("RUPEES: " + rupeesCount, "/objects/Rupee.png");

        labelsPanel.add(rupeesLabelStatic);
        labelsPanel.add(keysLabelStatic);

        rupeesAndKeysPanel.add(labelsPanel);


        // Rectangular Boxes Panel
        JPanel boxesPanel = new JPanel();
        boxesPanel.setBackground(Color.BLACK);

        // Add the first rectangular box
        JPanel rectangularBox1 = createRectangularBox(Color.BLUE, 50, 75, "B");  
        boxesPanel.add(rectangularBox1);

        // Add the second rectangular box
        JPanel rectangularBox2 = createRectangularBox(Color.BLUE, 50, 75, "A");  
        boxesPanel.add(rectangularBox2);

        rupeesAndKeysPanel.add(boxesPanel);

        topPanel.add(rupeesAndKeysPanel, BorderLayout.CENTER);

        // Health Label
        JLabel healthLabel = createHealthLabel();

        topPanel.add(healthLabel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setPreferredSize(new Dimension(768, 50)); // Adjust the preferred size according to your requirements

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel.startGameThread();
    }

    /**
     * Creates a JLabel with the specified text and icon.
     * Sets the font size, font color, and alignment.
     *
     * @param text     The text to display
     * @param iconPath The path to the icon image
     * @return The created JLabel
     */
    
    private JLabel createLabel(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JLabel label = new JLabel(text, icon, JLabel.LEFT);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Courier New", Font.PLAIN, 16));
        return label;
    }

    /**
     * Creates the health label with heart icons.
     * Aligns the hearts in place.
     */
    
    private JLabel createHealthLabel() {
        ImageIcon healthIcon = new ImageIcon(getClass().getResource("/objects/Heart.png"));
        JLabel healthLabel = new JLabel("<html><div style='text-align: center;'><span style='vertical-align: text-bottom;'>-LIFE-</span><br><img src='" + healthIcon + "'></div></html>", JLabel.CENTER);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Courier New", Font.PLAIN, 36));
        return healthLabel;
    }
    
    /**
     * Updates the health count and updates the health label accordingly.
     *
     * @param health The new health count
     */
    public void updateHealth(int health) {
        heartCount = health;
        updateHealthLabel(heartCount);
    }
    
    /**
     * Generates the HTML string for displaying heart icons based on the count.
     *
     * @param count      The number of hearts
     * @param heartIcon  The heart icon
     * @return The HTML string with heart icons
     */
    private String getHeartHtmlString(int count, ImageIcon heartIcon) {
        StringBuilder heartHtml = new StringBuilder();
        for (int i = 0; i < count; i++) {
            heartHtml.append("<img class='heart-icon' src='").append(heartIcon).append("'>");
        }
        return heartHtml.toString();
    }
    
    /**
     * Updates the health label based on the health count.
     *
     * @param health The current health count
     */
    public void updateHealthLabel(int health) {
        heartCount = health;

        if (heartCount >= 0) {
            healthLabel.setText("<html><div style='text-align: center;'>-LIFE-<br>" +
                    getHeartHtmlString(heartCount, new ImageIcon(getClass().getResource("/objects/Heart.png"))) +
                    "</div></html>");
        } else {
            heartCount = 0;
            String heartHtml = "<html><div style='text-align: center;'>-LIFE-<br>";
            heartHtml += getHeartHtmlString(heartCount, new ImageIcon(getClass().getResource("/objects/Heart.png")));
            heartHtml += "<br><span class='heart-icon'></span></div></html>";
            healthLabel.setText(heartHtml);
        }
    }

    /**
     * Creates a rectangular box panel with the specified color, width, height, and letter.
     * Adds a border, label, and image to the box panel.
     *
     * @param color  The color of the box panel
     * @param width  The width of the box panel
     * @param height The height of the box panel
     * @param letter The letter to display
     * @return The created rectangular box panel
     */
    private JPanel createRectangularBox(Color color, int width, int height, String letter) {
        JPanel box = new JPanel(new BorderLayout());
        box.setOpaque(false); // Set the box panel to be transparent
        box.setPreferredSize(new Dimension(width, height));
        box.setBorder(BorderFactory.createLineBorder(color, 2)); // Add border to the box panel

        JLabel letterLabel = new JLabel(letter, JLabel.CENTER);
        letterLabel.setForeground(Color.WHITE);
        letterLabel.setFont(new Font("Courier New", Font.PLAIN, 16));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.setBackground(color);
        labelPanel.add(letterLabel);

        box.add(labelPanel, BorderLayout.SOUTH);

        // Check if the letter is "B" and add the bomb image
        if (letter.equals("B")) {
            ImageIcon bombIcon = new ImageIcon(getClass().getResource("/objects/Bomb.png"));
            JLabel bombLabel = new JLabel(bombIcon);
            box.add(bombLabel, BorderLayout.CENTER);
        }

        // Check if the letter is "A" and add the sword image
        if (letter.equals("A")) {
            ImageIcon swordIcon = new ImageIcon(getClass().getResource("/objects/Sword.png"));
            // Scale the sword icon to a smaller size
            Image scaledImage = swordIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            ImageIcon scaledSwordIcon = new ImageIcon(scaledImage);
            JLabel swordLabel = new JLabel(scaledSwordIcon);
            box.add(swordLabel, BorderLayout.CENTER);
        }

        return box;
    }

    /**
     * Updates the keys count and the keys label.
     *
     * @param count The new keys count
     */
    public static void updateKeysCount(int count) {
        keysCount = count;
        keysLabelStatic.setText("KEYS: " + keysCount);
    }
    /**
     * Updates the rupees count and the rupees label.
     *
     * @param count The new rupees count
     */
    public static void updateRupeesCount(int count) {
        rupeesCount = count;
        rupeesLabelStatic.setText("RUPEES: " + rupeesCount);
    }

}
package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/*
Main class that creates the gamePanel and runs all the threading
It also creates the HUD 
@author David Kostanyan
@author Ivan Xiao 
*/
public class Main extends JFrame {
    //Label for the amount of keys 
    private static JLabel keysLabelStatic;
    //Label for amount of rupees 
    private static JLabel rupeesLabelStatic;
    //the amount of keys 
    private static int keysCount = 0;
    //amount of rupees 
    private static int rupeesCount = 0;
    
    private JPanel mainPanel;
    private JPanel topPanel;
    private GamePanel gamePanel;
    private JLabel healthLabel;

    public static void main(String[] args) {
        Main main = new Main();
        main.runGame();
    }

    public void runGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(768, 774);
        setResizable(false);
        setTitle("The Legend of Zelda");
        
        //Main gamePanel 
        GamePanel gamePanel = new GamePanel(this);
        
        //Background of HUD 
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        
        //Part of HUD 
        topPanel = new JPanel(new BorderLayout());
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
        
        ImageIcon healthIcon = new ImageIcon(getClass().getResource("/objects/Heart.png"));
        healthLabel = new JLabel("<html><div style='text-align: center;'><span style='vertical-align: text-bottom;'>-LIFE-</span><br><img src='" + healthIcon + "'></div></html>", JLabel.CENTER);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Courier New", Font.PLAIN, 36));

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
        
        this.gamePanel=gamePanel;
        gamePanel.startGameThread();
    }

    public void heartUpdate()
    {

    	topPanel.remove(healthLabel);

    	healthLabel = createHealthLabel();

        topPanel.add(healthLabel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

    }
    
    //Creates a label that can store text 
    private JLabel createLabel(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JLabel label = new JLabel(text, icon, JLabel.LEFT);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Courier New", Font.PLAIN, 16));
        return label;
    }
    //Creates the HealthLabel by aligning and creating a label
    private JLabel createHealthLabel() {
    	ImageIcon healthIcon;

    	if(gamePanel.getPlayer().getHealth() > 5 )
    	{

    		healthIcon = new ImageIcon(getClass().getResource("/objects/Heart.png"));

    	}
    	else if(gamePanel.getPlayer().getHealth() <=5 && gamePanel.getPlayer().getHealth() >0)
    	{

    		healthIcon = new ImageIcon(getClass().getResource("/objects/halfHeart.png"));

    	}
    	else
    	{

    		healthIcon = new ImageIcon(getClass().getResource("/objects/trans.png"));
    		gamePanel.getPlayer().setDone(true);

    	}

    	healthLabel = new JLabel("<html><div style='text-align: center;'><span style='vertical-align: text-bottom;'>-LIFE-</span><br><img src='" + healthIcon + "'></div></html>", JLabel.CENTER);
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(new Font("Courier New", Font.PLAIN, 36));
        return healthLabel;
    }

    //Creates the rectangular box 
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


    public static void updateKeysCount(int count) {
        keysCount = count;
        keysLabelStatic.setText("KEYS: " + keysCount);
    }

    public static void updateRupeesCount(int count) {
        rupeesCount = count;
        rupeesLabelStatic.setText("RUPEES: " + rupeesCount);
    }

}

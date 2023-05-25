import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    private int FPS = 60;
    private int hudHeight = 16 * 3; // height of the HUD panel
    
    private RoomManager rooms;
    private KeyHandler keyH;
    private Thread gameThread;
    private Player player;
    private CollisionChecker cChecker;
    private HUD hudPanel;

    public GamePanel() {
        keyH = new KeyHandler();
        rooms = new RoomManager(this, 3, 3);
        player = new Player(this, keyH);
        cChecker = new CollisionChecker(this, rooms, rooms.getRoomRow(), rooms.getRoomColumn());

        // adjust the panel dimensions to leave space for the HUD
        int panelWidth = screenWidth;
        int panelHeight = screenHeight - hudHeight; // Subtract hudHeight to accommodate the game area only
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        this.setBackground(Color.black); // Set the background to black for the non-playable area
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        hudPanel = new HUD();
        hudPanel.setPreferredSize(new Dimension(screenWidth - 48, hudHeight - 48)); // set the HUD panel dimensions
        add(hudPanel);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public CollisionChecker getCollision() {
        return cChecker;
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
        rooms.update();
    }

    public Player getPlayer() {
        return player;
    }

    public int getTileSize()
    {
    	return tileSize;
    }
    
    public int getHudHeight()
    {
    	return hudHeight;
    }
    
    public int getScreenHeight()
    {
    	return screenHeight;
    }
    
    public int getScreenWidth()
    {
    	return screenWidth;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        rooms.getRoomArray().get(rooms.getRoomRow()).get(rooms.getRoomColumn()).draw(g2);
        player.draw(g2);

        hudPanel.paintComponent(g2);

        g2.dispose();
    }
}

package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import object.ItemEntity;

/**
 * The GamePanel class creates a JPanel that is used to display the entire game.
 * The entire project is threaded and runs on this class. It uses runnable and a
 * GameThread to update the values of all the components.
 * 
 * Author: Sachin Chhaya
 * Author: David Kostanyan
 * Author: Pranay Thatikonda
 * Author: Christopher Li
 */
public class GamePanel extends JPanel implements Runnable {
    // Tile size before scale
    final int originalTileSize = 16;
    // Scale of the tiles
    final int scale = 3;
    // Scaled tile size
    final int tileSize = originalTileSize * scale;
    // Number of tiles on width
    final int maxScreenCol = 16;
    // Number of tiles on height
    final int maxScreenRow = 12;
    // Width of screen
    final int screenWidth = tileSize * maxScreenCol;
    // Width of height
    final int screenHeight = tileSize * maxScreenRow;
    // How many times it updates per second
    private int FPS = 60;

    private Timer timer; // UTILIZATION UNKNOWN AS OF NOW
    // Height of the HUD panel
    private int hudHeight = 16 * 3;

    // Creates a 2D array of rooms
    private RoomManager rooms;
    // Observes the key movements
    private KeyHandler keyH;
    // Checks collision of all entities
    private CollisionChecker cChecker;
    // Creates sound
    private Sound sound = new Sound();
    // Runtime Game loop
    private Thread gameThread;

    // Entity and objects
    // Link field
    private Player player;
    // Per room, the monsters that exist
    private Entity mobs[];
    // Per room, items that exist
    private ArrayList<ItemEntity> items;

    /**
     * Creates the GamePanel that initializes all the fields that update with the GameThread.
     */
    public GamePanel() {
        keyH = new KeyHandler(this);

        rooms = new RoomManager(this, 10, 11);

        player = new Player(this, keyH, rooms);
        mobs = new Entity[30];
        items = new ArrayList<ItemEntity>();
        cChecker = new CollisionChecker(this, rooms, rooms.getRoomRow(), rooms.getRoomColumn());

        int panelWidth = screenWidth;
        int panelHeight = screenHeight - hudHeight; // Subtract hudHeight to accommodate the game area only
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Starts the game thread and runs the update methods.
     */
    public void startGameThread() {
        playMusic(0);
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Makes the thread stop for s milliseconds.
     * 
     * @param s the number of milliseconds to sleep the thread
     */
    public void waitThread(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the collision checker object.
     * 
     * @return the CollisionChecker object
     */
    public CollisionChecker getCollision() {
        return cChecker;
    }

    /**
     * Gets the KeyHandler object.
     * 
     * @return the KeyHandler object
     */
    public KeyHandler getKeyHandler() {
        return keyH;
    }

    /**
     * Runs the game loop, updating the game at a specific FPS.
     */
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

    /**
     * Updates the positions and conditions of all entities.
     */
    public void update() {
        player.update();
        rooms.update();

        ArrayList<Entity> mobs = rooms.getCurrentRoom().getMobs();
        for (int i = 0; i < mobs.size(); i++) {
            if (mobs.get(i) != null) {
                mobs.get(i).update();
            }
        }

        for (int i = 0; i < rooms.getCurrentRoom().getBombs().size(); i++) {
            if (rooms.getCurrentRoom().getBombs().get(i) != null) {
                rooms.getCurrentRoom().getBombs().get(i).updateBomb();
            }
        }
    }

    /**
     * Gets the player object.
     * 
     * @return the Player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the size of the tiles.
     * 
     * @return the tile size
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Gets the height of the HUD panel.
     * 
     * @return the HUD panel height
     */
    public int getHudHeight() {
        return hudHeight;
    }

    /**
     * Gets the height of the screen.
     * 
     * @return the screen height
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Gets the width of the screen.
     * 
     * @return the screen width
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Draws all the entities that must be rendered in each room.
     * 
     * @param g the Graphics object used for drawing
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        rooms.getCurrentRoom().draw(g2);
        player.draw(g2);
        ArrayList<Entity> mobs = rooms.getCurrentRoom().getMobs();
        ArrayList<ItemEntity> items = rooms.getCurrentRoom().getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                items.get(i).draw(g2);
            }
        }
        for (int i = 0; i < mobs.size(); i++) {
            if (mobs.get(i) != null) {
                mobs.get(i).draw(g2);
            }
        }

        for (int i = 0; i < rooms.getCurrentRoom().getBombs().size(); i++) {
            if (rooms.getCurrentRoom().getBombs().get(i) != null) {
                rooms.getCurrentRoom().getBombs().get(i).drawItem(g2);
            }
        }

        g2.dispose();
    }

    /**
     * Plays the music specified by the given index and loops it.
     * 
     * @param i the index of the music to play
     */
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    /**
     * Stops the currently playing music.
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * Plays the sound effect specified by the given index.
     * 
     * @param i the index of the sound effect to play
     */
    public void playEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

    /**
     * Gets the list of mobs in the current room.
     * 
     * @return the list of mobs
     */
    public ArrayList<Entity> getMobs() {
        return rooms.getCurrentRoom().getMobs();
    }

    /**
     * Sets the list of mobs in the current room.
     * 
     * @param e the list of mobs to set
     */
    public void setMobs(ArrayList<Entity> e) {
        rooms.getCurrentRoom().setMobs(e);
    }

    /**
     * Gets the list of items in the current room.
     * 
     * @return the list of items
     */
    public ArrayList<ItemEntity> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the current room.
     * 
     * @param items the list of items to set
     */
    public void setItems(ArrayList<ItemEntity> items) {
        this.items = items;
    }

    /**
     * Gets the RoomManager object.
     * 
     * @return the RoomManager object
     */
    public RoomManager getRooms() {
        return rooms;
    }
}

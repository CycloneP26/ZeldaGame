package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import object.ItemEntity;
/*
The GamePanel class creates a JPanel that is used to display the entire game. 
The entire project is threaded and runs on this class. It uses runnable and a 
GameThread to update the values of all the components. 
@author Sachin Chhaya
@author David Kostanyan
@author Pranay Thatikonda
@author Christopher Li 
*/
public class GamePanel extends JPanel implements Runnable
{
	//tile size before scale
	final int originalTileSize=16; 
	//scale of the tiles 
	final int scale=3; 
	//scaled tile size
	final int tileSize=originalTileSize*scale; 
	//#of tiles on width
	final int maxScreenCol=16;
	//#of tiles on height
	final int maxScreenRow=12; 
	//width of screen
	final int screenWidth=tileSize*maxScreenCol; 
	//width of height
	final int screenHeight=tileSize*maxScreenRow;
	//how many times it updates per second
	private int FPS = 60; 
	
	private Timer timer; //UTILIZATION UNKNOWN AS OF NOW
	// height of the HUD panel
    	private int hudHeight = 16 * 3; 
	
	//Creates a 2D array of rooms
	private RoomManager rooms; 
	//Observes the key movements
	private KeyHandler keyH; 
	//Checks collision of all entities
	private CollisionChecker cChecker; 
	//Creates sound 
	private Sound sound = new Sound();
	//Runtime Game loop 
	private Thread gameThread; 
	
	//Entity and objects 
	//Link field
	private Player player; 
	//Per room, the monsters that exist
	private Entity mobs[]; 
	//Per room, items that exist 
	private ArrayList<ItemEntity> items; 
	
	private ArrayList<ItemEntity> fires;
	
	
	/*
	Creates the actual GamePanel that initializes all the fields that update 
	with the GameThread
	*/
	public GamePanel(Main main) 
	{
		
		keyH=new KeyHandler(this);
		
		rooms = new RoomManager(this, 10, 11);
		
		player=new Player(this, keyH, rooms, main);
		mobs=new Entity[30];
		items = new ArrayList<ItemEntity>();
		setFires(new ArrayList<ItemEntity>());
		cChecker = new CollisionChecker(this, rooms, rooms.getRoomRow(), rooms.getRoomColumn(), main);

		
		int panelWidth = screenWidth;
        int panelHeight = screenHeight - hudHeight; // Subtract hudHeight to accommodate the game area only
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	//Starts the game thread and runs the update methods
	public void startGameThread()
	{
		playMusic(0);
		gameThread=new Thread(this);
		gameThread.start();
		
	}
	//Makes the thread stop for s milliseconds
	public void waitThread(int s)
	{
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CollisionChecker getCollision()
	{
		
		return cChecker;
		
	}
	
	public KeyHandler getKeyHandler()
	{

		return keyH;

	}
	//Uses GameThread to run the game by updating in intervals 
	public void run()
	{
		double drawInterval=1000000000/FPS;
		double nextDrawTime=System.nanoTime()+drawInterval;
		
		while(gameThread!=null)
		{
			update();
			
			
			
			repaint();
			
			try {
				double remainingTime=nextDrawTime-System.nanoTime();
				remainingTime=remainingTime/1000000;
				
				if(remainingTime<0)
				{
					remainingTime=0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime+=drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	//Updates the positions and conditions of all entities 
	public void update()
	{
		
		player.update();
		rooms.update();
		
		ArrayList<Entity> mobs = rooms.getCurrentRoom().getMobs();
		for(int i=0; i<mobs.size();i++)
		{
			if(mobs.get(i)!=null)
			{
				mobs.get(i).update();
			}
		}
		
		
		for(int i=0; i<rooms.getCurrentRoom().getBombs().size();i++)
		{
			if(rooms.getCurrentRoom().getBombs().get(i)!=null)
			{
				rooms.getCurrentRoom().getBombs().get(i).updateBomb();
			}
		}
		
		for(int i = 0; i < rooms.getCurrentRoom().getFires().size(); i++)
		{
			if(rooms.getCurrentRoom().getFires().get(i) != null)
			{
				rooms.getCurrentRoom().getFires().get(i).update();
			}
		}
	}
	//All the entities that must be rendered in each room is drawn here
	public void paintComponent(Graphics g) //@param Graphics required to draw
	{
		
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		rooms.getCurrentRoom().draw(g2);
		player.draw(g2);
		ArrayList<Entity> mobs = rooms.getCurrentRoom().getMobs();
		ArrayList<ItemEntity> items = rooms.getCurrentRoom().getItems();
		fires = rooms.getCurrentRoom().getFires();
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) != null)
			{
				items.get(i).draw(g2);
			}
		}
		
		for(int i = 0; i < fires.size(); i++)
		{
			if(fires.get(i) != null)
			{
				fires.get(i).drawFire(g2);
			}
		}
		
		for(int i=0; i<mobs.size();i++)
		{
			if(mobs.get(i)!=null)
			{
				mobs.get(i).draw(g2);
			}
		}
		
		for(int i=0; i<rooms.getCurrentRoom().getBombs().size();i++)
		{
			if(rooms.getCurrentRoom().getBombs().get(i)!=null)
			{
				rooms.getCurrentRoom().getBombs().get(i).drawItem(g2);
			}
		}
		
		g2.dispose();
		
	}
	//Takes the index of the sound that is played and loops it
	public void playMusic(int i) //@param Integer is used to choose the sound file within the list
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	//Stops the sound/music 
	public void stopMusic()
	{
		sound.stop();
	}
	//Plays a sound but does not loop it 
	public void playEffect(int i) //@param takes integer to choose the sound file
	{
		sound.setFile(i);
		sound.play();
	}
	public Player getPlayer()
	{
		
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
	
	
	public ArrayList<Entity> getMobs() {
		return rooms.getCurrentRoom().getMobs();
	}
	
	public void setMobs(ArrayList<Entity> e)
	{
		rooms.getCurrentRoom().setMobs(e);
	}
	
	public ArrayList<ItemEntity> getItems() {
		return items;
	}
	public void setItems(ArrayList<ItemEntity> items) {
		this.items = items;
	}
	public RoomManager getRooms()
	{
		return rooms;
	}
	public ArrayList<ItemEntity> getFires() {
		return fires;
	}
	public void setFires(ArrayList<ItemEntity> fires) {
		this.fires = fires;
	}
}

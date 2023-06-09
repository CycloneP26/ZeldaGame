package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import object.ItemEntity;

public class GamePanel extends JPanel implements Runnable
{
	final int originalTileSize=16; //tile size before scale
	final int scale=3; //scale of the tiles 
	final int tileSize=originalTileSize*scale; //scaled tile size
	final int maxScreenCol=16; //#of tiles on width
	final int maxScreenRow=12; //#of tiles on height
	final int screenWidth=tileSize*maxScreenCol; //width of screen
	final int screenHeight=tileSize*maxScreenRow;//width of height
	private int FPS = 60; //how many times it updates per second
	private Timer timer; //UTILIZATION UNKNOWN AS OF NOW
	
    	private int hudHeight = 16 * 3; // height of the HUD panel
	
	
	private RoomManager rooms; //Creates a 2D array of rooms
	private KeyHandler keyH; //Observes the key movements
	private CollisionChecker cChecker; //Checks collision of all entities
	private Sound sound = new Sound(); //Creates sound 
	private Thread gameThread; //Runtime Game loop 
	
	//Entity and objects 
	private Player player; //Link field
	private Entity mobs[]; //Per room, the monsters that exist
	private ArrayList<ItemEntity> items; //Per room, items that exist 
	private ArrayList<Entity> projectileList;
	
	
	/*
	Creates the actual GamePanel that initializes all the fields that update 
	with the GameThread
	*/
	public GamePanel() 
	{
		
		keyH=new KeyHandler();
		
		rooms = new RoomManager(this, 10, 11);
		
		player=new Player(this, keyH, rooms);
		mobs=new Entity[30];
		items = new ArrayList<ItemEntity>();
		projectileList=new ArrayList<Entity>();
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
	//Starts the game thread and runs the update methods
	public void startGameThread()
	{
		playMusic(0);
		gameThread=new Thread(this);
		gameThread.start();
		
	}
	//SOMEONE COMMENT THIS 
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
	//SOMEONE COMMENT THIS
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
		ArrayList<Entity> projs= rooms.getCurrentRoom().getProjectiles();
		for(int i=0; i<mobs.size();i++)
		{
			if(mobs.get(i)!=null)
			{
				mobs.get(i).update();
			}
		}
		for(int i=0; i<projs.size();i++)
		{
			if(projs.get(i)!=null)
			{
				projs.get(i).update();
			}
		}
	
		
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
	//All the entities that must be rendered in each room is drawn here
	public void paintComponent(Graphics g) //@param Graphics required to draw
	{
		
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		rooms.getCurrentRoom().draw(g2);
		player.draw(g2);
		ArrayList<Entity> mobs = rooms.getCurrentRoom().getMobs();
		ArrayList<ItemEntity> items = rooms.getCurrentRoom().getItems();
		ArrayList<Entity> projs=rooms.getCurrentRoom().getProjectiles();
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) != null)
			{
				items.get(i).draw(g2);
			}
		}
		for(int i=0; i<mobs.size();i++)
		{
			if(mobs.get(i)!=null)
			{
				mobs.get(i).draw(g2);
			}
		}
		for(int i=0;i<projs.size();i++)
		{
			if(projs.get(i)!=null)
			{
				projs.get(i).draw(g2);
				
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
	public ArrayList<Entity> getProjectileList() {
		return projectileList;
	}
	public void setProjectileList(ArrayList<Entity> projectileList) {
		this.projectileList = projectileList;
	}
}

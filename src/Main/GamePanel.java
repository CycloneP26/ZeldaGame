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
	final int originalTileSize=16;
	final int scale=3;
	final int tileSize=originalTileSize*scale;
	final int maxScreenCol=16;
	final int maxScreenRow=12;
	final int screenWidth=tileSize*maxScreenCol;
	final int screenHeight=tileSize*maxScreenRow;
	private int FPS = 60;
	private Timer timer;
	
    	private int hudHeight = 16 * 3; // height of the HUD panel
	
	private AssetSetter assetS = new AssetSetter(this);
	private RoomManager rooms;
	private KeyHandler keyH;
	private CollisionChecker cChecker;
	private Sound sound = new Sound();
	private Thread gameThread;
	
	//Entity and objects 
	private Player player;
	private Entity mobs[];
	private ItemEntity items[] = new ItemEntity[10];
	
	
	
	public GamePanel()
	{
		
		keyH=new KeyHandler();
		
		rooms = new RoomManager(this, 3, 3);
		
		player=new Player(this, keyH, rooms);
		mobs=new Entity[30];
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
	public void setUpGame()
	{
		assetS.setObject();
		
		
	}
	public void startGameThread()
	{
		playMusic(0);
		gameThread=new Thread(this);
		gameThread.start();
		
	}
	
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
	
	public void paintComponent(Graphics g)
	{
		
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		rooms.getCurrentRoom().draw(g2);
		player.draw(g2);
		ArrayList<Entity> mobs = rooms.getCurrentRoom().getMobs();
		for(int i = 0; i < items.length; i++)
		{
			if(items[i] != null)
			{
				items[i].draw(g2, null);
			}
		}
		for(int i=0; i<mobs.size();i++)
		{
			if(mobs.get(i)!=null)
			{
				mobs.get(i).draw(g2);
			}
		}
		
		g2.dispose();
		
	}
	public void playMusic(int i)
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic()
	{
		sound.stop();
	}
	public void playEffect(int i)
	{
		sound.setFile(i);
		sound.play();
	}
	public Entity[] getMobs() {
		return mobs;
	}

	public void setMobs(Entity mobs[]) {
		this.mobs = mobs;
	}
	public ItemEntity[] getItems() 
	{
		return items;
	}
	public void setItems(ItemEntity items[]) {
		this.items = items;
	}
}

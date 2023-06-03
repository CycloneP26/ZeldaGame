package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

import object.ItemEntity;

public class GamePanel extends JPanel implements Runnable
{
	private final int originalTileSize=16;
	private final int scale=3;
	private final int tileSize=originalTileSize*scale;
	private final int maxScreenCol=16;
	private final int maxScreenRow=12;
	private final int screenWidth=tileSize*maxScreenCol;
	private final int screenHeight=tileSize*maxScreenRow;
	private Timer timer;
	private int FPS = 60;
	private ItemEntity obj[]= new ItemEntity[10];
	
	private RoomManager rooms;
	private KeyHandler keyH;
	private Thread gameThread;
	private Player player;
	private AssetSetter assetS = new AssetSetter(this);
	private CollisionChecker cChecker;
	
	
	public GamePanel()
	{
		
		keyH=new KeyHandler();
		
		rooms = new RoomManager(this, 3, 3);
		
		player=new Player(this,keyH);
		
		cChecker = new CollisionChecker(this, rooms, rooms.getRoomRow(), rooms.getRoomColumn());
		
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
		
		gameThread=new Thread(this);
		gameThread.start();
		
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
		
	}
	
	public Player getPlayer()
	{
		
		return player;
		
	}
	
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		rooms.getRoomArray().get(rooms.getRoomRow()).get(rooms.getRoomColumn()).draw(g2);
		player.draw(g2);
		g2.dispose();
		
	}
	public int getTileSize()
	{
		return tileSize;
	}
	public int getHeight()
	{
		return screenHeight;
	}
	public int getWidth()
	{
		return screenWidth;
	}

	public ItemEntity[] getObj() {
		return obj;
	}

	public void setObj(ItemEntity obj[]) {
		this.obj = obj;
		
	}
}

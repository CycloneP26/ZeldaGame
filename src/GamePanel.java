import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable
{
	final int originalTileSize=16; //tile size
	final int scale=3;
	public final int tileSize=originalTileSize*scale; 
	final int maxScreenCol=16; //column # of screen
	final int maxScreenRow=16; //row # of screen
	final int screenWidth=tileSize*maxScreenCol; //screen width
	final int screenHeight=tileSize*maxScreenRow; //screen height
	private Timer timer; //timer
	private int currentRoomRow; //current row in the room
	private int currentRoomColumn; //current column in the room
	
	int FPS=60;
	
	
	
	private RoomManager rooms; //room manager
	private KeyHandler keyH; //keyhandler
	private Thread gameThread; 
	private Player player; //Link
	private CollisionChecker cChecker; //collision checker
	
	
	public GamePanel()
	{
		//initializes everything
		keyH=new KeyHandler();
		rooms = new RoomManager(this, 3, 3);
		player=new Player(this,keyH);
		currentRoomRow = 1;
		currentRoomColumn = 1;
		cChecker = new CollisionChecker(this, rooms, currentRoomRow, currentRoomColumn);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	//returns collision checker status
	public CollisionChecker getCollision()
	{
		return cChecker;
	}
	
	
	//start game
	public void startGameThread()
	{
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	//runs the game, updating every movement and where Link is 
	public void run()
	{
		double drawInterval=1000000000/FPS;
		double nextDrawTime=System.nanoTime()+drawInterval;
		
		while(gameThread!=null)
		{
			update();
				
			// Check if the player has moved off the top edge of the screen. If yes:
			if(player.getY() < 0)
			{
				// Set the player's y-coordinate to the bottom of the screen, offset by 40 pixels.
				player.setY(screenHeight - 40);
				
				// Check if there is a room available above the current room. If yes:
				if(rooms.isRoomAvailable(currentRoomRow - 1, currentRoomColumn))
				{
					// Set the current room row to one row above the current room.
					currentRoomRow--;
					
					// Update the current row value in the collision checker.
					cChecker.setCurRow(currentRoomRow);
				}
			}

			//checks if player has moved to the left of the screen. if yes
			else if(player.getX()<0)
			{
				//set the player's x coordinate to the right edge of the screen
				player.setX(screenWidth-40);
				// Check if there is a room available to the left of the current room. If yes:
				if(rooms.isRoomAvailable(currentRoomRow, currentRoomColumn - 1))
				{
					// Set the current room column to one column to the left of the current room.
					currentRoomColumn--;
					
					// Update the current column value in the collision checker.
					cChecker.setCurCol(currentRoomColumn);
				}

			}
			//checks if the player has moved to the bottom of the screen. if yes
			else if(player.getY() + 40>screenHeight)
			{
				//move the players y coordinate to the top of the screen
				player.setY(0);
				// Check if there is a room available below the current room. If yes:
				if(rooms.isRoomAvailable(currentRoomRow + 1, currentRoomColumn))
				{
					// Set the current room row to one row below the current room.
					currentRoomRow++;
					
					// Update the current row value in the collision checker.
					cChecker.setCurRow(currentRoomRow);
				}
			}
			//check if the player has moved to the right of the screen. if yes
			else if(player.getX()+40>screenWidth)
			{
				//set the players x coordinates to the left of the screen
				player.setX(0);
				// Check if there is a room available to the right of the current room. If yes:
				if(rooms.isRoomAvailable(currentRoomRow, currentRoomColumn+1))
				{
					// Set the current room column to one column to the right of the current room.
					currentRoomColumn++;
					
					// Update the current column value in the collision checker.
					cChecker.setCurCol(currentRoomColumn);
				}
			}
			}
			
			repaint(); //update the room
			
			
			
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
	
	//update
	public void update()
	{
		player.update();
	}
	//paint the room
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		rooms.getRoomArray().get(currentRoomRow).get(currentRoomColumn).draw(g2);
		player.draw(g2);
		g2.dispose();
		
	}
}

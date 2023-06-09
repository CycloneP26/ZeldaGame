  package Main;

import java.util.ArrayList;

import object.ItemEntity;
import object.Rupee;
/*
This class uses methods to check if an entity or object is colliding with another entity or object
 @author Christopher Li
 @author Sachin Chhaya
 @author David Kostanyan
 @author Pranay Thatikonda
*/
public class CollisionChecker 
{
	//Access the main GamePanel
	private GamePanel gp;
	//Access each room 
	private RoomManager rooms;
	//The row of the current room in the 2d ArrayList
	private int curRow;
	//the column of the current in the 2d ArrayList 
	private int curCol;
	//the main method
	private Main main;
	
	/*
	Constructor for the Collision Checker that initializes the gamePanel, rooms, row and column 
	@param GamePanel gp used to access and initialize GamePanel
	@param RoomManager to access and initialize the rooms 
	@param int curRow, to access the current room row
	@param curCol, to access the current room column
	@param main, to access the main method 
	*/
	public CollisionChecker(GamePanel gp, RoomManager rooms, int curRow, int curCol, Main main)
	{
		
		this.gp = gp;
		this.rooms = rooms;
		this.curRow = curRow;
		this.curCol = curCol;
		this.main = main;
		
	}
	
	public void setCurRow(int n)
	{
		curRow = n;
	}
	
	public void setCurCol(int n)
	{
		curCol = n;
	}
	
	/*
	Check tile 
	Checks two tiles in the direction that the entity is facing to see if those tiles have collision on
	@param Entity entity is passed to check the collision tiles for any entity that is passed 
	*/
	public void checkTile(Entity entity)
	{
		
		int entityLeftX = entity.getX() + entity.getSolidArea().x;
		int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
		int entityTopY = entity.getY() + entity.getSolidArea().y;
		int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;
		
		int entityLeftCol = entityLeftX / gp.tileSize;
		int entityRightCol = entityRightX / gp.tileSize;
		int entityTopRow = entityTopY / gp.tileSize;
		int entityBottomRow = entityBottomY / gp.tileSize;
		
		
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
		//The two tiles 
		int tileNum1;
		int tileNum2;
		
		switch(entity.getDirection()) 
		{
		
		case "up":
			
			if(entityRightCol < 16)
			{
				
				entityTopRow = (entityTopY - entity.getSpeed()) / gp.tileSize;
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
					
				}
				if(curRoom.getTile()[tileNum1].getTraverse() == true || curRoom.getTile()[tileNum2].getTraverse() == true)
				{
					gp.getKeyHandler().setOn(false);
					gp.getKeyHandler().setUpPressed(false);
					for(int i=0; i<48; i++)
					{
						rooms.setCurrentRoom(rooms.getSwitchedRoom(curRoom, new Room(gp), i, "cave"));
						gp.repaint();
						
					}
					rooms.setCurrentRoomCol(curRoom.getToCaveC());
					rooms.setCurrentRoomRow(curRoom.getToCaveR());
					gp.getPlayer().setX(362);
					gp.getPlayer().setY(518);


				}
			}
			
			
			break;
		case "down":
			
			entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
			if(entityBottomRow < 12 && entityRightCol < 16)
			{
				
				tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
					
				}
				
			}
			
			break;
			
		case "left":
			
			if(entityBottomRow < 12)
			{
				entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.tileSize;
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
					
				}
				
			}
			
			
			break;
			
		case "right":
			
			
			entityRightCol = (entityRightX + entity.getSpeed()) / gp.tileSize;
			if(entityRightCol < 16 && entityBottomRow < 12)
			{	
				
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
				
				}
			}
			
			break;
		
		}
		
				
		
	}
	
	/*
	Checks the two tiles in the direction that the entity is being knocked in, sees if there is collision
	@param Entity entity passed to choose what entity is being checked 
	*/
	public void checkKnockTile(Entity entity)
	{
		
		int entityLeftX = entity.getX() + entity.getSolidArea().x;
		int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
		int entityTopY = entity.getY() + entity.getSolidArea().y;
		int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;
		
		int entityLeftCol = entityLeftX / gp.tileSize;
		int entityRightCol = entityRightX / gp.tileSize;
		int entityTopRow = entityTopY / gp.tileSize;
		int entityBottomRow = entityBottomY / gp.tileSize;
		
		
		int tileNum1;
		int tileNum2;
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
		switch(entity.getKnockedDir())
		{
		
		case "up":

			entityTopRow = (entityTopY - entity.getSpeed()) / gp.tileSize;
			if(entityRightCol < 16)
			{
				
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
					
				}
				
			}
			
			
			break;
case "down":
			
			entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
			if(entityBottomRow < 12 && entityRightCol < 16)
			{
				
				tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
					
				}
				if(curRoom.getTile()[tileNum1].getTraverse() == true || curRoom.getTile()[tileNum2].getTraverse() == true)
				{
					
					
					
				}
				
			}
			
			break;
			
		case "left":

			entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.tileSize;
			if(entityBottomRow < 12)
			{
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
					
				}
				
			}
			
			
			break;
		case "right":
			
			
			entityRightCol = (entityRightX + entity.getSpeed()) / gp.tileSize;
			if(entityRightCol < 16 && entityBottomRow < 12)
			{	
				
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].getCollision() == true || curRoom.getTile()[tileNum2].getCollision() == true)
				{
					
					entity.setCollisionOn(true);
				
				}
			}
			
			break;
		
		}
		
				
		
	}
	
	/*
	Checks if the player and an entity has collided and returns a boolean depending of if that happened
	@param Player e, passed to check the main player's collision with entities 
	*/
	public boolean checkFight(Player e) 
	{
		ArrayList<Entity> temp = gp.getRooms().getCurrentRoom().getMobs();
		
		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(i) != null)
			{
				//get the entity's position 
				
				int x = e.getSolidArea().x;
				int y = e.getSolidArea().y;
				
				e.getSolidArea().x = e.getX() + e.getSolidArea().x;
				e.getSolidArea().y = e.getY() + e.getSolidArea().y;
				
				int mobX = temp.get(i).getSolidArea().x;
				int mobY = temp.get(i).getSolidArea().y;
				
				
				//Object position
				temp.get(i).getSolidArea().x = temp.get(i).getX() + temp.get(i).getSolidArea().x;
				temp.get(i).getSolidArea().y = temp.get(i).getY() + temp.get(i).getSolidArea().y;
				
				
				
				switch(e.getDirection())
				{
				case "up":
					
					if(e.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
							e.setHealth(e.getHealth()-1);
							e.setKnocked(true);
							e.setKnockedDir("down");
							main.heartUpdate();
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							//Collided, so true
							return true;
					}
					break;
				case "down":
					e.getSolidArea().y += e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
							e.setHealth(e.getHealth()-1);
							e.setKnocked(true);
							e.setKnockedDir("up");
							main.heartUpdate();
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							//Collided, so true
							return true;
					}
					break;
				case "left":
					e.getSolidArea().x -= e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
							e.setHealth(e.getHealth()-1);
							e.setKnocked(true);
							e.setKnockedDir("right");
							main.heartUpdate();
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							//Collided, so true
							return true;
					}
					break;
				case "right":
					e.getSolidArea().x += e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
							e.setHealth(e.getHealth()-1);
							e.setKnocked(true);
							e.setKnockedDir("left");
							main.heartUpdate();
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							//Collided, so true
							return true;
					}
					break;
				}
			
			e.getSolidArea().x = e.getSolidAreaDefX();
			e.getSolidArea().y = e.getSolidAreaDefY();
			temp.get(i).getSolidArea().x = temp.get(i).getSolidAreaDefX();
			temp.get(i).getSolidArea().y = temp.get(i).getSolidAreaDefY();
			}
			if(temp.get(i).getHealth()<=0)
			{
				temp.remove(i);
			}
		}
		gp.setMobs(temp);
		//return false because no entity collided 
		return false;
	}
	/*
	Check if the player's sword has collided with an entity
	@param Sword sword passed to find the position and collision area of the sword
	*/
	public int checkSword(Sword sword) 
	{
		int index = -1;
		ArrayList<Entity> temp = gp.getRooms().getCurrentRoom().getMobs();
		
		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(i) != null)
			{
				//get the entity's position 
				sword.getSolidArea().x = sword.getX() + sword.getSolidArea().x;
				sword.getSolidArea().y = sword.getY() + sword.getSolidArea().y;
				
				//Object position
				temp.get(i).getSolidArea().x = temp.get(i).getX() + temp.get(i).getSolidArea().x;
				temp.get(i).getSolidArea().y = temp.get(i).getY() + temp.get(i).getSolidArea().y;
				switch(sword.getDirection())
				{
				case "up":
					
					if(sword.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
						//e.getSolidArea().y += e.getSpeed();
						System.out.println("attack");
						temp.get(i).setHealth(temp.get(i).getHealth() - 1);
						temp.get(i).setKnocked(true);
						temp.get(i).setKnockedDir("up");
						gp.playEffect(5);
					}
					break;
				case "down":
					sword.getSolidArea().y += sword.getSpeed();
					if(sword.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
						//e.getSolidArea().y += e.getSpeed();
						System.out.println("attack");
						temp.get(i).setHealth(temp.get(i).getHealth() - 1);
						temp.get(i).setKnocked(true);
						temp.get(i).setKnockedDir("down");
						gp.playEffect(5);
					}
					break;
				case "left":
					sword.getSolidArea().x -= sword.getSpeed();
					if(sword.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
						//e.getSolidArea().y += e.getSpeed();
						System.out.println("attack");
						temp.get(i).setHealth(temp.get(i).getHealth() - 1);
						temp.get(i).setKnocked(true);
						temp.get(i).setKnockedDir("left");
						gp.playEffect(5);
					}
					break;
				case "right":
					sword.getSolidArea().x += sword.getSpeed();
					if(sword.getSolidArea().intersects(temp.get(i).getSolidArea()))//checks if two rectangles are touching
					{
						//e.getSolidArea().y += e.getSpeed();
						System.out.println("attack");
						temp.get(i).setHealth(temp.get(i).getHealth() - 1);
						temp.get(i).setKnocked(true);
						temp.get(i).setKnockedDir("right");
						gp.playEffect(5);
					}
					break;
				}
			
			sword.getSolidArea().x = sword.getSolidAreaDefX();
			sword.getSolidArea().y = sword.getSolidAreaDefY();
			temp.get(i).getSolidArea().x = temp.get(i).getSolidAreaDefX();
			temp.get(i).getSolidArea().y = temp.get(i).getSolidAreaDefY();
			}
			if(temp.get(i).getHealth()<=0)
			{
				gp.getRooms().getCurrentRoom().addItem(new Rupee(gp, temp.get(i).getX(), temp.get(i).getY()));
				temp.remove(i);
			}
		}
		gp.setMobs(temp);
		return index;
	}
	
	/*
	Check if player is hitting any object, return index of the object
	@param Entity e passed to check what entity is being checked to interact
	@param boolean p, passed true if it is the main player
	*/
	public int checkObject(Entity e, boolean p) 
	{
		int index = -1;
		ArrayList<ItemEntity> temp = new ArrayList<ItemEntity>();
		temp = gp.getRooms().getCurrentRoom().getItems();
		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(i) != null)
			{
				//get the entity's position 
				e.getSolidArea().x = e.getX() + e.getSolidArea().x;
				e.getSolidArea().y = e.getY() + e.getSolidArea().y;
				
				//Object position
				temp.get(i).getSolidAreaI().x = temp.get(i).getWorldX() + temp.get(i).getSolidAreaI().x;
				temp.get(i).getSolidAreaI().y = temp.get(i).getWorldY() + temp.get(i).getSolidAreaI().y;
				switch(e.getDirection())
				{
				case "up":
					e.getSolidArea().y -= e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidAreaI()))//checks if two rectangles are touching
					{
						if(temp.get(i).isCollision())
						{
							e.setCollisionOn(true);
						}
						if(p == true)
						{
							index = i;
						}
					}
					break;
				case "down":
					e.getSolidArea().y += e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidAreaI()))//checks if two rectangles are touching
					{
						if(temp.get(i).isCollision())
						{
							e.setCollisionOn(true);
						}
						if(p == true)
						{
							index = i;
						}
					}
					break;
				case "left":
					e.getSolidArea().x -= e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidAreaI()))//checks if two rectangles are touching
					{
						if(temp.get(i).isCollision())
						{
							e.setCollisionOn(true);
						}
						if(p == true)
						{
							index = i;
						}
					}
					break;
				case "right":
					e.getSolidArea().x += e.getSpeed();
					if(e.getSolidArea().intersects(temp.get(i).getSolidAreaI()))//checks if two rectangles are touching
					{
						if(temp.get(i).isCollision())
						{
							e.setCollisionOn(true);
						}
						if(p == true)
						{
							index = i;
						}
					}
					break;
				}
			
			e.getSolidArea().x = e.getSolidAreaDefX();
			e.getSolidArea().y = e.getSolidAreaDefY();
			temp.get(i).getSolidAreaI().x = temp.get(i).getSolidAreaIDefX();
			temp.get(i).getSolidAreaI().y = temp.get(i).getSolidAreaIDefY();
			}
		}
		gp.setItems(temp);
		//returns index of the item that is collided with, -1 if nothing
		return index;
	}
}

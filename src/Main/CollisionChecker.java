  package Main;

import java.util.ArrayList;

import object.ItemEntity;
import object.Rupee;

public class CollisionChecker 
{

	private GamePanel gp;
	private RoomManager rooms;
	private int curRow;
	private int curCol;
	
	public CollisionChecker(GamePanel gp, RoomManager rooms, int curRow, int curCol)
	{
		
		this.gp = gp;
		this.rooms = rooms;
		this.curRow = curRow;
		this.curCol = curCol;
		
		
	}
	
	public void setCurRow(int n)
	{
		curRow = n;
	}
	
	public void setCurCol(int n)
	{
		curCol = n;
	}
	
	
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
					int thisX = gp.getPlayer().getX();
					int thisY = gp.getPlayer().getY();
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
				if(curRoom.getTile()[tileNum1].getTraverse() == true || curRoom.getTile()[tileNum2].getTraverse() == true)
				{
					
					
					
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
		
		System.out.println(entity.getSolidArea().y);
		
		int tileNum1;
		int tileNum2;
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
		switch(entity.getKnockedDir())
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
	
	
	public boolean checkFight(Player e) //Check if player is hitting any object, return index of the object 
	{
		int index = -1;
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
							System.out.println("hurt");
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							
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
							System.out.println("hurt");
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							
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
							System.out.println("hurt");
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							
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
							System.out.println("hurt");
							e.getSolidArea().x = x;
							e.getSolidArea().y = y;
							temp.get(i).getSolidArea().x = mobX;
							temp.get(i).getSolidArea().y = mobY;
							
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
		return false;
	}
	
	public int checkSword(Sword sword) //Check if player is hitting any object, return index of the object 
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
	
	//gp.getRooms().getCurrentRoom().addItem(new Rupee(gp, temp.get(i).getX(), temp.get(i).getY()));
				
	public int checkObject(Entity e, boolean p) //Check if player is hitting any object, return index of the object 
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
		return index;
	}
}

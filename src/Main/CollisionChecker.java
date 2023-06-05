package Main;

import object.ItemEntity;

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
		
		int tileNum1;
		int tileNum2;
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
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
				
			}
			
			
			break;
		case "down":
			
			entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
			if(entityBottomRow < 12 && entityRightCol < 16)
			{
				System.out.println();
				System.out.println("hi");
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
	public int checkEntity(Entity entity,Entity[]target)
	{
		return 2;
	}
	public int checkObject(Entity e, boolean p) //Check if player is hitting any object, return index of the object 
	{
		int index = 0;
		ItemEntity temp[] = new ItemEntity[10];
		//temp = gp.getItems();
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] != null)
			{
				//get the entity's position 
				e.getSolidArea().x = e.getX() + e.getSolidArea().x;
				e.getSolidArea().y = e.getY() + e.getSolidArea().y;
				
				//Object position
				temp[i].getSolidAreaI().x = temp[i].getWorldX() + temp[i].getSolidAreaI().x;
				temp[i].getSolidAreaI().y = temp[i].getWorldY() + temp[i].getSolidAreaI().y;
				switch(e.getDirection())
				{
				case "up":
					e.getSolidArea().y -= e.getSpeed();
					if(e.getSolidArea().intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				case "down":
					e.getSolidArea().y += e.getSpeed();
					if(e.getSolidArea().intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				case "left":
					e.getSolidArea().x -= e.getSpeed();
					if(e.getSolidArea().intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				case "right":
					e.getSolidArea().x += e.getSpeed();
					if(e.getSolidArea().intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				}
			
			//e.getSolidArea().x = e.getSolidAreaIDefX();
			//e.getSolidArea().y = e.getSolidAreaIDefY();
			temp[i].getSolidAreaI().x = temp[i].getSolidAreaIDefX();
			temp[i].getSolidAreaI().y = temp[i].getSolidAreaIDefY();
			}
		}
		//gp.setItems(temp);
		return index;
	}
	
}

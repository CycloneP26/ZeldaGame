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
		System.out.println(curRow);
		System.out.println(curCol);
		
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
		
		int entityLeftX = entity.getX() + entity.solidArea.x;
		int entityRightX = entity.getX() + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.getY() + entity.solidArea.y;
		int entityBottomY = entity.getY() + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftX / gp.getTileSize();
		int entityRightCol = entityRightX / gp.getTileSize();
		int entityTopRow = entityTopY / gp.getTileSize();
		int entityBottomRow = entityBottomY / gp.getTileSize();
		
		int tileNum1;
		int tileNum2;
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
		switch(entity.getDirection())
		{
		
		case "up":
			if(entityRightCol < 16)
			{
				
				entityTopRow = (entityTopY - entity.getSpeed()) / gp.getTileSize();
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].isCollision() == true || curRoom.getTile()[tileNum2].isCollision() == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.getTileSize();
			if(entityBottomRow < 12 && entityRightCol < 16)
			{
				
				System.out.println(entityBottomRow);
				tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].isCollision() == true || curRoom.getTile()[tileNum2].isCollision() == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			break;
			
		case "left":
			if(entityBottomRow < 12)
			{
				
				entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.getTileSize();
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				if(curRoom.getTile()[tileNum1].isCollision() == true || curRoom.getTile()[tileNum2].isCollision() == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			
			break;
			
		case "right":
			entityRightCol = (entityRightX + entity.getSpeed()) / gp.getTileSize();
			if(entityRightCol < 16 && entityBottomRow < 12)
			{
				
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].isCollision() || curRoom.getTile()[tileNum2].isCollision() == true)
				{
					
					entity.collisionOn = true;
				
				}
			}
			
			break;
		
		}
				
		
	}
	public int checkObject(Entity e, boolean p) //Check if player is hitting any object, return index of the object 
	{
		int index = 0;
		ItemEntity temp[] = new ItemEntity[10];
		temp = gp.getItems();
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] != null)
			{
				//get the entity's position 
				e.solidArea.x = e.getX() + e.solidArea.x;
				e.solidArea.y = e.getY() + e.solidArea.y;
				
				//Object position
				temp[i].getSolidAreaI().x = temp[i].getWorldX() + temp[i].getSolidAreaI().x;
				temp[i].getSolidAreaI().y = temp[i].getWorldY() + temp[i].getSolidAreaI().y;
				switch(e.getDirection())
				{
				case "up":
					e.solidArea.y -= e.getSpeed();
					if(e.solidArea.intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						System.out.println("up");
					}
					break;
				case "down":
					e.solidArea.y += e.getSpeed();
					if(e.solidArea.intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				case "left":
					e.solidArea.x -= e.getSpeed();
					if(e.solidArea.intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				case "right":
					e.solidArea.x += e.getSpeed();
					if(e.solidArea.intersects(temp[i].getSolidAreaI()))//checks if two rectangles are touching
					{
						
					}
					break;
				}
			
			e.solidArea.x = e.getSolidAreaDefX();
			e.solidArea.y = e.getSolidAreaDefY();
			temp[i].getSolidAreaI().x = temp[i].getSolidAreaIDefX();
			temp[i].getSolidAreaI().y = temp[i].getSolidAreaIDefY();
			}
		}
		gp.setItems(temp);
		return index;
	}
	
}

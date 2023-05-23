
public class CollisionChecker 
{

	private GamePanel gp;
	private RoomManager rooms;
	private int curRow;
	private int curCol;
	//To check the position 
	public CollisionChecker(GamePanel gp, RoomManager rooms, int curRow, int curCol)
	{
		
		this.gp = gp;
		this.rooms = rooms;
		this.curRow = curRow;
		this.curCol = curCol;
		System.out.println(curRow);
		System.out.println(curCol);
		//Make sure to get rid of this later 
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
		//find where the left most side is 
		int entityRightX = entity.getX() + entity.solidArea.x + entity.solidArea.width;
		//finds where the right most side is 
		int entityTopY = entity.getY() + entity.solidArea.y;
		//finds top 
		int entityBottomY = entity.getY() + entity.solidArea.y + entity.solidArea.height;
		//finds bottom 
		
		int entityLeftCol = entityLeftX / gp.tileSize;
		//finds the leftmost column of the character
		int entityRightCol = entityRightX / gp.tileSize;
		//finds the right most column
		int entityTopRow = entityTopY / gp.tileSize;
		//finds the top row 
		int entityBottomRow = entityBottomY / gp.tileSize;
		//finds the bottom row
		
		int tileNum1;
		int tileNum2;
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		//which room you are in 
		switch(entity.getDirection())
		{
		//checks the collision for the correct direction 
		case "up":
			if(entityRightCol < 16)
			{
				
				entityTopRow = (entityTopY - entity.getSpeed()) / gp.tileSize;
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				if(curRoom.getTile()[tileNum1].isCollision() == true || curRoom.getTile()[tileNum2].isCollision() == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
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
				
				entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.tileSize;
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				if(curRoom.getTile()[tileNum1].isCollision() == true || curRoom.getTile()[tileNum2].isCollision() == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			
			break;
			
		case "right":
			entityRightCol = (entityRightX + entity.getSpeed()) / gp.tileSize;
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
	
	
}

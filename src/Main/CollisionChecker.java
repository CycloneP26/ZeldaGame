package Main;

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
	
	
}

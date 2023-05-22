
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
		
		int entityLeftX = entity.x + entity.solidArea.x;
		int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.y + entity.solidArea.y;
		int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftX / gp.tileSize;
		int entityRightCol = entityRightX / gp.tileSize;
		int entityTopRow = entityTopY / gp.tileSize;
		int entityBottomRow = entityBottomY / gp.tileSize;
		
		int tileNum1;
		int tileNum2;
	
		
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
		switch(entity.direction)
		{
		
		case "up":
			entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
			
			if(entityBottomRow < 12 && entityRightCol < 16 && entityTopRow > -1 && entityLeftCol > -1)
			{
				System.out.println(entityTopRow);
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
			
			if(entityBottomRow < 12 && entityRightCol < 16 && entityTopRow > -1 && entityLeftCol > -1)
			{
				System.out.println(entityBottomRow);
				tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			break;
			
		case "left":
			entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
			
			if(entityBottomRow < 12 && entityRightCol < 16 && entityTopRow > -1 && entityLeftCol > -1)
			{
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
				if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
				{
					
					entity.collisionOn = true;
					
				}
				
			}
			
			
			break;
			
		case "right":
			entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
			
			if(entityBottomRow < 12 && entityRightCol < 16 && entityTopRow > -1 && entityLeftCol > -1)
			{
				tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
				tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
				if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
				{
					
					entity.collisionOn = true;
				
				}
			}
			
			break;
		
		}
				
		
	}
	
	
}

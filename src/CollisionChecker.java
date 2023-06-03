
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
	
	
}

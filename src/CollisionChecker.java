
public class CollisionChecker 
{

	private GamePanel gp;
	private RoomManager rooms;
	private int curRow;
	private int curCol;
	
	public CollisionChecker(GamePanel gp, RoomManager rooms, int curRow, int curCol)
	{
		
		this.gp = gp; //game panel
		this.rooms = rooms; //room
		this.curRow = curRow; //current row
		this.curCol = curCol; //current column 
		System.out.println(curRow);
		System.out.println(curCol);
		
	}
	
	//set row
	public void setCurRow(int n)
	{
		curRow = n;
	}
	
	//set column
	public void setCurCol(int n)
	{
		curCol = n;
	}
	
	//checks players location, and if it is running into a non-collidable wall
	//prevents Link from moving through
	public void checkTile(Entity entity)
	{
		
		int entityLeftX = entity.x + entity.solidArea.x; //calculate x coordinates of the left side of Link
		int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width; //calculates the x coordinates of the right side of Link
		int entityTopY = entity.y + entity.solidArea.y; // checks the Y coordinates of the top side of Link
		int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height; //checks the Y coordinates of the bottom side of link
		
		int entityLeftCol = entityLeftX / gp.tileSize; //calculates the column number to the left of Link
		int entityRightCol = entityRightX / gp.tileSize; //calculates the column number to the right of Link
		int entityTopRow = entityTopY / gp.tileSize; //calculates the column number to the top of Link
		int entityBottomRow = entityBottomY / gp.tileSize; //calculates the column number to the bottom of Link
		
		int tileNum1;
		int tileNum2;
	
		
		//rows and column of current room
		Room curRoom =rooms.getRoomArray().get(curRow).get(curCol);
		
		//uses to see what tile Link is facing is and prevents movement if the tile is non-collidable
		switch(entity.direction)
		{
		
		//checks the tile in front if Link is facing up, denying movement if the tile is non-collidable
		case "up":
			entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
			tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
			tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
			if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
			{
				
				entity.collisionOn = true;
				
			}
			
			break;
		
		//checks the tile to the bottom if Link is facing down, denying movement if the tile is non-collidable
		case "down":
			entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
			System.out.println(entityBottomRow);
			tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
			tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
			if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
			{
				
				entity.collisionOn = true;
				
			}
			
			break;
		
		//checks the tile to the left if Link is facing left, denying movement if the tile is non-collidable	
		case "left":
			entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
			tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
			tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
			if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
			{
				
				entity.collisionOn = true;
				
			}
			
			break;
		
		//checks the tile to the right if Link is facing right, denying movement if the tile is non-collidable
		case "right":
			entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
			tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
			tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
			if(curRoom.tile[tileNum1].collision == true || curRoom.tile[tileNum2].collision == true)
			{
				
				entity.collisionOn = true;
				
			}
			
			break;
		
		}
				
		
	}
	
	
}

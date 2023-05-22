
import java.util.ArrayList;

public class RoomManager {
	
	//A 2D arraylist of rooms
	private ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
	private GamePanel gp;
	private int currentRoomRow;
	private int currentRoomColumn;
	
	
	
	public RoomManager(GamePanel gp, int r, int c)
	{
		for(int i=0; i<r; i++)
		{
			ArrayList<Room> temp = new ArrayList<Room>();
			for(int j = 0; j<c; j++)
			{
				if(j==1 && i==1)
				{
					temp.add(new Room(gp, "111111100111111110000000000000011000000000000001100000000000000110000000000000010000000000000000000000000000000010000000000000011000000000000001100000000000000110000000000000011111111001111111"));
				}
				else
				{
					temp.add(new Room(gp));
				}
			}
			rooms.add(temp);
		}
		
		currentRoomRow = 1;
		currentRoomColumn = 1;
		
		this.gp = gp;
	}
	
	public RoomManager(GamePanel gp, int r, int c, String[][] maps)
	{
		for(int i=0; i<r; i++)
		{
			ArrayList<Room> temp = new ArrayList<Room>();
			for(int j = 0; j<c; j++)
			{
				if(maps[r][c].equals("X"))
				{
					temp.add(null);
				}
				else
				{
					temp.add(new Room(gp, maps[r][c]));
				}
				
			}
			rooms.add(temp);
		}
		
		currentRoomRow = 1;
		currentRoomColumn = 1;
		
		this.gp = gp;
	}
	
	public ArrayList<ArrayList<Room>> getRoomArray()
	{
		return rooms;
	}
	
	public void update()
	{
		
		Player player = gp.getPlayer();
		CollisionChecker cChecker = gp.getCollision();
		
		
		if(player.getY()<0)
		{
			player.setY(gp.screenHeight-40);
			if(isRoomAvailable(currentRoomRow-1, currentRoomColumn))
			{
				currentRoomRow--;
				cChecker.setCurRow(currentRoomRow);
			}
		}
		else if(player.getX()<0)
		{
			player.setX(gp.screenWidth-40);
			if(isRoomAvailable(currentRoomRow, currentRoomColumn-1))
			{
				currentRoomColumn--;
				cChecker.setCurCol(currentRoomColumn);
			}
		}
		else if(player.getY() + 40>gp.screenHeight)
		{
			player.setY(0);
			if(isRoomAvailable(currentRoomRow+1, currentRoomColumn))
			{
				currentRoomRow++;
				cChecker.setCurRow(currentRoomRow);
			}
		}
		else if(player.getX()+40>gp.screenWidth)
		{
			player.setX(0);
			if(isRoomAvailable(currentRoomRow, currentRoomColumn+1))
			{
				currentRoomColumn++;
				cChecker.setCurCol(currentRoomColumn);
			}
		}
		
	}
	
	public Room getSwitchedRoom(Room rOne, Room rTwo, int num, String direction)
	{
		int subNum = 0;
		String str = "";
		switch(direction)
		{
		case "up":
			subNum = 16*num;
			str += rTwo.getStr().substring(192-subNum-1);
			str += rOne.getStr().substring(0, 192-subNum);
		case "down":
			subNum = 16*num;
			str += rOne.getStr().substring(subNum);
			str += rTwo.getStr().substring(0, subNum);
			
		}
		
		
		
		Room temp = new Room(gp);
		return temp;
	}
	
	public int getRoomRow()
	{
		
		return currentRoomRow;
		
	}
	
	public int getRoomColumn()
	{
		
		return currentRoomColumn;
		
	}
	
	public boolean isRoomAvailable(int row, int column)
	{
		if(row<0)
		{
			return false;
		}
		if(column<0)
		{
			return false;
		}
		System.out.println(row);
		System.out.println(rooms.size());
		if(row>=rooms.size())
		{
			return false;
		}
		if(column>=rooms.get(0).size())
		{
			return false;
		}
		if(rooms.get(row).get(column).equals(null))
		{
			return false;
		}
		
		return true;
	}
}

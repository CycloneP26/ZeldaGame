
import java.util.ArrayList;
public class RoomManager {
	
	//A 2D arraylist of rooms
	private ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
	private GamePanel gp;
	private int currentRoomRow;
	private int currentRoomColumn;
	private Room currentRoom;
	
	
	
	public RoomManager(GamePanel gp, int r, int c)
	{
		for(int i=0; i<r; i++)
		{
			ArrayList<Room> temp = new ArrayList<Room>();
			for(int j = 0; j<c; j++)
			{
				if(j==1 && i==1)
				{
					temp.add(new Room(gp, "111111133111111113333333333333311333333333333331133333333333333113333333333333313333333333333333333333333333333313333333333333311333333333333331133333333333333113333333333333311111111331111111"));
				}
				else if(i==0)
				{
					temp.add(new Room(gp, "111111111111111133333333333333333333433333334333333433333333333333334333333433333333333333333333333333333343333333334333333333333333343333333333333343333334333333333333333333331111111331111111"));
				}
				else
				{
					temp.add(new Room(gp, "111111100111111110000000000000011000000000000001100000000000000110000000000000010000000000000000000000000000000010000000000000011000000000000001100000000000000110000000000000011111111001111111"));
				}
			}
			rooms.add(temp);
		}
		
		currentRoomRow = 1;
		currentRoomColumn = 1;
		this.currentRoom = getRoomArray().get(currentRoomRow).get(currentRoomColumn);

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
		this.currentRoom = getRoomArray().get(currentRoomRow).get(currentRoomColumn);
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
			if(isRoomAvailable(currentRoomRow-1, currentRoomColumn))
			{
				player.setY(gp.screenHeight-50);
				for(int i = 0; i<12; i++)
				{
					currentRoom = getSwitchedRoom(getRoomArray().get(currentRoomRow).get(currentRoomColumn), getRoomArray().get(currentRoomRow-1).get(currentRoomColumn), i, "up");  
					gp.repaint();
					gp.waitThread(125);
				}
				currentRoomRow--;
				cChecker.setCurRow(currentRoomRow);
			}
			
		}
		else if(player.getX()<0)
		{
			if(isRoomAvailable(currentRoomRow, currentRoomColumn-1))
			{
				player.setX(gp.screenWidth-40);
				currentRoomColumn--;
				cChecker.setCurCol(currentRoomColumn);
			}
		}
		else if(player.getY() + 40>gp.screenHeight)
		{
			if(isRoomAvailable(currentRoomRow+1, currentRoomColumn))
			{
				player.setY(0);
				for(int i = 0; i<12; i++)
				{
					currentRoom = getSwitchedRoom(getRoomArray().get(currentRoomRow).get(currentRoomColumn), getRoomArray().get(currentRoomRow+1).get(currentRoomColumn), i, "down");  
					gp.repaint();
					gp.waitThread(125);
				}
				currentRoomRow++;
				cChecker.setCurRow(currentRoomRow);
			}
		}
		else if(player.getX()+40>gp.screenWidth)
		{
			if(isRoomAvailable(currentRoomRow, currentRoomColumn+1))
			{
				player.setX(0);
				currentRoomColumn++;
				cChecker.setCurCol(currentRoomColumn);
			}
		}

		currentRoom = getRoomArray().get(currentRoomRow).get(currentRoomColumn);
		
	}
	
	public Room getSwitchedRoom(Room rOne, Room rTwo, int num, String direction)
	{
		int subNum = 0;
		String str = "";
		switch(direction)
		{
		case "up":
			subNum = 16*num;
			str += rTwo.getStr().substring(192-subNum);
			str += rOne.getStr().substring(0, 192-subNum);
			break;
		case "down":
			subNum = 16*num;
			str += rOne.getStr().substring(subNum);
			str += rTwo.getStr().substring(0, subNum);
			break;
		case "left":
		}
		
		
		Room temp = new Room(gp, str);
		return temp;
	}

	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	
	public void setCurrentRoom(Room cRoom)
	{
		currentRoom = cRoom;
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

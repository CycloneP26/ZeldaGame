
import java.util.ArrayList;

public class RoomManager {
	
	//A 2D arraylist of rooms
	private ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
	private GamePanel gp; //game panel
	private int rows; //rows of the room
	private int columns; //columns of the room
	
	
	//adds rooms
	public RoomManager(GamePanel gp, int r, int c)
	{
		for(int i=0; i<r; i++)
		{
			ArrayList<Room> temp = new ArrayList<Room>();
			for(int j = 0; j<c; j++)
			{
				temp.add(new Room(gp));
			}
			rooms.add(temp);
		}
	}
	
	public ArrayList<ArrayList<Room>> getRoomArray()
	{
		return rooms;
	}
	
	
	//checks if there is a room there in the direction Link is moving to
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

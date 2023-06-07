package Main;


import java.util.ArrayList;

import object.Key;
import object.Rupee;
public class RoomManager {
	
	//A 2D arraylist of rooms
	private ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
	private GamePanel gp;
	private int currentRoomRow;
	private int currentRoomColumn;
	private Room currentRoom;
	private boolean caveTravel;
	
	
	
	public RoomManager(GamePanel gp, int r, int c)
	{
		for(int i=0; i<r; i++)
		{
			ArrayList<Room> temp = new ArrayList<Room>();
			for(int j = 0; j<c; j++)
			{
				if(i==9 && j==0)
				{
					Room tempRoom = new Room(gp, "55555553355555555555j563355555555556333335555555556333333555555556333333375555555333333333333333553333333333335555333333333333555533333333333355553333333333335555555555555555555555555555555555");
					tempRoom.addMobs(new Octorok(gp, 250, 250));
					tempRoom.addItem(new Key(gp, 500, 350));
					tempRoom.addItem(new Rupee(gp, 400, 350));
					tempRoom.setToCaveC(10);
					tempRoom.setToCaveR(0);
					temp.add(tempRoom);
				}
				else if(i==9 && j==1)
				{
					Room tempRoom = new Room(gp, "559393933939393955939393393939395533333333333333563333933939393363939333333333333333339339393933539393333333333355333393393939335533333333333333553333333333333355999999999999995599999999999999");
					tempRoom.addMobs(new Octorok(gp, 250, 250));
					temp.add(tempRoom);
					
				}
				else if(i==9 && j==2)
				{
					Room tempRoom=new Room(gp,"1111111111111111a11111ba111111113a1111333333a11133a1113333333111333a113334333a113333ab33434333333333333334333c1133333333333331113333cd333333c111c11111dc1111111111111111111111111111111111111111");
					tempRoom.addMobs(new Spider(gp,300,300));
					tempRoom.addMobs(new Spider(gp,500,300));
					tempRoom.addMobs(new Spider(gp,300,200));
					tempRoom.addMobs(new Spider(gp,500,300));
					temp.add(tempRoom);
				}
				else if(i==9 && j==3)
				{
					temp.add(new Room(gp, "11111111111111111111111ba11111111111bab33a111111111b333333111111bab3333333ababa13333333333333333dcd3333333cdcdc1111d3333331111111111dcd33c1111111111111dc111111111111111111111111111111111111111"));
				}
				else if(i==9 && j==4)
				{
					temp.add(new Room(gp, "11111111111333111111111118133311111111b33333333311111b33333333331ba1b3333333333333333333333333331dc1d33hgggggggg11111d3e22222222111111de222222221111111e222222221111111e222222221111111e22222222"));
				}
				else if(i==9 && j==5)
				{
					temp.add(new Room(gp, "1111111111111111111111111111111133333333333333333333333333333333333333333333333333333333333333333333333333333333gggggggggggggggg2222222222222222222222222222222222222222222222222222222222222222"));
				}
				else if(i==9 && j==6)
				{
					temp.add(new Room(gp, "1111111111111111111111111111111133333333333333333333333333333333333333333333333333333333333333333333333333333333gggggggggggggggg2222222222222222222222222222222222222222222222222222222222222222"));
				}
				else if(i==9 && j==7)
				{
					temp.add(new Room(gp, "11111111111111111111111111111111331111111111111133a1ba111ba1baba333333333333333333333333333333333333333333333333gggggggggggggggg2222222222222222222222222222222222222222222222222222222222222222"));
				}
				else if(i==9 && j==8)
				{
					temp.add(new Room(gp, "111113333e222222111113333e222222111113333e222222ba11b3333e222222333333333e222222333333333e222222333333333e222222ggggggggg22222222222222222222222222222222222222222222222222222222222222222222222"));
				}
				else if(i==8 && j==8)
				{
					temp.add(new Room(gp, "111113333e222222a118b3333e222222333333333e222222333333333e222222333333333e222222333333333e222222333333333e222222333333333e222222333333333e222222c111d3333e222222111113333e222222111113333e222222"));
				}
				else if(i==8 && j==7)
				{
					temp.add(new Room(gp, "999999999999999999999999999999999933333333333333933999333333333333333333339999333339993993333333333333333399993393399933333333339933333333333333999999999999999999999999999999999999999999999999"));
				}
				else if(i==8 && j==6)
				{
					temp.add(new Room(gp, "999399999999399999939999999939993333333333933999993399999999399999339999999933339933999999993333993399999999333399339999999999993333999999999999999999999999999999999999999999999999999999999999"));
				}
				else if(i==8 && j==4)
				{
					temp.add(new Room(gp, "939333939399399993933393939939993333333333333399333333333393333933933393933333333333333333933333339333939333333333333333339333393333333333333399999999999993339999999999999333999999999999933399"));
				}
				else if(i==0 && j==10)
				{
					Room cave = new Room(gp, "cave");
					cave.setCaveC(0);
					cave.setCaveR(9);
					temp.add(cave);
					
				}
				else if(i==0)
				{
					temp.add(new Room(gp, "111111111111111133333333333333333333433333334333333433333333333333334333333433333333333333333333333333333343333333334333333333333333343333333333333343333334333333333333333333331111111331111111"));
				}
				else if(j<10)
				{
					temp.add(new Room(gp, "111111100111111110000000000000011000000000000001100000000000000110000000000000010000000000000000000000000000000010000000000000011000000000000001100000000000000110000000000000011111111001111111"));
				}
				
				
			}
			rooms.add(temp);
		}
		
		currentRoomRow = 9;
		currentRoomColumn = 0;
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
	
	public void setCurrentRoomRow(int c)
	{
		currentRoomRow = c;
	}
	
	public void setCurrentRoomCol(int c)
	{
		currentRoomColumn = c;
	}
	
	
	public void update()
	{
		
		Player player = gp.getPlayer();
		CollisionChecker cChecker = gp.getCollision();
		
		
		
		if(player.getY()<0)
		{
			if(isRoomAvailable(currentRoomRow-1, currentRoomColumn))
			{
				gp.getKeyHandler().setOn(false);
				gp.getKeyHandler().setUpPressed(false);
				
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
				
				gp.getKeyHandler().setOn(false);
				gp.getKeyHandler().setLeftPressed(false);
				
				for(int i = 1; i<16; i++)
				{
					currentRoom = getSwitchedRoom(getRoomArray().get(currentRoomRow).get(currentRoomColumn-1), getRoomArray().get(currentRoomRow).get(currentRoomColumn), i, "left");  
					gp.repaint();
					gp.waitThread(125);
				}
				player.setX(gp.screenWidth-40);
				currentRoomColumn--;
				cChecker.setCurCol(currentRoomColumn);
			}
			else
			{
				System.out.println("hiiii");
				gp.getKeyHandler().setOn(false);
				gp.getKeyHandler().setLeftPressed(false);
			}
		}
		else if(player.getY() + 40>gp.screenHeight)
		{
			if(isRoomAvailable(currentRoomRow+1, currentRoomColumn))
			{
				gp.getKeyHandler().setOn(false);
				gp.getKeyHandler().setDownPressed(false);
				
				for(int i = 0; i<12; i++)
				{
					currentRoom = getSwitchedRoom(getRoomArray().get(currentRoomRow).get(currentRoomColumn), getRoomArray().get(currentRoomRow+1).get(currentRoomColumn), i, "down");  
					gp.repaint();
					gp.waitThread(125);
				}
				player.setY(0);
				currentRoomRow++;
				cChecker.setCurRow(currentRoomRow);
			}
			else if(currentRoom.getType().equals("cave"))
			{
				currentRoomRow = currentRoom.getCaveR();
				currentRoomColumn = currentRoom.getCaveC();
				player.setY(250);
				player.setX(250);
			}
		}
		else if(player.getX()+40>gp.screenWidth)
		{
			
			if(isRoomAvailable(currentRoomRow, currentRoomColumn+1))
			{
				
				gp.getKeyHandler().setOn(false);
				gp.getKeyHandler().setRightPressed(false);
				
				for(int i = 1; i<16; i++)
				{
					currentRoom = getSwitchedRoom(getRoomArray().get(currentRoomRow).get(currentRoomColumn+1), getRoomArray().get(currentRoomRow).get(currentRoomColumn), i, "right");  
					gp.repaint();
					gp.waitThread(125);
				}
				player.setX(0);
				currentRoomColumn++;
				cChecker.setCurCol(currentRoomColumn);
			}
		}
		gp.getKeyHandler().setOn(true);
		currentRoom = getRoomArray().get(currentRoomRow).get(currentRoomColumn);
		cChecker.setCurRow(currentRoomRow);
		cChecker.setCurCol(currentRoomColumn);
		
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
			gp.getPlayer().setY((48*num));
			break;
		case "down":
			subNum = 16*num;
			str += rOne.getStr().substring(subNum);
			str += rTwo.getStr().substring(0, subNum);
			gp.getPlayer().setY((48*12)-(48*num));
			break;
		case "left":
			for(int i = 0; i<192; i++)
			{
				if(i%16==0)
				{
					str += rOne.getStr().substring(i+16-num, i+16);
					i+= num;
					i--;
				}
				else
				{
					str += rTwo.getStr().substring(i-num, i-num+1);
				}
				
			}
			gp.getPlayer().setX((48*num));
			break;
		case "right":
			for(int i = 0; i<192; i++)
			{
				if(i%16==16-num)
				{
					str += rOne.getStr().substring(i-(16-num), i+num-(16-num));
					i+= num;
					i--;
					
				}
				else
				{
					str += rTwo.getStr().substring(i+num, i+num+1);
				}
				
			}
			gp.getPlayer().setX((48*16)-(48*num));
			break;
		case "cave":
			for(int i=0; i<num; i++)
			{
				str += "88";
			}
			str += rOne.getStr().substring(num);
			break;
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
		try
		{
			if(rooms.get(row).get(column).equals(null))
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		
		return true;
	}
}

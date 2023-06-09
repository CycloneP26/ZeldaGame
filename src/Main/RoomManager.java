package Main;

/*
This class takes the rooms and turns them into a large map that consists of all the rooms. It also compiles all the mobs, items, entities, etc that are in each room and handles movement between rooms
@author Pranay Thatikonda
@author David Kostanyan
*/
import java.util.ArrayList;

import object.Key;
import object.Rupee;
import object.HeartContainer;
import object.StartSword;
import object.Item_Bomb;
import object.Fire;
public class RoomManager {
	
	//A 2D arraylist of rooms
	private ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
	//The gamepanel that this is in
	private GamePanel gp;
	//the index of the row that the current room is in in the arraylist
	private int currentRoomRow;
	//the index of the column that the current room is in in the arraylist
	private int currentRoomColumn;
	//the current room that the player is in
	private Room currentRoom;
	
	
	/*Creates the roomManager and the places that the players and mobs interact
	@param gp is the gamepanel that it is in
	@param r is the number of rows of rooms
	@param c is the number of columns of rooms
	*/
	public RoomManager(GamePanel gp, int r, int c)
	{
		for(int i=0; i<r; i++)
		{
			ArrayList<Room> temp = new ArrayList<Room>();
			for(int j = 0; j<c; j++)
			{
				if(i==9 && j==0)
				{
					Room tempRoom = new Room(gp, "55555555555555555555j563355555555556333335555555556333333555555556333333375555555333333333333333553333333333335555333333333333555533333333333355553333333333335555555555555555555555555555555555");
					tempRoom.addMobs(new Octorok(gp, 250, 250));
					tempRoom.addItem(new Key(gp, 500, 350));
					tempRoom.addItem(new Rupee(gp, 400, 350));
					
					tempRoom.addItem(new HeartContainer(gp, 250, 350));
					tempRoom.setToCaveC(10);
					tempRoom.setToCaveR(0);
					temp.add(tempRoom);
				}
				else if(i==9 && j==1)
				{
					Room tempRoom = new Room(gp, "559999999999999955939393393939395533333333333333563333933939393363939333333333333333339339393933539393333333333355333393393939335533333333333333553333333333333355999999999999995599999999999999");
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
					Room tempRoom=new Room(gp, "11111111111111111111111ba11111111111bab33a111111111b333333111111bab3333333ababa13333333333333333dcd3333333cdcdc1111d3333331111111111dcd33c1111111111111dc111111111111111111111111111111111111111");
					tempRoom.addMobs(new Spider(gp,300,300));
					tempRoom.addMobs(new Spider(gp,500,300));
					tempRoom.addMobs(new Spider(gp,300,200));
					tempRoom.addMobs(new Spider(gp,500,300));
					temp.add(tempRoom);
				}
				else if(i==9 && j==4)
				{
					Room tempRoom=new Room(gp, "11111111111333111111111111133311111111b33333333311111b33333333331ba1b3333333333333333333333333331dc1d33hgggggggg11111d3e22222222111111de222222221111111e222222221111111e222222221111111e22222222");
					tempRoom.setSecret(true);
					tempRoom.setSecretRoom("1111111111133311111111111j133311111111b33333333311111b33333333331ba1b3333333333333333333333333331dc1d33hgggggggg11111d3e22222222111111de222222221111111e222222221111111e222222221111111e22222222");
					tempRoom.setSecretFound(false);
					tempRoom.addMobs(new Leever(gp,600,150));
					tempRoom.addMobs(new Leever(gp,420,200));
					tempRoom.addMobs(new Zora(gp,500,500));
					tempRoom.setToCaveC(10);
					tempRoom.setToCaveR(2);
					temp.add(tempRoom);
				}
				else if(i==9 && j==5)
				{
					Room tempRoom=new Room(gp, "1111111111111111111111111111111133333333333333333333333333333333333333333333333333333333333333333333333333333333gggggggggggggggg2222222222222222222222222222222222222222222222222222222222222222");
					tempRoom.addMobs(new Leever(gp,600,150));
					tempRoom.addMobs(new Leever(gp,500,100));
					tempRoom.addMobs(new Leever(gp,400,200));
					tempRoom.addMobs(new Leever(gp,300,250));
					tempRoom.addMobs(new Zora(gp,500,500));
					temp.add(tempRoom);
				}
				else if(i==9 && j==6)
				{
					Room tempRoom=new Room(gp, "1111111111111111111111111111111133333333333333333333333333333333333333333333333333333333333333333333333333333333gggggggggggggggg2222222222222222222222222222222222222222222222222222222222222222");
					tempRoom.addMobs(new BlueOctorok(gp,600,150));
					tempRoom.addMobs(new BlueOctorok(gp,600,250));
					tempRoom.addMobs(new BlueOctorok(gp,400,200));
					tempRoom.addMobs(new BlueOctorok(gp,300,250));
					tempRoom.addMobs(new Zora(gp,500,500));
					temp.add(tempRoom);
				}
				else if(i==9 && j==7)
				{
					Room tempRoom=new Room(gp, "11111111111111111111111111111111331111111111111133a1ba111ba1baba333333333333333333333333333333333333333333333333gggggggggggggggg2222222222222222222222222222222222222222222222222222222222222222");
					tempRoom.addMobs(new Octorok(gp,600,250));
					tempRoom.addMobs(new Octorok(gp,500,250));
					tempRoom.addMobs(new Octorok(gp,400,250));
					tempRoom.addMobs(new Zora(gp,500,500));
					temp.add(tempRoom);
				}
				else if(i==9 && j==8)
				{
					Room tempRoom=new Room(gp, "111113333e222222111113333e222222111113333e222222ba11b3333e222222333333333e222222333333333e222222333333333e222222ggggggggg22222222222222222222222222222222222222222222222222222222222222222222222");
					tempRoom.addMobs(new BlueOctorok(gp,300,150));
					tempRoom.addMobs(new Zora(gp,500,500));
					temp.add(tempRoom);
				}
				else if(i==8 && j==8)
				{
					Room tempRoom = new Room(gp, "111113333e222222a11jb3333e222222333333333e222222333333333e222222333333333e222222333333333e222222333333333e222222333333333e222222333333333e222222c111d3333e222222111113333e222222111113333e222222");
					tempRoom.addMobs(new BlueOctorok(gp,200,400));
					tempRoom.addMobs(new Octorok(gp,200,300));
					tempRoom.addMobs(new Octorok(gp,300,400));
					tempRoom.addMobs(new Octorok(gp,150,350));
					tempRoom.addMobs(new Octorok(gp,250,400));
					tempRoom.setToCaveC(10);
					tempRoom.setToCaveR(4);
					temp.add(tempRoom);
					
				}
				else if(i==8 && j==7)
				{
					Room tempRoom=new Room(gp, "999999999999999999999999999999999933333333333333933999333333333333333333339999333339993993333333333333333399993393399933333333339933333333333333999999999999999999999999999999999999999999999999");
					
				}
				else if(i==8 && j==6)
				{
					temp.add(new Room(gp, "999399999999399999939999999939993333333333933999993399999999399999339999999933339933999999993333993399999999333399339999999999993333999999999999999999999999999999999999999999999999999999999999"));
				}
				else if(i==8 && j==5)
				{
					temp.add(new Room(gp, "999939999999939999993999999993999999399933333333999939933333339933333993333333993333399333333399333339933333339999999993333333999999999933333333999999999999999999999999999999999999999999999999"));
				}
				else if(i==8 && j==4)
				{
					Room tempRoom=new Room(gp, "939333939399399993933393939939999333333333333399933333333393333993933393933333339333333333933333939333939333333393333333339333399333333333333399999999999993339999999999999333999999999999933399");
					tempRoom.addMobs(new BlueOctorok(gp,500,200));
					tempRoom.addMobs(new BlueOctorok(gp,200,200));
					tempRoom.addMobs(new Moblin(gp,400,300));
					tempRoom.addMobs(new Moblin(gp,400,200));
					tempRoom.addMobs(new BlueMoblin(gp,200,150));
					temp.add(tempRoom);
					
				}
				else if(i==7 && j==8)
				{
					temp.add(new Room(gp, "111111111e2222221111b3333e222222111133333e222222111133333e222222111b33333e22222211b333333e222222113333333e222222113333333e22222211d333333e2222221111d3333e222222111113333e222222111113333e222222"));
				}
				else if(i==7 && j==7)
				{
					temp.add(new Room(gp, "555555555555555555555675555555555633333375563375633333333763333533339333333393353333333333333335333393333333933553333333355333355533333355553355555555555555555555555555555555555555555555555555"));
				}
				else if(i==7 && j==6)
				{
					Room tempRoom=new Room(gp, "999399999999399999939999999939999993999999993999999399999999399933333333333333333333333333333333333333333333333399939999999939999993999999993999999399999999399999939999999939999993999999993999");
					tempRoom.addMobs(new BlueMoblin(gp,300,300));
					tempRoom.addMobs(new BlueMoblin(gp,350,300));
					tempRoom.addMobs(new BlueMoblin(gp,400,300));
					tempRoom.addMobs(new BlueMoblin(gp,450,300));
					temp.add(tempRoom);
				}
				else if(i==7 && j==5)
				{
					Room tempRoom=new Room(gp, "999999999999999999999999999999993333333333333999999999999999399999999999999933339999999999993333999999999999333399999999999939993333399999993999999939999999399999993999999939999999399999993999");
					tempRoom.addMobs(new BlueMoblin(gp,600,300));
					tempRoom.addMobs(new BlueMoblin(gp,600,350));
					temp.add(tempRoom);
				}
				else if(i==7 && j==4)
				{
					Room tempRoom=new Room(gp, "999999999999999993933393939933999333333333333333933333333393333993933393933333399333333333933339939333939333333993333333339333399333333333333333939333939399399993933393939939999393339393993999");
					tempRoom.addMobs(new BlueMoblin(gp,100,100));
					tempRoom.addMobs(new BlueMoblin(gp,50,200));
					tempRoom.addMobs(new Moblin(gp,200,200));
					tempRoom.addMobs(new Moblin(gp,200,300));
					temp.add(tempRoom);
				}
				else if(i==6 && j==7)
				{
					Room tempRoom=new Room(gp, "999999999999999999999999999999999933333333333399933333333333333933333333333333393333333333333339333333333333333993333333333333399933333333333399999999999999999999999999999999999999999999999999");
					tempRoom.addMobs(new Armos(gp,300,300));
					tempRoom.addMobs(new Armos(gp,500,300));
					temp.add(tempRoom);
				}
				else if(i==6 && j==6)
				{
					Room tempRoom=new Room(gp, "939393933939393993939393393939399333333333333339933333933339333993939333393339339333339333393333939393333933393393333393333933399333333333333339999399999999399999939999999939999993999999993999");
					tempRoom.addMobs(new BlueMoblin(gp,50,200));
					tempRoom.addMobs(new BlueMoblin(gp,50,300));
					temp.add(tempRoom);
				}
				else if(i==5 && j==6)
				{
					temp.add(new Room(gp, "999999999999999999999993399999999333333333333339939393333339333993333333333339399393933333393339933333333333393993939333333933399333333333333339939393933939393993939393393939399393939339393939"));
				}
				else if(i==0 && j==10)
				{
					Room cave = new Room(gp, "cave");
					cave.setCaveC(0);
					cave.setCaveR(9);
					cave.addFires(new Fire(gp, 200, 250));
					cave.addFires(new Fire(gp, 500, 250));
					cave.addItem(new StartSword(gp, 350, 250));
					temp.add(cave);
					
				}
				
				else if(i==2 && j==10)
				{
					Room cave = new Room(gp, "cave");
					cave.setCaveC(4);
					cave.setCaveR(9);
					cave.addItem(new HeartContainer(gp, 350, 250, true));
					cave.addItem(new Item_Bomb(gp, 250, 250, true));
					cave.addFires(new Fire(gp, 200, 250));
					cave.addFires(new Fire(gp, 500, 250));
					temp.add(cave);
					
				}
				else if(i==4 && j==10)
				{
					Room cave = new Room(gp, "cave");
					cave.setCaveC(8);
					cave.setCaveR(8);
					cave.addFires(new Fire(gp, 200, 250));
					cave.addFires(new Fire(gp, 500, 250));
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
	/* This method handles updating the current room
	If the player moves off the screen, it checks if the room is available and then moves the player
	It also does the animation for the switching rooms
	*/
	public void update()
	{
		
		Player player = gp.getPlayer();
		CollisionChecker cChecker = gp.getCollision();
		if(currentRoom.isSecretFound())
		{
			Room ogRoom = rooms.get(currentRoomRow).get(currentRoomColumn);
			Room secretRoom = new Room(gp, ogRoom.getSecretRoom());
			secretRoom.setToCaveC(ogRoom.getToCaveC());
			secretRoom.setToCaveR(ogRoom.getToCaveR());
			for(int i = 0; i<ogRoom.getMobs().size(); i++)
			{
				secretRoom.getMobs().add(ogRoom.getMobs().get(i));
			}
			for(int i = 0; i<ogRoom.getItems().size(); i++)
			{
				secretRoom.getItems().add(ogRoom.getItems().get(i));
			}
			rooms.get(currentRoomRow).remove(currentRoomColumn);
			rooms.get(currentRoomRow).add(currentRoomColumn, secretRoom);
		}
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
		}
		else if(player.getY() + 40>gp.screenHeight)
		{
			
			if(currentRoom.getType().equals("cave"))
			{
				
				currentRoomRow = currentRoom.getCaveR();
				currentRoomColumn = currentRoom.getCaveC();
				player.setY(250);
				player.setX(250);
			}
			else if(isRoomAvailable(currentRoomRow+1, currentRoomColumn))
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
	
	/*This method helps in the animation of the switching rooms
	@param rOne is the room that the player is coming from
	@param rTwo is the room that the player is going to
	@param num is the stage that the animation is in, 1-16 for left and right, 1-12 for up and down
	@param direction is the direction that the player is going in
	@return Room is the room that has a layout that is between rOne and rTwo using some string manipulation
	how far between it is is based off of num
	*/
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
				str += "8888";
			}
			try
			{
				str += rOne.getStr().substring(num*4);
			}
			catch(Exception e)
			{
				str += "8";
			}
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
	/*Checks if the room that the player is going to is available or not
	@param row is the row of the room that the method is checking
	@param column is the column of the room that the method is checking
	@return boolean returns true if the room exists and can be entered or false if the room doesn't exist
	*/
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

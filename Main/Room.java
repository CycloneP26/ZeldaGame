package Main;


import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.ItemEntity;
public class Room {
	
	
	private GamePanel gp;
	private Tile[] tile;
	private int[][] tileLayout;
	private String str;
	private ArrayList<Entity> mobs;
	private ArrayList<ItemEntity> items;
	private ArrayList<Entity> projectiles;
	private int caveR;
	private int caveC;
	private String type;
	private int toCaveC;
	private int toCaveR;
	public Room(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[30];
		
		getTileImage();
		
		for(int i = 0; i<tileLayout.length; i++)
		{
			for(int j = 0; j<tileLayout[0].length; j++)
			{
				tileLayout[i][j] = (int)(Math.random()*3);
			}
		}
		setType("something");
		str = mapToStr(tileLayout);
		mobs = new ArrayList<Entity>();
		items = new ArrayList<ItemEntity>();
		projectiles=new ArrayList<Entity>();
		
	}
	public Room(GamePanel gp, String str)
	{
		this.gp = gp;
		this.str = str;
		
		if(str.equals("cave"))
		{
			this.str = "111111111111111111111111111111111188888888888811118888888888881111888888888888111188888888888811118888888888881111888888888888111188888888888811118888888888881111111118811111111111111881111111";
			setType("cave");
		}
		else
		{
			setType("something");
		}
		tile = new Tile[30];
		tileLayout = new int[12][16];
		getTileImage();
		
		
		tileLayout = strToMap(this.str);
		mobs = new ArrayList<Entity>();
		items = new ArrayList<ItemEntity>();
		projectiles=new ArrayList<Entity>();
	}
	
	public int[][] strToMap(String str)
	{
		int[][] retArray = new int[12][16];
		int count = 0;
		for(int i = 0; i<12; i++)
		{
			for(int j = 0; j<16; j++)
			{
				if(str.substring(count, count+1) != "\n" || str.substring(count, count+1) != "\r")
				{
					
					//retArray[i][j]+=Integer.parseInt(str.substring(count, count+1));
					retArray[i][j]+= convertToNum(str.substring(count, count+1));
					
				}
				
				count++;
			}
		}
		
		
		return retArray;
	}

	public String mapToStr(int[][] map)
	{
		String retStr = "";
		for(int i = 0; i<map.length; i++)
		{
			for(int j = 0; j<map[0].length; j++)
			{
				retStr += map[i][j];
			}
		}
		return retStr;
	}
	
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/groundWithDots.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/download.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/clearFlatGround.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/middleRock.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/greenWall.png"));
			tile[5].collision = true;
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/greenDiagOne.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/greenDiagTwo.png"));
			tile[7].collision = true;
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
			
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cactus.png"));
			tile[9].collision = true;
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cactus.png"));
			tile[10].collision = true;
			
			//a
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rockDiagOne.png"));
			tile[11].collision = true;
			
			//b
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rockDiagTwo.png"));
			tile[12].collision = true;
			
			//c
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rockDiagThree.png"));
			tile[13].collision = true;
			
			//d
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rockDiagFour.png"));
			tile[14].collision = true;
			
			//e
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterLeft.png"));
			tile[15].collision = true;
			
			//f
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterRight.png"));
			tile[16].collision = true;
			
			//g
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterTop.png"));
			tile[17].collision = true;
			
			//h
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterTopLeft.png"));
			tile[18].collision = true;
			
			//i
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterTopRight.png"));
			tile[19].collision = true;
			
			//j
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
			tile[20].setTraverse(true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int[][] getTileLayout()
	{
		
		return tileLayout;
		
	}
	
	public int convertToNum(String s)
	{
		char c = s.charAt(0);
		if(Character.isDigit(c))
		{
			return Integer.parseInt(s);
		}
		else
		{
			char temp = c;
			int count = 0;
			while(temp != 'a')
			{
				temp--;
				count++;
			}
			return count + 11;
		}
	}
	
	public ArrayList<Entity> getMobs()
	{
		return mobs;
	}
	public void addItem(ItemEntity i)
	{
		items.add(i);
	}
	public void addMobs(Entity e)
	{
		mobs.add(e);
	}
	public void setMobs(ArrayList<Entity> mobs)
	{
		this.mobs = mobs;
	}
	public Tile[] getTile()
	{
		return tile;
	}
	
	public void draw(Graphics2D g2)
	{
		for(int i = 0; i<tileLayout.length; i++)
		{
			for(int j = 0; j<tileLayout[0].length; j++)
			{
				g2.drawImage(tile[tileLayout[i][j]].image, j*48, i*48, gp.tileSize, gp.tileSize, null);
			}
		}
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public ArrayList<ItemEntity> getItems() {
		return items;
	}
	public void setItems(ArrayList<ItemEntity> items) {
		this.items = items;
	}
	public int getCaveR() {
		return caveR;
	}
	public void setCaveR(int caveR) {
		this.caveR = caveR;
	}
	public int getCaveC() {
		return caveC;
	}
	public void setCaveC(int caveC) {
		this.caveC = caveC;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getToCaveC() {
		return toCaveC;
	}
	public void setToCaveC(int toCaveC) {
		this.toCaveC = toCaveC;
	}
	public int getToCaveR() {
		return toCaveR;
	}
	public void setToCaveR(int toCaveR) {
		this.toCaveR = toCaveR;
	}
	public ArrayList<Entity> getProjectiles() {
		return projectiles;
	}
	public void setProjectiles(ArrayList<Entity> projectiles) {
		this.projectiles = projectiles;
	}
}
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

		str = mapToStr(tileLayout);
		mobs = new ArrayList<Entity>();
		items = new ArrayList<ItemEntity>();
		
	}
	public Room(GamePanel gp, String str)
	{
		this.gp = gp;
		this.str = str;
		
		tile = new Tile[30];
		tileLayout = new int[12][16];
		getTileImage();
		
		tileLayout = strToMap(str);
		mobs = new ArrayList<Entity>();
		items = new ArrayList<ItemEntity>();
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
}


import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Room {
	
	
	private GamePanel gp;
	private Tile[] tile;
	private int[][] tileLayout;
	private String str;
	
	public Room(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[10];
		
		getTileImage();
		
		for(int i = 0; i<tileLayout.length; i++)
		{
			for(int j = 0; j<tileLayout[0].length; j++)
			{
				tileLayout[i][j] = (int)(Math.random()*3);
			}
		}

		str = mapToStr(tileLayout);
		
	}
	public Room(GamePanel gp, String str)
	{
		this.gp = gp;
		this.str = str;
		
		tile = new Tile[10];
		tileLayout = new int[12][16];
		getTileImage();
		
		tileLayout = strToMap(str);
		
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
					retArray[i][j]+=Integer.parseInt(str.substring(count, count+1));
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
}

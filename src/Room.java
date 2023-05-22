
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
		tileLayout = new int[12][16];
		getTileImage();
		
		for(int i = 0; i<tileLayout.length; i++)
		{
			for(int j = 0; j<tileLayout[0].length; j++)
			{
				tileLayout[i][j] = (int)(Math.random()*3);
			}
		}
		
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
				retArray[i][j]+=Integer.parseInt(str.substring(count, count+1));
				count++;
			}
		}
		
		
		return retArray;
	}
	
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/groundWithDots.png")));
			
			tile[1] = new Tile();
			tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/download.png")));
			tile[1].setCollision(true);
			
			tile[2] = new Tile();
			tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
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
				g2.drawImage(tile[tileLayout[i][j]].getImage(), j*48, i*48, gp.tileSize, gp.tileSize, null);
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

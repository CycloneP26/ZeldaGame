import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	int[][] tileLayout;
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[10];
		
		getTileImage();
		
		tileLayout = new int[12][16];
		
		for(int i = 0; i<tileLayout.length; i++)
		{
			for(int j = 0; j<tileLayout[0].length; j++)
			{
				tileLayout[i][j] = (int)(Math.random()*3);
			}
		}
	}
	
	public TileManager(GamePanel gp, int[][] tiles)
	{
		this.gp = gp;
		
		tile = new Tile[10];
		
		getTileImage();
		
		tileLayout = tiles;
	}
	
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/groundWithDots.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/download.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int[][] getTiles()
	{
		return tileLayout;
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
}

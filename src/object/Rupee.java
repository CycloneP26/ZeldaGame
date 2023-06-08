package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Rupee extends ItemEntity
{
	public Rupee(GamePanel gp) 
	{
		super(gp);
		setName("rupee");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Rupee.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public Rupee(GamePanel gp, int x, int y) 
	{
		super(gp);
		setName("rupee");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Rupee.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
	}	
}
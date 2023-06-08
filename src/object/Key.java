package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Key extends ItemEntity
{	
	public Key(GamePanel gp)
	{
		super(gp);
		setName("key");
		try 
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"))); 
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		
	}
	public Key(GamePanel gp, int x, int y)
	{
		super(gp);
		setName("key");
		try 
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"))); 
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		setWorldX(x);
		setWorldY(y);
	}
}

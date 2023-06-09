package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class HeartContainer extends ItemEntity 
{

	public HeartContainer(GamePanel gp)
	{
		super(gp);
		setName("heart");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/New_Piskel.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public HeartContainer(GamePanel gp, int x, int y)
	{
		super(gp);
		setName("heart");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/New_Piskel.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
	}
	public HeartContainer(GamePanel gp, int x, int y, boolean shop)
	{
		super(gp);
		setName("heart");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/New_Piskel.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
		setCost(20);
		setShop(shop);
	}
}


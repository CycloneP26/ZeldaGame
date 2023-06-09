package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
/*
This class is to create a HeartContainer that the player can pick up to "increase their max hearts"
It extends ItemEntity to be drawn with the rest of the items 
@author Sachin Chhaya 
*/
public class HeartContainer extends ItemEntity 
{
	//Unused Constructor (Base version)
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
	/*
	Constructor to create a heart container that is not within a shop 
	@param GamePanel gp, access the main GamePanel
	@param int x,y position 
	*/
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
	/*
	Constructor to create a heart container that is used in a shop 
	@param GamePanel gp, access the main GamePanel
	@param int x,y position 
	*/
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


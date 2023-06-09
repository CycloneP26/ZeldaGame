package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
/*
Creates an item that is able to be picked up by Link
It is the original sword that is found in the cave
extends ItemEntity to be able to be picked up 
@author Sachin Chhaya 
*/
public class StartSword extends ItemEntity
{
	//Not going to be used (base Constructor) 
	public StartSword(GamePanel gp)
	{
		super(gp);
		setName("startSword");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/StartSword.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/*
	Constructor that creates a starting sword at a specific location on the room
	@param GamePanel gp, access the main GamePanel
	@param int x,y position 
	*/
	public StartSword(GamePanel gp, int x, int y)
	{
		super(gp);
		setWorldX(x);
		setWorldY(y);
		setName("startSword");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/StartSword.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

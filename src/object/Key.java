package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

/*
Creates a key item that can be placed anywhere and picked up. The purpose of the class is to initialize its image
and to determine the position that it will be in. 

@author Sachin Chhaya 
*/

public class Key extends ItemEntity
{	
	//This contructor will most likely not be used, but it is if u want to make a key with no set location
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
	/*
	This contructor makes a key that can access the GamePanel and is able to pass x and y values to 
	choose it's position. The try/catch block is used to correctly path the image.
	@param GamePanel to allow the Key to access and be added to the GamePanel
	@param int x, the x position that it will initially have
	@param int y, the y position that it will initially have
	*/
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

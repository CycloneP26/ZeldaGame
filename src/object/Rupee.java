package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
/*
This class creates a Rupee item that can be picked up and added to the inventory.
The contructor's purpose is to correctly path the image so it can be properly used in GamePanel
@author Sachin Chhaya 
*/
public class Rupee extends ItemEntity
{
	//This constructor will most likely not be used check the other one 
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
	/*
	Constructor for the rupee that initializes its position and image
	@param GamePanel used for the Rupee to access the GamePanel 
	@param int x is used to determine the x position
	@param int y is used to determine the y position
	*/
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

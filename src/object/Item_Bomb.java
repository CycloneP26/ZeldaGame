package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
/*
Creates a bomb item that can be picked up and added to the "inventory"
@author Sachin Chhaya 
*/
public class Item_Bomb extends ItemEntity 
{
	/*
	Constructor that creates a bomb at a given location
	@param GamePanel gp, access the main GamePanel
	@param int x,y position 
	*/
	public Item_Bomb(GamePanel gp, int x, int y) {
		super(gp);
		setName("bomb");
		try
		{
		setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Bomb.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
	}
	/*
	Constructor that creates a bomb in a shop to be used 
	@param GamePanel gp, access the main GamePanel
	@param int x,y position 
	@param boolean shop to verify if this will require rupees to access 
	*/
	public Item_Bomb(GamePanel gp, int x, int y, boolean shop) {
		super(gp);
		setName("bomb");
		try
		{
		setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Bomb.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
		setShop(shop);
		setCost(10);
	}
}

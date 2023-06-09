package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
/*
Creates a fire object that is within the caves and animates between two images
@author Sachin Chhaya
*/
public class Fire extends ItemEntity 
{
	//Access the main Game Panel 
	private GamePanel gp;
	/*
	Creates a fire object and calls super constructor to pass the main GamePanel to itemEntity
	@param GamePanel gp passed to access
	@param int x, y position 
	*/
	public Fire(GamePanel gp, int x, int y) 
	{
		super(gp);
		this.gp = gp;
		getFire();
		setWorldX(x);
		setWorldY(y);

	}
	//Paths and sets up the images for the fire 
	public void getFire()
	{
		setFire1(setup("/objects/fire1",gp.getTileSize(), gp.getTileSize()));
		setFire2(setup("/objects/fire2", gp.getTileSize(), gp.getTileSize()));
	}

}

package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
The bomb class sets up the images and life span of the bomb item, that the player can use.
This is done through extending useableEntity class with a setup method.
@author Pranay Thatikonda
@author Sachin Chhaya 
*/
public class Bomb extends useableEntity  
{
	//Be able to access the gamePanel 
	private GamePanel gp;
	
	//Sets up the images and calls the super constructor for the GamePanel
	//@param GamePanel gp to be able to get the tile size 
	public Bomb(GamePanel gp) 
	{
		super(gp);
		this.setLifeSpan(80);
		this.gp = gp;
		//getBombImage();
		setbPhase1(setup("/objects/Bomb", gp.getTileSize(), gp.getTileSize()));
		setbPhase2(setup("/objects/bPhase2", 144, 144));
		setbPhase3(setup("/objects/bPhase3", 144, 144));
	}
//	public void getBombImage()
//	{
//		setbPhase1(setup("/objects/Bomb", gp.getTileSize(), gp.getTileSize()));
//		setbPhase2(setup("/objects/bPhase2", 144, 144));
//		setbPhase3(setup("/objects/bPhase3", 144, 144));
//	}
}

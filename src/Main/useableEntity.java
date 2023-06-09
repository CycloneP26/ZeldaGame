package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
This class is the superClass for all useable items that the player has (We only have one item as of now)
It has its own draw and update method because these items have its own animations
@author Sachin Chhaya
@author Pranay Thatikonda 
*/
public class useableEntity 
{
	//Coordinates 
	private int x;
	private int y;
	//Access the GamePanel 
	private GamePanel gp;
	//Images for the bomb phases 
	private BufferedImage bPhase1, bPhase2, bPhase3;
	//Sprite counter for the update method
	private int spriteCounter = 0;
	//Sprite num to change which image will be used 
	private int spriteNum = 0;
	//Boolean to check if it changes size 
	private boolean spriteJumpOne;
	//Boolean to check if bomb is places 
	private boolean placed;
	//Life span of the bomb 
	private int lifeSpan = 0;
	//speed 
	private int thisSpeed;
	/*
	Superconstructor for the useableEntity that starts the x and y to where the player is 
	@param GamePanel gp passes to access the main GamePanel 
	*/
	public useableEntity(GamePanel gp)
	{
		this.setGp(gp);
		x = gp.getPlayer().getX();
		y = gp.getPlayer().getY();
		thisSpeed = 80;
		spriteJumpOne = false;
		placed = false;
	}

	/*
	This is the update method that changes the bomb according to how much time has passed 
	*/
	public void updateBomb()
	{
		spriteCounter++;
		lifeSpan--;
		if(spriteCounter > 18)
		{
			if(spriteNum == 0)
			{
				spriteNum = 1;
			}
		}
		if(spriteCounter > 36)
		{
			if(spriteNum == 1)
			{
				spriteNum = 2;
			}
		}
	}
	/*
	draws the images for the item, which is mainly used by bomb
	Depends on the direction of the 
	*/
	public void drawItem(Graphics2D g2)
	{
		BufferedImage image = null;
		int width = 0;
		int height = 0;
		if(gp.getPlayer().getItemUse())
		{
			switch(gp.getPlayer().getDirection())
			{
			
			case"up":
				
				if(spriteNum == 0)
				{
					if(!placed)
					{
						y -= thisSpeed;
						placed = true;
					}
					
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					if(!spriteJumpOne)
					{
						x -= 48;
						y -= 48;
						spriteJumpOne = true;
					}
					
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			case "down":
				
				if(spriteNum == 0)
				{
					if(!placed)
					{
						y += thisSpeed;
						placed = true;
					}
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					if(!spriteJumpOne)
					{
						x -= 48;
						y -= 48;
						spriteJumpOne = true;
					}
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			case"left": 
				
				if(spriteNum == 0)
				{
					if(!placed)
					{
						x -= thisSpeed;
						placed = true;
					}
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					if(!spriteJumpOne)
					{
						x -= 48;
						y -= 48;
						spriteJumpOne = true;
					}
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			case"right":
				
				
				if(spriteNum == 0)
				{
					if(!placed)
					{
						x += thisSpeed;
						placed = true;
					}
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					if(!spriteJumpOne)
					{
						x -= 48;
						y -= 48;
						spriteJumpOne = true;
					}
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			}
			
			g2.drawImage(image, x,y, width, height, null);
			
		}
	}
	/*
	image pathing so is works properly with gamePanel
	@param String imagePath to path the image
	@param int width is the width of the image
	@param int height is the height of the image 
	*/
	
	public BufferedImage setup(String imagePath,int width,int height)
	{
		UtilityTool uTool=new UtilityTool();
		BufferedImage image=null;
		try
		{
			image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image=uTool.scaleImage(image,width,height);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return image; //returns the image after it is pathed correctly
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public GamePanel getGp() {
		return gp;
	}
	public void setGp(GamePanel gp) {
		this.gp = gp;
	}





	public BufferedImage getbPhase1() {
		return bPhase1;
	}





	public void setbPhase1(BufferedImage bPhase1) {
		this.bPhase1 = bPhase1;
	}


	public BufferedImage getbPhase3() {
		return bPhase3;
	}


	public void setbPhase3(BufferedImage bPhase3) {
		this.bPhase3 = bPhase3;
	}


	public BufferedImage getbPhase2() {
		return bPhase2;
	}


	public void setbPhase2(BufferedImage bPhase2) {
		this.bPhase2 = bPhase2;
	}


	public int getLifeSpan() {
		return lifeSpan;
	}


	public void setLifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}
}

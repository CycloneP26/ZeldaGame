package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class useableEntity 
{
	private int x;
	private int y;
	private GamePanel gp;
	private BufferedImage bPhase1, bPhase2, bPhase3;
	private int spriteCounter = 0;
	private int spriteNum = 0;
	private int lifeSpan = 0;
	public useableEntity(GamePanel gp)
	{
		this.setGp(gp);
		x = gp.getPlayer().getX();
		y = gp.getPlayer().getY();
	}

	
	public void updateBomb()
	{
		spriteCounter++;
		lifeSpan--;
		if(spriteCounter > 12)
		{
			if(spriteNum == 0)
			{
				spriteNum = 1;
			}
		}
		if(spriteCounter > 25)
		{
			if(spriteNum == 1)
			{
				spriteNum = 2;
			}
		}
	}
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
				y -= 48;
				if(spriteNum == 0)
				{
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					x -= 48;
					y -= 48;
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					x -= 48;
					y -= 48;
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			case "down":
				y +=48;
				if(spriteNum == 0)
				{
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					x -= 48;
					y += 48;
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					x -= 48;
					y += 48;
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			case"left": 
				x -= 48;
				if(spriteNum == 0)
				{
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					x -= 48;
					y -= 48;
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					x -= 48;
					y -= 48;
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			case"right":
				x += 548;
				if(spriteNum == 0)
				{
					image = getbPhase1();
					width = 48;
					height = 48;
				}
				else if(spriteNum == 1)
				{
					x += 48;
					y -= 48;
					image = getbPhase2();
					width = 144;
					height = 144;
				}
				else if (spriteNum ==2)
				{
					x += 48;
					y -= 48;
					image = getbPhase3();
					width = 144;
					height = 144;
				}
				break;
			}
			g2.drawImage(image, x,y, width, height, null);
			
		}
	}
	
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
		return image;
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

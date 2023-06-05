package Main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.Bomb;
import object.ItemEntity;


public class Player extends Entity implements ActionListener 
{
	private GamePanel gp;
	private KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		this.gp=gp;
		this.keyH=keyH;
		solidArea = new Rectangle(8, 16, 32, 28);
		setSolidAreaDefX(solidArea.x);//For item interaction (Default values)
		setSolidAreaDefY(solidArea.y);
		
		setDefaultValue();
		try {
			getPlayerImage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			getPlayerAttackImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			getBItemImage();
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}
	}
	public void setDefaultValue()
	{
		setX(100);
		setY(100);
		setSpeed(4);
		setDirection("down");
	}
	
	public void getPlayerImage() throws IOException
	{
		
		setUp1(setup("/player/linkMovingBack",gp.getTileSize(),gp.getTileSize()));
		setUp2(setup("/player/linkMovingBack1",gp.getTileSize(),gp.getTileSize()));
		setDown1(setup("/player/linkMovingDown",gp.getTileSize(),gp.getTileSize()));
		setDown2(setup("/player/linkMovingDown1",gp.getTileSize(),gp.getTileSize()));
		setLeft1(setup("/player/linkMovingLeft",gp.getTileSize(),gp.getTileSize()));
		setLeft2(setup("/player/linkMovingLeft1",gp.getTileSize(),gp.getTileSize()));
		setRight1(setup("/player/linkMovingRight",gp.getTileSize(),gp.getTileSize()));
		setRight2(setup("/player/linkMovingRight1",gp.getTileSize(),gp.getTileSize()));
	}
	public void getPlayerAttackImage() throws IOException
	{
			setSwordUp(setup("/player/linkSwordUp1",gp.getTileSize(),gp.getTileSize()*2));
			setSwordLeft(setup("/player/linkSwordLeft1",gp.getTileSize()*2,gp.getTileSize()));
			setSwordRight(setup("/player/linkSwordRight1",gp.getTileSize()*2,gp.getTileSize()));
			setSwordDown(setup("/player/linkSwordDown1",gp.getTileSize(),gp.getTileSize()*2));
			setSwordUp1(setup("/player/linkSwordUp",gp.getTileSize(),gp.getTileSize()*2));
			setSwordLeft1(setup("/player/linkSwordLeft",gp.getTileSize()*2,gp.getTileSize()));
			setSwordRight1(setup("/player/linkSwordRight",gp.getTileSize()*2,gp.getTileSize()));
			setSwordDown1(setup("/player/linkSwordDown",gp.getTileSize(),gp.getTileSize()*2));
	}
	public void getBItemImage() throws IOException
	{
		setItemLeft (setup("/player/useItemLeft", gp.getTileSize(), gp.getTileSize()));
		setItemUp  (setup("/player/useItemUp",gp.getTileSize(),gp.getTileSize()));
		setItemRight  (setup("/player/useItemRight",gp.getTileSize(),gp.getTileSize()));
		setItemDown  (setup("/player/useItemDown",gp.getTileSize(),gp.getTileSize()));
	}
	public void attacking()
	{
		spriteCounter++;
		if(spriteCounter>5&&spriteCounter<=25)
		{
			spriteNum=2;
		}
		if(spriteCounter>25)
		{
			spriteNum=2;
			spriteCounter=0;
			attacking=false;
		}
	
	}
	public void item()
	{
		//Makes sure useItem animation turns off
		spriteCounter++;
		if(spriteCounter<=5)
		{
			spriteNum=1;
		}
		if(spriteCounter>5&&spriteCounter<=25)
		{
			spriteNum=2;
		}
		if(spriteCounter>45)
		{
			spriteNum=1;
			spriteCounter=0;
			itemUse =false;
		}
	}
	public void update()
	{
		//movement and attacking
		if(attacking==true)
		{
			attacking();
		}
		else if (itemUse == true)
		{
			item();
		}
		else if(keyH.isUpPressed()==true||keyH.isDownPressed()==true
				||keyH.isLeftPressed()==true
				||keyH.isRightPressed()==true
				||keyH.isSwordPressed()==true
				||keyH.isbItem() == true)//For now, bItem is only bomb, we need to finish UI to implement multiple items
		{
			if(keyH.isSwordPressed()==true)
			{
				setSpeed(0);
				attacking=true;
			}
			if(keyH.isbItem() == true)
			{
				itemUse = true;
			}
			if(keyH.isUpPressed()==true)
			{
				if (keyH.isbItem() == true)
				{
					itemUse = true;
				}
				setSpeed(4);
				setDirection("up");
			}
			else if(keyH.isDownPressed()==true)
			{
				if (keyH.isbItem() == true)
				{
					itemUse = true;
				}
				setSpeed(4);
				setDirection("down");
			}
			else if(keyH.isLeftPressed()==true)
			{
				if (keyH.isbItem() == true)
				{
					itemUse = true;
				}
				setSpeed(4);
				setDirection("left");
			}
			else if(keyH.isRightPressed()==true)
			{
				if (keyH.isbItem() == true)
				{
					itemUse = true;
				}
				setSpeed(4);
				setDirection("right");
			}
			collisionOn = false;
			gp.getCollision().checkTile(this);
			
			//Object Collision as well

			int objIndex = gp.getCollision().checkObject(this, true);
			if(collisionOn == false)
			{
				
				switch(getDirection())
				{
				
				case "up":
					setY(getY()-getSpeed());
					break;
				case "down":
					setY(getY()+getSpeed());
					break;
				case "left":
					setX(getX()-getSpeed());
					break;
				case "right":
					setX(getX()+getSpeed());
					break;
				
				}
				
			}
			
			spriteCounter++;
			if(spriteCounter>12) 
			{
				if(spriteNum==1)
				{
					spriteNum=2;
				}
				else if(spriteNum==2)
				{
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
		
	}
	
		
	public void draw(Graphics2D g2)
	{
		//animation
		BufferedImage image=null;
		switch(getDirection()) {
		case "up":
			if(attacking==false)
			{
				if(itemUse)
				{
					image = getItemUp();
				
				}
				else
				{
					if(spriteNum==1){image=getUp1();}
					if(spriteNum==2){image=getUp2();}
				}	
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=getSwordUp();}
				if(spriteNum==2) {image=getSwordUp1();}
			}
			break;
		case "down":
			if(attacking==false)
			{
				if(itemUse)
				{
					image = getItemDown();
	
				}
				else
				{
				if(spriteNum==1){image=getDown1();}
				if(spriteNum==2){image=getDown2();}
				}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=getSwordDown();}
				if(spriteNum==2) {image=getSwordDown1();}
			}
			break;
		case "left":
			if(attacking==false)
			{
				if(itemUse)
				{
					image = getItemLeft();
	
				}
				else 
				{
				if(spriteNum==1){image=getLeft1();}
				if(spriteNum==2){image=getLeft2();}
				}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=getSwordLeft();}
				if(spriteNum==2) {image=getSwordLeft1();}
			}
			break;
		case "right":
			if(attacking==false)
			{
				if(itemUse)
				{
					image = getItemRight();
				}
				else
				{
				if(spriteNum==1){image=getRight1();}
				if(spriteNum==2){image=getRight2();}
				}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=getSwordRight();}
				if(spriteNum==2) {image=getSwordRight1();}
			}
			break;
		}
		
		if(getDirection()=="left"&&attacking)
		{
			g2.drawImage(image,super.getX()-40,super.getY(),null);
			
		}
		else if(getDirection()=="up"&&attacking)
		{
			g2.drawImage(image,super.getX(),super.getY()-40,null);
		}
		else
		{
			g2.drawImage(image,super.getX(),super.getY(),null);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

package Main;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.DrbgParameters;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Player extends Entity implements ActionListener 
{
	
	private KeyHandler keyH;
	private RoomManager rooms;
	private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	private BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1,swordDown,swordDown1;
	private final int screenX;
	private final int screenY;
	private GamePanel gp;
	private int rupees = 0;
	private int keys = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH, RoomManager rooms)
	{
		super(gp);
		this.gp = gp;
		this.keyH=keyH;
		this.rooms = rooms;
		this.screenX = 0;
		this.screenY = 0;
		
		setSolidArea(new Rectangle(8, 16, 32, 28));
		setSolidAreaDefX(getSolidArea().x);
		setSolidAreaDefY(getSolidArea().y);
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
		setX(384);
		setY(288);
		setSpeed(4);
		setDirection("down");
	}
	
	public void getPlayerImage() throws IOException
	{
		
		setUp1(setup("/player/linkMovingBack",getTileSize(),getTileSize()));
		setUp2(setup("/player/linkMovingBack1",getTileSize(),getTileSize()));
		setDown1(setup("/player/linkMovingDown",getTileSize(),getTileSize()));
		setDown2(setup("/player/linkMovingDown1",getTileSize(),getTileSize()));
		setLeft1(setup("/player/linkMovingLeft",getTileSize(),getTileSize()));
		setLeft2(setup("/player/linkMovingLeft1",getTileSize(),getTileSize()));
		setRight1(setup("/player/linkMovingRight",getTileSize(),getTileSize()));
		setRight2(setup("/player/linkMovingRight1",getTileSize(),getTileSize()));
	}
	public void getPlayerAttackImage() throws IOException
	{
		setSwordUp(setup("/player/linkSwordUp1",getTileSize(),getTileSize()*2));
		setSwordLeft(setup("/player/linkSwordLeft1",getTileSize()*2,getTileSize()));
		setSwordRight(setup("/player/linkSwordRight1",getTileSize()*2,getTileSize()));
		setSwordDown(setup("/player/linkSwordDown1",getTileSize(),getTileSize()*2));
		setSwordUp1(setup("/player/linkSwordUp",getTileSize(),getTileSize()*2));
		setSwordLeft1(setup("/player/linkSwordLeft",getTileSize()*2,getTileSize()));
		setSwordRight1(setup("/player/linkSwordRight",getTileSize()*2,getTileSize()));
		setSwordDown1(setup("/player/linkSwordDown",getTileSize(),getTileSize()*2));
	}
	public void getBItemImage() throws IOException
	{
		//if(something about which item is being used)
		setItemLeft (setup("/player/useItemLeft", getTileSize(), getTileSize()));
		setItemUp  (setup("/player/useItemUp",getTileSize(),getTileSize()));
		setItemRight  (setup("/player/useItemRight",getTileSize(),getTileSize()));
		setItemDown  (setup("/player/useItemDown",getTileSize(),getTileSize()));
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
			if(keyH.isbItem() == true)
			{
				itemUse = true;
			}
			if(keyH.isSwordPressed()==true)
			{
				setSpeed(0);
				attacking=true;
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
			
			
			setCollisionOn(false);
			getGp().getCollision().checkTile(this);
			int index = getGp().getCollision().checkObject(this, true);
			pickUpObj(index);
			
			if(isCollisionOn() == false)
			{
					
					switch(getDirection())
					{
					
					case "up":
						
						if(!rooms.isRoomAvailable(rooms.getRoomRow() - 1, rooms.getRoomColumn()))
						{
							if(getY() > 0)
							{
								setY(getY()-getSpeed());
							}
						}
						else
						{
							
							setY(getY()-getSpeed());
							
						}
						break;
					case "down":
						
						if(!rooms.isRoomAvailable(rooms.getRoomRow() + 1, rooms.getRoomColumn()))
						{
							if(getY() < getGp().screenHeight - 50)
							{
								setY(getY()+getSpeed());
							}
						}
						else
						{
							
							setY(getY()+getSpeed());
							
						}
						
						break;
					case "left":
						
						if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() - 1))
						{
							if(getX() > 0)
							{
								setX(getX()-getSpeed());
							}
						}
						else
						{
							
							setX(getX()-getSpeed());
							
						}
						
						break;
					case "right":
						if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() + 1))
						{
							if(getX() < getGp().screenWidth - 45)
							{
								setX(getX()+getSpeed());
							}
						}
						else
						{
							
							setX(getX()+getSpeed());
							
						}
						
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
	
	public void pickUpObj(int i)
	{
		if(i != -1)
		{
			String objName = gp.getItems().get(i).getName();
			switch(objName)
			{
			case "key":
				keys++;
				gp.getItems().set(i, null);
				gp.playEffect(3);
				break;
			case "rupee":
				gp.getItems().set(i, null);
				gp.playEffect(2);
				rupees++;
				break;
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
	public int getScreenX() 
	{
		return screenX;
	}
	public int getScreenY()
	{
		return screenY;
	}
	
	

}

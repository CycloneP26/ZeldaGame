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
	
	private KeyHandler keyH; //KeyHandler which scans the keystrokes
	private RoomManager rooms; //Be able to access the room that the player is in 
	private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //Images for Link's movement 
	private BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1,swordDown,swordDown1; //Images for link's sword movement 
	private final int screenX; //SOMEONE COMMENT THIS 
	private final int screenY; //AND THIS 
	private GamePanel gp; //Access the GamePanel 
	private int rupees = 0; //amount of rupees
	private int keys = 0; //amount of keys 
	
	//Constructor initializes all the fields that deal with position, and paths the images 
	public Player(GamePanel gp, KeyHandler keyH, RoomManager rooms) //@param takes a GamePanel, keyhandler, and RoomManagager to access the main panel, the keystrokes, and the map layouts
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
	//Sets the positions to default
	public void setDefaultValue()
	{		
		setX(384);
		setY(288);
		setSpeed(4);
		setDirection("down");
	}
	
	//Sets up and paths all the images for the player 
	public void getPlayerImage() throws IOException //@throws IO Exception to properly use the images
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
	//Sets up and paths all the images for when the player attacks
	public void getPlayerAttackImage() throws IOException //@throws IO Exception to properly use the images
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
	//Sets up and paths all images of the player using items 
	public void getBItemImage() throws IOException //@throws IO Exception to properly use the images
	{
		//if(something about which item is being used)
		setItemLeft (setup("/player/useItemLeft", getTileSize(), getTileSize()));
		setItemUp  (setup("/player/useItemUp",getTileSize(),getTileSize()));
		setItemRight  (setup("/player/useItemRight",getTileSize(),getTileSize()));
		setItemDown  (setup("/player/useItemDown",getTileSize(),getTileSize()));
	}
	//Works with the gameThread and updates a counter which animates the player according to the amount of ticks that are passed 
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
	//Works with gameThread and updates the counter which changes the sprite according to the ticks that are passed
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
	//Updates the player movements and checks the collisions 
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
			int index2 = getGp().getCollision().checkFight(this, true);
			pickUpObj(index);
			
			if(isCollisionOn() == false)
			{
					
					switch(getDirection())
					{
					
					case "up":
						
//						if(!rooms.isRoomAvailable(rooms.getRoomRow() - 1, rooms.getRoomColumn()))
//						{
							if(getY() > 0)
							{
								setY(getY()-getSpeed());
							}
						//}
						else
						{
							
							setY(getY()-getSpeed());
							
						}
						break;
					case "down":
						
//						if(!rooms.isRoomAvailable(rooms.getRoomRow() + 1, rooms.getRoomColumn()))
//						{
							if(getY() < getGp().screenHeight - 50)
							{
								setY(getY()+getSpeed());
							}
//						}
						else
						{
							
							setY(getY()+getSpeed());
							
						}
						
						break;
					case "left":
						
//						if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() - 1))
//						{
							if(getX() > 0)
							{
								setX(getX()-getSpeed());
							}
						//}
						else
						{
							
							setX(getX()-getSpeed());
							
						}
						
						break;
					case "right":
//						if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() + 1))
//						{
							if(getX() < getGp().screenWidth - 45)
							{
								setX(getX()+getSpeed());
							}
//						}
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
	
	//Plays a sound when an item is picked up, and removes it from the screen
	public void pickUpObj(int i) //@param takes the index of the items array that is picked up 
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
	//Draws the image according to what actions are being performed 
	public void draw(Graphics2D g2) //@param Graphics2D required to draw the images 
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
	
	public boolean getAttacking()
	{
		return attacking;
	}

}

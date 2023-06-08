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
/*
The player class is used to create the Link entity that the player uses.This is done through using things 
like the ActionListener to observe keystrokes that allow methods to choose the sprite/action that should 
be drawn. The player's position and collision is also used in this class.
@author Sachin Chhaya
@author David Kostanyan 

*/

public class Player extends Entity implements ActionListener 
{
	//KeyHandler which scans the keystrokes
	private KeyHandler keyH; 
	//Be able to access the room that the player is in 
	private RoomManager rooms; 
	//Images for Link's movement 
	private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; 
	//Images for link's sword movement 
	private BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1,swordDown,swordDown1; 
	private final int screenX; //SOMEONE COMMENT THIS 
	private final int screenY; //AND THIS 
	//Access the GamePanel 
	private GamePanel gp; 
	//amount of rupees
	private int rupees = 0; 
	//amount of keys 
	private int keys = 0; 
	private int attackCounter = 0;
	private int knockCounter = 0;
	
	private boolean gotHeartC = false;
	private boolean firstSword = false;
	private boolean hasSword = false;
	private boolean gotItem = false;
	
	/*Constructor initializes all the fields that deal with position, and paths the images 
	@param GamePanel to access and update the panel
	@param KeyHandler to use the ActionListener and observe keystrokes
	@param RoomManager to find collision and position of the player 
	*/
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
	//Sets the positions to default
	public void setDefaultValue()
	{		
		setX(250);
		setY(288);
		setSpeed(4);
		setDirection("down");
	}
	
	/*Sets up and paths all the images for the player 
	@throws IO Exception to properly utilize the images without errors 
	*/
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
		setNewItem(setup("/player/newItem", getTileSize(), getTileSize()));
		setaSword(setup("/objects/StartSword", getTileSize(), getTileSize()));
		setHeartC(setup("/objects/New_Piskel", getTileSize(), getTileSize()));
	}
	/*Sets up and paths all the images for the player attack images  
	@throws IO Exception to properly utilize the images without errors 
	*/
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
	/*Sets up and paths all the images for the player using item images  
	@throws IO Exception to properly utilize the images without errors 
	*/
	public void getBItemImage() throws IOException
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
		attackCounter++;
		if(attackCounter>5&&attackCounter<=25)
		{
			spriteNum=2;
		}
		if(attackCounter == 5)
		{
			
			Sword sword = new Sword(gp, getDirection(), this);
			
		}
		if(attackCounter == 15)
		{
			
			for(int i = 0; i < gp.getRooms().getCurrentRoom().getMobs().size(); i++)
			{
				
				gp.getRooms().getCurrentRoom().getMobs().get(i).setKnocked(false);
				
			}
			
		}
		if(attackCounter>25)
		{
			spriteNum=2;
			attackCounter=0;
			attacking=false;
		}
	
	}
	
	public void knocked()
	{
		
		setKnockCounter(getKnockCounter() + 1);
		
		setSpeed(10);
		getGp().getCollision().checkKnockTile(this);
		
		
		if(isCollisionOn() == false)
		{
				
				switch(getKnockedDir())
				{
				
				case "up":
					
//					if(!rooms.isRoomAvailable(rooms.getRoomRow() - 1, rooms.getRoomColumn()))
//					{
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
					
//					if(!rooms.isRoomAvailable(rooms.getRoomRow() + 1, rooms.getRoomColumn()))
//					{
						if(getY() < getGp().screenHeight - 50)
						{
							setY(getY()+getSpeed());
						}
//					}
					else
					{
						
						setY(getY()+getSpeed());
						
					}
					
					break;
				case "left":
					if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() - 1))
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
			
			if(knockCounter>7)
			{
				setKnocked(false);
				knockCounter = 0;
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
			setItemUse(false);
		}
	}
	
	public void gotItem()
	{
		spriteCounter++;

		if(spriteCounter > 60)
		{
			gotItem = false;
			gotHeartC = false;
			firstSword = false;
		}
	}
	
	public void update()
	{
		
		{
			
			
			gp.getCollision().checkFight(this);
			
			//movement and attacking
			if(getKnocked())
			{
				
				knocked();
				
			}
			else
			{
				
				if(gotItem)
				{
					gotItem();
				}
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
					||keyH.isbItem() == true)
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
						setSpeed(3);
						setDirection("up");
					}
					else if(keyH.isDownPressed()==true)
					{
						if (keyH.isbItem() == true)
						{
							itemUse = true;
						}
						setSpeed(3);
						setDirection("down");
					}
					else if(keyH.isLeftPressed()==true)
					{
						if (keyH.isbItem() == true)
						{
							itemUse = true;
						}
						setSpeed(3);
						setDirection("left");
					}
					else if(keyH.isRightPressed()==true)
					{
						if (keyH.isbItem() == true)
						{
							itemUse = true;
						}
						setSpeed(3);
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
								
//								if(!rooms.isRoomAvailable(rooms.getRoomRow() - 1, rooms.getRoomColumn()))
//								{
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
								
//								if(!rooms.isRoomAvailable(rooms.getRoomRow() + 1, rooms.getRoomColumn()))
//								{
									if(getY() < getGp().screenHeight - 50)
									{
										setY(getY()+getSpeed());
									}
//								}
								else
								{
									
									setY(getY()+getSpeed());
									
								}
								
								break;
							case "left":
								
//								if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() - 1))
//								{
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
//								if(!rooms.isRoomAvailable(rooms.getRoomRow(), rooms.getRoomColumn() + 1))
//								{
									if(getX() < getGp().screenWidth - 45)
									{
										setX(getX()+getSpeed());
									}
//								}
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
		}
	}
	
	
	
	/*Plays a sound when an item is picked up, and removes it from the screen
	@param takes the index of the items array that is picked up 
	*/
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
				Main.updateKeysCount(keys); // Update keys count in the HUD
				break;
			case "rupee":
				gp.getItems().set(i, null);
				gp.playEffect(2);
				rupees++;
				Main.updateRupeesCount(rupees); // Update rupees count in the HUD
				break;
			case "startSword":
				gp.getItems().set(i, null);
				gp.playEffect(4);
				gotItem = true;
				setHasSword(true);
				keyH.setLeftPressed(false);
				keyH.setRightPressed(false);
				keyH.setDownPressed(false);
				keyH.setUpPressed(false);
				firstSword = true;
				break;
			case "heart":
				gp.getItems().set(i, null);
				gp.playEffect(4);
				keyH.setLeftPressed(false);
				keyH.setRightPressed(false);
				keyH.setDownPressed(false);
				keyH.setUpPressed(false);
				gotItem = true;
				gotHeartC = true;
				break;
			}
		}
	}
	/*Draws the image according to what actions are being performed 
	@param Graphics2D required to draw the images 
	*/
	public void draw(Graphics2D g2) 
	{
		//animation
		BufferedImage image=null;
		if(gotItem)
		{
			image = getNewItem();
			if(firstSword)
			{
				g2.drawImage(getaSword(), super.getX() , super.getY() - 48, null);
			}
			else if (gotHeartC)
			{
				g2.drawImage(getHeartC(), super.getX() + 2, super.getY() -48, null);
			}
		}
		else
		{
		
		
		switch(getDirection()) {
		case "up":
			if(attacking==false)
			{
				if(getItemUse())
				{
					
					image = getItemUp();
					if(gp.getRooms().getCurrentRoom().getBombs().size()==0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().add(new Bomb(gp));
						System.out.println(gp.getRooms().getCurrentRoom().isSecret());
						if(gp.getRooms().getCurrentRoom().isSecret())
						{
							System.out.println("secret :O");
							gp.getRooms().getCurrentRoom().setSecretFound(true);
						}
					}
					
				}
				else
				{
					
					if(gp.getRooms().getCurrentRoom().getBombs().size()>0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().remove(0);
					}
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
					if(gp.getRooms().getCurrentRoom().getBombs().size()==0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().add(new Bomb(gp));
					}

				}
				else
				{
					if(gp.getRooms().getCurrentRoom().getBombs().size()>0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().remove(0);
					}
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
					if(gp.getRooms().getCurrentRoom().getBombs().size()==0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().add(new Bomb(gp));
					}

				}
				else 
				{
					if(gp.getRooms().getCurrentRoom().getBombs().size()>0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().remove(0);
					}
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
					System.out.println("hi1sf");
					image = getItemRight();
					if(gp.getRooms().getCurrentRoom().getBombs().size()==0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().add(new Bomb(gp));
					}
				}
				else
				{
					if(gp.getRooms().getCurrentRoom().getBombs().size()>0) 
					{
						gp.getRooms().getCurrentRoom().getBombs().remove(0);
					}
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
	/*
	This method takes the image file path and the image's height and width to scale and intialize the image so it can be used as a Bufferedimage
	@param String imagePath is used to determine the file location of the image
	@param int width is used to determine the width of the BufferedImage
	@param int height is used to determine the height of the BufferedImage 
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
	public int getKnockCounter() {
		return knockCounter;
	}
	public void setKnockCounter(int knockCounter) {
		this.knockCounter = knockCounter;
	}
	public int getAttackCounter() {
		return attackCounter;
	}
	public void setAttackCounter(int attackCounter) {
		this.attackCounter = attackCounter;
	}
	public boolean isGotHeartC() {
		return gotHeartC;
	}
	public void setGotHeartC(boolean gotHeartC) {
		this.gotHeartC = gotHeartC;
	}
	public boolean isFirstSword() {
		return firstSword;
	}
	public void setFirstSword(boolean firstSword) {
		this.firstSword = firstSword;
	}
	public boolean isHasSword() {
		return hasSword;
	}
	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}
	public boolean isGotItem() {
		return gotItem;
	}
	public void setGotItem(boolean gotItem) {
		this.gotItem = gotItem;
	}

}

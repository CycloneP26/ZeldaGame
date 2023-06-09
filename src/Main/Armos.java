package Main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
/*
This class creates an enemy called Armos that attacks the enemy, and they emerge from a dormant stature
@author David Kostanyan 
*/
public class Armos extends Entity
{
	/*
	Creates an Armos with a set location and access to the main GamePanel
	@param GamePanel gp, access the main gamePanel 
	@param int x,y position of the enemy
	*/
	public Armos(GamePanel gp,int x,int y) 
	{
		super(gp);
		// TODO Auto-generated constructor stub
		setX(250);
		setY(250);
		setDirection("right");
		setSpeed(1);
		setHealth(5);
		setSolidArea(new Rectangle(0,0,42,42));
		getImage();
	}
	//Sets up and paths images for the Armos 
	public void getImage()
	{
		setArmosFrozen(setup("/mobs/ArmosFrozen",getTileSize(),getTileSize()));
		setArmosDown(setup("/mobs/ArmosDown",getTileSize(),getTileSize()));
		setArmosDown1(setup("/mobs/ArmosDown1",getTileSize(),getTileSize()));
		setArmosUp(setup("/mobs/ArmosUp",getTileSize(),getTileSize()));
		setArmosUp1(setup("/mobs/ArmosUp1",getTileSize(),getTileSize()));
	}
	//Creates the movement patterns for the Armos and randomizes their direction
	public void setAction()
	{
		setActionLockCounter(getActionLockCounter()+1);
		if(getActionLockCounter()==125)
		{
			Random random=new Random();
			int i= random.nextInt(200)+1;
			if(i<=25)
			{
				setDirection("up");
			}
			if(i>25&&i<=50)
			{
				setDirection("down");

			}
			if(i>50&&i<=75)
			{
				setDirection("left");

			}
			if(i>75&&i<=100)
			{
				setDirection("right");


			}
			setActionLockCounter(0);
		}
	}
	//Is it colliding with player
	boolean colliding=false;
	//Updates the movement and position of the armos 
	public void update()
	{
		setAction();
		setCollisionOn(false);
		getGp().getCollision().checkTile(this);
		if(isCollisionOn() == false)
		{
			switch(getDirection())
			{
			case "down":
				setSpeed(1);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500&&getHealth()<5)
				{
					if(getY()+getSpeed()<500)
					{
						setY(getY()+getSpeed());
					}
					else
					{
						setAction();
					}
				}

				break;
			case "left":
				setSpeed(1);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500&&getHealth()<5)
				{
					if(getX()-getSpeed()>0)
					{
						setX(getX()-getSpeed());
					}
					else
					{
						setAction();
					}
				}
				break;
			case "right":

				setSpeed(1);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500&&getHealth()<5)
				{
					if(getX()+getSpeed()<700)
					{
						setX(getX()+getSpeed());
					}
					else
					{
						setAction();
					}
				}

				break;
			case "up":
				setSpeed(1);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500&&getHealth()<5)
				{
					if(getY()-getSpeed()>0)
					{
						setY(getY()-getSpeed());
					}
					else
					{
						setAction();
					}
				}

				break;
			}
		}
		spriteCounter++;
		if(spriteCounter>12)
		{
			if(spriteNum==1&&getHealth()<5)
			{
				spriteNum=2;
			}
			else if(spriteNum==2)
			{
				spriteNum=3;
			}
			else if(spriteNum==3)
			{
				spriteNum=2;
			}
			spriteCounter=0;
		}


	}
	/*
	Draws the armo according to what the spriteNum is in the update method
	@param Graphics2D g2 is required to draw 
	*/
	public void draw(Graphics2D g2)
		{
			BufferedImage image=null;
			switch(getDirection()) {
			case "up":
					if(spriteNum==1){image=getArmosFrozen();}
					if(spriteNum==2){image=getArmosUp();}
					if(spriteNum==3){image=getArmosUp1();}

				break;
			case "down":
				if(spriteNum==1){image=getArmosFrozen();}
				if(spriteNum==2){image=getArmosDown();}
				if(spriteNum==3){image=getArmosDown1();}



				break;
			case "left":
				if(spriteNum==1){image=getArmosFrozen();}
				if(spriteNum==2){image=getArmosDown();}
				if(spriteNum==3){image=getArmosDown1();}


				break;
			case "right":
				if(spriteNum==1){image=getArmosFrozen();}
				if(spriteNum==2){image=getArmosDown();}
				if(spriteNum==3){image=getArmosDown1();}

				break;
			}
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}


}

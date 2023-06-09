package Main;





import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
Moblin is an enemy entity that can damage and be damaged by the player. Has its own movement.
@author David Kostanyan
*/

public class BlueMoblin extends Entity
{
	//Creates a Moblin object
	public BlueMoblin(GamePanel gp)
	{
		super(gp);
		setX(250);
		setY(250);
		setDirection("down");
		setSpeed(1);
		getImage();
		setSolidArea(new Rectangle(5,5,42,42));
	}
	//Alternate Moblin object creation
	public BlueMoblin(GamePanel gp, int x, int y)
	{
		super(gp);
		setX(x);
		setY(y);
		setDirection("down");
		setSpeed(1);
		setSolidArea(new Rectangle(5,5,42,42));
		setHealth(3);
		getImage();
	}
	//overrides toString() and calls for a return
	public String toString()
	{
		return "BlueMoblin"; //returns the name of the entity
	}
	//gets the images for the entity movement
	public void getImage()
	{
		setBlueHoglinUp(setup("/mobs/BlueHoglinUp",getTileSize(),getTileSize()));
		setBlueHoglinUp1(setup("/mobs/BlueHoglinUp1",getTileSize(),getTileSize()));
		setBlueHoglinDown(setup("/mobs/BlueHoglinDown",getTileSize(),getTileSize()));
		setBlueHoglinDown1(setup("/mobs/BlueHoglinDown1",getTileSize(),getTileSize()));
		setBlueHoglinLeft(setup("/mobs/BlueHoglinLeft",getTileSize(),getTileSize()));
		setBlueHoglinLeft1(setup("/mobs/BlueHoglinLeft1",getTileSize(),getTileSize()));
		setBlueHoglinRight(setup("/mobs/BlueHoglinRight",getTileSize(),getTileSize()));
		setBlueHoglinRight1(setup("/mobs/BlueHoglinRight1",getTileSize(),getTileSize()));
	}
	//uses a random number generator to determine the movement of the entity
	public void setAction()
	{
		setActionLockCounter(getActionLockCounter()+1);
		if(getActionLockCounter()==120)
		{
			Random random=new Random();
			int i= random.nextInt(100)+1;
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
	//updates the position of the entity based on the direction chosen in setAction()
	public void update() //Updates the positions and animates entity 
	{
		setAction();
		setCollisionOn(false);
		getGp().getCollision().checkTile(this);
		if(isCollisionOn() == false)
		{
			switch(getDirection())
			{
			case "up":
				if(getX()>0&&getY()>0&&getX()<700&&getY()<500)
				{
					if(getY()-getSpeed()>0)
					{

						setY(getY()-2*getSpeed());
					}
					else
					{
						setAction();
					}
				}
			case "down":
				if(getX()>0&&getY()>0&&getX()<700&&getY()<500)
				{
					if(getY()+getSpeed()<400)
					{
						System.out.println(getX());
						setY(getY()+getSpeed());
					}
					else
					{

						setAction();
					}
				}

				break;
			case "left":
				if(getX()>0&&getY()>0&&getX()<700&&getY()<500)
				{
					if(getX()-getSpeed()>0)
					{
						System.out.println(getX());
						setX(getX()-2*getSpeed());
					}
					else
					{
						setAction();
					}
				}

				break;
			case "right":
				if(getX()>0&&getY()>0&&getX()<700&&getY()<500)
				{
					if(getX()+getSpeed()<700)
					{
						System.out.println(getX());
						setX(getX()+getSpeed());
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
	//renders the entity on the screen
	public void draw(Graphics2D g2) //@param Graphics2D is taken to draw the images
	{
		BufferedImage image=null;

		int screenX=100;//worldX-getGp().getPlayer().getX()+getGp().getPlayer().getScreenX();
		int screenY=100;//worldY-getGp().getPlayer().getY()+getGp().getPlayer().getScreenY();
		//if(worldX+getGp().getTileSize()>getGp().getPlayer().getX()-getGp().getPlayer().getScreenX() &&
		  // worldX-getGp().getTileSize()<getGp().getPlayer().getX()+getGp().getPlayer().getScreenX()&&
		  // worldY+getGp().getTileSize()>getGp().getPlayer().getY()-getGp().getPlayer().getScreenY()&&
		  // worldY-getGp().getTileSize()<getGp().getPlayer().getY()+getGp().getPlayer().getScreenY())
		{
			switch(getDirection()) {
			case "up":
				if(spriteNum==1){image=getBlueHoglinUp();}
				if(spriteNum==2){image=getBlueHoglinUp1();}
				break;
			case "down":
				if(spriteNum==1){image=getBlueHoglinDown();}
				if(spriteNum==2){image=getBlueHoglinDown1();}
				break;
			case "left":
				if(spriteNum==1){image=getBlueHoglinLeft();}
				if(spriteNum==2){image=getBlueHoglinLeft1();}
				break;
			case "right":
				if(spriteNum==1){image=getBlueHoglinRight();}
				if(spriteNum==2){image=getBlueHoglinRight1();}
				break;
			}
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}
	}
}

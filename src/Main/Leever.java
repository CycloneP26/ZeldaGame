package Main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
/*
This class creates the enemy Leever that extends Entity and moves side to side
It has its own update and draw methods because of its distinct animation 
@author David Kostanyan 
*/
public class Leever extends Entity {

	//Basic Constructor (Most likely will not be used)
	public Leever(GamePanel gp) {
		super(gp);
		setX(250);
		setY(250);
		setDirection("right");
		setSpeed(1);

		getImage();
	}
	/*Constructor that is used to set its exact location
	@param GamePanel gp to pass the main GamePanel 
	@param int x and y which is its exact positions 
	*/
	public Leever(GamePanel gp, int x, int y)
	{
		super(gp);
		setX(x);
		setY(y);
		setDirection("left");
		setSpeed(1);
		setHealth(101001);
		getImage();
	}
	//Paths and initializes the images that this monster uses 
	public void getImage()
	{
		setLeeverSand(setup("/mobs/LeeverSand",getTileSize(),getTileSize()));
		setLeeverEmerge(setup("/mobs/LeeverEmerge",getTileSize(),getTileSize()));
		setLeeverEmerge2(setup("/mobs/LeeverEmerge",getTileSize(),getTileSize()));
		setLeever1(setup("/mobs/Leever1",getTileSize(),getTileSize()));
		setLeever2(setup("/mobs/Leever2",getTileSize(),getTileSize()));
	}
	//Name of enemy 
	public String toString()
	{
		return "Leever";
	}
	//Creates a random direction between left or right 
	public void setAction()
	{
		setActionLockCounter(getActionLockCounter()+1);
		if(getActionLockCounter()==120)
		{
			Random random=new Random();
			int i= random.nextInt(100)+1;
			if(i<=50)
			{
				setDirection("left");
			}
			if(i>50)
			{
				setDirection("right");
			}
			setActionLockCounter(0);
		}
	}
	//Updates the direction and movement everytime it is called by the GameThread in GamePanel 
	public void update()
	{
		setAction();
		setCollisionOn(false);
		getGp().getCollision().checkTile(this);
		if(isCollisionOn() == false)
		{
			switch(getDirection())
			{
			case "left":
				if(getX()>0&&getY()>0&&getX()<700&&getY()<700)
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
				if(getX()>0&&getY()>0&&getX()<700&&getY()<700)
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

			}


		}
		spriteCounter++;
		if(spriteCounter>14)
		{
			if(spriteNum==1)
			{
				spriteNum=2;
			}
			else if(spriteNum==2)
			{

				spriteNum=3;
			}
			else if(spriteNum==3)
			{

				spriteNum=4;
			}
			else if(spriteNum==4)
			{
				spriteNum=5;
			}
			else if(spriteNum==5)
			{
				spriteNum=4;
			}
			spriteCounter=6;
		}
	}
	/*
	Draws the monster with alternating images to annimate
	@param Graphics2D used to draw everything 
	*/
	public void draw(Graphics2D g2) 
	{
		BufferedImage image=null;

		//getY()-getGp().getPlayer().getY()+getGp().getPlayer().getScreenY();
		//if(getX()+getGp().getTileSize()>getGp().getPlayer().getX()-getGp().getPlayer().getScreenX() &&
		  // getX()-getGp().getTileSize()<getGp().getPlayer().getX()+getGp().getPlayer().getScreenX()&&
		  // getY()+getGp().getTileSize()>getGp().getPlayer().getY()-getGp().getPlayer().getScreenY()&&
		  // getY()-getGp().getTileSize()<getGp().getPlayer().getY()+getGp().getPlayer().getScreenY())
		{
			switch(getDirection()) {
			case "left":
				if(spriteNum==1){image=getLeeverSand();}
				if(spriteNum==2){image=getLeeverEmerge();}
				if(spriteNum==3){image=getLeeverEmerge2();}
				if(spriteNum==4){image=getLeever1();}
				if(spriteNum==5){image=getLeever2();}
				break;
			case "right":
				if(spriteNum==1){image=getLeeverSand();}
				if(spriteNum==2){image=getLeeverEmerge();}
				if(spriteNum==3){image=getLeeverEmerge2();}
				if(spriteNum==4){image=getLeever1();}
				if(spriteNum==5){image=getLeever2();}
				break;
			}
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}
	}
}

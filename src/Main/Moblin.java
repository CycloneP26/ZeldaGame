package Main;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Moblin extends Entity
{
	public Moblin(GamePanel gp)
	{
		super(gp);
		setX(250);
		setY(250);
		setDirection("down");
		setSpeed(1);
		getBruhImage();
		setSolidArea(new Rectangle(5,5,38,38));
	}

	public Moblin(GamePanel gp, int x, int y)
	{
		super(gp);
		setX(x);
		setY(y);
		setDirection("down");
		setSpeed(1);
		getBruhImage();
		setSolidArea(new Rectangle(5,5,42,42));
		setHealth(3);
	}
	public String toString()
	{
		return "Octorok";
	}
	public void getBruhImage()
	{
		setHoglinUp(setup("/mobs/HoglinUp",getTileSize(),getTileSize()));
		setHoglinUp1(setup("/mobs/HoglinUp1",getTileSize(),getTileSize()));
		setHoglinDown(setup("/mobs/HoglinDown",getTileSize(),getTileSize()));
		setHoglinDown1(setup("/mobs/HoglinDown1",getTileSize(),getTileSize()));
		setHoglinLeft(setup("/mobs/HoglinLeft",getTileSize(),getTileSize()));
		setHoglinLeft1(setup("/mobs/HoglinLeft1",getTileSize(),getTileSize()));
		setHoglinRight(setup("/mobs/HoglinRight",getTileSize(),getTileSize()));
		setHoglinRight1(setup("/mobs/HoglinRight1",getTileSize(),getTileSize()));
	}
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
				if(spriteNum==1){image=getHoglinUp();}
				if(spriteNum==2){image=getHoglinUp1();}
				break;
			case "down":
				if(spriteNum==1){image=getHoglinDown();}
				if(spriteNum==2){image=getHoglinDown1();}
				break;
			case "left":
				if(spriteNum==1){image=getHoglinLeft();}
				if(spriteNum==2){image=getHoglinLeft1();}
				break;
			case "right":
				if(spriteNum==1){image=getHoglinRight();}
				if(spriteNum==2){image=getHoglinRight1();}
				break;
			}
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}
	}
}

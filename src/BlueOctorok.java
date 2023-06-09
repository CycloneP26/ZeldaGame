package Main;

import java.awt.Rectangle;
import java.util.Random;

public class BlueOctorok extends Entity
{

	public BlueOctorok(GamePanel gp)
	{
		super(gp);
		setX(250);
		setY(250);
		setDirection("down");
		setSpeed(1);
		setSolidArea(new Rectangle(5,5,38,38));
		
		getBruhImage();
	}
	
	public BlueOctorok(GamePanel gp, int x, int y)
	{
		super(gp);
		setX(x);
		setY(y);
		setDirection("down");
		setSpeed(1);
		getBruhImage();
		setSolidArea(new Rectangle(5,5,38,38));
		setHealth(3);
		
	}
	public String toString()
	{
		return "Octorok";
	}
	public void getBruhImage()
	{
		setUp1(setup("/mobs/BlueOctorokUp",getTileSize(),getTileSize()));
		setUp2(setup("/mobs/BlueOctorokUp1",getTileSize(),getTileSize()));
		setDown1(setup("/mobs/BlueOctorokDown",getTileSize(),getTileSize()));
		setDown2(setup("/mobs/BlueOctorokDown1",getTileSize(),getTileSize()));
		setLeft1(setup("/mobs/BlueOctorokLeft",getTileSize(),getTileSize()));
		setLeft2(setup("/mobs/BlueOctorokLeft1",getTileSize(),getTileSize()));
		setRight1(setup("/mobs/BlueOctorokRight",getTileSize(),getTileSize()));
		setRight2(setup("/mobs/BlueOctorokRight1",getTileSize(),getTileSize()));
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
						System.out.println("hehehehe");
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
						setX(getX()-getSpeed());
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
		int i=new Random().nextInt(100)+1;
		if(i>99)
		{
			
		}
	}
}



package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class OctorokProjectile extends Projectile
{
	private GamePanel gp;
	public OctorokProjectile(GamePanel gp) 
	{
		super(gp);
		this.gp=gp;
		setSpeed(5);
		getImage();
	}
	public void getImage()
	{
		setRock(setup("/projectiles/OctorokProjectile",gp.getTileSize(),gp.getTileSize()));
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
	public void draw(Graphics2D g2)
	{
		BufferedImage image=null;
		switch(getDirection()) {
		case "up":
			if(spriteNum==1){image=getRock();}
			if(spriteNum==2){image=getRock();}
			break;
		case "down":

			if(spriteNum==1){image=getRock();}
			if(spriteNum==2){image=getRock();}


			break;
		case "left":

			if(spriteNum==1){image=getRock();}
			if(spriteNum==2){image=getRock();}

			break;
		case "right":

				if(spriteNum==1){image=getRock();}
				if(spriteNum==2){image=getRock();}

			break;
		}
	}
}

package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Octorok extends Entity
{
	public Octorok(GamePanel gp)
	{
		super(gp);
		setX(250);
		setY(250);
		setDirection("down");
		setSpeed(1);
		getBruhImage();
	}
	
	public Octorok(GamePanel gp, int x, int y)
	{
		super(gp);
		setX(x);
		setY(y);
		setDirection("down");
		setSpeed(1);
		getBruhImage();
	}
	public String toString()
	{
		return "Octorok";
	}
	public void getBruhImage()
	{
		setUp1(setup("/mobs/OctorokUp",getTileSize(),getTileSize()));
		setUp2(setup("/mobs/OctorokUp1",getTileSize(),getTileSize()));
		setDown1(setup("/mobs/OctorokDown",getTileSize(),getTileSize()));
		setDown2(setup("/mobs/OctorokDown1",getTileSize(),getTileSize()));
		setLeft1(setup("/mobs/OctorokLeft",getTileSize(),getTileSize()));
		setLeft2(setup("/mobs/OctorokLeft1",getTileSize(),getTileSize()));
		setRight1(setup("/mobs/OctorokRight",getTileSize(),getTileSize()));
		setRight2(setup("/mobs/OctorokRight1",getTileSize(),getTileSize()));
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
}

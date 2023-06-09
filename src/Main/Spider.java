package Main;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Timer;


public class Spider extends Entity implements ActionListener
{
	
	private Timer timer;
	private boolean jumping=false;
	private int jumpCounter=0;

	public Spider(GamePanel gp)
	{
		super(gp);
		setX(250);
		setY(250);
		setDirection("down");
		setSpeed(1);
		setHealth(5);
		getBruhImage();
		timer=new Timer(25,this);
	}

	public Spider(GamePanel gp, int x, int y)
	{
		super(gp);
		setX(x);
		setY(y);
		setDirection("down");
		jumping=false;
		setSpeed(1);
		setHealth(5);
		getBruhImage();
	}
	public String toString()
	{
		return "Spider";
	}
	public void getBruhImage()
	{
		setSpiderStill(setup("/mobs/SpiderStill",getTileSize(),getTileSize()));
		setSpiderJump(setup("/mobs/SpiderJump",getTileSize(),getTileSize()));
	}
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
				jumping=false;
			}
			if(i>25&&i<=50)
			{
				setDirection("down");
				jumping=false;
			}
			if(i>50&&i<=75)
			{
				setDirection("left");
				jumping=false;
			}
			if(i>75&&i<=100)
			{
				setDirection("right");
				jumping=false;

			}
			if(i>100&&i<=125)
			{
				setDirection("downRight");
				jumping=false;

			}
			if(i>125&&i<=150)
			{
				setDirection("downLeft");
				jumping=false;

			}
			if(i>150&&i<=175)
			{
				setDirection("upRight");

			}
			if(i>175&&i<200)
			{
				setDirection("upLeft");
				jumping=false;

			}
			setActionLockCounter(0);
		}
	}

	boolean colliding=false;
	boolean inBounds=getX()>0&&getX()<768&&getY()>0&&getY()<576;
	public void update()
	{
		setAction();
		setCollisionOn(false);
		getGp().getCollision().checkTile(this);
		if(isCollisionOn() == false)
		{
			switch(getDirection())
			{
			case "downRight":
				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
				{
					if(getX()>0&&getX()+getSpeed()<700&&getY()>0&&getY()+getSpeed()<500)
					{
						setX(getX()+getSpeed());
						setY(getY()+getSpeed());
					}
					else
					{
						setAction();
					}
				}
				else
				{
					jumping=false;
				}


				break;
			case "downLeft":
				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
				{
					if(getX()-getSpeed()>0&&getX()<700&&getY()>0&&getY()+getSpeed()<500)
					{
						setX(getX()-getSpeed());
						setY(getY()+getSpeed());
					}
					else
					{
						setAction();
					}
				}
				else
				{

					jumping=false;

				}
				break;
			case "down":
				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
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
				else
				{
					jumping=false;

				}

				break;
			case "left":
				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
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
				else
				{
					jumping=false;


				}
				break;
			case "upLeft":
				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
				{
					if(getX()-getSpeed()>0&&getY()-getSpeed()>0)
					{
						setX(getX()-getSpeed());
						setY(getY()-getSpeed());
					}
					else
					{
						setAction();
					}
				}
				else
				{
					jumping=false;

				}
				break;
			case "right":
				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
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
				else
				{
					jumping=false;

				}
				break;
			case "upRight":

				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
				{
					if(getX()+getSpeed()<700&&getY()-getSpeed()>0)
					{
						setX(getX()+getSpeed());
						setY(getY()-getSpeed());
					}
					else
					{
						setAction();
					}
				}
				else
				{
					jumping=false;

				}
				break;
			case "up":

				jumping=true;
				setSpeed(3);
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
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
				else
				{
					jumping=false;

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
		if(jumping)
		{
			jumpCounter++;
			if(jumpCounter>50)
			{
				jumping=false;
				jumpCounter=0;
			}
		}

	}

	public void draw(Graphics2D g2)
		{
			BufferedImage image=null;
			switch(getDirection()) {
			case "up":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "down":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "left":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "right":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "upLeft":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "upRight":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "downRight":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			case "downLeft":
				if(jumping==false)
				{
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					if(spriteNum==1) {image=getSpiderJump();}
					if(spriteNum==2) {image=getSpiderJump();}
				}
				break;
			}
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

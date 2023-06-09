package Main;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Timer;

/*The spider class is a jumping entity in the Monster array list. 
 * It has 8 directional movement, the most of any mob in our game.
 * 
 * @author: David Kostanyan
 */
public class Spider extends Entity implements ActionListener
{

	//boolean that tracks whether the spider is jumping
	private boolean jumping=false;
	//int that is used in locking the animation of the spider
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
	//sets the images in the Entity super class to the pictures for each respective spider
	public void getBruhImage()
	{
		setSpiderStill(setup("/mobs/SpiderStill",getTileSize(),getTileSize()));
		setSpiderJump(setup("/mobs/SpiderJump",getTileSize(),getTileSize()));
	}
	//Randomizes the direction of the spider
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
				setDirection2("b");
			}
			if(i>25&&i<=50)
			{
				setDirection("down");
				jumping=false;
				setDirection2("b");
			}
			if(i>50&&i<=75)
			{
				setDirection("left");
				jumping=false;
				setDirection2("b");
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

	
	//updates the location of the spider
	public void update()
	{
		//calls the randomization of the direction of the spider
		setAction();
		//checks if collision is on before updating
		setCollisionOn(false);
		//checks if the things in two tiles next to each other have collided
		getGp().getCollision().checkTile(this);
		if(isCollisionOn() == false)
		{
			switch(getDirection())
			{
			case "downRight":
				jumping=true;
				setSpeed(3);
				//makes sure that the spider is within the boundaries of the screen
				if(getX()>0&&getX()<700&&getY()>0&&getY()<500)
				{
					//checks if the entity isnt about to collide with the borders of the screen
					if(getX()>0&&getX()+getSpeed()<700&&getY()>0&&getY()+getSpeed()<500)
					{
						//makes it move in both directions since its diagonal
						setX(getX()+getSpeed());
						setY(getY()+getSpeed());
					}
					//if it is about to collide, randomly switches direction
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
		//switches sprites for animation
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
		//jump animation counter, if the spider has been jumping for more than 50 ticks it stops, rinse and repeat
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
	//draws the sprite based off of direction
	public void draw(Graphics2D g2)
		{
			BufferedImage image=null;
			switch(getDirection()) {
			case "up":
				if(jumping==false)
				{
					//draws if the entity is standing still
					if(spriteNum==1){image=getSpiderStill();}
					if(spriteNum==2){image=getSpiderJump();}
				}
				if(jumping==true)
				{
					//draws if the spider is jumping 
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
			//draws image
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

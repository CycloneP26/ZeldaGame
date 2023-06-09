package Main;





import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Zora extends Entity
{
	public Zora(GamePanel gp)
	{
		super(gp);
		setX(250);
		setY(250);
		if(gp.getPlayer().getY()>getY())
		{
			setDirection("down");
		}
		else
		{
			setDirection("up");
		}
		setSpeed(1);
		getImage();
		setSolidArea(new Rectangle(5,5,42,42));
	}
	
	public Zora(GamePanel gp, int x, int y)
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
	public String toString()
	{
		return "BlueMoblin";
	}
	public void getImage()
	{
		setZoraSand(setup("/mobs/ZoraSand",getTileSize(),getTileSize()));
		setZoraSandEmerge(setup("/mobs/ZoraSandEmerge",getTileSize(),getTileSize()));
		setZoraUp(setup("/mobs/ZoraUp",getTileSize(),getTileSize()));
		setZoraDown(setup("/mobs/ZoraDown",getTileSize(),getTileSize()));
	}
	public void setAction()
	{
		setActionLockCounter(getActionLockCounter()+1);
		if(getActionLockCounter()==120)
		{
			Random random=new Random();
			int i= random.nextInt(100)+1;
			if(getGp().getPlayer().getY()<getY())
			{
				
				setDirection("up");
				System.out.println(getDirection());
			}
			if(getGp().getPlayer().getY()>getY())
			{
				setDirection("down");
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
				setSpeed(0);
				break;
			case "down":
				setSpeed(0);
				break;
			case "left":
				setSpeed(0);
				break;
			case "right":
				setSpeed(0);

				break;

			}

		}
		spriteCounter++;
		if(spriteCounter>40)
		{
			if(spriteNum==1)
			{
				spriteNum=2;
			}
			else if(spriteNum==2)
			{
				if(getDirection()=="down")
				{
					spriteNum=3;
				}
				else
				{
					spriteNum=4;
				}
			}
			else if(spriteNum==3||spriteNum==4)
			{
				spriteNum=2;
			}
			else
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
				if(spriteNum==1){image=getZoraSand();}
				if(spriteNum==2){image=getZoraSandEmerge();}
				if(spriteNum==3){image=getZoraUp();}
				if(spriteNum==4){image=getZoraUp();}
				break;
			case "down":
				if(spriteNum==1){image=getZoraSand();}
				if(spriteNum==2){image=getZoraSandEmerge();}
				if(spriteNum==3){image=getZoraDown();}
				if(spriteNum==4){image=getZoraDown();}
				break;
			}
			g2.drawImage(image,getX(),getY(),getGp().getTileSize(),getGp().getTileSize(),null);
		}
	}
}

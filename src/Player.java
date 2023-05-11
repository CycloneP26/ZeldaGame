import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import object.Bomb;
import object.ObjectMain;

public class Player extends Entity implements ActionListener 
{
	GamePanel gp;
	KeyHandler keyH;
	private Timer timer;
	private ArrayList<ObjectMain> bombs; //bombs
	
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		bombs = new ArrayList<ObjectMain>();
		bombs.add(new Bomb());
		this.gp=gp;
		this.keyH=keyH;
		setDefaultValue();
		try {
			getPlayerImage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		timer=new Timer(60,this);
		try {
			getPlayerAttackImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			getBItemImage();
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}
	}
	public void setDefaultValue()
	{
		x=100;
		y=100;
		speed=4;
		direction="down";
	}
	
	public void getPlayerImage() throws IOException
	{
		
		up1=setup("/player/linkMovingBack",gp.tileSize,gp.tileSize);
		up2=setup("/player/linkMovingBack1",gp.tileSize,gp.tileSize);
		down1=setup("/player/linkMovingDown",gp.tileSize,gp.tileSize);
		down2=setup("/player/linkMovingDown1",gp.tileSize,gp.tileSize);
		left1=setup("/player/linkMovingLeft",gp.tileSize,gp.tileSize);
		left2=setup("/player/linkMovingLeft1",gp.tileSize,gp.tileSize);
		right1=setup("/player/linkMovingRight",gp.tileSize,gp.tileSize);
		right2=setup("/player/linkMovingRight1",gp.tileSize,gp.tileSize);
	}
	public void getPlayerAttackImage() throws IOException
	{
			swordUp=setup("/player/linkSwordUp1",gp.tileSize,gp.tileSize*2);
			swordLeft=setup("/player/linkSwordLeft1",gp.tileSize*2,gp.tileSize);
			swordRight=setup("/player/linkSwordRight1",gp.tileSize*2,gp.tileSize);
			swordDown=setup("/player/linkSwordDown1",gp.tileSize,gp.tileSize*2);
			swordUp1=setup("/player/linkSwordUp",gp.tileSize,gp.tileSize*2);
			swordLeft1=setup("/player/linkSwordLeft",gp.tileSize*2,gp.tileSize);
			swordRight1=setup("/player/linkSwordRight",gp.tileSize*2,gp.tileSize);
			swordDown1=setup("/player/linkSwordDown",gp.tileSize,gp.tileSize*2);
	}
	public void getBItemImage() throws IOException
	{
		itemLeft = setup("/player/useItemLeft", gp.tileSize, gp.tileSize);
		itemUp = setup("/player/useItemUp",gp.tileSize,gp.tileSize);
		itemRight = setup("/player/useItemRight",gp.tileSize,gp.tileSize);
		itemDown = setup("/player/useItemDown",gp.tileSize,gp.tileSize);
		
		bomb = setup("/objects/Bomb",gp.tileSize,gp.tileSize);
		//if(something about which item is being used)
		
	}
	public void attacking()
	{
		timer.start();
		spriteCounter++;
		if(spriteCounter<=5)
		{
			spriteNum=1;
		}
		if(spriteCounter>5&&spriteCounter<=25)
		{
			spriteNum=2;
		}
		if(spriteCounter>25)
		{
			spriteNum=1;
			spriteCounter=0;
			attacking=false;
		}
	
	}
	public void item()
	{
		spriteCounter++;
		if(spriteCounter<=5)
		{
			spriteNum=1;
		}
		if(spriteCounter>5&&spriteCounter<=25)
		{
			spriteNum=2;
		}
		if(spriteCounter>25)
		{
			spriteNum=1;
			spriteCounter=0;
			bombUse =false;
		}
	}
	public void update()
	{
		//movement and attacking
		if(attacking==true)
		{
			timer.start();
			attacking();
		}
		else if(bombUse == true)
		{
			item();
		}
		else if(keyH.upPressed==true
				||keyH.downPressed==true
				||keyH.leftPressed==true
				||keyH.rightPressed==true
				||keyH.swordPressed==true
				||keyH.bItem == true)
		{
			if(keyH.swordPressed==true)
			{
				speed=0;
				attacking=true;
			}
			if(keyH.bItem == true)
			{
				bombUse = true;
			}
			if(keyH.upPressed==true)
			{
				if (keyH.bItem == true)
				{
					bombUse = true;
				}
				speed=4;
				direction="up";
				y-=speed;
			}
			else if(keyH.downPressed==true)
			{
				if (keyH.bItem == true)
				{
					bombUse = true;
				}
				speed=4;
				direction="down";
				y+=speed;
			}
			else if(keyH.leftPressed==true)
			{
				if (keyH.bItem == true)
				{
					bombUse = true;
				}
				speed=4;
				direction="left";
				x-=speed;
			}
			else if(keyH.rightPressed==true)
			{
				if (keyH.bItem == true)
				{
					bombUse = true;
				}
				speed=4;
				direction="right";
				x+=speed;
			}
			
			spriteCounter++;
			if(spriteCounter>12) {
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
		
		}
	
		
	public void draw(Graphics2D g2)
	{
		//animation
		BufferedImage image=null;
		switch(direction) {
		case "up":
			if(attacking==false)
			{
				timer.stop();
				if(bombUse)
				{
					image = itemUp;
				}
				else
				{
				if(spriteNum==1){image=up1;}
				if(spriteNum==2){image=up2;}
				}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordUp;}
				if(spriteNum==2) {image=swordUp1;}
			}
			break;
		case "down":
			if(attacking==false)
			{
				timer.stop();
				if(bombUse)
				{
					image = itemDown;
				}
				else {
				if(spriteNum==1){image=down1;}
				if(spriteNum==2){image=down2;}
				}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordDown;}
				if(spriteNum==2) {image=swordDown1;}
			}
			break;
		case "left":
			if(attacking==false)
			{
				timer.stop();
				if(bombUse)
				{
					image = itemLeft;
				}
				else {
				if(spriteNum==1){image=left1;}
				if(spriteNum==2){image=left2;}
				}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordLeft;}
				if(spriteNum==2) {image=swordLeft1;}
			}
			break;
		case "right":
			if(attacking==false)
			{
				timer.stop();
				if(bombUse)
				{
					image = itemRight;
				}
				else {
				if(spriteNum==1){image=right1;}
				if(spriteNum==2){image=right2;}}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordRight;}
				if(spriteNum==2) {image=swordRight1;}
			}
			break;
		}
		
		if(direction=="left"&&attacking)
		{
			g2.drawImage(image,x-40,y,null);
			
		}
		else if(direction=="up"&&attacking)
		{
			g2.drawImage(image,x,y-40,null);
		}
		else
		{
			g2.drawImage(image,x,y,null);
		}
	}
	public BufferedImage setup(String imagePath,int width,int height)
	{
		UtilityTool uTool=new UtilityTool();
		BufferedImage image=null;
		try
		{
			image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image=uTool.scaleImage(image,width,height);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

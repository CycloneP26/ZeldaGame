import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Player extends Entity implements ActionListener 
{
	GamePanel gp;
	KeyHandler keyH;
	private Timer timer;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		this.gp=gp;
		this.keyH=keyH;
		setDefaultValue();
		getPlayerImage();
		timer=new Timer(60,this);
		try {
			getPlayerAttackImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setDefaultValue()
	{
		x=100;
		y=100;
		speed=4;
		direction="down";
	}
	
	public void getPlayerImage()
	{
		
		try {
			up1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingBack.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingBack1.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingDown.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingDown1.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingHorizontal.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingHorizontal1.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingRight.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingRight1.png"));
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void getPlayerAttackImage() throws IOException
	{
		try { 
			swordUp=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordUp1.png"));
		
			swordLeft=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordLeft1.png"));
		
			swordRight=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordRight1.png"));
		
			swordDown=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordDown1.png"));
			
			swordUp1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordUp.png"));
			
			swordLeft1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordLeft.png"));
			
			swordRight1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordRight.png"));
			
			swordDown1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordDown.png"));
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void attacking()
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
			attacking=false;
		}
	}
	
	public void update()
	{
		if(attacking==true)
		{
			timer.start();
			attacking();
		}
		if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true||keyH.swordPressed==true)
		{
			if(keyH.swordPressed==true)
			{
				speed=0;
				attacking=true;
			}
			if(keyH.upPressed==true)
			{
				speed=4;
				direction="up";
				y-=speed;
			}
			else if(keyH.downPressed==true)
			{
				speed=4;
				direction="down";
				y+=speed;
			}
			else if(keyH.leftPressed==true)
			{
				speed=4;
				direction="left";
				x-=speed;
			}
			else if(keyH.rightPressed==true)
			{
				speed=4;
				direction="right";
				x+=speed;
			}
			gp.keyH.swordPressed=false;
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
		BufferedImage image=null;
		switch(direction) {
		case "up":
			if(attacking==false)
			{
				timer.stop();
				if(spriteNum==1){image=up1;}
				if(spriteNum==2){image=up2;}
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
				if(spriteNum==1){image=down1;}
				if(spriteNum==2){image=down2;}
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
				if(spriteNum==1){image=left1;}
				if(spriteNum==2){image=left2;}
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
				if(spriteNum==1){image=right1;}
				if(spriteNum==2){image=right2;}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordRight;}
				if(spriteNum==2) {image=swordRight1;}
			}
			break;
		}
		
			
		g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

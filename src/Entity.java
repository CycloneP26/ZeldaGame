import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int x,y;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1,swordDown,swordDown1;
	public String direction;
	
	public int spriteCounter=0;
	public int spriteNum=1;
	boolean attacking=false;
	boolean bomb = false;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
}

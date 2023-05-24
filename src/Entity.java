import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Entity {
	public int x,y;
	public int speed;
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

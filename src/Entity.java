import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int x,y; //x and y coordinates
	public int speed; //speed of the entity
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //loads images in each direction, for each step
	public BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1,swordDown,swordDown1; //loads images of sword action, in each direction
	public String direction; //direction 
	
	public int spriteCounter=0;
	public int spriteNum=1;
	boolean attacking=false; //boolean for attacking
	boolean bomb = false; //boolean if Link has a usable bomb
	
	public Rectangle solidArea; //boundary
	public boolean collisionOn = false; //can go through anything currently
	
	//returns x
	public int getX()
	{
		return x;
	}
	//returns y
	public int getY()
	{
		return y;
	}
	//sets x
	public void setX(int x)
	{
		this.x = x;
	}
	//sets y
	public void setY(int y)
	{
		this.y = y;
	}
}

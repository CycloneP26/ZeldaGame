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
	
	
}

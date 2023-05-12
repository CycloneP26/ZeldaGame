import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	private int x,y;
	private int speed;
	private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	private BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1,swordDown,swordDown1;
	private String direction;
	
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public BufferedImage getUp1() {
		return up1;
	}
	public void setUp1(BufferedImage up1) {
		this.up1 = up1;
	}
	public BufferedImage getUp2() {
		return up2;
	}
	public void setUp2(BufferedImage up2) {
		this.up2 = up2;
	}
	public BufferedImage getDown1() {
		return down1;
	}
	public void setDown1(BufferedImage down1) {
		this.down1 = down1;
	}
	public BufferedImage getDown2() {
		return down2;
	}
	public void setDown2(BufferedImage down2) {
		this.down2 = down2;
	}
	public BufferedImage getLeft1() {
		return left1;
	}
	public void setLeft1(BufferedImage left1) {
		this.left1 = left1;
	}
	public BufferedImage getLeft2() {
		return left2;
	}
	public void setLeft2(BufferedImage left2) {
		this.left2 = left2;
	}
	public BufferedImage getRight1() {
		return right1;
	}
	public void setRight1(BufferedImage right1) {
		this.right1 = right1;
	}
	public BufferedImage getRight2() {
		return right2;
	}
	public void setRight2(BufferedImage right2) {
		this.right2 = right2;
	}
	public BufferedImage getSwordUp() {
		return swordUp;
	}
	public void setSwordUp(BufferedImage swordUp) {
		this.swordUp = swordUp;
	}
	public BufferedImage getSwordUp1() {
		return swordUp1;
	}
	public void setSwordUp1(BufferedImage swordUp1) {
		this.swordUp1 = swordUp1;
	}
	public BufferedImage getSwordLeft() {
		return swordLeft;
	}
	public void setSwordLeft(BufferedImage swordLeft) {
		this.swordLeft = swordLeft;
	}
	public BufferedImage getSwordLeft1() {
		return swordLeft1;
	}
	public void setSwordLeft1(BufferedImage swordLeft1) {
		this.swordLeft1 = swordLeft1;
	}
	public BufferedImage getSwordRight() {
		return swordRight;
	}
	public void setSwordRight(BufferedImage swordRight) {
		this.swordRight = swordRight;
	}
	public BufferedImage getSwordRight1() {
		return swordRight1;
	}
	public void setSwordRight1(BufferedImage swordRight1) {
		this.swordRight1 = swordRight1;
	}
	public BufferedImage getSwordDown() {
		return swordDown;
	}
	public void setSwordDown(BufferedImage swordDown) {
		this.swordDown = swordDown;
	}
	public BufferedImage getSwordDown1() {
		return swordDown1;
	}
	public void setSwordDown1(BufferedImage swordDown1) {
		this.swordDown1 = swordDown1;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
}

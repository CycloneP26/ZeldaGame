package Main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
/*
Entity is the overarching superclass for all the enemies, the player, and many components that are drawn on the screen
It uses update and draw methods to change and animate the sprites
@author David Kostanyan
@author Sachin Chhaya 
*/
public class Entity {
	//Be able to access the main GamePanel to add components
	private GamePanel gp;   
	//position of entity 
	private int worldX,worldY; 
	//speed of the entity 
	private int speed; 
	//all images for Link's movement 
	private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; 
	//images for sword movement 
	private BufferedImage swordUp,swordUp1,swordLeft,swordLeft1,swordRight,swordRight1, 
	swordDown,swordDown1, itemUp, itemDown, itemRight, itemLeft;
	//Images for Octorok
	private BufferedImage OctorokUp,OctorokUp1,OctorokDown,OctorokDown1,OctorokLeft,OctorokLeft1,OctorokRight,OctorokRight1; 
	private BufferedImage HoglinDown, HoglinDown1, HoglinLeft, HoglinLeft1, HoglinRight, HoglinRight1, HoglinUp, HoglinUp1;
	private BufferedImage BlueHoglinDown, BlueHoglinDown1, BlueHoglinLeft, BlueHoglinLeft1, BlueHoglinRight, BlueHoglinRight1, BlueHoglinUp, BlueHoglinUp1;
	//Images for Spider 
	private BufferedImage SpiderStill,SpiderJump;
	//Images for Leever 
	private BufferedImage LeeverSand, LeeverEmerge, LeeverEmerge2, Leever1, Leever2;
	//Images for Armos
	private BufferedImage ArmosFrozen,ArmosDown,ArmosDown1,ArmosUp,ArmosUp1;
	//Images for Zoras
	private BufferedImage ZoraSand, ZoraSandEmerge, ZoraUp, ZoraDown;
	//Image for Rock 
	private BufferedImage Rock;
	//Direction that the entity is facing
	private String direction; 
	//health of the entity 
	private int health; 
	//Boolean whether the entity is knocked 
	private boolean knocked;
	//Direction that it is being knocked in 
	private String knockedDir;
	//default area of entity to check collision
	private int solidAreaDefX, solidAreaDefY; 
	//Updates with game thread to animate
	public int spriteCounter=0; 
	//Updates with spriteCounter 
	public int spriteNum=1; 
	
	//I KNOW PUBLIC VARIABLES 
	boolean attacking=false; 
	boolean bomb = false;
	boolean itemUse = false;
	 //area of entity to check collision
	private Rectangle solidArea=new Rectangle(0,0,48,48);
	//whether the entity will use collision
	private boolean collisionOn = false; 
	//For-loop counter that restricts movement 
	private int actionLockCounter=0; 
	//Image for link when he gets a newItem
	private BufferedImage newItem;
	//Images for the picked up sword and heart
	private BufferedImage aSword, heartC;
	
	/*constructor for all Entity, just to access the gamePanel
	@param GamePanel gp to access the main GamePanel
	*/
	public Entity(GamePanel gp) 
	{
		this.setGp(gp);
	}
	//Calling actionlock counter 
	public void setAction()
	{

	}
	//Updates the positions and animates entity 
	public void update()
	{
		setAction();
		collisionOn=false;
		getGp().getCollision().checkTile(this);
		if(isCollisionOn() == false)
		{
			switch(getDirection())
			{
			case "up":
				if(worldX>0&&worldY>0&&worldX<700&&worldY<700)
				{
					if(worldY-speed>0)
					{
						worldY-=speed;
					}
					else
					{
						setAction();
					}
				}
			case "down":
				if(worldX>0&&worldY>0&&worldX<700&&worldY<700)
				{
					if(worldY+speed<500)
					{
						worldY+=speed;
					}
					else
					{
						setAction();
					}
				}

				break;
			case "left":
				if(worldX>0&&worldY>0&&worldX<700&&worldY<700)
				{
					if(worldX-speed>0)
					{
						worldX-=speed;
					}
					else
					{
						setAction();
					}
				}

				break;
			case "right":
				if(worldX>0&&worldY>0&&worldX<700&&worldY<700)
				{
					if(worldX+speed<700)
					{
						worldX+=speed;
					}
					else
					{
						setAction();
					}
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
	}
	/*
	Draws the entity and especially Link 
	@param Graphics2D is taken to draw the images
	*/
	public void draw(Graphics2D g2) 
	{
		BufferedImage image=null;

		
		{
			switch(getDirection()) {
			case "up":
				if(spriteNum==1){image=getUp1();}
				if(spriteNum==2){image=getUp2();}
				break;
			case "down":
				if(spriteNum==1){image=getDown1();}
				if(spriteNum==2){image=getDown2();}
				break;
			case "left":
				if(spriteNum==1){image=getLeft1();}
				if(spriteNum==2){image=getLeft2();}
				break;
			case "right":
				if(spriteNum==1){image=getRight1();}
				if(spriteNum==2){image=getRight2();}
				break;
			}
			g2.drawImage(image,worldX,worldY,gp.getTileSize(),gp.getTileSize(),null);
		}
	}
	/*
	image pathing so is works properly with gamePanel
	@param String imagePath to path the image
	@param int width is the width of the image
	@param int height is the height of the image 
	*/
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
		return image; //returns the image after it is pathed correctly
	}
	public int getTileSize()
	{
		return getGp().tileSize;
	}
	public int getX()
	{
		return worldX;
	}
	public int getY()
	{
		return worldY;
	}
	public void setX(int x)
	{
		this.worldX = x;
	}
	public void setY(int y)
	{
		this.worldY = y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public BufferedImage getOctorokUp() {
		return OctorokUp;
	}
	public void setOctorokUp(BufferedImage b)
	{
		this.OctorokUp=b;
	}
	public BufferedImage getOctorokUp1() {
		return OctorokUp1;
	}
	public void setOctorokUp1(BufferedImage b)
	{
		this.OctorokUp1=b;
	}
	public BufferedImage getOctorokDown() {
		return OctorokDown;
	}
	public void setOctorokDown(BufferedImage b)
	{
		this.OctorokDown=b;
	}
	public BufferedImage getOctorokDown1() {
		return OctorokDown1;
	}
	public void setOctorokDown1(BufferedImage b)
	{
		this.OctorokDown1=b;
	}
	public BufferedImage getOctorokLeft() {
		return OctorokLeft;
	}
	public void setOctorokLeft(BufferedImage b)
	{
		this.OctorokLeft=b;
	}
	public BufferedImage getOctorokLeft1() {
		return OctorokLeft1;
	}
	public void setOctorokLeft1(BufferedImage b)
	{
		this.OctorokLeft1=b;
	}
	public BufferedImage getOctorokRight() {
		return OctorokRight;
	}
	public void setOctorokRight(BufferedImage b)
	{
		this.OctorokRight=b;
	}
	public BufferedImage getOctorokRight1() {
		return OctorokRight1;
	}
	public void setOctorokRight1(BufferedImage b)
	{
		this.OctorokRight1=b;
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
	public BufferedImage getItemUp(){return itemUp;}
	public BufferedImage getItemDown(){return itemDown;}
	public BufferedImage getItemLeft(){return itemLeft;}
	public BufferedImage getItemRight(){return itemRight;}
	public void setItemUp(BufferedImage img) {this.itemUp = img;}
	public void setItemDown(BufferedImage img) {this.itemDown = img;}
	public void setItemLeft(BufferedImage img) {this.itemLeft = img;}
	public void setItemRight(BufferedImage img) {this.itemRight = img;}


	public Rectangle getSolidArea() {
		return solidArea;
	}

	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}

	public boolean isCollisionOn() {
		return collisionOn;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}
	
	public GamePanel getGp() {
		return gp;
	}
	public void setGp(GamePanel gp) {
		this.gp = gp;
	}
	public int getActionLockCounter() {
		return actionLockCounter;
	}
	public void setActionLockCounter(int actionLockCounter) {
		this.actionLockCounter = actionLockCounter;
	}
	public int getSolidAreaDefY() {
		return solidAreaDefY;
	}
	public void setSolidAreaDefY(int solidAreaDefY) {
		this.solidAreaDefY = solidAreaDefY;
	}
	public int getSolidAreaDefX() {
		return solidAreaDefX;
	}
	public void setSolidAreaDefX(int solidAreaDefX) {
		this.solidAreaDefX = solidAreaDefX;
	}
	public void setHealth(int h)
	{
		health = h;
	}
	public int getHealth()
	{
		return health;
	}
	public BufferedImage getSpiderStill() {
		return SpiderStill;
	}
	public void setSpiderStill(BufferedImage spiderStill) {
		SpiderStill = spiderStill;
	}
	public BufferedImage getSpiderJump() {
		return SpiderJump;
	}
	public void setSpiderJump(BufferedImage spiderJump) {
		SpiderJump = spiderJump;
	}

	public BufferedImage getRock() {
		return Rock;
	}
	public void setRock(BufferedImage rock) {
		Rock = rock;
	}
	public BufferedImage getLeeverSand() {
		return LeeverSand;
	}
	public void setLeeverSand(BufferedImage leeverSand) {
		LeeverSand = leeverSand;
	}
	public BufferedImage getLeeverEmerge() {
		return LeeverEmerge;
	}
	public void setLeeverEmerge(BufferedImage leeverEmerge) {
		LeeverEmerge = leeverEmerge;
	}
	public BufferedImage getLeeverEmerge2() {
		return LeeverEmerge2;
	}
	public void setLeeverEmerge2(BufferedImage leeverEmerge2) {
		LeeverEmerge2 = leeverEmerge2;
	}
	public BufferedImage getLeever1() {
		return Leever1;
	}
	public void setLeever1(BufferedImage leever1) {
		Leever1 = leever1;
	}
	public BufferedImage getLeever2() {
		return Leever2;
	}
	public void setLeever2(BufferedImage leever2) {
		Leever2 = leever2;
	}
	public boolean getItemUse()
	{
		return itemUse;
	}
	public void setItemUse(boolean use)
	{
		itemUse = use;
	}
	public boolean getKnocked() {
		return knocked;
	}
	public void setKnocked(boolean knocked) {
		this.knocked = knocked;
	}
	public String getKnockedDir() {
		return knockedDir;
	}
	public void setKnockedDir(String knockedDir) {
		this.knockedDir = knockedDir;
	}
	public BufferedImage getNewItem() {
		return newItem;
	}
	public void setNewItem(BufferedImage newItem) {
		this.newItem = newItem;
	}
	public BufferedImage getaSword() {
		return aSword;
	}
	public void setaSword(BufferedImage aSword) {
		this.aSword = aSword;
	}
	public BufferedImage getHeartC() {
		return heartC;
	}
	public void setHeartC(BufferedImage heartC) {
		this.heartC = heartC;
	}
	public BufferedImage getHoglinDown() {
		return HoglinDown;
	}
	public void setHoglinDown(BufferedImage hoglinDown) {
		HoglinDown = hoglinDown;
	}
	public BufferedImage getHoglinDown1() {
		return HoglinDown1;
	}
	public void setHoglinDown1(BufferedImage hoglinDown1) {
		HoglinDown1 = hoglinDown1;
	}
	public BufferedImage getHoglinLeft() {
		return HoglinLeft;
	}
	public void setHoglinLeft(BufferedImage hoglinLeft) {
		HoglinLeft = hoglinLeft;
	}
	public BufferedImage getHoglinLeft1() {
		return HoglinLeft1;
	}
	public void setHoglinLeft1(BufferedImage hoglinLeft1) {
		HoglinLeft1 = hoglinLeft1;
	}
	public BufferedImage getBlueHoglinDown() {
		return BlueHoglinDown;
	}
	public void setBlueHoglinDown(BufferedImage blueHoglinDown) {
		BlueHoglinDown = blueHoglinDown;
	}
	public BufferedImage getBlueHoglinDown1() {
		return BlueHoglinDown1;
	}
	public void setBlueHoglinDown1(BufferedImage blueHoglinDown1) {
		BlueHoglinDown1 = blueHoglinDown1;
	}
	public BufferedImage getBlueHoglinLeft() {
		return BlueHoglinLeft;
	}
	public void setBlueHoglinLeft(BufferedImage blueHoglinLeft) {
		BlueHoglinLeft = blueHoglinLeft;
	}
	public BufferedImage getArmosFrozen() {
		return ArmosFrozen;
	}
	public void setArmosFrozen(BufferedImage armosFrozen) {
		ArmosFrozen = armosFrozen;
	}
	public BufferedImage getZoraSand() {
		return ZoraSand;
	}
	public void setZoraSand(BufferedImage zoraSand) {
		ZoraSand = zoraSand;
	}
	public BufferedImage getArmosDown() {
		return ArmosDown;
	}
	public void setArmosDown(BufferedImage armosDown) {
		ArmosDown = armosDown;
	}
	public BufferedImage getZoraSandEmerge() {
		return ZoraSandEmerge;
	}
	public void setZoraSandEmerge(BufferedImage zoraSandEmerge) {
		ZoraSandEmerge = zoraSandEmerge;
	}
	public BufferedImage getArmosDown1() {
		return ArmosDown1;
	}
	public void setArmosDown1(BufferedImage armosDown1) {
		ArmosDown1 = armosDown1;
	}
	public BufferedImage getArmosUp() {
		return ArmosUp;
	}
	public void setArmosUp(BufferedImage armosUp) {
		ArmosUp = armosUp;
	}
	public BufferedImage getArmosUp1() {
		return ArmosUp1;
	}
	public void setArmosUp1(BufferedImage armosUp1) {
		ArmosUp1 = armosUp1;
	}
	public BufferedImage getZoraUp() {
		return ZoraUp;
	}
	public void setZoraUp(BufferedImage zoraUp) {
		ZoraUp = zoraUp;
	}
	public BufferedImage getZoraDown() {
		return ZoraDown;
	}
	public void setZoraDown(BufferedImage zoraDown) {
		ZoraDown = zoraDown;
	}
	public BufferedImage getHoglinRight() {
		return HoglinRight;
	}
	public void setHoglinRight(BufferedImage hoglinRight) {
		HoglinRight = hoglinRight;
	}
	public BufferedImage getBlueHoglinLeft1() {
		return BlueHoglinLeft1;
	}
	public void setBlueHoglinLeft1(BufferedImage blueHoglinLeft1) {
		BlueHoglinLeft1 = blueHoglinLeft1;
	}
	public BufferedImage getHoglinRight1() {
		return HoglinRight1;
	}
	public void setHoglinRight1(BufferedImage hoglinRight1) {
		HoglinRight1 = hoglinRight1;
	}
	public BufferedImage getHoglinUp() {
		return HoglinUp;
	}
	public void setHoglinUp(BufferedImage hoglinUp) {
		HoglinUp = hoglinUp;
	}
	public BufferedImage getHoglinUp1() {
		return HoglinUp1;
	}
	public void setHoglinUp1(BufferedImage hoglinUp1) {
		HoglinUp1 = hoglinUp1;
	}
	public BufferedImage getBlueHoglinRight() {
		return BlueHoglinRight;
	}
	public void setBlueHoglinRight(BufferedImage blueHoglinRight) {
		BlueHoglinRight = blueHoglinRight;
	}
	public BufferedImage getBlueHoglinRight1() {
		return BlueHoglinRight1;
	}
	public void setBlueHoglinRight1(BufferedImage blueHoglinRight1) {
		BlueHoglinRight1 = blueHoglinRight1;
	}
	public BufferedImage getBlueHoglinUp() {
		return BlueHoglinUp;
	}
	public void setBlueHoglinUp(BufferedImage blueHoglinUp) {
		BlueHoglinUp = blueHoglinUp;
	}
	public BufferedImage getBlueHoglinUp1() {
		return BlueHoglinUp1;
	}
	public void setBlueHoglinUp1(BufferedImage blueHoglinUp1) {
		BlueHoglinUp1 = blueHoglinUp1;
	}
}

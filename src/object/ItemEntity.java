package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;
/*
This class encompasses all items that appear on the map. It is the superclass 
for all items and it is used to draw and keep track of them.
@author Sachin Chhaya 
*/
public class ItemEntity 
{
	//GamePanel to access the GamePanel
	private GamePanel gp;
	//The image of the item
	private BufferedImage image;
	//Name of the item
	private String name;
	//whether collision fo rthe item is on
	private boolean collision = false;
	//X and Y positions on the map
	private int worldX, worldY;
	//The area where the item can collide 
	private Rectangle solidAreaI = new Rectangle (0,0,48,48);
	//Default positions of the items (for collision)
	private int solidAreaIDefX = 0;
	private int solidAreaIDefY = 0;
	private int fireCount = 0;
	private int spriteCounter = 0;
	private BufferedImage fire1, fire2;
	private boolean shop;
	private int cost;
	/*Superconstructor that takes a GamePanel for the item to access gp
	@param GamePanel gp to access gp
	*/
	public ItemEntity(GamePanel gp)
	{
		this.gp = gp;
	}
	/*
	Draws the item at the position that it is given
	@param Graphics2D g2 is required to draw the item on the GamePanel 
	*/
	
	public void update()
	{
		spriteCounter++;
		if(spriteCounter > 15)
		{
			fireCount = 1;
		}
		if(spriteCounter > 30)
		{
			fireCount = 0;
			spriteCounter = 0;
		}
	}
	public void drawFire(Graphics2D g2)
	{
		if(fireCount == 0)
		{
			g2.drawImage(fire1, worldX, worldY, gp.getTileSize(), gp.getTileSize(), null);
		}
		else if (fireCount == 1)
		{
			g2.drawImage(fire2, worldX, worldY, gp.getTileSize(), gp.getTileSize(), null);
		}
	}
	public void draw(Graphics2D g2)
	{
		g2.drawImage(image, worldX, worldY, gp.getTileSize(), gp.getTileSize(), null);
	}
	
	public BufferedImage setup(String imagePath,int width,int height) //image pathing so is works properly with gamePanel
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
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public boolean isCollision() {
		return collision;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWorldY() {
		return worldY;
	}
	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}
	public int getWorldX() {
		return worldX;
	}
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	public Rectangle getSolidAreaI() {
		return solidAreaI;
	}
	public void setSolidAreaI(Rectangle solidAreaI) {
		this.solidAreaI = solidAreaI;
	}
	public int getSolidAreaIDefX() {
		return solidAreaIDefX;
	}
	public void setSolidAreaIDefX(int solidAreaIDefX) {
		this.solidAreaIDefX = solidAreaIDefX;
	}
	public int getSolidAreaIDefY() {
		return solidAreaIDefY;
	}
	public void setSolidAreaIDefY(int solidAreaIDefY) {
		this.solidAreaIDefY = solidAreaIDefY;
	}
	public BufferedImage getFire1() {
		return fire1;
	}
	public void setFire1(BufferedImage fire1) {
		this.fire1 = fire1;
	}
	public BufferedImage getFire2() {
		return fire2;
	}
	public void setFire2(BufferedImage fire2) {
		this.fire2 = fire2;
	}

	public boolean isShop() {
		return shop;
	}

	public void setShop(boolean shop) {
		this.shop = shop;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}

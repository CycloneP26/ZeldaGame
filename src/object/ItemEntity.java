package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;
/*
This class encompasses all items that appear on the map. It is the superclass 
for all items and it is used to draw and keep track of them.
@author Sachin Chhaya 
*/
public class ItemEntity 
{
	
	private GamePanel gp;
	private BufferedImage image;
	private String name;
	private boolean collision = false;
	private int worldX, worldY;
	private Rectangle solidAreaI = new Rectangle (0,0,48,48);
	//Default positions of the items (for collision)
	private int solidAreaIDefX = 0;
	private int solidAreaIDefY = 0;
	public ItemEntity(GamePanel gp)
	{
		this.gp = gp;
	}
	public void draw(Graphics2D g2)
	{
		g2.drawImage(image, worldX, worldY, gp.getTileSize(), gp.getTileSize(), null);
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
}

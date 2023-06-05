package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class ItemEntity 
{
	private BufferedImage image;
	private String name;
	private boolean collision = false;
	private int worldX, worldY;
	private Rectangle solidAreaI = new Rectangle (0,0,48,48);
	
	//Default positions of the items (for collision)
	private int solidAreaIDefX = 0;
	private int solidAreaIDefY = 0;
	public void draw(Graphics2D g2, GamePanel gp)
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
}

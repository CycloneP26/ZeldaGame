package object;

import java.awt.image.BufferedImage;

public class ItemEntity 
{
	private BufferedImage image;
	private String name;
	private boolean collision = false;
	private int worldX, worldY;
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
}

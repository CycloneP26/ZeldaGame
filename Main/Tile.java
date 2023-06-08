package Main;


import java.awt.image.BufferedImage;

public class Tile {
	
	public BufferedImage image;
	public boolean collision;
	private boolean traversable;
	private boolean breakable;

	
	public Tile()
	{
		collision = false;
	}
	public Tile(BufferedImage image, boolean coll)
	{
		this.image = image;
		collision = coll;
	}
	
	public Tile(BufferedImage image, boolean coll, boolean traverse)
	{
		this.image = image;
		collision = coll;
		traversable = traverse;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void setImage(BufferedImage i)
	{
		image = i;
	}

	public boolean getCollision()
	{
		return collision;
	}

	public void setCollision(boolean coll)
	{
		collision = coll;
	}
	
	public void setTraverse(boolean traverse)
	{
		traversable = traverse;
	}
	
	public boolean getTraverse()
	{
		return traversable;
	}
	public boolean isBreakable() {
		return breakable;
	}
	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}
	
	
	
	
}

package Main;

import java.awt.image.BufferedImage;

public class Tile {
	
	public BufferedImage image;
	public boolean collision;
	
	public Tile()
	{
		collision = false;
	}
	public Tile(BufferedImage image, boolean coll)
	{
		this.image = image;
		collision = coll;
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
	
}

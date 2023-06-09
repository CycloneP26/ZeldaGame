package Main;


import java.awt.image.BufferedImage;
/*
Tile class is used to create each tile on the floor of the JPanel.
It takes an image and a boolean variable to determine what the tile
looks like as well as whether it will have collision.
@author Pranay Thatikonda 
*/
public class Tile {
	
	//Image of the tile 
	public BufferedImage image;
	//Whether it has collision 
	public boolean collision;
	//Can it be walked on
	private boolean traversable;
	//Can it break by a bomb
	private boolean breakable;

	//Constructor for a normal tile 
	public Tile()
	{
		collision = false;
	}
	/*
	Constructor for a tile, specifying if it has collision
	@param BufferedImage image used to pass an image for the tile 
	@param boolean coll boolean passed to see if the tile will implament collision
	*/
	public Tile(BufferedImage image, boolean coll)
	{
		this.image = image;
		collision = coll;
	}
	/*
	Constructor for a tile that takes an image and two booleans to specify if it has collision, and if it can be walked on
	@param BufferedImage image used to pass image for the tile 
	@param boolean coll passed to make a tile with collision or not
	@param boolean traverse passed to make a tile with or without the ability to walk on it 
	*/
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

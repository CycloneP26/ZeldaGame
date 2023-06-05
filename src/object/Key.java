package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends ItemEntity
{
	public Key()
	{
		setName("key");
		try 
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"))); 
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		setCollision(true);
	}
}

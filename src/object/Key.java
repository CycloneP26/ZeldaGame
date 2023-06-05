package object;

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

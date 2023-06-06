package object;

public class Key {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Key(GamePanel gp, int x, int y)
	{
		super(gp);
		setName("key");
		try 
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"))); 
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		setWorldX(x);
		setWorldY(y);
	}
}

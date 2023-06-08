package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class StartSword extends ItemEntity
{

	public StartSword(GamePanel gp)
	{
		super(gp);
		setName("startSword");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/StartSword.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public StartSword(GamePanel gp, int x, int y)
	{
		super(gp);
		setWorldX(x);
		setWorldY(y);
		setName("startSword");
		try
		{
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/StartSword.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Bomb extends ObjectMain
{
	private final int EXPLOSIONRAD = 16; //Radius of the bomb blast 
	private Timer timer;
	public Bomb()
	{
		name = "Bomb";
		try //Used to negate error for image pathing 
		{
			//Image insertion for the Bomb
			ImageIO.read(getClass().getResourceAsStream("/objects/Bomb.png"));
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	public int getRad()
	{
		return EXPLOSIONRAD;
	}
}

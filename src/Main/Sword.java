	package Main;

import java.awt.Rectangle;

public class Sword extends Entity
{

	private GamePanel gp;
	
	public Sword(GamePanel gp, String direction, Player player)
	{
		
		super(gp);
		this.gp = gp;
		setDirection(direction);
		
		if(getDirection() == "left")
		{
			
			setX(player.getX() - 30);
			setY(player.getY() + 5);
			setSolidArea(new Rectangle(0, 0, 30, 19));
			gp.getCollision().checkSword(this);
			
			
		}
		
		if(getDirection() == "right")
		{
			
			setX(player.getX() + 48);
			setY(player.getY() + 5);
			setSolidArea(new Rectangle(0, 0, 30, 19));
			gp.getCollision().checkSword(this);
			
		}
		
		if(getDirection() == "up")
		{
			
			setX(player.getX() + 5);
			setY(player.getY() - 30);
			setSolidArea(new Rectangle(0, 0, 19, 30));
			gp.getCollision().checkSword(this);
			
		}
		
		if(getDirection() == "down")
		{
			
			setX(player.getX() + 5);
			setY(player.getY() + 48);
			setSolidArea(new Rectangle(0, 0, 19, 30));
			gp.getCollision().checkSword(this);
			
		}
		
		
	}
	
}
package Main;

public class Projectile extends Entity
{
	private Entity user;
	public Projectile(GamePanel gp) 
	{
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user)
	{
		setX(worldX);
		setY(worldY);
		setDirection(direction);
		this.user=user;

	}
	public void update()
	{
		switch(getDirection())
		{
		case"up":
			setY(getY()-getSpeed());
		break;
		case"down":
			setY(getY()+getSpeed());
		break;
		case"left":
			setX(getX()-getSpeed());
		break;
		case"right":
			setX(getX()+getSpeed());
		break;
		}
		spriteCounter++;
		if(spriteCounter>12)
		{
			if(spriteNum==1)
			{
				spriteNum=2;
			}
			else if(spriteNum==2)
			{
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}

}

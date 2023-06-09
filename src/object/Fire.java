package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Fire extends ItemEntity 
{
	private GamePanel gp;
	public Fire(GamePanel gp, int x, int y) 
	{
		super(gp);
		this.gp = gp;
		getFire();
		setWorldX(x);
		setWorldY(y);

	}
	public void getFire()
	{
		setFire1(setup("/objects/fire1",gp.getTileSize(), gp.getTileSize()));
		setFire2(setup("/objects/fire2", gp.getTileSize(), gp.getTileSize()));
	}

}

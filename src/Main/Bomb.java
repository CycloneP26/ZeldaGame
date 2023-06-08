package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomb extends useableEntity  
{
	private GamePanel gp;
	
	
	public Bomb(GamePanel gp) 
	{
		super(gp);
		this.setLifeSpan(80);
		this.gp = gp;
		getBombImage();
	}
	public void getBombImage()
	{
		setbPhase1(setup("/objects/Bomb", gp.getTileSize(), gp.getTileSize()));
		setbPhase2(setup("/objects/bPhase2", 144, 144));
		setbPhase3(setup("/objects/bPhase3", 144, 144));
	}
}
	
	
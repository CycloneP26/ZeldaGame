package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Item_Bomb extends ItemEntity 
{

	public Item_Bomb(GamePanel gp, int x, int y) {
		super(gp);
		setName("bomb");
		try
		{
		setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Bomb.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
	}
	public Item_Bomb(GamePanel gp, int x, int y, boolean shop) {
		super(gp);
		setName("bomb");
		try
		{
		setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Bomb.png")));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setWorldX(x);
		setWorldY(y);
		setShop(shop);
		setCost(10);
	}
}

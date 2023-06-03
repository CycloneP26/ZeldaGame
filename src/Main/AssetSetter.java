package Main;

import object.ItemEntity;
import object.Key;

public class AssetSetter 
{
	
	private GamePanel gp;
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp; 
	}
	public void setObject()
	{
		ItemEntity[] temp = new ItemEntity[10];
		temp = gp.getObj();
		temp[0] = new Key();
		temp[0].setWorldX(gp.getTileSize() * 23);
		temp[0].setWorldY(gp.getTileSize()  * 7);
		gp.setObj(temp);
		
	}
}
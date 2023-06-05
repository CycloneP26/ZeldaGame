package Main;

import object.ItemEntity;
import object.Key;

public class AssetSetter //To initialize the items on the map 
{
	private GamePanel gp;
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	public void setObj()
	{
		ItemEntity[] temp = new ItemEntity[10];
		temp = gp.getItems();
		temp[0] = new Key();
		temp[0].setWorldX(gp.getTileSize()* 0);
		temp[0].setWorldY(gp.getTileSize() * 0);
		gp.setItems(temp);
		
	}
}
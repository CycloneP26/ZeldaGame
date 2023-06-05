package Main;



import object.ItemEntity;
import object.Key;

public class AssetSetter {

	private GamePanel gp;

	public AssetSetter(GamePanel gp)
	{
		this.gp=gp;
	}
	public void setObject()
	{
		ItemEntity[] temp = new ItemEntity[10];
		temp = gp.getItems();
		temp[0] = new Key();
		temp[0].setWorldX(gp.getTileSize()* 0);
		temp[0].setWorldY(gp.getTileSize() * 0);
		gp.setItems(temp);
	}
	public void setMonster()
	{
		gp.getMobs()[0]=new Octorok(gp);
		gp.getMobs()[0].setX(gp.getTileSize()*10);
		gp.getMobs()[0].setY(gp.getTileSize()*10);

	}
}

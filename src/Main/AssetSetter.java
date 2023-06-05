package Main;



import object.ItemEntity;
import object.Key;

public class AssetSetter {

	private GamePanel gp;

	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	public void setObject()
	{
		gp.getItems()[0] = new Key(gp);
		gp.getItems()[0].setWorldX(gp.getTileSize() * 8);
		gp.getItems()[0].setWorldY(gp.getTileSize() * 8);
	}
	public void setMonster()
	{
		gp.getMobs()[0]=new Octorok(gp);
		gp.getMobs()[0].setX(gp.getTileSize()*10);
		gp.getMobs()[0].setY(gp.getTileSize()*10);

	}
}

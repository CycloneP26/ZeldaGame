
public class AssetSetter {

	private GamePanel gp;

	public AssetSetter(GamePanel gp)
	{
		this.gp=gp;
	}
	public void setObject()
	{

	}
	public void setMonster()
	{
		gp.getMobs()[0]=new Octorok(gp);
		gp.getMobs()[0].setX(gp.getTileSize()*10);
		gp.getMobs()[0].setY(gp.getTileSize()*10);

	}
}

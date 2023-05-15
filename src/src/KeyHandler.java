import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	private boolean upPressed, downPressed, leftPressed, rightPressed, swordPressed, bItem;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W)
		{
			setUpPressed(true);
		}
		if(code==KeyEvent.VK_S)
		{
			setDownPressed(true);
		}
		if(code==KeyEvent.VK_A)
		{
			setLeftPressed(true);
		}
		if(code==KeyEvent.VK_D)
		{
			setRightPressed(true);
		}
		if(code==KeyEvent.VK_Q)
		{
			setSwordPressed(true);
		}
		if(code == KeyEvent.VK_B)
		{
			setbItem(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W)
		{
			setUpPressed(false);
		}
		if(code==KeyEvent.VK_S)
		{
			setDownPressed(false);
		}
		if(code==KeyEvent.VK_A)
		{
			setLeftPressed(false);
		}
		if(code==KeyEvent.VK_D)
		{
			setRightPressed(false);
		}
		if(code==KeyEvent.VK_Q)
		{
			setSwordPressed(false);
		}
		if(code == KeyEvent.VK_B)
		{
			setbItem(false); 
		}
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public boolean isSwordPressed() {
		return swordPressed;
	}

	public void setSwordPressed(boolean swordPressed) {
		this.swordPressed = swordPressed;
	}

	public boolean isbItem() {
		return bItem;
	}

	public void setbItem(boolean bItem) {
		this.bItem = bItem;
	}

}

package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	private boolean upPressed, downPressed, leftPressed, rightPressed, swordPressed, bItem; //All booleans that determine if a key is pressed
	private boolean on;
	private GamePanel gp;
	
	public KeyHandler(GamePanel gp) //Calls the super constructor for the KeyListener class, and it turns on the ability to observe keystrokes
	{

		super();
		on = true;
		this.gp = gp;
	}

	public void setOn(boolean on) //@param boolean to change whether it is on or off
	{

		this.on = on;

	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	When a key is pressed, it changes the booleans that show which key is pressed
	*/
	@Override
	public void keyPressed(KeyEvent e) //@param keystroke argument is passed 
	{
		int code=e.getKeyCode();
		if(on == true)
		{

			if(code==KeyEvent.VK_W && !gp.getPlayer().isGotItem())
			{
				upPressed=true;
			}
			if(code==KeyEvent.VK_S && !gp.getPlayer().isGotItem())
			{
				downPressed=true;
			}
			if(code==KeyEvent.VK_A && !gp.getPlayer().isGotItem())
			{
				leftPressed=true;
			}
			if(code==KeyEvent.VK_D && !gp.getPlayer().isGotItem())
			{
				rightPressed=true;
			}
			if(code==KeyEvent.VK_Q && gp.getPlayer().isHasSword()&& !gp.getPlayer().isGotItem() )
			{
				swordPressed=true;
			}
			if(code == KeyEvent.VK_B && !gp.getPlayer().isGotItem())
			{
				bItem = true;
			}

		}
		
	}
	//When a keystroke is released, it makes the booleans false 
	@Override
	public void keyReleased(KeyEvent e) //@param takes the keystroke argument 
	{
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W)
		{
			upPressed=false;
		}
		if(code==KeyEvent.VK_S)
		{
			downPressed=false;
		}
		if(code==KeyEvent.VK_A)
		{
			leftPressed=false;
		}
		if(code==KeyEvent.VK_D)
		{
			rightPressed=false;
		}
		if(code==KeyEvent.VK_Q)
		{
			swordPressed=false;
		}
		if(code == KeyEvent.VK_B)
		{
			bItem = false; 
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

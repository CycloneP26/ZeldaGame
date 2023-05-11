import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	public boolean upPressed, downPressed, leftPressed, rightPressed, swordPressed, bItem;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//WASD movements and actions, Q for sword and B for bomb
	public void keyPressed(KeyEvent e) 
	{
		int code=e.getKeyCode();
		//move up when pressing W
		if(code==KeyEvent.VK_W)
		{
			upPressed=true;
		}
		//move down when pressing S
		if(code==KeyEvent.VK_S)
		{
			downPressed=true;
		}
		//move left when pressing A
		if(code==KeyEvent.VK_A)
		{
			leftPressed=true;
		}
		//move right when pressing D
		if(code==KeyEvent.VK_D)
		{
			rightPressed=true;
		}
		//attacks with sword when pressing Q
		if(code==KeyEvent.VK_Q)
		{
			swordPressed=true;
		}
		//uses bomb when pressing B
		if(code == KeyEvent.VK_B)
		{
			bItem = true;
		}
		
	}

	@Override
	//stoping WASD actions, sword, and bomb 
	public void keyReleased(KeyEvent e) 
	{
		int code=e.getKeyCode();
		//stops moving
		if(code==KeyEvent.VK_W)
		{
			upPressed=false;
		}
		//stops moving
		if(code==KeyEvent.VK_S)
		{
			downPressed=false;
		}
		//stops moving
		if(code==KeyEvent.VK_A)
		{
			leftPressed=false;
		}
		//stops moving
		if(code==KeyEvent.VK_D)
		{
			rightPressed=false;
		}
		//stops moving
		if(code==KeyEvent.VK_Q)
		{
			swordPressed=false;
		}
		//stops moving
		if(code == KeyEvent.VK_B)
		{
			bItem = false; 
		}
	}

}

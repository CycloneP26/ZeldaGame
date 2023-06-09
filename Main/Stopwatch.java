package Main;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch 
{
	boolean end=false;

	Timer timer=new Timer();

	int i=0;
	TimerTask task=new TimerTask() 
	{
		public void run()
		{
			System.out.println(i);
			i++;
			if(i==3)
			{
				timer.cancel();
				end=true;
			}
		}
	};
	public void runTimer()
	{
		timer.schedule(task,0,1000);
	}
	public boolean getEnd()
	{
		return end;
	}


}

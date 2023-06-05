package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound 
{
	private Clip clip;
	private URL soundURL[] = new URL[30];
	
	public Sound()
	{
		soundURL[0] = getClass().getResource("/sound/01 Title BGM.wav");
		soundURL[1] = getClass().getResource("/sound/SwordSlash.wav");
		soundURL[2] = getClass().getResource("/sound/LOZ_Get_Rupee.wav");
		soundURL[3] = getClass().getResource("/sound/LOZ_Get_Heart.wav");
	}
	public void setFile(int i)
	{
		try 
		{
			AudioInputStream a = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(a);
		}
		catch(Exception e) 
		{
			
		}
	}
	public void play()
	{
		clip.start();
	}
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop()
	{
		clip.stop();
	}
}

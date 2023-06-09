package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
The sound class is used to create a sound object that has access to all the sounds that will be used in the game
this is done by making methods that play the sounds as well as creating an array that stores all the sounds and their file path
@author Sachin Chhaya 
*/
public class Sound 
{
	//The actual sound clip that gets played by the methods
	private Clip clip;
	//Array that stores all the sounds that will be used 
	private URL soundURL[] = new URL[30];
	
	//Where the array is filled by the sounds that are imported to this project 
	public Sound()
	{
		soundURL[0] = getClass().getResource("/sound/01 Title BGM.wav");
		soundURL[1] = getClass().getResource("/sound/SwordSlash.wav");
		soundURL[2] = getClass().getResource("/sound/LOZ_Get_Rupee.wav");
		soundURL[3] = getClass().getResource("/sound/LOZ_Get_Heart.wav");
		soundURL[4] = getClass().getResource("/sound/LOZ_Get_Item.wav");
		soundURL[5] = getClass().getResource("/sound/LOZ_Enemy_Hit.wav");
		soundURL[6] = getClass().getResource("/sound/LOZ_Link_Hurt.wav");
		
	}
	/*
	setFile takes an index parameter that is used to set the clip that is going to be played 
	@param int i is the index of the soundURL array that is used to pick which sound is going to be used 
	*/
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
	//Starts the sound/music
	public void play()
	{
		clip.start();
	}
	//Loops the music 
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	//stops the sound/music 
	public void stop()
	{
		clip.stop();
	}
}

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio
{
	
	private Clip clip;
	
	public Audio(String name)
	{
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(SmallShoot.class.getResource(name)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void start()
	{
		clip.start();
	}

}

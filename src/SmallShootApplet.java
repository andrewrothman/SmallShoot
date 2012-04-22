import java.applet.Applet;

public class SmallShootApplet extends Applet
{

	private static final long serialVersionUID = 1L;
	
	public void init()
	{
		this.setSize(960, 640);
		
		SmallShoot ss = new SmallShoot();
		
		ss.gameCanvas = new GameCanvas();
		ss.gameCanvas.setSize(this.getSize());
		In.attachTo(ss.gameCanvas);
		
		this.add(ss.gameCanvas);
		
		new Thread(ss).start();
	}

}

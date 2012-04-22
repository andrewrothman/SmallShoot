import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class SmallShoot implements Runnable
{

	public GameCanvas gameCanvas;

	public static void main(String args[])
	{
		new SmallShoot().startDesktop();
	}

	public void startDesktop()
	{
		JFrame frame = new JFrame("SmallShoot");
		frame.setMinimumSize(new Dimension(960, 640));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		gameCanvas = new GameCanvas();
		gameCanvas.setSize(frame.getMinimumSize());
		In.attachTo(gameCanvas);
		frame.add(gameCanvas);

		frame.setVisible(true);

		gameCanvas.requestFocus();

		new Thread(this).start();
	}

	@Override
	public void run()
	{
		this.gameCanvas.createBufferStrategy(2);

		this.gameCanvas.start();

		while (true)
		{
			Graphics2D g = (Graphics2D) this.gameCanvas.getBufferStrategy()
					.getDrawGraphics();

			gameCanvas.tick();
			gameCanvas.draw(g);

			this.gameCanvas.getBufferStrategy().show();
			g.dispose();
			
			try
			{
				Thread.sleep(5);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}

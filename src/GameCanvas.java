import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameCanvas extends Canvas
{
	
	private static final long serialVersionUID = 1L;
	
	public static int x, y;
	public static Calvin calvin = new Calvin();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	private static Timer genEntityTimer = new Timer();
	
	public static int points = 0;
	
	public void start()
	{
		calvin.w = 20;
		calvin.h = 20;
		
		calvin.x = this.getSize().width / 2;
		calvin.y = this.getSize().height / 2;
		
		genEntityTimer.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				genEntity();
			}
		}, 1000, 1000);
	}
	
	public void tick()
	{
		if (calvin != null)
		{
			if (In.keyDown[KeyEvent.VK_UP] && calvin.y > 0)
				calvin.move(0, -1);
			else if (In.keyDown[KeyEvent.VK_DOWN] && calvin.y < this.getSize().height - 35)
				calvin.move(0, 1);
		
			if (In.keyDown[KeyEvent.VK_LEFT] && calvin.x > 0)
				calvin.move(-1, 0);
			else if (In.keyDown[KeyEvent.VK_RIGHT] && calvin.x < this.getSize().width - 35)
				calvin.move(1, 0);
		
			if (In.keyDown[KeyEvent.VK_W])
				calvin.shoot(0, -1);
			else if (In.keyDown[KeyEvent.VK_S])
				calvin.shoot(0, 1);
		
			if (In.keyDown[KeyEvent.VK_A])
				calvin.shoot(-1, 0);
			else if (In.keyDown[KeyEvent.VK_D])
				calvin.shoot(1, 0);
		}
		
		if (calvin == null)
			return;
		
		calvin.tick();
		
		for (int i = 0; i < enemies.size(); i++)
		{
			Enemy e = enemies.get(i);
			
			if (calvin.x > e.x - e.w && calvin.x < e.x + e.w && calvin.y > e.y - e.h && calvin.y < e.y + e.h)
			{
				calvin.health -= e.health * 3;
				enemies.remove(i);
				new Audio("/calvin_hurt.wav").start();
				points -= 2;
				
				if (calvin.health < 0)
				{
					genEntityTimer.cancel();
					calvin = null;
					return;
				}
			}
			else
			{
				e.tick();
			}
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		if (calvin != null)
			calvin.draw(g);
		
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).draw(g);
		
		g.setColor(Color.white);
		g.drawString("Points: " + points, 10, 20);
	}

	public void onClick(MouseEvent event)
	{
		
	}
	
	public void genEntity()
	{
		Enemy e = new Enemy();
		e.w = 20;
		e.h = 20;
	
		e.x = (int) ((Math.random() * 1000) % this.getSize().width);
		e.y = (int) ((Math.random() * 1000) % this.getSize().height);
		enemies.add(e);
	}
	
}

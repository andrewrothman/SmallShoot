import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Calvin extends Entity
{
	
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private int shootTimer;
	
	public int health = 100;
	
	public void tick()
	{
		for (int i = 0; i < bullets.size(); i++)
		{
			Bullet b = bullets.get(i);
			
			if (b.x > 960 || b.x < 0 || b.y > 640 || b.y < 0)
				bullets.remove(i);
			else
				b.tick();
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.white);
		g.fillRect((int) (x + w / 2), (int) (y + h / 2), (int) w, (int) h);
		
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).draw(g);
		
		g.setColor(Color.white);
		g.drawString("" + health, (float) x, (float) y);
	}
	
	public void shoot(int xChange, int yChange)
	{
		if (shootTimer == 0)
		{
			Bullet b = new Bullet();
			b.x = x + w;
			b.y = y + h / 2;
			
			b.xVelocity = xChange * 2;
			b.yVelocity = yChange * 2;
			
			bullets.add(b);
			
			shootTimer = 20;
			
			new Audio("/shoot.wav").start();
		}
		else
		{
			shootTimer--;
		}
	}
	
	public void move(int xChange, int yChange)
	{
		x += xChange;
		y += yChange;
	}

}

import java.awt.Color;
import java.awt.Graphics2D;


public class Enemy extends Entity
{
	
	public double health = 5;
	
	private double speed = (Math.random() % 0.3) + 0.7;
	
	public void tick()
	{
		if (GameCanvas.calvin.x > x)
			move(0.5, 0);
		else
			move(-0.5, 0);
		
		if (GameCanvas.calvin.y > y)
			move(0, 0.5);
		else
			move(0, -0.5);
		
		for (int i = 0; i < GameCanvas.calvin.bullets.size(); i++)
		{
			Bullet b = GameCanvas.calvin.bullets.get(i);
			
			if (b.x > x && b.x < x + 30 && b.y > y && b.y < y + 30)
			{
				health--;
				GameCanvas.calvin.bullets.remove(i);
			}
		}
		
		if (health < 0)
		{
			GameCanvas.enemies.remove(this);
			new Audio("/enemy_hurt.wav").start();
			GameCanvas.points += 10;
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.red);
		g.fillRect((int) (x + w / 2), (int) (y + h / 2), (int) w, (int) h);
		
		g.drawString("" + health, (float) x, (float) y);
	}
	
	public void move(double xChange, double yChange)
	{
		x += speed * xChange;
		y += speed * yChange;
	}

}
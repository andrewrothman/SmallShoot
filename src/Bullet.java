import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Bullet extends Entity
{
	
	public int xVelocity, yVelocity;
	public Color color = Color.white;
	
	private ArrayList<HashMap<String, String>> previousPositions = new ArrayList<HashMap<String, String>>();
	
	public Bullet()
	{
		w = 5;
		h = 5;
	}
	
	@Override
	public void tick()
	{
		x += xVelocity;
		y += yVelocity;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.fillRect((int) (x + w / 2), (int) (y + h / 2), (int) w, (int) h);
		g.setColor(new Color(Math.max(0, color.getRed() - 100), Math.max(0, color.getGreen() - 100), Math.max(0, color.getBlue() - 100), color.getAlpha()));
		g.fillRect((int) (x + w / 2) - xVelocity, (int) (y + h / 2) - yVelocity, (int) w, (int) h);
		
		for (int i = 0; i < previousPositions.size(); i++)
		{
			HashMap<String, String> oldPositionData = previousPositions.get(i);
			
			int colorChange = ((255 / 10) * i);
			g.setColor(new Color(Math.max(0, color.getRed() - colorChange), Math.max(0, color.getGreen() - colorChange), Math.max(0, color.getBlue() - colorChange), color.getAlpha()));
			g.fillRect(Integer.parseInt(oldPositionData.get("X")) + (int) w / 2, Integer.parseInt(oldPositionData.get("Y")) + (int) h / 2, (int) w, (int) h);
		}
		
		HashMap<String, String> positionData = new HashMap<String, String>();
		positionData.put("X", "" + (int) x);
		positionData.put("Y", "" + (int) y);
		
		previousPositions.add(0, positionData);
		
		if (previousPositions.size() >= 10)
			previousPositions.remove(previousPositions.size() - 1);
	}

}

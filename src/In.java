import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class In implements KeyListener, MouseListener, MouseMotionListener
{

	public static boolean keyDown[] = new boolean[256];
	public static int x, y;
	private static In defaultIn;
	private static GameCanvas canvas;

	public static void attachTo(GameCanvas c)
	{
		defaultIn = new In();

		canvas = c;

		c.addKeyListener(defaultIn);
		c.addMouseListener(defaultIn);
		c.addMouseMotionListener(defaultIn);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keyDown[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keyDown[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		x = arg0.getX() - GameCanvas.x;
		y = arg0.getY() - GameCanvas.y;
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		x = arg0.getX() - GameCanvas.x;
		y = arg0.getY() - GameCanvas.y;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		x = arg0.getX() - GameCanvas.x;
		y = arg0.getY() - GameCanvas.y;
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		x = arg0.getX() - GameCanvas.x;
		y = arg0.getY() - GameCanvas.y;
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		x = arg0.getX() - GameCanvas.x;
		y = arg0.getY() - GameCanvas.y;
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		x = arg0.getX() - GameCanvas.x;
		y = arg0.getY() - GameCanvas.y;
		
		canvas.onClick(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{

	}

}
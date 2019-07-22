package ajbdnlkjasg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class SnakeButtonHandler implements KeyListener
{
	public SnakeBlock snake;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(snake == null)
		{
			System.out.println("java gay");
			return;
		}
		
		if(snake.direction.hasMooved)
		{
			return;
		}

		if(e.getKeyChar()=='s' && snake.direction.y != -1)
		{
			snake.direction.x = 0;
			snake.direction.y = 1;
		}
		else if(e.getKeyChar()=='w' && snake.direction.y != 1)
		{
			snake.direction.x = 0;
			snake.direction.y = -1;
		}
		else if(e.getKeyChar()=='d' && snake.direction.x != -1)
		{
			snake.direction.x = 1;
			snake.direction.y = 0;
		}
		else if(e.getKeyChar()=='a' && snake.direction.x != 1)
		{
			snake.direction.x = -1;
			snake.direction.y = 0;
		}
		snake.direction.hasMooved = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
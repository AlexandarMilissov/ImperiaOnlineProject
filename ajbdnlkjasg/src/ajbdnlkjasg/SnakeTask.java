package ajbdnlkjasg;

import java.util.TimerTask;

public class SnakeTask extends TimerTask {

	SnakeBlock snake;
	@Override
	public void run() {
		if(!snake.isActive)
		{
			this.cancel();
			return;
		}
		snake.Move(snake.x + snake.direction.x,snake.y + snake.direction.y);
		snake.direction.hasMooved = false;
	}

}

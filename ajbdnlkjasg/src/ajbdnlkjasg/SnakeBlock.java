package ajbdnlkjasg;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SnakeBlock {
	
	public int x;
	public int y;
	SnakeDirection direction;
	public SnakeBlock child;
	SnakeField field;
	JFrame frame;
	boolean isActive = true;
	
	public SnakeBlock(int X, int Y,SnakeField f,SnakeDirection d,JFrame Frame)
	{
		frame = Frame;
		x = X;
		y = Y;
		field = f;
		field.field[y][x].setText("0");
		direction = d;
	}
	
	public void Move(int newX, int newY)
	{
		if(newY <= field.field.length-1
		&& newY >= 0
		&& newX <= field.field[y].length-1
		&& newX >= 0)
		{
		}
		else
		{
			End();
			return;
		}
		
		if(field.field[newY][newX].getText()=="0")
		{
			End();
		}
		if(field.field[newY][newX].getText()=="*")
		{
			Eat();
		}
		field.field[y][x].setText("");
		field.field[newY][newX].setText("0");
		if(child != null) 
		{
			child.Move(x, y);
		}
		x = newX;
		y = newY;
	}
	void Eat()
	{
		addChild();
		
		CreateFood(field.field[y].length,field.field.length);
	}
	
	void addChild()
	{
		SnakeBlock block = new SnakeBlock(x,y,field,direction,frame);
		if(child == null)
		{
			child = block;
			return;
		}
		else
		{
			child.addChild();
		}
	}

	public void CreateFood(int maxX, int maxY)
	{
		int freeSpaces = 0;
		for(int i = 0; i < field.field.length - 1;i++)
		{
			for(int j = 0; j < field.field[y].length - 1;j++)
			{
				if(field.field[i][j].getText() != "0")
				{
					freeSpaces++;
				}
			}
		}
		if(freeSpaces == 0)
		{
			Win();
		}
		Random rand = new Random();
		int x = rand.nextInt(maxY);
		int y = rand.nextInt(maxX);
		if(field.field[x][y].getText() != "")
		{
			CreateFood(maxX, maxY);
			return;
		}
		field.field[x][y].setText("*");
	}
	
	private void Win() {
		System.out.println("Win");
		End();
	}

	public void End()
	{
		TextScreen textScreen = new TextScreen();
		textScreen.JText.setText("End");
		textScreen.JText.setHorizontalTextPosition(SwingConstants.CENTER);
		textScreen.JText.setHorizontalAlignment(SwingConstants.CENTER);
		System.out.println("End");
		isActive = false;
		frame.dispose();
	}
	
}

package ajbdnlkjasg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MinesweeperButtonField{
	private int height;
	private int width;
	private int size = 45;
	MinesweeperTile[][] field;
	JFrame frame;
	public static int bombCount = 0;
	public boolean isSettingFlags = false;
	public MineSweeperKeyListener mskl;
	
	public MinesweeperButtonField(int Height, int Width, int Size, JFrame Frame, MineSweeperKeyListener MSKL)
	{
		mskl = MSKL;
		height = Height;
		width = Width;
		size = Size;
 		field = new MinesweeperTile[height][width];
 		frame = Frame;
 		
		Random rand = new Random();
 		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				frame.setBounds(0,0,16 + (height * size),38 +((width) * size));
				
				field[y][x] = new MinesweeperTile(x,y,new JButton(""),this,frame);
				frame.getContentPane().add(field[y][x].button);
				field[y][x].button.setLocation(x * size, y * size);
				field[y][x].button.setSize(size, size);
				field[y][x].button.addKeyListener(mskl);
				
				int r = rand.nextInt(5);
				if(r != 1)
				{
					r = 0;
				}
				field[y][x].isBomb = r*(-1);
			}
		}
 		SetNeigbours();
 		
 		JButton butt = new JButton("");
		frame.getContentPane().add(butt);
		
		System.out.println(bombCount);
	}

	public void SetNeigbours()
	{
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				if(field[y][x].isBomb == 0)
				{
					List<MinesweeperTile> n = GetNeighboursBombs(x,y);
					field[y][x].neighbours = n.size();
					//field[y][x].button.setText(""+n);
				}
				else
				{
					bombCount++;
				}
			}
		}
	}
	

	List<MinesweeperTile> GetNeighbours(int x,int y) 
	{
		List<MinesweeperTile> neighbours = new ArrayList<MinesweeperTile>();
		
		if(x > 0
		&& y > 0)
		{
			neighbours.add(field[y-1][x-1]);
		}
		if(y > 0)
		{
			neighbours.add(field[y-1][x]);
		}
		if(x < field[y].length - 1
		&& y > 0)
		{
			neighbours.add(field[y-1][x+1]);
		}
		if(x < field[y].length - 1)
		{
			neighbours.add(field[y][x+1]);
		}
		if(x < field[y].length - 1
		&& y < field.length - 1)
		{
			neighbours.add(field[y+1][x+1]);
		}
		if(y < field.length - 1)
		{
			neighbours.add(field[y+1][x]);
		}
		if(x > 0
		&& y < field.length - 1)
		{
			neighbours.add(field[y+1][x-1]);
		}
		if(x > 0)
		{
			neighbours.add(field[y][x-1]);
		}
		
		return neighbours;
	}

	List<MinesweeperTile> GetNeighboursBombs(int x,int y) 
	{
		List<MinesweeperTile> neighboursBombs = new ArrayList<MinesweeperTile>();
		List<MinesweeperTile> neighbours = GetNeighbours(x,y);
		for(MinesweeperTile t : neighbours)
		{
			if(t.isBomb == -1)
			{
				neighboursBombs.add(t);
			}
		}
		return neighboursBombs;
	}
	public void CheckIfWin()
	{
		List<MinesweeperTile> list = new ArrayList<MinesweeperTile>();
	    for (MinesweeperTile[] array : field) {
	        list.addAll(Arrays.asList(array));
	    }
	    
	    for(MinesweeperTile t : list)
	    {
	    	if(t.isBomb == -1 && t.isMarked)
	    	{
	    		continue;
	    	}
	    	else if(t.isBomb != -1 && !t.isMarked)
	    	{
	    		continue;
	    	}
	    	else
	    	{
	    		return;
	    	}
	    }
    	Win();
	}


	private void Win() {
		TextScreen win = new TextScreen();
		win.JText.setText("Win");
		frame.dispose();
	}
}



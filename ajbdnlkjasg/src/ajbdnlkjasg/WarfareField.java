package ajbdnlkjasg;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WarfareField {

	private int height;
	private int width;
	private int size = 45;
	WarfareTile[][] field;
	JPanel frame;
	Warfare game;
	
	public WarfareField(int Height, int Width, int Size, JPanel Frame, Warfare Game)
	{
		height = Height;
		width = Width;
		size = Size;
 		field = new WarfareTile[height][width];
 		frame = Frame;
 		game = Game;
 		
 		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				frame.setBounds(0,0,16 + (height * size),38 +((width) * size));
				
				field[y][x] = new WarfareTile(x, y, frame, game);
				frame.add(field[y][x].button);
				field[y][x].button.setLocation(x * size, y * size);
				field[y][x].button.setSize(size, size);
				
			}
		}
 		
 		JButton butt = new JButton("");
		frame.add(butt);	
	}
	List<WarfareTile> availableTiles;
	List<WarfareTile> CheckedTiles;
	public boolean isTileInRange(WarfareUnit Unit, WarfareTile EndTile, int distance, boolean IgnoreObsticles)
	{
		int totalCost = 0;
		int speed = distance;
		availableTiles = new ArrayList<WarfareTile>();
		CheckedTiles = new ArrayList<WarfareTile>();

		if(speed > 0)
		{
			availableTiles = getAllNearbyTiles(Unit.tile, IgnoreObsticles);
			CheckedTiles.add(Unit.tile);
		}
		
		
		while(speed > 0)
		{

			List<WarfareTile> i = new ArrayList<WarfareTile>();
			for(WarfareTile t : availableTiles)
			{
				i.add(t);
			}
			
			for(WarfareTile t : availableTiles)
			{
				if(t == EndTile)
				{
					return true;
				}
				else
				{
					CheckedTiles.add(t);
					i.addAll(getAllNearbyTiles(t,IgnoreObsticles));
					i.remove(t);
				}
			}
			availableTiles = i;
			speed --;
		}
		
		
		return false;
	}
	
	private List<WarfareTile> getAllNearbyTiles(WarfareTile Tile, boolean IgnoreObsticles)
	{
		List<WarfareTile> surroundingTiles = new ArrayList<WarfareTile>();
		
		int x = Tile.x;
		int y = Tile.y;
		
		if(x > 0
		&& y > 0)
		{
			if(!(availableTiles.contains(field[y-1][x-1])
				|| CheckedTiles.contains(field[y-1][x-1])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y-1][x-1].unit == null)
					{
						surroundingTiles.add(field[y-1][x-1]);
					}
				}
				else
				{
					surroundingTiles.add(field[y-1][x-1]);
				}
			}
		}
		if(y > 0)
		{
			if(!(availableTiles.contains(field[y-1][x])
				|| CheckedTiles.contains(field[y-1][x])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y-1][x].unit == null)
					{
						surroundingTiles.add(field[y-1][x]);
					}
				}
				else
				{
					surroundingTiles.add(field[y-1][x]);
				}
			}
		}
		if(x < field[y].length - 1
		&& y > 0)
		{
			if(!(availableTiles.contains(field[y-1][x+1])
				|| CheckedTiles.contains(field[y-1][x+1])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y-1][x+1].unit == null)
					{
						surroundingTiles.add(field[y-1][x+1]);
					}
				}
				else
				{
					surroundingTiles.add(field[y-1][x+1]);
				}
			}
		}
		if(x < field[y].length - 1)
		{
			if(!(availableTiles.contains(field[y][x+1])
				|| CheckedTiles.contains(field[y][x+1])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y][x+1].unit == null)
					{
						surroundingTiles.add(field[y][x+1]);
					}
				}
				else
				{
					surroundingTiles.add(field[y][x+1]);
				}
			}
		}
		if(x < field[y].length - 1
		&& y < field.length - 1)
		{
			if(!(availableTiles.contains(field[y+1][x+1])
				|| CheckedTiles.contains(field[y+1][x+1])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y+1][x+1].unit == null)
					{
						surroundingTiles.add(field[y+1][x+1]);
					}
				}
				else
				{
					surroundingTiles.add(field[y+1][x+1]);
				}
			}
		}
		if(y < field.length - 1)
		{
			if(!(availableTiles.contains(field[y+1][x])
				|| CheckedTiles.contains(field[y+1][x])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y-1][x].unit == null)
					{
						surroundingTiles.add(field[y+1][x]);
					}
				}
				else
				{
					surroundingTiles.add(field[y+1][x]);
				}
			}
		}
		if(x > 0
		&& y < field.length - 1)
		{
			if(!(availableTiles.contains(field[y+1][x-1])
				|| CheckedTiles.contains(field[y+1][x-1])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y+1][x-1].unit == null)
					{
						surroundingTiles.add(field[y+1][x-1]);
					}
				}
				else
				{
					surroundingTiles.add(field[y+1][x-1]);
				}
			}
		}
		if(x > 0)
		{
			if(!(availableTiles.contains(field[y][x-1])
				|| CheckedTiles.contains(field[y][x-1])))
			{
				if(!IgnoreObsticles)
				{
					if(field[y][x-1].unit == null)
					{
						surroundingTiles.add(field[y][x-1]);
					}
				}
				else
				{
					surroundingTiles.add(field[y][x-1]);
				}
			}
		}
		return surroundingTiles;
	}	
}


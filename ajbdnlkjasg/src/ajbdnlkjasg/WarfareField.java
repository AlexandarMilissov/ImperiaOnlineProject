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
	public List<WarfareTile> isTileInRange(WarfareUnit Unit, WarfareTile EndTile, int distance, boolean IgnoreObsticles)
	{
		for (WarfareTile[] elem: field) 
		{
		    for (WarfareTile t: elem) 
		    {
		    	t.pathParent = null;
		    	t.costToThis = 0;
		    }
		}
		
		
		
		int speed = distance;
		availableTiles = new ArrayList<WarfareTile>();
		CheckedTiles = new ArrayList<WarfareTile>();
		List<WarfareTile> Path = new ArrayList<WarfareTile>();
		

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
					WarfareTile T = t;
					do
					{
						Path.add(T);
						if(T.pathParent == null)
						{
							break;
						}
						T = T.pathParent;
					}while(true);
					return Path;
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
		
		
		return null;
	}
	
	private List<WarfareTile> getAllNearbyTiles(WarfareTile Tile, boolean IgnoreObsticles)
	{
		List<WarfareTile> surroundingTiles = new ArrayList<WarfareTile>();
		
		int x = Tile.x;
		int y = Tile.y;
		
		if(x > 0
		&& y > 0)
		{
			WarfareTile tile = AddTile(y-1,x-1,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(y > 0)
		{
			WarfareTile tile = AddTile(y-1,x,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(x < field[y].length - 1
		&& y > 0)
		{
			WarfareTile tile = AddTile(y-1,x+1,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(x < field[y].length - 1)
		{
			WarfareTile tile = AddTile(y,x+1,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(x < field[y].length - 1
		&& y < field.length - 1)
		{
			WarfareTile tile = AddTile(y+1,x+1,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(y < field.length - 1)
		{
			WarfareTile tile = AddTile(y+1,x,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(x > 0
		&& y < field.length - 1)
		{
			WarfareTile tile = AddTile(y+1,x-1,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		if(x > 0)
		{
			WarfareTile tile = AddTile(y,x-1,IgnoreObsticles,Tile);
			if(tile != null)
			{
				surroundingTiles.add(tile);
			}
		}
		return surroundingTiles;
	}	
	
	
	
	
	WarfareTile AddTile(int y, int x ,boolean IgnoreObsticles,WarfareTile Parent)
	{
		WarfareTile toAdd = null;
		
		if(!availableTiles.contains(field[y][x]))
		{
			if(!CheckedTiles.contains(field[y][x]))
			{
				if(!IgnoreObsticles)
				{
					if(field[y][x].unit == null)
					{
						toAdd = field[y][x];
						toAdd.costToThis = Parent.costToThis + toAdd.cost;
						toAdd.pathParent = Parent;
					}
				}
				else
				{
					toAdd = field[y][x];
					toAdd.costToThis = Parent.costToThis + toAdd.cost;
					toAdd.pathParent = Parent;
				}
			}
		}
		else
		{
			if(field[y][x].costToThis > Parent.costToThis + field[y][x].cost)
			{
				field[y][x].costToThis = Parent.costToThis + field[y][x].cost;
				field[y][x].pathParent = Parent;
			}
		}
		return toAdd;
	}
}


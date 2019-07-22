package ajbdnlkjasg;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MinesweeperTile {
	int x;
	int y;
	int neighbours;
	public int isBomb;
	public boolean isMarked;
	JButton button = new JButton();
	MinesweeperButtonField field;
	JFrame frame;
	static int pressedTimes = 0;
	
	public MinesweeperTile(int X, int Y, JButton Button ,MinesweeperButtonField Field, JFrame Frame)
	{
		x = X;
		y = Y;
		field = Field;
		frame = Frame;
		
		button = Button;
		button.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {

		    	if(!isMarked && !field.isSettingFlags)
		    	{
		    		Pressed();
				}
		    	if(field.isSettingFlags)
		    	{
		    		if(isMarked)
		    		{
			    	    button.setBackground(null);
		    		}
		    		else
		    		{
			    	    button.setBackground(Color.RED);
		    		}
		    		isMarked = !isMarked;
		    	}
		    	
				field.CheckIfWin();
		    }
		});
	}
	public void Pressed()
	{
		if(pressedTimes == 0)
		{
			isBomb = 0;
			field.SetNeigbours();
		}
		pressedTimes++;
		List<MinesweeperTile> n = field.GetNeighbours(x, y);
    	if(isBomb == -1)
    	{
    		TextScreen win = new TextScreen();
    		win.JText.setText("Lose");
    		pressedTimes = 0;
    		frame.dispose();
    	}
    	else
    	{
    		button.setText("" + neighbours);
        	if(neighbours == 0)
        	{
        		for(MinesweeperTile t : n)
        		{
        			if(t.button.getText() == "")
        			{
            			t.Pressed();
        			}
        		}
        	}
    	}
	}
}

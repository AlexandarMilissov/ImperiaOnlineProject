package ajbdnlkjasg;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SnakeField {
	private int height;
	private int width;
	private int size = 50;
	JLabel[][] field;
	JFrame frame;
	public SnakeField(int Height, int Width, int Size, JFrame Frame)
	{
		height = Height;
		width = Width;
		size = Size;
 		field = new JLabel[height][width];
 		frame = Frame;
 		
 		
 		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				frame.setBounds(0,0,16 + (height * size),38 +((width) * size));
				field[y][x] = new JLabel("",SwingConstants.CENTER);
				frame.getContentPane().add(field[y][x]);
				field[y][x].setLocation(x * size, y * size);
				field[y][x].setSize(size, size);
			}
		}

		JLabel label = new JLabel("");
		frame.getContentPane().add(label);
	}
}

package ajbdnlkjasg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WarfareTile {

	int x;
	int y;
	JPanel frame;
	JButton button;
	public WarfareUnit unit;
	Warfare game;
	WarfareTile This = this;
	public int cost = 1;
	public int costToThis = 0;
	public WarfareTile pathParent = null;
	
	public WarfareTile(int X, int Y, JPanel Frame, Warfare Game)
	{
		x = X;
		y = Y;
		frame = Frame;
		button = new JButton();
		game = Game;
		

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.FieldButtonClicked(This);
			}
		});
	}
	public void SetText(String text)
	{
		button.setText(text);
	}
	public void RemoveUnit()
	{
		unit = null;
		button.setText("");
		button.setBackground(null);
	}
	public void AddUnit(WarfareUnit Unit)
	{
		unit = Unit;
		unit.tile = this;
		button.setText(Unit.name);
		button.setBackground(Unit.side.colour);
	}
}

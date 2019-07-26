package ajbdnlkjasg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;


public class Warfare {

	JFrame frame;
	private int height = 10;
	private int width = 10;
	private int size = 45;
	WarfareField field;
	public WarfareSide left;
	public WarfareSide right;
	public int turnCounter = 1;
	int leftTurnAllow = 0;
	int rightTurnAllow = 1;
	
	
	WarfareUnit selectedUnit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Warfare window = new Warfare();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Warfare() {
		
		
		initialize();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int)(screenSize.height * 0.95);
		this.size = screenHeight / this.height;
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel Field = new JPanel();
		frame.getContentPane().add(Field, BorderLayout.CENTER);
		Field.setLayout(null);
		field = new WarfareField(height, width, (int)(size * 0.9), Field, this);
		
		JPanel Left = new JPanel();
		frame.getContentPane().add(Left, BorderLayout.WEST);
		Left.setLayout(null);
		
		JPanel Right = new JPanel();
		frame.getContentPane().add(Right, BorderLayout.EAST);
		Right.setLayout(null);

		Left.setBounds(0, 0, Left.getSize().width, Field.getSize().height);
		Field.setBounds(Left.getSize().width, 0, Field.getSize().width, Field.getSize().height);
		Right.setBounds(Left.getSize().width + Field.getSize().width, 0, Right.getSize().width, Left.getSize().height);
		
		
		
		
		
		frame.setSize(Field.getSize().width + Left.getSize().width + Right.getSize().width,(int) (screenHeight*(0.93)));
		
		left = new WarfareSide(new ArrayList<WarfareUnit>(),Color.GREEN,field,leftTurnAllow,"left",this);
		right = new WarfareSide(new ArrayList<WarfareUnit>(),Color.RED,field,rightTurnAllow,"right",this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 304, 217);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WarfareWindowListener windowListener = new WarfareWindowListener();
		windowListener.game = this;
		frame.addWindowListener(windowListener);
		frame.setVisible(true);
	}
	
	public void UpdateTurnCounters()
	{
		turnCounter++;
		selectedUnit = null;
		left.tools.UpdateTurnCounter(turnCounter);
		right.tools.UpdateTurnCounter(turnCounter);
	}
	
	public void FieldButtonClicked(WarfareTile Tile)
	{
		//checking which player pressed the field
		WarfareSide side;
		//left player
		if(turnCounter%2 != leftTurnAllow)
		{
			side = left;
		}
		//right player
		else
		{
			side = right;
		}
		
		//checking if player has enough actions
		if(side.actionsRemaining == 0)
		{
			//he doesn't
			return;
		}
		
		
		//player adds unit to the board
		if(side.selectedNewUnit != null && Tile.unit == null)
		{
			if(side.money < side.selectedNewUnit.cost)
			{
				//you don't have enough money
				return;
			}
			
			side.selectedNewUnit.DoAction();
			side.money -= side.selectedNewUnit.cost;
			System.out.println("New Army, Money Left: " + side.money);
			Tile.AddUnit(side.selectedNewUnit);
			side.AddtheNewUnit(Tile);
			
			side.DoAction();
			return;
		}

		

		//deselect unit
		if(selectedUnit != null && selectedUnit.tile == Tile)
		{
			selectedUnit = null;
			System.out.println("Deselected");
			return;
		}
		//moving selected unit or attacking with it
		//checking if unit is selected
		if(selectedUnit != null)
		{
			//move
			//check if tile it empty
			if(Tile.unit == null)
			{
				//check if tile is in range 
				if(field.isTileInRange(selectedUnit, Tile, selectedUnit.speed, false) != null)
				{
					//check if unit has enough action points
					if(selectedUnit.timesLeft > 0)
					{
						//if it has enough reduce them
						selectedUnit.timesLeft--;
					}
					//if it doesn't it can't move
					else
					{
						return;
					}
					
					//move Unit
					selectedUnit.tile.RemoveUnit();
					Tile.AddUnit(selectedUnit);
					
					System.out.println("Move");
					//Deselect 
					selectedUnit = null;
					side.DoAction();
					return;
				}
				//tile is not in walking range
				else
				{
					System.out.println("Move not in range");
					return;
				}
			}
			//tile is not empty
			else
			{
				//check it target is in range
				if(field.isTileInRange(selectedUnit, Tile, selectedUnit.range, true) != null)
				{
					//check if unit is an ally
					if(Tile.unit.side == side)
					{
						//it is
						//can't to shit here 
						//or can you?
						return;
					}
					//there is an enemy 
					//attack them
					else
					{
						//check if unit has enough action points
						if(selectedUnit.timesLeft > 0)
						{
							//if it has enough reduce them
							selectedUnit.timesLeft--;
						}
						//if it doesn't it can't attack
						else
						{
							return;
						}
						
						
						//attack
						selectedUnit.Attack(Tile.unit);
						
						
						System.out.println("Attack");
						//Deselect 
						selectedUnit = null;
						side.DoAction();
						return;
					}
				}
				else
				{
					System.out.println("Target not in range");
					return;
				}
			}
		}
		
		//select unit
		//if you don't have selected unit
		if(selectedUnit == null )
		{
			//and if there is unit in this place
			if(	Tile.unit != null)
			{
				//and its on your side
				if(Tile.unit.side == side)
				{
					//select this unit
					selectedUnit = Tile.unit;
					System.out.println("Selected");
					return;
				}
			}
		}
	}
}

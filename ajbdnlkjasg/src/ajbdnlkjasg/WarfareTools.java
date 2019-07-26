package ajbdnlkjasg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarfareTools {

	public WarfareSide thisSide; 
	JFrame frame;
	public JLabel Name;
	private JLabel Actions;
	private JButton End;
	private JLabel TurnCounter;
	private JLabel Money;
	WarfareUnit unit = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarfareTools window = new WarfareTools();
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
	public WarfareTools() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 285, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		Name = new JLabel("");
		Name.setBounds(99, 0, 194, 31);
		frame.getContentPane().add(Name);
		
		Money = new JLabel("Money: ");
		Money.setBounds(35, 73, 48, 14);
		frame.getContentPane().add(Money);
		
		Actions = new JLabel("Actions: ");
		Actions.setBounds(180, 73, 89, 14);
		frame.getContentPane().add(Actions);
		//Infintary button
		JButton btnInfintary = new JButton("Infintary");
		btnInfintary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(unit == null)
				{
					unit = new WarfareUnitInfintary();
				}
				else
				{
					unit = null;
				}
				thisSide.SelectNewUnit(unit);
			}
		});
		btnInfintary.setBounds(180, 118, 89, 23);
		frame.getContentPane().add(btnInfintary);
		
		//end turn button
		End = new JButton("End");
		End.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisSide.EndTurn();
			}
		});
		End.setBounds(10, 238, 89, 23);
		frame.getContentPane().add(End);
		
		TurnCounter = new JLabel("1");
		TurnCounter.setBounds(212, 17, 43, 14);
		frame.getContentPane().add(TurnCounter);
		
		JButton btnCavalry = new JButton("Cavalry");
		btnCavalry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(unit == null)
				{
					unit = new WarfareUnitCavalry();
				}
				else
				{
					unit = null;
				}
				thisSide.SelectNewUnit(unit);
			}
		});
		btnCavalry.setBounds(180, 141, 89, 23);
		frame.getContentPane().add(btnCavalry);
		
		JButton btnBowman = new JButton("Bowman");
		btnBowman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(unit == null)
				{
					unit = new WarfareUnitBowman();
				}
				else
				{
					unit = null;
				}
				thisSide.SelectNewUnit(unit);
			}
		});
		btnBowman.setBounds(180, 165, 89, 23);
		frame.getContentPane().add(btnBowman);
	}
	public void UpdateMoneyCounter(int number) {
		this.Money.setText(Integer.toString(number));
	}
	public void UpdateTurnCounter(int number) {
		this.TurnCounter.setText(Integer.toString(number));
	}
	public void UpdateActionsCounter(int number) {
		this.Actions.setText("Actions: " + number);
	}
}

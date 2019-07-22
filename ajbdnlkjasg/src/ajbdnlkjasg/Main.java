package ajbdnlkjasg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton Snake = new JButton("Snake");
		Snake.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	Snake snake = new Snake();
		    	frame.dispose();
		    }
		});
		GridBagConstraints gbc_Snake = new GridBagConstraints();
		gbc_Snake.insets = new Insets(0, 0, 5, 0);
		gbc_Snake.anchor = GridBagConstraints.NORTH;
		gbc_Snake.fill = GridBagConstraints.HORIZONTAL;
		gbc_Snake.gridx = 0;
		gbc_Snake.gridy = 0;
		frame.getContentPane().add(Snake, gbc_Snake);
		
		JButton Minesweeper = new JButton("Minesweeper");
		Minesweeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Minesweeper minesweeper = new Minesweeper();
				frame.dispose();
			}
		});
		
		GridBagConstraints gbc_Minesweeper = new GridBagConstraints();
		gbc_Minesweeper.fill = GridBagConstraints.HORIZONTAL;
		gbc_Minesweeper.anchor = GridBagConstraints.NORTH;
		gbc_Minesweeper.insets = new Insets(0, 0, 5, 0);
		gbc_Minesweeper.gridx = 0;
		gbc_Minesweeper.gridy = 1;
		frame.getContentPane().add(Minesweeper, gbc_Minesweeper);
		
		
		
		
	}

}

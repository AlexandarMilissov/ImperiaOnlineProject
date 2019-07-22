package ajbdnlkjasg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class Minesweeper {

	private JFrame frame;
	private int height = 10;
	private int width = 10;
	private int size = 45;
	
	MinesweeperButtonField field;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Minesweeper window = new Minesweeper();
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
	public Minesweeper() {
		initialize();
		
		MineSweeperKeyListener mskl = new MineSweeperKeyListener();
		frame.addKeyListener(mskl);
		field = new MinesweeperButtonField(height,width,size,frame,mskl);
		mskl.field = this.field;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

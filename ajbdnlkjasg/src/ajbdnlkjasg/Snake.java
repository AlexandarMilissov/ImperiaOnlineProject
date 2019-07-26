package ajbdnlkjasg;

import java.awt.EventQueue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Snake {
	long delay = 500;
	Timer timer = new Timer(true);
	
	public JFrame frame;
	private int height = 10;
	private int width = 10;
	private int size = 50;
	
	SnakeField field;
	SnakeBlock snakeHead;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Snake window = new Snake();
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
	public Snake() {
		initialize();
		
		field = new SnakeField(height,width,size,frame);
		
		SnakeDirection direction = new SnakeDirection();
		snakeHead = new SnakeBlock(0,0,field,direction,frame);
		
		SnakeButtonHandler buttonHandler = new SnakeButtonHandler();
		frame.addKeyListener(buttonHandler);
		

		buttonHandler.snake = snakeHead;
		snakeHead.CreateFood(width, height);
		
		SnakeTask task = new SnakeTask();
		task.snake = snakeHead;
		timer.schedule(task , 100, delay);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		MyWindowListener windowListener = new MyWindowListener();
		windowListener.sGame = this;
		frame.addWindowListener(windowListener);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
	}
}

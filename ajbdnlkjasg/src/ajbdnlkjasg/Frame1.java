package ajbdnlkjasg;

import java.awt.EventQueue;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class Frame1 {

	private JFrame frame;
	static PrintWriter writer;
	private JTextField Name;

	/**
	 * Launch the application.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args){

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
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
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Name = new JTextField();
		Name.setBounds(0, 238, 165, 23);
		Name.setColumns(10);
		frame.getContentPane().setLayout(null);
		
		JButton Check = new JButton("Check");
		Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Check.setBounds(169, 238, 96, 23);
		frame.getContentPane().add(Check);
		frame.getContentPane().add(Name);
		
		JButton New = new JButton("New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Name.getText());
				try {
					writer = new PrintWriter((new FileOutputStream(new File("D:\\persons.txt"), true )));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				writer.append(Name.getText());
				writer.close();
			}
		});
		New.setBounds(269, 238, 89, 23);
		frame.getContentPane().add(New);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 11, 327, 210);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 50, 50);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setBounds(50, 0, 50, 50);
		panel.add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(0, 50, 50, 50);
		panel.add(label_1);
	}
}
package ajbdnlkjasg;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class WarfareWindowListener implements WindowListener
{
	Warfare game;
    public void windowOpened(WindowEvent e) {
    }

	@Override
	public void windowClosing(WindowEvent e) {
		Main main = new Main();
		game.left.tools.frame.dispose();
		game.right.tools.frame.dispose();
		game.frame.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

package ajbdnlkjasg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class MineSweeperKeyListener implements KeyListener {

	public MinesweeperButtonField field;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		field.isSettingFlags = !field.isSettingFlags;
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

package lobby;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

class PopupListener extends MouseAdapter {
	
	Lobby lobby;
	Robot robot;
	
	public PopupListener(Lobby lobby) {
		this.lobby = lobby;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void mousePressed(MouseEvent e) {
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	maybeShowPopup(e);
    }
    
    private void maybeShowPopup(MouseEvent e) {
    	JList<String> list = (JList<String>)e.getSource();
        if (list.locationToIndex(e.getPoint()) == -1) {
            list.clearSelection();
        } else if (e.isPopupTrigger() && !e.isShiftDown()) {
        	System.out.println(list.getSelectedValue());
            lobby.showPopup(e);
        }
    }
}
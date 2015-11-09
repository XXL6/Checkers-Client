package lobby;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

class PopupListener extends MouseAdapter {
	
	Lobby lobby;
	
	public PopupListener(Lobby lobby) {
		this.lobby = lobby;
	}
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            lobby.showPopup(e);
        }
    }
}
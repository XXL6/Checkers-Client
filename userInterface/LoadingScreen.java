package userInterface;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class LoadingScreen {
	
	JDialog dialog;
	
	public LoadingScreen() {

	}
	
	public void showLoginLoading() {
		dialog = new JDialog(null, "Logging in", ModalityType.DOCUMENT_MODAL);
		dialog.setSize(200, 150);
		//modalDialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);	
	}
	
	public void showGeneralError() {
		JOptionPane.showMessageDialog(null, "Tutorial not yet implemented", "tutorial", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void usernameTakenError() {
		JOptionPane.showMessageDialog(null, "Username already in use.", "Server Error", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void stop() {
		dialog.setVisible(false);
	}
	
}
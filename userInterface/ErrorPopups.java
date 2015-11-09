package userInterface;

import javax.swing.JOptionPane;

public class ErrorPopups {
	
	//String error;
	
	public ErrorPopups() {

	}
	
	public void showLoginError() {
			JOptionPane.showMessageDialog(null, "Username or IP address invalid", "Login Error", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showGeneralError() {
		JOptionPane.showMessageDialog(null, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", JOptionPane.INFORMATION_MESSAGE);
		
	}
}

package userInterface;

import javax.swing.JOptionPane;

public class ErrorPopups {
	
	String error;
	
	public ErrorPopups(String error) {
		this.error = error;
		showError(error);
	}
	
	private void showError(String error) {
		if (error.equalsIgnoreCase("username or password")) {
			JOptionPane.showMessageDialog(null, "Username or password invalid", "Login Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
}

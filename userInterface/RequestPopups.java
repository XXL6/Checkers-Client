package userInterface;

import javax.swing.JOptionPane;

public class RequestPopups {
	public RequestPopups() {
		
	}
	
	public String privateMessageRequest() {
		String message = JOptionPane.showInputDialog("Enter your message");		
		return message;		
	}
}

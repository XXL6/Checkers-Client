package loginHandler;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import userInterface.ErrorPopups;
import userInterface.LoginWindowInterface;
import serverCommunication.ServerInterface;

public class Login implements ActionListener{
	
	private LoginWindowInterface loginUI;
	private ErrorPopups error;
	private ServerInterface server;
	
	public Login(LoginWindowInterface window, ServerInterface server) {
		loginUI = window;
		this.server = server;
	}
	
	//if the username and address both fit a predetermined format
	//send the credentials to the server
	public void login(String username, String address) {
		LoginInfoParser parser = new LoginInfoParser(username, address);
		//parser.setData(username, address);
		if (parser.usernameValid() && parser.addressValid()) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						server.connectToServer(address, username);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				});		
			
		} else {
			error = new ErrorPopups();
			error.showLoginError();
		}
	}

	@Override
	public void actionPerformed(ActionEvent buttonPressed) {
		if (buttonPressed.getActionCommand().equalsIgnoreCase("login")) {
			login(loginUI.getUsername(), loginUI.getAddress());		
		} else if (buttonPressed.getActionCommand().equalsIgnoreCase("tutorial")) {
			error = new ErrorPopups();
			error.showGeneralError();
		}
	}
}

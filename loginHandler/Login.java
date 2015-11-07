package loginHandler;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import userInterface.ErrorPopups;
import userInterface.LoginWindowInterface;
import serverCommunication.ServerInterface;

public class Login implements ActionListener{
	private LoginInfoParser parser;
	private LoginInfoRetriever retriever;
	private LoginWindowInterface loginUI;
	private ErrorPopups error;
	private ServerInterface server;
	
	public Login(LoginWindowInterface window, ServerInterface server) {
		parser = new LoginInfoParser();
		loginUI = window;
		this.server = server;
	}
	
	public void parseData(String username, String address) {
		parser.setData(username, address);
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
		parseData(loginUI.getUsername(), loginUI.getAddress());
		
	}
}

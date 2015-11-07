package setup;

import userInterface.LoginWindowInterface;
import userInterface.LoginWindow;

import java.awt.EventQueue;

import serverCommunication.ServerInterface;
import loginHandler.Login;

public class LoginInitializer {
	
	private LoginWindowInterface loginWindow;
	private Login login;
	ServerInterface serverInterface;
	
	public LoginInitializer(ServerInterface serverInterface) {
		this.serverInterface = serverInterface;
	}//end WindowInitializer constructor
	
	public void startLogin() {
		loginWindow = new LoginWindow();
		login = new Login(loginWindow, serverInterface);
		loginWindow.assignLoginButton(login);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				loginWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});		
		
	}//end startLogin
	
	public void stopLogin() {
		loginWindow.display(false);
		loginWindow = null;
		login = null;
	}
	
}//end WindowInitializer

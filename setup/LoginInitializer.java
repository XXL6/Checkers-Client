package setup;

import userInterface.LoginWindowInterface;
import userInterface.LoginWindow;

import java.awt.EventQueue;

import serverCommunication.ServerInterface;
import loginHandler.Login;

public class LoginInitializer {
	
	private LoginWindowInterface loginWindow;
	private Login login;
	
	public LoginInitializer() {
		
	}//end WindowInitializer constructor
	
	public void startLogin(ServerInterface serverInterface) {
		loginWindow = new LoginWindow();
		login = new Login(loginWindow, serverInterface);
		loginWindow.assignLoginButton(login);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				loginWindow.display();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});		
		
	}//end startLogin
	
}//end WindowInitializer

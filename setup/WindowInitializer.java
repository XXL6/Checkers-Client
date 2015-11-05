package setup;

import userInterface.LoginWindow;

import java.awt.EventQueue;

import Interfaces.ServerInterface;
import loginHandler.Login;

public class WindowInitializer {
	
	private LoginWindow loginWindow;
	private Login login;
	
	public WindowInitializer() {
		
	}//end WindowInitializer constructor
	
	public void startLogin(ServerInterface serverInterface) {
		loginWindow = new LoginWindow();
		login = new Login(loginWindow, serverInterface);
		loginWindow.assignLoginButton(login);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				loginWindow.Display();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});		
		
	}//end startLogin
	
}//end WindowInitializer

package setup;

import userInterface.LoginWindowInterface;
import userInterface.LoginWindow;

import java.awt.EventQueue;

import serverCommunication.ServerInterface;
import loginHandler.Login;

public class LoginInitializer extends Thread{
	
	private LoginWindowInterface loginWindow;
	private Login login;
	private ServerInterface serverInterface;
	
	public LoginInitializer(ServerInterface serverInterface) {
		this.serverInterface = serverInterface;
	}//end WindowInitializer constructor
	
	public void run() {
		loginWindow = new LoginWindow();
		login = new Login(loginWindow, serverInterface);
		loginWindow.assignLoginButton(login);
		loginWindow.assignTutorialButton(login);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				loginWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
		synchronized(this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stopLogin();
		
	}//end startLogin
	
	public void stopLogin() {
		loginWindow.display(false);
		loginWindow = null;
		login = null;
	}
	
}//end WindowInitializer

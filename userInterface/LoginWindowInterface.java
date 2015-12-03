package userInterface;

import java.awt.event.ActionListener;

public interface LoginWindowInterface {
	//public LoginWindow();
	
	public String getUsername();
	
	public String getAddress();
	
	public void clearUsername();
	
	public void clearAddress();
	
	public void assignLoginButton(ActionListener listener);
	
	public void display(boolean display);
}

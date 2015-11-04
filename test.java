import userInterface.LoginWindow;

public class test {
	public static void main(String[] args) {
		try {
		LoginWindow window = new LoginWindow();
		window.Display();
	} catch (Exception e) {
		e.printStackTrace();
		}
	}
}

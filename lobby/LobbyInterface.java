package lobby;

public interface LobbyInterface {
	
	public void displayMessage(String username, String message, boolean isPrivate);
	
	public void displayGeneralMessage(String message);
	
	public void sendMessage(String message);
	
	public void refreshUsers(String[] username);
	
	public void addUser(String username);
	
	public void removeUser(String username);
	
	public void sendPrivateMessage(String username, String message);
	
	public void startLobby();
}

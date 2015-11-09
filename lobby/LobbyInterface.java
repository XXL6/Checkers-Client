package lobby;

public interface LobbyInterface {
	
	public void displayMessage(String username, String message, boolean isPrivate);
	
	public void displayGeneralMessage(String message);
	
	public void clearChat();
	
	public void sendMessage();
	
	public void sendPrivateMessage(String username);
	
	public void refreshUsers(String[] username, String clientUsername);
	
	public void addUser(String username);
	
	public void removeUser(String username);
	
	public void startLobby();
	
	public void disconnect();

}

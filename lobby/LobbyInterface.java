package lobby;

import java.awt.event.MouseEvent;

public interface LobbyInterface {

	void startLobby();

	void displayMessage(String username, String message, boolean isPrivate);

	void displayGeneralMessage(String message);

	void clearChat();

	void refreshUsers(String[] usernames, String clientUsername);

	void addUser(String username);

	void removeUser(String username);

	void refreshTables(int[] tables);

	void incomingTableInfo(int tableID, String black, String red);

	void sendMessage();

	void sendPrivateMessage(String username);

	void requestPrivateMessage();

	void disconnect();

	void showPopup(MouseEvent mouseEvent);
	
	void createTable();

	void setUsername(String username);

	void joinTable();

	void toggleWindow();

	void spectate();

	void refreshLocalTables();

	void tutorial();
}
package lobby;
import userInterface.LobbyWindow;

import java.awt.EventQueue;

import serverCommunication.ServerInterface;

public class Lobby implements LobbyInterface{
	
	LobbyWindow lobbyWindow;
	ServerInterface serverInterface;
	ChatSender sender;
	Disconnector disconnector;
	
	public Lobby(ServerInterface serverInterface) {
		lobbyWindow = new LobbyWindow();
		this.serverInterface = serverInterface;
		sender = new ChatSender(serverInterface, lobbyWindow);
		lobbyWindow.setChatListener(sender);
		disconnector = new Disconnector(serverInterface);
		lobbyWindow.setDisconnectListener(disconnector);
	}

	public void startLobby() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				lobbyWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}
	@Override
	public void displayMessage(String username, String message, boolean isPrivate) {
		if (isPrivate) {
			lobbyWindow.insertText("PM FROM " + username + ": " + message);
		} else {
			lobbyWindow.insertText(username + ": " + message);
		}
	}
	
	public void displayGeneralMessage(String message) {
		lobbyWindow.insertText(message);
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshUsers(String[] usernames, String clientUsername) {
		lobbyWindow.clearUsers();
		for (String s: usernames) {
			if (s.equalsIgnoreCase(clientUsername)) {
				lobbyWindow.insertUser("[You]  " + s, true);
			}
			else if (!(lobbyWindow.containsUser(s))){
				lobbyWindow.insertUser(s, false);
			}
		}
	}
	

	
	public void addUser(String username) {
		if (!(lobbyWindow.containsUser(username))) {
			lobbyWindow.insertUser(username, false);
		}
	}

	@Override
	public void removeUser(String username) {
		lobbyWindow.removeUser(username);
		
	}
	public void refreshTables(int[] tables) {
		String stringToAdd;
		lobbyWindow.clearTables();
		for (int i: tables) {
			stringToAdd = "Table " + i + ": " + "\n";
				lobbyWindow.insertTable(stringToAdd);
		}
	}
	@Override
	public void sendPrivateMessage(String username, String message) {
		// TODO Auto-generated method stub
		
	}
}

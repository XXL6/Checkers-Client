package game;

import java.awt.EventQueue;

import chatHandler.ChatManager;
import chatHandler.Message;
import serverCommunication.ServerInterface;
import userInterface.GameWindow;

public class Game {
	
	ServerInterface serverInterface;
	ChatManager chatManager;
	GameWindow gameWindow;
	String clientName;
	
	public Game(ServerInterface serverInterface, ChatManager chatManager, GameWindow gameWindow, String clientUsername) {
		this.serverInterface = serverInterface;
		this.chatManager = chatManager;
		this.gameWindow = gameWindow;
		this.clientName = clientUsername;
	}

	public void startLobby() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				gameWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}
	
	public void sendMessage() {
		String toSend;
		toSend = gameWindow.retrieveInputText();
		if (!(toSend.equals(""))) {
			Message message = new Message(toSend);
			chatManager.send(message);
			displayClientMessage(message);
		}
	}

	public void clearChat() {
		gameWindow.clearText();
		
	}

	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
	public void displayClientMessage(Message message) {
		
		gameWindow.insertText(clientName + ": " + message.getMessage());
	}
	
	public void displayMessage(String username, String message, boolean isPrivate) {
		if (isPrivate) {
			gameWindow.insertText("PM FROM " + username + ": " + message);
		} else if (!username.equals(clientName)){
			gameWindow.insertText(username + ": " + message);
		}
	}

	public void setUsername(String username) {
		clientName = username;
		
	}
	
	public void refreshUsers(String[] usernames, String clientUsername) {
		gameWindow.clearUsers();
		for (String s: usernames) {
			if (s.equals(clientUsername)) {
				gameWindow.insertUser("[You]  " + s, true);
			}
			else if (!(gameWindow.containsUser(s))){
				gameWindow.insertUser(s, false);
			}
		}
	}
	
	public void addUser(String username) {
		if (!(gameWindow.containsUser(username))) {
			gameWindow.insertUser(username, false);
		}
	}

	public void removeUser(String username) {
		gameWindow.removeUser(username);
		
	}
	
	public void leaveTable() {
		serverInterface.leaveTable(clientName);
	}
}

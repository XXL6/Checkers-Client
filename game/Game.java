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
	int tableID;
	String opponent = null;
	String clientColor = null;
	String opponentColor = null;
	
	public Game(ServerInterface serverInterface, ChatManager chatManager, GameWindow gameWindow, 
			String clientUsername, int tableID) {
		this.serverInterface = serverInterface;
		this.chatManager = chatManager;
		this.gameWindow = gameWindow;
		setUsername(clientUsername);
		this.tableID = tableID;
	}

	public void startGame() {
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
		serverInterface.disconnect(true);
		
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
		gameWindow.insertUser("[You]  " + clientName, true);
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
	
	public void addOpponent(String username) {
		if (!(gameWindow.containsUser(username))) {
			gameWindow.insertUser(username, false);
			opponent = username;
		}
	}

	public void removeUser(String username) {
		gameWindow.removeUser(username);
		
	}
	
	public void removeOpponent() {
		gameWindow.removeUser(opponent);
		opponent = null;
	}
	public void leaveTable() {
		System.out.println("leaving table");
		serverInterface.leaveTable(clientName);
	}
	
	public int getID() {
		return tableID;
	}
	
	public void setColor(String color) {
		if (color.equalsIgnoreCase("black")) {
			clientColor = "black";
			opponentColor = "red";
		} else {
			clientColor = "red";
			opponentColor = "black";
		}
	}
}

package game;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

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
	GeneralButtonListener listener = null;
	CheckerBoard board;

	boolean ready = false;
	

	Sequencer sequencer = null;

	public Game(ServerInterface serverInterface, ChatManager chatManager, 
			String clientUsername, int tableID) {
		board = new CheckerBoard(serverInterface, this);
		gameWindow = new GameWindow(board);
		this.serverInterface = serverInterface;
		this.chatManager = chatManager;
		//this.gameWindow = gameWindow;
		setUsername(clientUsername);
		this.tableID = tableID;
	}

	public void setupGame() {
		listener = new GeneralButtonListener(this);
		gameWindow.setButtonListeners(listener);
		//gameWindow.setClientColor(clientColor);
		//gameWindow.setOpponentColor(opponentColor);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				gameWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
		try{
			URL tempURL = Game.class.getResource("/"+"resources/green.mid");
			 sequencer = MidiSystem.getSequencer();
			 sequencer.open();
			 
			 sequencer.setSequence(MidiSystem.getSequence(tempURL));
			 sequencer.start();
			 sequencer.setLoopCount(100);
			}catch(Exception e){
				
			}
	}
	public void startGame() {	
		gameWindow.setClientColor(clientColor);
		gameWindow.setOpponentColor(opponentColor);
		board.setColor(clientColor);
		board.arrangeCheckers();
		
	
			 
	}
	public void stopGame() {
		gameWindow.display(false);
		gameWindow = null;
		listener = null;
		
		sequencer.stop();
	}
	
	public void sendMessage() {
		String toSend;
		toSend = gameWindow.retrieveInputText();
		if (!(toSend.equals(""))) {
			Message message = new Message(toSend, opponent);
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
	
	public void readyUp() {
		if (ready == false){
		serverInterface.playerReady(clientName);
		}
		ready = true;
	}
	public void displayClientMessage(Message message) {
		
		gameWindow.insertText(clientName + ": " + message.getMessage());
	}
	
	public void displayMessage(String username, String message, boolean isPrivate) {
//		if (isPrivate) {
//			gameWindow.insertText("PM FROM " + username + ": " + message);
//		} else if (!username.equals(clientName)){
			gameWindow.insertText(username + ": " + message);
//		}
	}

	public void setUsername(String username) {
		clientName = username;
		board.setUsername(username);
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
	
	public void refreshBoardState(byte[][] boardState) {
		board.refreshBoard(boardState);
	}
	public void addOpponent(String username) {
		if (!(gameWindow.containsUser(username))) {
			gameWindow.insertUser(username, false);
			opponent = username;
		}
		ready = false;
	}

	public void removeUser(String username) {
		gameWindow.removeUser(username);
		
	}
	
	public void removeOpponent() {
		ready = false;
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
	
	public void moveChecker(int fRow, int fCol, int tRow, int tCol) {
		
	}
	
	public void undoMove() {
		board.undoMove();
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

	public void enableBoard(boolean b) {
		if (b) {
			board.enable();
		} else {
			board.disable();
		}
	}
	public void setYourTurn(boolean setYourTurn){
		
		gameWindow.setYourTurn(setYourTurn);
	
	}
	

}

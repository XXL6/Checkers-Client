package observatory;

import java.awt.EventQueue;

import game.CheckerBoard;
import serverCommunication.ServerInterface;
import userInterface.ObserverWindow;
import userInterface.ObserverWindow2;

public class Observer extends Thread{
	
	private CheckerBoard board;
	private ObserverWindow2 window;
	private int tableID;
	private String clientName;
	private ServerInterface serverInterface;
	
	public Observer(int tableID, String clientName, ServerInterface serverInterface) {
		board = new CheckerBoard(null, null);
		board.setColor("red");
		window = new ObserverWindow2(board);
		GeneralButtonListener listener = new GeneralButtonListener(this);
		window.setButtonListeners(listener);
		this.tableID = tableID;
		this.serverInterface = serverInterface;
		this.clientName = clientName;
	}
	
	public void run() {
		//board.arrangeCheckers();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.display(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			});
	}
	
	public void updateBoard(byte[][] boardState) {
		board.refreshBoard(boardState);
	}
	
	public int getTableId() {
		return tableID;
	}
	
	public void setUsers(String blackUser, String redUser) {
		window.setPlayers(blackUser, redUser);
		window.showPlayers(true);
	}

	public void stopObserving() {
		serverInterface.stopObserving(clientName, tableID);
		window.display(false);
	}
}

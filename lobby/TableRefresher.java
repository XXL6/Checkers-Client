package lobby;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;

public class TableRefresher extends Thread{
	
	int[] tableID;
	LobbyWindow lobbyUI;
	ServerInterface serverInterface;
	TableManager manager;
	
	public TableRefresher(int[] tableID, LobbyWindow window, ServerInterface serverInterface, TableManager manager) {
		this.tableID = tableID;
		this.lobbyUI = window;
		this.serverInterface = serverInterface;
		this.manager = manager;
	}
	
	public void run() {
		GameTable table = null;
		lobbyUI.clearTables();
		for (int i: tableID) {
			GameTable tempTable = new GameTable(i);
			lobbyUI.insertTable(tempTable);
			serverInterface.getTblStatus("somebody", i);
		}
		for (int i = 0; i < tableID.length; i++) {

			synchronized(this) {
				try {
					this.wait();
					table = manager.getTable();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				if (table.getTableID() == tableID[i]) {
					manager.insertTable(table);
				} else {
					i--;
					manager.insertTable(table);
				}
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
}

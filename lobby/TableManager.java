package lobby;

import java.util.ArrayList;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;

public class TableManager extends Thread{

	LobbyWindow lobbyUI;
	ServerInterface serverInterface;
	TableRefresher tableRefresher;
	GameTable table;
//	ArrayList<Integer> localTableList = new ArrayList<Integer>();
	
	public TableManager(LobbyWindow lobbyUI, ServerInterface serverInterface) {
		this.lobbyUI = lobbyUI;
		this.serverInterface = serverInterface;
	}
	
	public void insertTable(int tableID) {
		
	}
	
	public void refreshTables(int[] tableID) {
		tableRefresher = new TableRefresher(tableID, lobbyUI, serverInterface, this);
		tableRefresher.start();
//		for (int i: tableID) {
//			localTableList.add(i);
//		}
	}
	
	public void updateNextTable(int tableID, String black, String red) {
		if (tableRefresher.isAlive()) {
			synchronized(tableRefresher) {
				table = new GameTable();
				table.setTableInfo(tableID, black, red);
				tableRefresher.notify();
			}
		} else {
			table = new GameTable();
			table.setTableInfo(tableID, black, red);
			insertTable(table);
		}
	}
	
	public GameTable getTable() {
		return table;
	}
	
	public void removeTable(int tableID) {
		lobbyUI.removeTable(tableID);
	}
	
	public void insertTable(GameTable table) {
		lobbyUI.insertTable(table);
	}
}

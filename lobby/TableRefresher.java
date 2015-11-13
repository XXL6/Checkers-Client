package lobby;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;

public class TableRefresher extends Thread{
	
	int[] tableID;
	TableList list;
	ServerInterface serverInterface;
	TableManager manager;
	boolean tableComplete = false;
	
	public TableRefresher(int[] tableID, TableList list, ServerInterface serverInterface, TableManager manager) {
		this.tableID = tableID;
		this.list = list;
		this.serverInterface = serverInterface;
		this.manager = manager;
	}
	
	public void run() {
		GameTable table = null;
		list.clear();
		while (!tableComplete) {
			for (int i: tableID) {
				GameTable tempTable = new GameTable(i);
				list.insert(tempTable);
				serverInterface.getTblStatus("somebody", i);
			}
//			try {
//				Thread.sleep(7000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			for (int i = 0; i < tableID.length; i++) {
	
				synchronized(this) {
					try {
						this.wait(5000);
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
			tableComplete = !manager.areAnyTablesLoading();
		}
		System.out.println("Refresh done");
		return;
	}
	
}

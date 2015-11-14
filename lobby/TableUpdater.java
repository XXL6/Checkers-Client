package lobby;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;
import userInterface.TableList;

public class TableUpdater extends Thread{
	
	int[] tableID;
	TableList list;
	ServerInterface serverInterface;
	TableManager manager;
	boolean tableComplete = false;
	
	public TableUpdater(int[] tableID, TableList list, ServerInterface serverInterface, TableManager manager) {
		this.tableID = tableID;
		this.list = list;
		this.serverInterface = serverInterface;
		this.manager = manager;
	}
	
	public void run() {
		
		GameTable table = null;
		list.clear();
		while (true) {
			for (int i: tableID) {
				GameTable tempTable = new GameTable(i);
				list.insert(tempTable);
				serverInterface.getTblStatus("somebody", i);
			}
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized(this) {
				try {
					this.wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				
			}
			tableComplete = !manager.areAnyTablesLoading();
		}
		//System.out.println("Refresh done");
		//return;
	}
	
}

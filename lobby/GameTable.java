package lobby;

public class GameTable {
	
	private int tableID;
	private String black;
	private String red;
	
	public GameTable() {
		
	}
	
	public void setTableInfo(int tableID, String black, String red) {
		this.tableID = tableID;
		this.black = black;
		this.red = red;
	}
	
	public int getTableID() {
		return tableID;
	}
	
	public String getBlackSeat() {
		return black;
	}
	
	public String getRedSeat() {
		return red;
	}
	
	public String toString() {
		return "Table " + tableID + ": " + "Black: [" + black + "] Red: [" + red + "]\n";
	}
}

package userInterface;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import lobby.GameTable;

public class TableList extends JList<GameTable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<GameTable> MODEL = new DefaultListModel<GameTable>();
	
	public TableList() {
		super.setModel(MODEL);
	}
	
	public TableList(DefaultListModel<GameTable> model) {
		super(model);
	}
	
	public void insert(GameTable table) {
		boolean found = false;
		for (int i = 0; i < MODEL.size(); i++) {
			if (MODEL.getElementAt(i).getTableID() == table.getTableID()) {
				MODEL.setElementAt(table, i);
				found = true;
				break;
			} else {
				found = false;
			}
		}
		if (!found) {
			MODEL.addElement(table);
		}
	}
	
	public boolean tablesLoading() {
		for (int i = 0; i < MODEL.size(); i++) {
			if (MODEL.getElementAt(i).isLoading()) {
				return true;
			}
		}
		return false;
	}
	
	public void remove(int tableIdentifier) {
		int toBeRemoved;
		for(int i = 0; i < MODEL.getSize(); i++){
		     toBeRemoved =  MODEL.getElementAt(i).getTableID(); 
		     if (toBeRemoved == tableIdentifier) {
		    	 MODEL.remove(i);
		     }
		}
	}
	
	public void clear() {
		MODEL.clear();
	}
}

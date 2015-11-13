package userInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import lobby.GameTable;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.InputMap;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;

public class LobbyWindow implements LobbyWindowInterface{

	private JFrame frmCheckers;
	private JTextField chatTxtField;
	private JTextArea chatTxtArea;
	private JList<String> usrList;
	private DefaultListModel<String> usrListModel;
	private DefaultListModel<GameTable> tableListModel;
	private JButton btnDisconnect;
	private JButton btnSend;
	private JButton btnCreateTable;
	private JButton btnTutorial;
	private JList tableList;
	private JScrollPane chatScrollPane;
	private JButton btnClearChat;
	private JPopupMenu popupMenu;

	/**
	 * Create the application.
	 */
	public LobbyWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void display(boolean display) {
		frmCheckers.setVisible(display);
	}
	
	private void initialize() {
		frmCheckers = new JFrame();
		frmCheckers.setTitle("Checkers");
		usrListModel = new DefaultListModel<String>();
		tableListModel = new DefaultListModel<GameTable>();
		frmCheckers.setBounds(100, 100, 750, 700);
		frmCheckers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chatTxtArea = new JTextArea();
		chatTxtArea.setEditable(false);
		chatTxtArea.setLineWrap(true);
		chatTxtArea.setRows(2);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		chatTxtArea.setBorder(border);
		chatScrollPane = new JScrollPane (chatTxtArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		usrList = new JList<String>(usrListModel) {
				@Override
                public int locationToIndex(Point location) {
                    int index = super.locationToIndex(location);
                    if (index != -1 && !getCellBounds(index, index).contains(location)) {
                        return -1;
                    }
                    else {
                        return index;
                    }
                }};
		usrList.setBorder(border);
		JScrollPane userScrollPane = new JScrollPane (usrList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		chatTxtField = new JTextField();
		chatTxtField.setColumns(10);
		chatTxtField.setBorder(border);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnCreateTable = new JButton("Create Table");
		btnCreateTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tableList = new JList<GameTable>(tableListModel);
		tableList.setBorder(border);
		JScrollPane tableScrollPane = new JScrollPane (tableList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);			
		
		JLabel lblTablesInServer = new JLabel("Tables in server");
		lblTablesInServer.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnTutorial = new JButton("Tutorial");
		btnTutorial.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnClearChat = new JButton("Clear Chat");
		btnClearChat.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		GroupLayout groupLayout = new GroupLayout(frmCheckers.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tableScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chatTxtField, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userScrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnClearChat, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
							.addComponent(btnTutorial, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCreateTable, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDisconnect, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTablesInServer))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTablesInServer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisconnect)
						.addComponent(btnCreateTable)
						.addComponent(btnTutorial)
						.addComponent(btnClearChat))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chatTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(userScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		frmCheckers.getRootPane().setDefaultButton(btnSend);
		frmCheckers.getContentPane().setLayout(groupLayout);
//		usrList.addMouseListener( new MouseAdapter()
//		{
//		    public void mousePressed(MouseEvent e)
//		    {
//		        if ( SwingUtilities.isRightMouseButton(e) )
//		        {
//		            JList list = (JList)e.getSource();
//		            int row = list.locationToIndex(e.getPoint());
//		            list.setSelectedIndex(row);
//		        }
//		    }
//
//		});
	}
	
	public void setChatListener(ActionListener listener) {
		btnSend.addActionListener(listener);
	}
	
	public void setDisconnectListener(ActionListener listener) {
		btnDisconnect.addActionListener(listener);
	}
	
	public void setChatClearListener(ActionListener listener) {
		btnClearChat.addActionListener(listener);
	}
	
	public void setCreateTableListener(ActionListener listener) {
		btnCreateTable.addActionListener(listener);
	}
	public void insertText(String text) {
		chatTxtArea.append(text + "\n");
		chatTxtArea.validate();
		chatScrollPane.getVerticalScrollBar().setValue(chatScrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public void clearText() {
		chatTxtArea.setText("");
		chatTxtArea.validate();
		
	}
	public String retrieveInputText() {
		String returnString = chatTxtField.getText();
		chatTxtField.setText("");
		return returnString;
	}
	
	public void insertUser(String username, boolean client) {
		if (client) {
			usrListModel.insertElementAt(username, 0);	
		} else {
			usrListModel.addElement(username);
		}
	}
	
	public void removeUser(String username) {
		String removeString;
		for(int i = 0; i < usrListModel.getSize(); i++){
		     removeString =  usrListModel.getElementAt(i).toString(); 
		     if (removeString.equalsIgnoreCase(username)) {
		    	 usrListModel.remove(i);
		     }
		}
	}
	
	public boolean containsUser(String username) {
		for(int i = 0; i < usrListModel.getSize(); i++){
		    if (usrListModel.getElementAt(i).toString().equalsIgnoreCase(username)) {
		    	return true;
		    }
		}
		return false;
	}
	
	public void clearUsers() {
		usrListModel.clear();
	}
	
	public void insertTable(GameTable table) {
		int tableLocation;
		boolean found = false;
		for (int i = 0; i < tableListModel.size(); i++) {
			if (tableListModel.getElementAt(i).getTableID() == table.getTableID()) {
				tableListModel.setElementAt(table, i);
				found = true;
				break;
			} else {
				found = false;
			}
		}
		if (!found) {
			tableListModel.addElement(table);
		}
	}
	
	public void updateTable(GameTable table) {
		
	}
	public void removeTable(GameTable table) {
		tableListModel.removeElement(table);
	}
	
	public boolean tablesLoading() {
		for (int i = 0; i < tableListModel.size(); i++) {
			if (tableListModel.getElementAt(i).isLoading()) {
				return true;
			}
		}
		return false;
	}
	public void removeTable(int tableIdentifier) {
		int toBeRemoved;
		for(int i = 0; i < tableListModel.getSize(); i++){
		     toBeRemoved =  tableListModel.getElementAt(i).getTableID(); 
		     if (toBeRemoved == tableIdentifier) {
		    	 tableListModel.remove(i);
		     }
		}
	}
	
	public void clearTables() {
		tableListModel.clear();
	}
	
	public void addPopupMenu(ActionListener actionListener, MouseListener mouseListener) {
	    popupMenu = new JPopupMenu();
	    JMenuItem menuItem = new JMenuItem("Send a PM");
	    menuItem.addActionListener(actionListener);
	    popupMenu.add(menuItem);
	 
	    usrList.addMouseListener(mouseListener);
	}
	
	public void displayPopupMenu(MouseEvent mouseEvent) {
		popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	}
	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}
	
	public String getSelectedUser() {
		return usrList.getSelectedValue();
	}
}

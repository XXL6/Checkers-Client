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
	private JTextField chatInputField;
	private ChatArea chatArea;
	private UserList userList;
	private DefaultListModel<String> usrListModel;
	private DefaultListModel<GameTable> tableListModel;
	private JButton btnDisconnect;
	private JButton btnSend;
	private JButton btnCreateTable;
	private JButton btnTutorial;
	private JButton btnJoinTable;
	private JButton btnSpectate;
	private TableList tableList;
	private JScrollPane chatScrollPane;
	private JButton btnClearChat;
	private JPopupMenu popupMenu;
	private JButton btnRefreshTables;

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
	
	public boolean isEnabled() {
		return frmCheckers.isEnabled();
	}
	
	public void toggleWindow(boolean display) {
			frmCheckers.setEnabled(!display);
			frmCheckers.setVisible(!display);
		}
	private void initialize() {
		frmCheckers = new JFrame();
		frmCheckers.setTitle("Checkers");
		usrListModel = new DefaultListModel<String>();
		tableListModel = new DefaultListModel<GameTable>();
		frmCheckers.setBounds(100, 100, 750, 810);
		frmCheckers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatArea = new ChatArea();
		userList = new UserList();
		tableList = new TableList();
		//chatArea = new ChatArea();
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setRows(2);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		//chatArea.setBorder(border);
		chatScrollPane = new JScrollPane (chatArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScrollPane.setBorder(border);
		//userList = new UserList(usrListModel);
		//userList.setModel(usrListModel);
		//userList.setBorder(border);
		JScrollPane userScrollPane = new JScrollPane (userList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		userScrollPane.setBorder(border);
		chatInputField = new JTextField();
		chatInputField.setColumns(10);
		chatInputField.setBorder(border);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnCreateTable = new JButton("Create Table");
		btnCreateTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		//tableList = new TableList(tableListModel);
		//tableList.setModel(tableListModel);
		//tableList.setBorder(border);
		JScrollPane tableScrollPane = new JScrollPane (tableList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);			
		tableScrollPane.setBorder(border);
		JLabel lblTablesInServer = new JLabel("Tables in server:");
		lblTablesInServer.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnTutorial = new JButton("Tutorial");
		btnTutorial.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnClearChat = new JButton("Clear Chat");
		btnClearChat.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnJoinTable = new JButton("Join Table");
		btnJoinTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnSpectate = new JButton("Spectate");
		btnSpectate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSpectate.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnRefreshTables = new JButton("Refresh");
		
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
									.addComponent(chatInputField, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userScrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnClearChat, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
							.addComponent(btnSpectate, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnJoinTable, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTutorial, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreateTable, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDisconnect, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTablesInServer)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRefreshTables)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTablesInServer)
						.addComponent(btnRefreshTables))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisconnect)
						.addComponent(btnClearChat)
						.addComponent(btnCreateTable)
						.addComponent(btnTutorial)
						.addComponent(btnJoinTable)
						.addComponent(btnSpectate))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chatInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(userScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		frmCheckers.getRootPane().setDefaultButton(btnSend);
		frmCheckers.getContentPane().setLayout(groupLayout);
	}
	
	public void setButtonListeners(ActionListener listener) {
		btnSend.addActionListener(listener);
		btnDisconnect.addActionListener(listener);
		btnClearChat.addActionListener(listener);
		btnCreateTable.addActionListener(listener);
		btnJoinTable.addActionListener(listener);
		btnSpectate.addActionListener(listener);
		btnRefreshTables.addActionListener(listener);
		btnTutorial.addActionListener(listener);
	}

	public void insertText(String text) {
		chatArea.insert(text);
		//chatArea.validate();
		//chatScrollPane.getVerticalScrollBar().setValue(chatScrollPane.getVerticalScrollBar().getMaximum());
		chatArea.setCaretPosition(chatArea.getDocument().getLength());
	}
	
	public void clearText() {
		chatArea.clear();
		
	}
	public String retrieveInputText() {
		String returnString = chatInputField.getText();
		chatInputField.setText("");
		return returnString;
	}
	
//	public void insertUser(String username, boolean client) {
//		if (client) {
//			usrListModel.insertElementAt(username, 0);	
//		} else {
//			usrListModel.addElement(username);
//		}
//	}
	
	public void insertUser(String username, boolean client) {
		userList.insertUser(username, client);
	}
	
	public void removeUser(String username) {
		userList.removeUser(username);
	}
	
	public boolean containsUser(String username) {
		return userList.containsUser(username);
	}
	
	public void clearUsers() {
		userList.clearUsers();
	}
	
	public void insertTable(GameTable table) {
		tableList.insert(table);
	}
	
	
	public boolean tablesLoading() {
		return tableList.tablesLoading();
	}
	
	public void removeTable(int tableIdentifier) {
		tableList.remove(tableIdentifier);
	}
	
	public void clearTables() {
		tableList.clear();
	}
	
	public TableList getTableList() {
		return tableList;
	}
	public void addPopupMenu(ActionListener actionListener, MouseListener mouseListener) {
	    popupMenu = new JPopupMenu();
	    JMenuItem menuItem = new JMenuItem("Send a PM");
	    menuItem.addActionListener(actionListener);
	    popupMenu.add(menuItem);
	 
	    userList.addMouseListener(mouseListener);
	}
	
	public void displayPopupMenu(MouseEvent mouseEvent) {
		popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	}
	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}
	
	public String getSelectedUser() {
		return userList.getSelectedValue();
	}
	
	public int getSelectedTable() {
		return tableList.getSelectedValue().getTableID();
	}
}

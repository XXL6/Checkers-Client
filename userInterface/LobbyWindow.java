package userInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class LobbyWindow implements LobbyWindowInterface{

	private JFrame frame;
	private JTextField chatTxtField;
	private JTextArea chatTxtArea;
	private JList<String> usrList;
	private DefaultListModel<String> listModel;
	private JButton disconnectButton;
	private JButton sendButton;
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
		frame.setVisible(display);
	}
	
	private void initialize() {
		frame = new JFrame();
		listModel = new DefaultListModel<String>();
		frame.setBounds(100, 100, 750, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chatTxtArea = new JTextArea();
		chatTxtArea.setEditable(false);
		chatTxtArea.setLineWrap(true);
		chatTxtArea.setRows(2);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		chatTxtArea.setBorder(border);
		JScrollPane chatScrollPane = new JScrollPane (chatTxtArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		usrList = new JList<String>(listModel);
		usrList.setBorder(border);
		JScrollPane userScrollPane = new JScrollPane (usrList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		chatTxtField = new JTextField();
		chatTxtField.setColumns(10);
		chatTxtField.setBorder(border);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setFont(new Font("Tahoma", Font.BOLD, 40));
		
		sendButton = new JButton("Send");
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(chatScrollPane)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(chatTxtField, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userScrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(disconnectButton, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
							.addGap(72))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addComponent(disconnectButton, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chatTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(userScrollPane, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
					.addGap(21))
		);
		frame.getContentPane().setLayout(groupLayout);
		chatScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
	}
	
	public void setChatListener(ActionListener listener) {
		chatTxtField.addActionListener(listener);
		sendButton.addActionListener(listener);
	}
	
	public void setDisconnectListener(ActionListener listener) {
		disconnectButton.addActionListener(listener);
	}
	public void insertText(String text) {
		chatTxtArea.append(text + "\n");
	}
	
	public String retrieveInputText() {
		String returnString = chatTxtField.getText();
		chatTxtField.setText("");
		return returnString;
	}
	
	public void insertUser(String username, boolean client) {
		if (client) {
			listModel.insertElementAt(username, 0);	
		} else {
			listModel.addElement(username);
		}
	}
	
	public void removeUser(String username) {
		String removeString;
		for(int i = 0; i < listModel.getSize(); i++){
		     removeString =  listModel.getElementAt(i).toString(); 
		     if (removeString.equalsIgnoreCase(username)) {
		    	 listModel.remove(i);
		     }
		}
	}
	
	public boolean containsUser(String username) {
		for(int i = 0; i < listModel.getSize(); i++){
		    if (listModel.getElementAt(i).toString().equalsIgnoreCase(username)) {
		    	return true;
		    }
		}
		return false;
	}
	public void clearUsers() {
		listModel.clear();
	}
}

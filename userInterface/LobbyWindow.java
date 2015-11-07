package userInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

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
import javax.swing.JTextField;

public class LobbyWindow implements LobbyWindowInterface{

	private JFrame frame;
	private JTextField chatTxtField;
	private JTextArea chatTxtArea;
	private JList usrList;
	private DefaultListModel<String> listModel;
	
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
		listModel = new DefaultListModel();
		frame.setBounds(100, 100, 750, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chatTxtArea = new JTextArea();
		chatTxtArea.setLineWrap(true);
		chatTxtArea.setRows(2);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		chatTxtArea.setBorder(border);
		usrList = new JList(listModel);
		usrList.setBorder(border);
		
		chatTxtField = new JTextField();
		chatTxtField.setColumns(10);
		chatTxtField.setBorder(border);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(chatTxtArea, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
						.addComponent(chatTxtField, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usrList, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(413)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(chatTxtArea, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chatTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(usrList, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void setChatListener(ActionListener listener) {
		chatTxtField.addActionListener(listener);
	}
	
	public void insertText(String text) {
		chatTxtArea.append(text + "\n");
	}
	
	public String retrieveInputText() {
		String returnString = chatTxtField.getText();
		chatTxtField.setText("");
		return returnString;
	}
	
	public void insertUser(String username) {
		listModel.addElement(username);
	}
	
	public void removeUser(String username) {
		String removeString;
		for(int i = 0; i < listModel.getSize(); i++){
		     removeString =  listModel.getElementAt(i).toString(); 
		     if (removeString.equalsIgnoreCase(username));
		     listModel.remove(i);
		}
	}
	
	public void clearUsers() {
		listModel.clear();
	}
}

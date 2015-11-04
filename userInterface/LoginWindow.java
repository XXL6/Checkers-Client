package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class LoginWindow {

	private JFrame frmCheckersGameClient;
	private JTextField nameTxtField;
	private JTextField addrTxtField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginWindow window = new LoginWindow();
//					window.frmCheckersGameClient.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}
	public void Display() {
		frmCheckersGameClient.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCheckersGameClient = new JFrame();
		frmCheckersGameClient.setTitle("Checkers Game Client\r\n");
		frmCheckersGameClient.getContentPane().setBackground(Color.WHITE);
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblIpAddress = new JLabel("IP Address:");
		
		nameTxtField = new JTextField();
		nameTxtField.setColumns(15);
		
		addrTxtField = new JTextField();
		addrTxtField.setToolTipText("Must contain 4 integers no greater than 255 separated by periods. Ex. \"255.255.255.0\"");
		addrTxtField.setColumns(15);
		
		JButton btnLogin = new JButton("Login");
		
		JButton btnClear = new JButton("Clear");
		
		JButton btnTutorial = new JButton("Tutorial");
		
		JLabel lblCheckersClientLogin = new JLabel("Checkers Client Login");
		lblCheckersClientLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout groupLayout = new GroupLayout(frmCheckersGameClient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsername)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIpAddress))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnTutorial, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
								.addComponent(nameTxtField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
								.addComponent(addrTxtField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(lblCheckersClientLogin)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblCheckersClientLogin)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(nameTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addrTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIpAddress))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnClear)
						.addComponent(btnTutorial))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		frmCheckersGameClient.getContentPane().setLayout(groupLayout);
		frmCheckersGameClient.setBounds(100, 100, 336, 213);
		frmCheckersGameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import loginHandler.LoginInfoRetriever;

public class LoginWindow implements ActionListener {

	private JFrame frmCheckersGameClient;
	private JTextField textField;
	private JButton btnLogin;
	private JTextField addrTxtField;
	//private JButton btnClear;
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
		
		btnLogin = new JButton("Login");
		
		JButton btnClear = new JButton("Clear");
		
		JButton btnTutorial = new JButton("Tutorial");
		
		JLabel lblCheckersClientLogin = new JLabel("Checkers Client Login");
		lblCheckersClientLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textField = new JTextField();
		textField.setToolTipText("The username may not contain whitespaces.");
		textField.setColumns(15);
		
		addrTxtField = new JTextField();
		addrTxtField.setColumns(10);
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
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(addrTxtField, Alignment.LEADING)
									.addComponent(textField, Alignment.LEADING))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(lblCheckersClientLogin)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblCheckersClientLogin)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIpAddress)
						.addComponent(addrTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnClear)
						.addComponent(btnTutorial))
					.addGap(14))
		);
		frmCheckersGameClient.getContentPane().setLayout(groupLayout);
		frmCheckersGameClient.setBounds(100, 100, 336, 213);
		frmCheckersGameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void assignLoginButton(ActionListener listener) {
		//listener = new LoginInfoParser();
		btnLogin.addActionListener(listener);
		//System.out.println(listener.toString());
		//btnClear.addActionListener(listener);		
	}
	
	public String getUsername() {
		return textField.getText();
	}
	
	public String getAddress() {
		return addrTxtField.getText();
	}
	
	private MaskFormatter createFormatter(String string) {
		MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(string);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("BUTTONPRESSED");
		
	}
}

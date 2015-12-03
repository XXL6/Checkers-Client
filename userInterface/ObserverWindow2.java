package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;

import game.CheckerBoard;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class ObserverWindow2 {

	private JFrame frmObserve;

	private JLabel lblPlayerBlack;
	private JLabel lblPlayerRed;
	private JLabel lblPlayersVersus;
	private CheckerBoard board;
	private JButton btnExitSpec;
	
	public ObserverWindow2(CheckerBoard board) {
		this.board = board;
		initialize();
	}

	public void display(boolean display) {
		frmObserve.setVisible(display);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmObserve = new JFrame();
		frmObserve.setBounds(100, 100, 680, 650);
		//frmObserve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JLabel lblPlayersVersus = new JLabel("");
		lblPlayersVersus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblPlayerBlack = new JLabel("");
		lblPlayerBlack.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblPlayerRed = new JLabel("");
		lblPlayerRed.setForeground(Color.RED);
		lblPlayerRed.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnExitSpec = new JButton("Exit Spec");
		GroupLayout groupLayout = new GroupLayout(frmObserve.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPlayersVersus)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(board, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlayerRed)
								.addComponent(lblPlayerBlack)
								.addComponent(btnExitSpec))))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(lblPlayersVersus)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlayerBlack)
								.addComponent(board, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(76)
							.addComponent(lblPlayerRed)
							.addGap(109)
							.addComponent(btnExitSpec)))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		frmObserve.getContentPane().setLayout(groupLayout);
	}
	
	public void setButtonListeners(ActionListener listener) {
		btnExitSpec.addActionListener(listener);
	}
	public void setPlayers(String blackUser, String redUser) {
		lblPlayerBlack.setText((blackUser.equals("-1")) ? "" : blackUser);
		lblPlayerRed.setText((redUser.equals("-1")) ? "" : redUser);
		if (!(blackUser.equals("-1") && redUser.equals("-1")))
			lblPlayersVersus.setText(blackUser + " :: VS :: " + redUser);
	}
	public void showPlayers(boolean show) {
		if (show) {
			lblPlayerBlack.setVisible(false);
			lblPlayerRed.setVisible(false);
			lblPlayersVersus.setVisible(false);
		} else {
			lblPlayerBlack.setVisible(true);
			lblPlayerRed.setVisible(true);
			lblPlayersVersus.setVisible(true);
		}
	}
}

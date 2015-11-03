package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
	JFrame loginFrame;
	JPanel loginPanel;
	JButton loginButton, tutorialButton, clearButton;
	JLabel loginTitle;
	JTextField username;
	JTextField ipAddress;
	
	public Login() {
		standardConstructor();
	}
	
	public void standardConstructor() {
		loginFrame = new JFrame("Checker Login");
		loginFrame.setSize(400, 300);
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.WHITE);
		
		loginButton = new JButton("Login");
		tutorialButton = new JButton("Tutorial");
		clearButton = new JButton("Clear");
		Dimension preferredSize = new Dimension(60,30);
		tutorialButton.setPreferredSize(preferredSize);
		loginTitle = new JLabel("Checkers Login");
		username = new JTextField(10);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(2, 2, 2, 2);
		constraint.gridx = 0;
		constraint.gridy = 4;
		loginPanel.add(loginButton, constraint);
		constraint.gridx = 0;
		constraint.gridy = 6;
		loginPanel.add(tutorialButton, constraint);
		constraint.gridx = 0;
		constraint.gridy = 5;
		loginPanel.add(clearButton, constraint);
		constraint.gridx = 0;
		constraint.gridy = 1;
		loginPanel.add(loginTitle, constraint);
		constraint.gridx = 0;
		constraint.gridy = 2;
		loginPanel.add(username, constraint);
		
		loginFrame.add(loginPanel);
	}
}

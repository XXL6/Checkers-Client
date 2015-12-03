package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import javafx.scene.layout.Border;
import javafx.scene.paint.Color;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TutorialWindow {

	private JFrame tutorialFrame;
	private JButton btnExit;
	private JButton btnPrev;
	private JButton btnNext;
	private JTextArea tutorialText;
	private JLabel tutImage;
	private JPanel panel;
	/**
	 * Create the application.
	 */
	public TutorialWindow() {
		initialize();
	}

	public void display(boolean yo) {
		tutorialFrame.setVisible(yo);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tutorialFrame = new JFrame();
		tutorialFrame.setBounds(100, 100, 547, 719);
		//tutorialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnExit = new JButton("Exit");
		
		btnPrev = new JButton("Prev");
		
		btnNext = new JButton("Next");
//		Border border = BorderFactory.createLineBorder(Color.BLACK);
		tutorialText = new JTextArea();
		tutorialText.setEditable(false);
		tutorialText.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		
		panel = new JPanel();
		panel.setVisible(true);
		
		GroupLayout groupLayout = new GroupLayout(tutorialFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tutorialText, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnExit)
							.addGap(18)
							.addComponent(btnPrev)
							.addGap(18)
							.addComponent(btnNext))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addComponent(tutorialText, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(236, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tutorialText, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit)
						.addComponent(btnPrev)
						.addComponent(btnNext))
					.addGap(264)
					.addComponent(tutorialText, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit)
						.addComponent(btnPrev)
						.addComponent(btnNext))
					.addContainerGap())
		);
		
		tutImage = new JLabel();
		panel.add(tutImage);
		tutorialFrame.getContentPane().setLayout(groupLayout);
	}
	
	public void assignListeners(ActionListener listener) {
		btnExit.addActionListener(listener);
		btnNext.addActionListener(listener);
		btnPrev.addActionListener(listener);
	}
	
	public void updateText(String text) {
		tutorialText.setText(text);
		//tutorialText.setLineWrap(true);
		
	}
	
	public void updatePicture(JLabel label) {
		panel.removeAll();
		panel.add(label);
		
	}
	
	public void showNext(boolean show) {
		btnNext.setVisible(show);
	}
	
	public void showPrev(boolean show) {
		btnPrev.setVisible(show);
	}
	
//	public void refresh() {
//		GroupLayout groupLayout = new GroupLayout(tutorialFrame.getContentPane());
//		groupLayout.setHorizontalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
//						.addComponent(tutorialText, Alignment.LEADING)
//						.addComponent(tutImage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
//						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
//							.addComponent(btnExit)
//							.addGap(18)
//							.addComponent(btnPrev)
//							.addGap(18)
//							.addComponent(btnNext)))
//					.addContainerGap(39, Short.MAX_VALUE))
//		);
//		groupLayout.setVerticalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(tutImage, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(tutorialText, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
//					.addGap(18)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(btnExit)
//						.addComponent(btnPrev)
//						.addComponent(btnNext))
//					.addContainerGap())
//		);
//		tutorialFrame.getContentPane().setLayout(groupLayout);
//	}
}

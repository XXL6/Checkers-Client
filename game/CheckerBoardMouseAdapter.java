package game;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class CheckerBoardMouseAdapter extends MouseAdapter {
	 private JLabel dragLabel = null;
     private int dragLabelWidthDiv2;
     private int dragLabelHeightDiv2;
     private JPanel clickedPanel = null;
     private JPanel backingPanel = null;
     private JPanel[][] panelGrid = null;
     JLayeredPane pane;

     
     public CheckerBoardMouseAdapter(JPanel backingPanel, JPanel[][] panelGrid, JLayeredPane pane) {
    	 this.backingPanel = backingPanel;
    	 this.panelGrid = panelGrid;
    	 this.pane = pane;
     }
     @Override
     public void mousePressed(MouseEvent me) {
         clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
         Component[] components = clickedPanel.getComponents();
         if (components.length == 0) {
             return;
         }
         // if we click on jpanel that holds a jlabel
         if (components[0] instanceof JLabel) {

             // remove label from panel
             dragLabel = (JLabel) components[0];
             clickedPanel.remove(dragLabel);
             clickedPanel.revalidate();
             clickedPanel.repaint();

             dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
             dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

             int x = me.getPoint().x - dragLabelWidthDiv2;
             int y = me.getPoint().y - dragLabelHeightDiv2;
             dragLabel.setLocation(x, y);
             pane.add(dragLabel, JLayeredPane.DRAG_LAYER);
             pane.repaint();
         }
     }

     @Override
     public void mouseDragged(MouseEvent me) {
         if (dragLabel == null) {
             return;
         }
         int x = me.getPoint().x - dragLabelWidthDiv2;
         int y = me.getPoint().y - dragLabelHeightDiv2;
         dragLabel.setLocation(x, y);
         pane.repaint();
     }

     @Override
     public void mouseReleased(MouseEvent me) {
         if (dragLabel == null) {
             return;
         }
         pane.remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
         JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
         if (droppedPanel == null) {
             // if off the grid, return label to home
             clickedPanel.add(dragLabel);
             clickedPanel.revalidate();
         } else {
             int r = -1;
             int c = -1;
             searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                 for (int col = 0; col < panelGrid[row].length; col++) {
                     if (panelGrid[row][col] == droppedPanel) {
                         r = row;
                         c = col;
                         break searchPanelGrid;
                     }
                 }
             }

             if (r == -1 || c == -1) {
                 // if off the grid, return label to home
                 clickedPanel.add(dragLabel);
                 clickedPanel.revalidate();
             } else {
                 droppedPanel.add(dragLabel);
                 droppedPanel.revalidate();
             }
         }

         pane.repaint();
         dragLabel = null;
     }
 }


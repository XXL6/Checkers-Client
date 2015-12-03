package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import serverCommunication.ServerInterface;

//import lolol.DragLabelOnLayeredPane.MyMouseAdapter;

public class CheckerBoard extends JLayeredPane {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -7313749626074051125L;
	private static BufferedImage checkerBoardImage;
	private int iBoardWidth, iBoardHeight;
	private static int boardWidth = 500;
	private static int boardHeight = 500;
	private static int GAP = 1;
	private int GRID_ROWS = 8;
	private int GRID_COLUMNS = 8;
	private byte board[][] = new byte[8][8];
	private static final Dimension LAYERED_PANE_SIZE = new Dimension(boardWidth, boardHeight);
    private static final Dimension LABEL_SIZE = new Dimension(60, 60);
	private JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLUMNS];
    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLUMNS, GAP, GAP);
    private JPanel backingPanel = new JPanel(gridlayout);
    private boolean boardActive = false;
    private String clientColor = null;
    private String clientUsername = null;
    private ServerInterface serverInterface = null;
    private int fRow, fCol;
    private int tRow, tCol;
  /*
  Just a quick thing I made to populate a 2d array in the right checkers positions, though there was probably an easier
  to do it. It can easily be altered to make the array populated by checkers instances.
  */
  
  
    public CheckerBoard(ServerInterface serverInterface){
    	this.serverInterface = serverInterface;
    	//Setup the board image
    	try {
			checkerBoardImage = ImageIO.read(new File("./resources/Board.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//resize the image to fit within the frame
    	iBoardWidth = checkerBoardImage.getWidth(this) / 2;
    	iBoardHeight = checkerBoardImage.getHeight(this) / 2;
    	resizeBoard(boardWidth, boardHeight);
    	
        //Sets up a background panel for the Checker containing panels to rest on
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setBackground(Color.black);
        backingPanel.setOpaque(false);
        //Populates each of the checker containers with a new JPanel with a gridbaglayout
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
                panelGrid[row][col] = new JPanel(new GridBagLayout());
                panelGrid[row][col].setOpaque(false);
                backingPanel.add(panelGrid[row][col]);
            }
        }

        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        BoardMouseAdapter mouseAdapter = new BoardMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if (checkerBoardImage != null) {
//    		int x = this.getParent().getWidth()/2 - boardWidth;
//    		int y = this.getParent().getHeight()/2 - boardHeight;
//    		g.drawImage(checkerBoardImage, x, y, this);
    		g.drawImage(checkerBoardImage, 0, 0, null);
    	}
    }
    /*
     * http://stackoverflow.com/questions/9417356/bufferedimage-resize/9417836#9417836
     * added to size the board appropriately
     */
    public static BufferedImage resizeBoard(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
    
    public void arrangeCheckers() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if(((i<3 && ((j%2) != 0) && i!=1) )|| ((i == 1 && (j%2) == 0))){
            		board[i][j] = 1;
            	}else if((i>4 && ((j%2) == 0) && i !=6) || (i == 6 && (j%2) != 0)){
            		board[i][j] = 2;
            	}
            	else{
            		board[i][j] = 0;
            	}
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j]+ ", ");
            }
            System.out.println("");
        }
        refreshBoard(board);
    }
    
    public static void resizeBoard(int newWidth, int newHeight) {
    	checkerBoardImage = resizeBoard(checkerBoardImage, newWidth, newHeight);
    }
    
    public int getBoardHeight() {
    	return this.boardHeight;
    }
    
    public int getBoardWidth() {
    	return this.boardWidth;
    }
    
    public void refreshBoard(byte[][] boardState) {
    	if (clientColor.equals("red")) {
	        for (int row = 0; row < GRID_ROWS; row++) {
	            for (int col = 0; col < GRID_COLUMNS; col++) {
	            	if (boardState[row][col] == 1 && panelGrid[row][col].getComponentCount() < 1) {
	            		Checker blackChecker = new Checker("black");
	                    blackChecker.setOpaque(false);
	                    panelGrid[row][col].add(blackChecker);
	            	} else if (boardState[row][col] == 2 && panelGrid[row][col].getComponentCount() < 1) {
	            		Checker redChecker = new Checker("red");
	                    redChecker.setOpaque(false);
	                    panelGrid[row][col].add(redChecker);
	            	} else if (boardState[row][col] == 3 && panelGrid[row][col].getComponentCount() < 2) {
	                    Checker blackKingChecker = new Checker("blackKing");
	                    blackKingChecker.setOpaque(false);
	                    panelGrid[row][col].removeAll();
	                    panelGrid[row][col].add(blackKingChecker);
	            	} else if (boardState[row][col] == 4 && panelGrid[row][col].getComponentCount() < 2) {
	                    Checker redKingChecker = new Checker("redKing");
	                    redKingChecker.setOpaque(false);
	                    panelGrid[row][col].removeAll();
	                    panelGrid[row][col].add(redKingChecker);
	            	} else if (boardState[row][col] == 0) {
	            		panelGrid[row][col].removeAll();
	            	}
	            }
	        }
    	} else {
    		byte[][] tempBoardState = new byte[8][8];
    		for (int i = 0; i < GRID_ROWS; i++) {
        		for (int j = 0; j < GRID_COLUMNS; j++) {
        			tempBoardState[i][j] = boardState[GRID_ROWS - i - 1][GRID_COLUMNS - j - 1];
        		}
    			//tempBoardState[i] = boardState[GRID_ROWS - i - 1];
    		}
    		boardState = tempBoardState;
    		for (int row = 0; row < GRID_ROWS; row++) {
	            for (int col = 0; col < GRID_COLUMNS; col++) {
	            	if (boardState[row][col] == 1 && panelGrid[row][col].getComponentCount() < 1) {
	            		Checker blackChecker = new Checker("black");
	                    blackChecker.setOpaque(false);
	                    panelGrid[row][col].add(blackChecker);
	            	} else if (boardState[row][col] == 2 && panelGrid[row][col].getComponentCount() < 1) {
	            		Checker redChecker = new Checker("red");
	                    redChecker.setOpaque(false);
	                    panelGrid[row][col].add(redChecker);
	            	} else if (boardState[row][col] == 3 && panelGrid[row][col].getComponentCount() < 2) {
	                    Checker blackKingChecker = new Checker("blackKing");
	                    blackKingChecker.setOpaque(false);
	                    panelGrid[row][col].removeAll();
	                    panelGrid[row][col].add(blackKingChecker);
	            	} else if (boardState[row][col] == 4 && panelGrid[row][col].getComponentCount() < 2) {
	                    Checker redKingChecker = new Checker("redKing");
	                    redKingChecker.setOpaque(false);
	                    panelGrid[row][col].removeAll();
	                    panelGrid[row][col].add(redKingChecker);
	            	} else if (boardState[row][col] == 0) {
	            		panelGrid[row][col].removeAll();
	            	}
	            }
	        }
    	}
    	validate();
    	repaint();
    }
    
    public void disable() {
    	boardActive = false;
    }
    
    public void enable() {
    	boardActive = true;
    }
    
    public void setColor(String color) {
    	clientColor = color;
    }
    
    public void setUsername(String username) {
    	this.clientUsername = username;
    }
    public void moveChecker(int fRow, int fCol, int tRow, int tCol) {
    	Component[] components = panelGrid[fRow][fCol].getComponents();
    	if (components.length < 1) {
    		System.out.print("Error: Asked to move non-existing checker");
    		return;
    	}
    	panelGrid[tRow][tCol].add(components[0]);
    	panelGrid[fRow][fCol].removeAll();
    	validate();
    	repaint();
    }
    
    public void undoMove() {
    	moveChecker(tRow, tCol, fRow, fCol);
    }
    
    private void removeHighlights() {
		for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
            	
                panelGrid[row][col].setBackground(new Color(0,255,0,0));
                panelGrid[row][col].setOpaque(false);
            }
		}
    }
    
    private void highlightAvailableMoves(int fRow, int fCol, String color) {
		if (fCol > 0 && fRow > 0) {
			if (panelGrid[fRow - 1][fCol - 1].getComponentCount() < 1) {
    			panelGrid[fRow - 1][fCol - 1].setBackground(new Color(0,255,0,50));
    			panelGrid[fRow - 1][fCol - 1].setOpaque(true);
			} else if (panelGrid[fRow - 1][fCol - 1].getComponentCount() > 0) {
				Checker checker = (Checker)panelGrid[fRow - 1][fCol - 1].getComponents()[0];
				if (!(checker.getColor().toLowerCase().contains(clientColor))) {
					fRow--;
					fCol--;
	    			if (fCol > 0 && fRow > 0 && panelGrid[fRow - 1][fCol - 1].getComponentCount() < 1) {
		    			panelGrid[fRow - 1][fCol - 1].setBackground(new Color(0,255,0,50));
		    			panelGrid[fRow - 1][fCol - 1].setOpaque(true);
	    			}
	    			fRow++;
	    			fCol++;
				}
			}
		}
		if (fCol < GRID_COLUMNS-1  && fRow > 0) {
			if (panelGrid[fRow - 1][fCol + 1].getComponentCount() < 1) {
    			panelGrid[fRow - 1][fCol + 1].setBackground(new Color(0,255,0,50));
    			panelGrid[fRow - 1][fCol + 1].setOpaque(true);
			} else if (panelGrid[fRow - 1][fCol + 1].getComponentCount() > 0) {
				Checker checker = (Checker)panelGrid[fRow - 1][fCol + 1].getComponents()[0];
				if (!(checker.getColor().toLowerCase().contains(clientColor))) {
					fRow--;
					fCol++;
	    			if (fCol < GRID_COLUMNS-1  && fRow > 0 && panelGrid[fRow - 1][fCol + 1].getComponentCount() < 1) {
		    			panelGrid[fRow - 1][fCol + 1].setBackground(new Color(0,255,0,50));
		    			panelGrid[fRow - 1][fCol + 1].setOpaque(true);
	    			}
	    			fRow++;
	    			fCol--;
				}
				
			}
		}
    	if (color.toLowerCase().contains("king")) {
    		if (fCol > 0 && fRow < GRID_ROWS - 1) {
    			if (panelGrid[fRow + 1][fCol - 1].getComponentCount() < 1) {
        			panelGrid[fRow + 1][fCol - 1].setBackground(new Color(0,255,0,50));
        			panelGrid[fRow + 1][fCol - 1].setOpaque(true);
    			} else if (panelGrid[fRow + 1][fCol - 1].getComponentCount() > 0) {
    				Checker checker = (Checker)panelGrid[fRow + 1][fCol - 1].getComponents()[0];
    				if (!(checker.getColor().toLowerCase().contains(clientColor))) {
    					fRow++;
    					fCol--;
    	    			if (fCol > 0 && fRow < GRID_ROWS - 1 && panelGrid[fRow + 1][fCol - 1].getComponentCount() < 1) {
    		    			panelGrid[fRow + 1][fCol - 1].setBackground(new Color(0,255,0,50));
    		    			panelGrid[fRow + 1][fCol - 1].setOpaque(true);
    	    			}
    	    			fRow--;
    	    			fCol++;
    				}
    			}
    		}
    		if (fCol < GRID_COLUMNS-1  && fRow < GRID_ROWS - 1) {
    			if (panelGrid[fRow + 1][fCol + 1].getComponentCount() < 1) {
        			panelGrid[fRow + 1][fCol + 1].setBackground(new Color(0,255,0,50));
        			panelGrid[fRow + 1][fCol + 1].setOpaque(true);
    			} else if (panelGrid[fRow + 1][fCol + 1].getComponentCount() > 0) {
    				Checker checker = (Checker)panelGrid[fRow + 1][fCol + 1].getComponents()[0];
    				if (!(checker.getColor().toLowerCase().contains(clientColor))) {
    					fRow++;
    					fCol++;
    	    			if (fCol < GRID_COLUMNS-1  && fRow < GRID_ROWS - 1 && panelGrid[fRow + 1][fCol + 1].getComponentCount() < 1) {
    		    			panelGrid[fRow + 1][fCol + 1].setBackground(new Color(0,255,0,50));
    		    			panelGrid[fRow + 1][fCol + 1].setOpaque(true);
    	    			}
    	    			fRow--;
    	    			fCol++;
    				}
    				
    			}
    		}
    		
    	}
    }
    private class BoardMouseAdapter extends MouseAdapter {
        private Checker dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
        	if (boardActive) {
	            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
	            Component[] components = clickedPanel.getComponents();
	            if (components.length == 0) {
	                return;
	            }
	            // if we click on jpanel that holds a jlabel
	            if (components[0] instanceof JLabel) {
	            	
	                // remove label from panel
	                dragLabel = (Checker) components[0];
	                if (!dragLabel.getColor().contains(clientColor)) {
	                	clickedPanel = null;
	                	dragLabel = null;
	                	return;
	                }
	                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
	                    for (int col = 0; col < panelGrid[row].length; col++) {
	                        if (panelGrid[row][col] == clickedPanel) {
	                            fRow = row;
	                            fCol = col;
	                            break searchPanelGrid;
	                        }
	                    }
	                }

	                highlightAvailableMoves(fRow, fCol, dragLabel.getColor());
	                clickedPanel.remove(dragLabel);
	                clickedPanel.revalidate();
	                clickedPanel.repaint();
	
	                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
	                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;
	
	                int x = me.getPoint().x - dragLabelWidthDiv2;
	                int y = me.getPoint().y - dragLabelHeightDiv2;
	                dragLabel.setLocation(x, y);
	                add(dragLabel, JLayeredPane.DRAG_LAYER);
	                repaint();
	            }
        	}
        }

        @Override
        public void mouseDragged(MouseEvent me) {
        	if (boardActive) {
	            if (dragLabel == null) {
	                return;
	            }
	            int x = me.getPoint().x - dragLabelWidthDiv2;
	            int y = me.getPoint().y - dragLabelHeightDiv2;
	            dragLabel.setLocation(x, y);
	            repaint();
        	}
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        	if (boardActive) {
	            if (dragLabel == null) {
	                return;
	            }
	            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
	            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
	            //Component[] components = droppedPanel.getComponents();

	            if (droppedPanel == null) {
	                // if off the grid, return label to home
	                clickedPanel.add(dragLabel);
	                clickedPanel.revalidate();
	            } else if (droppedPanel.getComponents().length > 0){
	            	clickedPanel.add(dragLabel);
	                clickedPanel.revalidate();
	            } else {
	                tRow = -1;
	                tCol = -1;
	                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
	                    for (int col = 0; col < panelGrid[row].length; col++) {
	                        if (panelGrid[row][col] == droppedPanel) {
	                            tRow = row;
	                            tCol = col;
	                            break searchPanelGrid;
	                        }
	                    }
	                }
	
	                if (tRow == -1 || tCol == -1) {
	                    // if off the grid, return label to home
	                    clickedPanel.add(dragLabel);
	                    clickedPanel.revalidate();
	                } else if (!droppedPanel.isOpaque()) {
	                	clickedPanel.add(dragLabel);
	                    clickedPanel.revalidate();
	                } else {
	                    droppedPanel.add(dragLabel);
	                    droppedPanel.revalidate();
	                    if (fRow != tRow || fCol != tCol) {
		                    if (clientColor.equalsIgnoreCase("black"))
		                    	serverInterface.move(clientUsername, GRID_ROWS - 1 - fRow, GRID_COLUMNS - 1 - fCol, 
		                    			GRID_ROWS - 1 - tRow, GRID_COLUMNS - 1 - tCol);
		                    else
		                    	serverInterface.move(clientUsername, fRow, fCol, tRow, tCol);
		                    boardActive = false;
		                    System.out.println("From row: " + fRow + " and column: " + fCol);
		                    System.out.println("To row: " + tRow + " and column: " + tCol);
	                    }
	                }
	            }
	            removeHighlights();
	            validate();
	            repaint();
	            dragLabel = null;
	            
	        }
        }
    }
}

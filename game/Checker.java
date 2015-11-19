package game;

public class Checker {
private boolean canMakeAMove(int row1, int row2, int col1, int col2, int player) {
  // Need a get legal move methode and call this method.
  // Use array row and column to store moves made.
         
      if (player == RED) {
         if (board[row1][col1] == RED && row2 > row1)
             return false;  // Red piece move down one direction.
          return true;  // Legal move.
      }
      else {
         if (board[row1][col1] == BLACK && row2 < row1)
             return false;  // Black piece can move up one direction.
          return true;  // Legal move.
      }
      
      if (col2 < 0 || col2 >= 8  || row2 < 0 || row2 >= 8)
         return false;  // Cant make move no locations.
    
      if (board[row2][col2] != EMPTY)
         return false;  // Square ocupied.

   }  // end canMove()
}

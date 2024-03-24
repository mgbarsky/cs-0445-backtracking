package backtracking;

/**
 * N-Queens puzzle
 * Place N-queens on a NxN chess board such that they do not threaten each other.
 * Simple demo of exhaustive recursive solution 
 * which uses backtracking optimization 
 * @author M. Barsky
 *
 */
public class NQueens {
	//To be non-threatening n queens should be all in different columns
	// so assuming that Queen i occupies column i
	// we just need to find a different row for each Queen i
	// that is also not threatened from diagonal by all previous placements	
	
	public static void placeQueens(int [][] board) {
		// We start trying to place Queen 0 in col 0 row 0
		findRow (board, 0, 0);		
	}
	
	/**
	 * Checks if placing a queen in board[row][col] is legal:
	 * Checks a new placement against all the previous placements in columns 0...col-1
	 * @param board
	 * @param row
	 * @param col
	 * @return true if this placement is legal
	 */
	private static boolean underThreat (int [][] board, int row, int col) {
		// the very first column: we do not have any threats so far
		if (col == 0)
			return false;
		
		//there could be already a queen in the same row of previous columns
		for (int j = 0; j < col; j++) {
			if (board[row][j] == 1) return true;
		}		
		// we tested that there is no threat on the same row
		
		// now we need to test the diagonal threat
		for (int j = 0; j < col ; j++) {
			for (int i=0; i < board.length; i++) {
				if (board[i][j] == 1) {  // here is a queen: check it does not threaten a new queen at board[row][col]
					if ((row - col == i - j) ||   // downward facing diagonals 
							(row + col == i + j)) // upward facing diagonals 
						return true;  // there is a queen on the same diagonal
				}
			}			
		}
		return false;
	}
	
	/**
	 * This tries to find the row for the current column col
	 * If found - tries to recursively assess the placement of the next queen in col+1
	 * If this whole process was unsuccessful at any recursive step - 
	 * returns false and the solution for the previous column is undone 
	 * and the next row is tried in turn
	 * @param board
	 * @param row
	 * @param col
	 * @return true if placement of the queen in board[row][col] leads to a successful final solution
	 */
	private static boolean findRow (int [][] board, int row, int col) {
		// BASE CASE
		// we are at the last column and position board[row][col] is legal
		if (col == board[0].length - 1) { // this is the last queen
			if (!underThreat (board, row, col)) { // we placed it!
				board[row][col] = 1;
				return true;
			} 
		}
		
		// let's try different rows until we find the one that is compatible
		// with all previous placings
		for (int i = 0; i < board.length; i++) {
			if (!underThreat (board, i, col)) {
				board[i][col] = 1;  // place queen here temporarily
				// and check next col until the end ...
				boolean success = findRow (board, row, col+1);
				if (!success) {
					//undo placement
					board[i][col] = 0;
				}
				else
					return true;  // queen stays in row i
			}
		}
		
		// checked all the rows and cant place in any of them
		// we need to undo the placement in the previous column
		return false;	
	}
	
	// prints the resulting placement
	public static void printBoard (int [][] board) {
		for (int i=0; i < board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				String cell = "-";
				if (board[i][j] == 1)
					cell = "Q";
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args) {
		int n = 20;
		int [][] board = new int[n][n];
		placeQueens(board);
		printBoard (board);
	}
}

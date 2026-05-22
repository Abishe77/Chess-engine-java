package chess_game;

import java.util.ArrayList;

public class Board {
	private int[][] grid;

	// Create a object of Move_generator class here so that makeMove() can call
	// getMoves() from the move gen class
	// Add these at the top of your Board class
	private int selectedRow = -1;
	private int selectedCol = -1;  //Used for GUI
	private ArrayList<int[]> currentValidMoves = new ArrayList<>();
	private int checkKingRow = -1;
	private int checkKingCol = -1;

	
	
	

	private Move_generator move_gen = new Move_generator();

	public Board() { // creating a constructor for state representation
		this.grid = new int[8][8];

		// Initialize pawns
		// WHITE
		int i = 6;
		for (int j = 0; j < 8; j++) {
			grid[i][j] = Piece.w_pawn;
		}

		// BLACK
		int a = 1;
		for (int k = 0; k < 8; k++) {
			grid[a][k] = Piece.b_pawn;
		}

		// Initializing the other white pieces

		grid[7][3] = Piece.w_queen;
		grid[7][4] = Piece.w_king;

		grid[7][0] = Piece.w_rook;
		grid[7][7] = Piece.w_rook;

		grid[7][1] = Piece.w_knight;
		grid[7][6] = Piece.w_knight;

		grid[7][2] = Piece.w_bishop;
		grid[7][5] = Piece.w_bishop;

		// Initializing the other black pieces

		grid[0][3] = Piece.b_queen;
		grid[0][4] = Piece.b_king;

		grid[0][0] = Piece.b_rook;
		grid[0][7] = Piece.b_rook;

		grid[0][1] = Piece.b_knight;
		grid[0][6] = Piece.b_knight;

		grid[0][2] = Piece.b_bishop;
		grid[0][5] = Piece.b_bishop;

	}

	// Create a method called executeMove() to interact the usermove with GUI
	// without taking a lot of computation
	public void executeMove(int current_row, int current_col, int target_row, int target_col) {
		// Just swap the pieces so that GUI can track it easily

		/*
		 * The another main purpose of this is , this chess game gonna contain two types
		 * 2 player and AI so it is useful to create a method beforehand
		 */
		int temp = grid[current_row][current_col];
		grid[target_row][target_col] = temp;
		grid[current_row][current_col] = 0;
		this.flag *= -1;
		checkDetection();
	}

	public void setPieceAt(int row, int col, int value) {
		this.grid[row][col] = value; // Just for testing by placing a piece manually because grid is private
	}

	/*
	 * Since grid[][] is private this cannot be used in move gen class so create a
	 * method, it returns current position of a piece when called looks way more
	 * structured than normal no specific reasons
	 */

	public int getPieceAt(int row, int col) {
		return this.grid[row][col];
	}

	private int flag = 1;

	public void makeMove(Board board, int current_row, int current_col, int target_row, int target_col) {
		ArrayList<int[]> moves = move_gen.getMoves(board, current_row, current_col);
		for (int i = 0; i < moves.size(); i++) {
			if ((this.flag > 0 && Piece.isWhite(getPieceAt(current_row, current_col)))
					|| (this.flag < 0 && Piece.isBlack(getPieceAt(current_row, current_col)))) {
				if (moves.get(i)[0] == target_row && moves.get(i)[1] == target_col) {
					/*
					 * Checking if the selected move is in Arraylist of the specific piece
					 */
					if (ghostCheck(current_row, current_col, target_row, target_col, move_gen)) {

						executeMove(current_row, current_col, target_row, target_col);
						return;

					}
				}
			}
		}

	}

	// Now creating a simulation board so that filtration becomes easier without
	// collapsing original engine

	public boolean ghostCheck(int current_row, int current_col, int target_row, int target_col,
			Move_generator move_gen) {
		int[][] ghost_board = new int[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ghost_board[i][j] = grid[i][j];
			}
		}
		// Teleport logic
		int current_piece = ghost_board[current_row][current_col];
		ghost_board[target_row][target_col] = current_piece;
		ghost_board[current_row][current_col] = 0;

		// Check the flag
		int current_flag = (current_piece > 0) ? 1 : -1;

		// Now initial king position on ghost board and scan the board to find the
		// location of the king
		int king_row = -1;
		int king_col = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// Check if the piece is king
				if (Math.abs(ghost_board[i][j]) == 6) {
					if ((current_flag > 0 && ghost_board[i][j] > 0) || // Flag check if it the color is matching or not
							(current_flag < 0 && ghost_board[i][j] < 0)) {

						// if matches update king_row and king_col to i and j because it is the current
						// position of king
						king_row = i;
						king_col = j;
						break; // Because king is found no more looping is needed
					}

				}
			}
		}

		return move_gen.isKingSafe(ghost_board, king_row, king_col, current_flag);

	}

	// Now a method to detect whether the game is over (checkamate) ,draw
	// (stalemate) , there is any chance to continue

	public int checkDetection() {
		int king_row = -1;
		int king_col = -1;

		// Scan the whole board to locate king

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((this.flag > 0 && this.grid[i][j] == 6) || (this.flag < 0 && this.grid[i][j] == -6)) {
					// This logic says the current flag number which is color and king color is
					// matching or not

					// Update the king position
					king_row = i;
					king_col = j;
					break; // Because when located no need of scanning
				}
			}

		}
		boolean kingIsSafe = move_gen.isKingSafe(grid, king_row, king_col, this.flag);
		boolean legalMoves = false; // Initialzing with false any present it will changed to true

		// Now scan the piece of same color as king on the board because with that piece
		// king might be protected
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int piece = this.grid[i][j];
				if ((piece > 0 && this.flag > 0) || (piece < 0 && this.flag < 0)) { // Make sure they are same color
					ArrayList<int[]> saving_moves = move_gen.getMoves(this, i, j);
					// This arraylist to store the all legal moves of that piece

					for (int[] move : saving_moves) { // extracts all legal ones
						int target_row = move[0];
						int target_col = move[1];

						/*
						 * Pass this targets to ghostCheck() if it returns true change legalMoves to
						 * true because there exists a move that can save king
						 */
						if (ghostCheck(i, j, target_row, target_col, move_gen)) {
							legalMoves = true;
							break; // Because there exsits something so do that }
						}

					}
				}
				if (legalMoves)
					break; // Breaking outer for to reduce computation time
			}
			if (legalMoves)
				break;

		}
		if (!kingIsSafe) {
	        this.checkKingRow = king_row;
	        this.checkKingCol = king_col;
	    } else {
	        this.checkKingRow = -1; // Reset if not in check
	        this.checkKingCol = -1;
	    }
		if (!legalMoves) {
			if (!kingIsSafe) {
				return 0; // Checkmate
			}
			return 1; // Stalemate
			

		}
		return 2; // Continue has moves

	}
	
	public void handleInput(int row, int col) {
	    if (selectedRow == -1) {
	        // Selection Logic  Ensure it's the right turn!
	        int piece = getPieceAt(row, col);
	        if (piece != 0) {
	            // Check if player is picking their own color
	            if ((flag > 0 && piece > 0) || (flag < 0 && piece < 0)) {
	                selectedRow = row;
	                selectedCol = col;
	                updateValidMoves(row, col);
	                System.out.println("Piece selected at " + row + "," + col);
	            }
	        }
	    } else {
	    	makeMove(this, selectedRow, selectedCol, row, col);
	        selectedRow = -1;
	        selectedCol = -1;
	        currentValidMoves.clear();
	    }
	}
	public void updateValidMoves(int row, int col) {
	    currentValidMoves = move_gen.getMoves(this, row, col);
	    // Filter moves only keep those that keep the king safe
	    currentValidMoves.removeIf(move -> !ghostCheck(row, col, move[0], move[1], move_gen));
	}
	
	
	public ArrayList<int[]> getCurrentValidMoves() { 
	    return this.currentValidMoves; 
	}
	public int getCheckKingRow() { 
	    return this.checkKingRow; 
	}
	public int getCheckKingCol() { 
	    return this.checkKingCol; 
	}

}

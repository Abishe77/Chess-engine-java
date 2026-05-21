package chess_game;

import java.util.ArrayList;

public class Board {
	private int[][] grid;

	// Create a object of Move_generator class here so that makeMove() can call
	// getMoves() from the move gen class

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
	
	public void setPieceAt(int row , int col , int value) {
		this.grid[row][col]=value;   //Just for testing by placing a piece manually because grid is private
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
						int temp = grid[current_row][current_col];
						/*
						 * Just teleport the element from current to target and make current as zero and
						 * update the flag for alternate piece color turn
						 */
						grid[target_row][target_col] = temp;
						grid[current_row][current_col] = 0;
						this.flag *= -1;
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

}

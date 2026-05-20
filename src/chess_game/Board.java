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
			if (moves.get(i)[0] == target_row && moves.get(i)[1] == target_col) {
				/*
				 * Checking if the selected move is in Arraylist of the specific piece
				 */
				int temp = grid[current_row][current_col]; /*
															 * Just teleport the element from current to target and make
															 * current as zero and update the flag for alternate piece
															 * color turn
															 */
				grid[target_row][target_col] = temp;
				grid[current_row][current_col] = 0;
				this.flag *= -1;
				return;
			}
		}

	}

}

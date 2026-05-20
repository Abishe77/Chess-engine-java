package chess_game;

public class Board {
	private int[][] grid;

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

}

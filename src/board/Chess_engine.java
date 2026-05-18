package board;

import java.util.ArrayList;

public class Chess_engine {

	// White piece intialization

	static final int w_knight = 2;
	static final int w_bishop = 3;
	static final int w_rook = 4;
	static final int w_queen = 5;
	static final int w_king = 6;

	// Black piece initialization

	static final int b_knight = -2;
	static final int b_bishop = -3;
	static final int b_rook = -4;
	static final int b_queen = -5;
	static final int b_king = -6;

	public static int[][] makeBoard() {

		/*
		 * simulating a board using 2D arrays with 8x8 rows and columns each rows
		 * corresponds to position from (0,0) to (7,7)
		 */

		int[][] board = new int[8][8];
		int i = 1; // Initializing the board of row 1 with black pawns
		for (int j = 0; j < 8; j++) {
			board[i][j] = -1;
		}
		int a = 6; // Initializing the board of row 6 with white pawns
		for (int y = 0; y < 8; y++) {
			board[a][y] = 1;
		}

		// Initializing the other white pieces

		board[7][3] = w_queen;
		board[7][4] = w_king;

		board[7][0] = w_rook;
		board[7][7] = w_rook;

		board[7][1] = w_knight;
		board[7][6] = w_knight;

		board[7][2] = w_bishop;
		board[7][5] = w_bishop;

		// Initializing the other black pieces

		board[0][3] = b_queen;
		board[0][4] = b_king;

		board[0][0] = b_rook;
		board[0][7] = b_rook;

		board[0][1] = b_knight;
		board[0][6] = b_knight;

		board[0][2] = b_bishop;
		board[0][5] = b_bishop;

		return board;
	}

	// MOVES FOR PAWN

	public static ArrayList<int[]> pawnMoves(int[][] board, int row, int col) {

		int current_pawn = board[row][col];
		ArrayList<int[]> possibilities = new ArrayList<>();

		// This is list is to store legal moves of pawn piece
		if (current_pawn == 1) { // If the pawn is white

			/*
			 * The below if logic checks if the place ahead of pawn is empty if not skips
			 * and does nothing if there is space it checks whether it is in 6th row i.e,
			 * initial position if yes then checks for two squares if its empty it can do
			 * two square move + one square if it is not empty the if exits and does the one
			 * square move
			 */

			if (row - 1 >= 0 && board[row - 1][col] == 0) {
				if (row == 6) {
					if (board[row - 2][col] == 0) {
						possibilities.add(new int[] { row - 2, col });
					}
				}
				possibilities.add(new int[] { row - 1, col });
			}

			/*
			 * Now for attack strategy check for edge cases separate this into two sections
			 * edge cases often come at boundaries of board So take the boundaries now pawn
			 * can do 2 attacks one is left and right
			 */

			// LEFT PAWN ATTACK STRATEGY
			if (row - 1 >= 0 && col - 1 >= 0) {
				if (board[row - 1][col - 1] < 0) {
					possibilities.add(new int[] { row - 1, col - 1 });

				}
			}

			// RIGHT PAWN ATTACK STRATEGY
			if (row - 1 >= 0 && col + 1 < 8) {
				if (board[row - 1][col + 1] < 0) {
					possibilities.add(new int[] { row - 1, col + 1 });
				}
			}
		}

		// Below is black pawn logic just vice versa of white pawn
		if (current_pawn == -1) { // If the pawn is black
			if (row + 1 < 8 && board[row + 1][col] == 0) {
				if (row == 1) {
					if (board[row + 2][col] == 0) {
						possibilities.add(new int[] { row + 2, col });
					}
				}
				possibilities.add(new int[] { row + 1, col });
			}

			// LEFT PAWN ATTACK STRATEGY
			if (row + 1 < 8 && col + 1 < 8) {
				if (board[row + 1][col + 1] > 0) {
					possibilities.add(new int[] { row + 1, col + 1 });

				}
			}

			// RIGHT PAWN ATTACK STRATEGY
			if (row + 1 < 8 && col - 1 >= 0) {
				if (board[row + 1][col - 1] > 0) {
					possibilities.add(new int[] { row + 1, col - 1 });
				}
			}
		}
		return possibilities;

	}
	// MOVES FOR KNIGHT

	public static ArrayList<int[]> knightMoves(int[][] board, int row, int col) {

		int current_knight = board[row][col];

		ArrayList<int[]> possibilities = new ArrayList<>();

		/*
		 * For Knight color is secondary unlike pawn and knight can move in 8 directions
		 * from its own position, they are (-1,-2) , (-1 ,2) ,(1,-2),(1,2)
		 * ,(-2,1),(-2,-1) ,(2,-1),(2,1) irrespective of their color just check boundary
		 * for each 8 moves and at that time check for color if the piece is white it
		 * cannot land on positive if its black it cannot land on negative
		 */

		if (row - 1 >= 0 && col - 2 >= 0) {
			if ((board[row - 1][col - 2] == 0) || (current_knight == 2 && board[row - 1][col - 2] < 0)
					|| (current_knight == -2 && board[row - 1][col - 2] > 0)) {
				possibilities.add(new int[] { row - 1, col - 2 });
			}
		}
		if (row - 1 >= 0 && col + 2 < 8) {
			if ((board[row - 1][col + 2] == 0) || (current_knight == 2 && board[row - 1][col + 2] < 0)
					|| (current_knight == -2 && board[row - 1][col + 2] > 0)) {
				possibilities.add(new int[] { row - 1, col + 2 });
			}
		}
		if (row + 1 < 8 && col - 2 >= 0) {
			if ((board[row + 1][col - 2] == 0) || (current_knight == 2 && board[row + 1][col - 2] < 0)
					|| (current_knight == -2 && board[row + 1][col - 2] > 0)) {
				possibilities.add(new int[] { row + 1, col - 2 });
			}
		}
		if (row + 1 < 8 && col + 2 < 8) {
			if ((board[row + 1][col + 2] == 0) || (current_knight == 2 && board[row + 1][col + 2] < 0)
					|| (current_knight == -2 && board[row + 1][col + 2] > 0)) {
				possibilities.add(new int[] { row + 1, col + 2 });
			}
		}
		if (row - 2 >= 0 && col + 1 < 8) {
			if ((board[row - 2][col + 1] == 0) || (current_knight == 2 && board[row - 2][col + 1] < 0)
					|| (current_knight == -2 && board[row - 2][col + 1] > 0)) {
				possibilities.add(new int[] { row - 2, col + 1 });
			}
		}
		if (row - 2 >= 0 && col - 1 >= 0) {
			if ((board[row - 2][col - 1] == 0) || (current_knight == 2 && board[row - 2][col - 1] < 0)
					|| (current_knight == -2 && board[row - 2][col - 1] > 0)) {
				possibilities.add(new int[] { row - 2, col - 1 });
			}
		}
		if (row + 2 < 8 && col - 1 >= 0) {
			if ((board[row + 2][col - 1] == 0) || (current_knight == 2 && board[row + 2][col - 1] < 0)
					|| (current_knight == -2 && board[row + 2][col - 1] > 0)) {
				possibilities.add(new int[] { row + 2, col - 1 });
			}
		}
		if (row + 2 < 8 && col + 1 < 8) {
			if ((board[row + 2][col + 1] == 0) || (current_knight == 2 && board[row + 2][col + 1] < 0)
					|| (current_knight == -2 && board[row + 2][col + 1] > 0)) {
				possibilities.add(new int[] { row + 2, col + 1 });
			}
		}

		return possibilities;
	}

	// MOVES FOR BISHOP

	public static ArrayList<int[]> bishopMoves(int[][] board, int row, int col) {
		int current_bishop = board[row][col];
		int i = 1;

		ArrayList<int[]> possibilities = new ArrayList<>();

		// UPPER LEFT DIAGONAL
		while (row - i >= 0 && col - i >= 0 && board[row - i][col - i] == 0) {
			possibilities.add(new int[] { row - i, col - i });
			i++;
		}
		if (row - i >= 0 && col - i >= 0) {
			if ((current_bishop > 0 && board[row - i][col - i] < 0)
					|| (current_bishop < 0 && board[row - i][col - i] > 0)) {
				possibilities.add(new int[] { row - i, col - i });
			}
		}
		i = 1;

		// UPPER RIGHT DIAGONAL
		while (row - i >= 0 && col + i < 8 && board[row - i][col + i] == 0) {
			possibilities.add(new int[] { row - i, col + i });
			i++;
		}
		if (row - i >= 0 && col + i < 8) {
			if ((current_bishop > 0 && board[row - i][col + i] < 0)
					|| (current_bishop < 0 && board[row - i][col + i] > 0)) {
				possibilities.add(new int[] { row - i, col + i });
			}
		}
		i = 1;

		// LOWER LEFT DIAGONAL

		while (row + i < 8 && col - i >= 0 && board[row + i][col - i] == 0) {
			possibilities.add(new int[] { row + i, col - i });
			i++;
		}
		if (row + i < 8 && col - i >= 0) {
			if ((current_bishop > 0 && board[row + i][col - i] < 0)
					|| (current_bishop < 0 && board[row + i][col - i] > 0)) {
				possibilities.add(new int[] { row + i, col - i });
			}
		}
		i = 1;

		// LOWER RIGHT DIAGONAL

		while (row + i < 8 && col + i < 8 && board[row + i][col + i] == 0) {
			possibilities.add(new int[] { row + i, col + i });
			i++;
		}
		if (row + i < 8 && col + i < 8) {
			if ((current_bishop > 0 && board[row + i][col + i] < 0)
					|| (current_bishop < 0 && board[row + i][col + i] > 0)) {
				possibilities.add(new int[] { row + i, col + i });
			}
		}
		i = 1;

		return possibilities;

	}
	// MOVES FOR ROOK

	public static ArrayList<int[]> rookMoves(int[][] board, int row, int col) {
		int current_rook = board[row][col];
		int i = 1;

		ArrayList<int[]> possibilities = new ArrayList<>();

		// UP STRAIGHT

		while (row - i >= 0 && board[row - i][col] == 0) {
			possibilities.add(new int[] { row - i, col });
			i++;
		}
		if (row - i >= 0) {
			if ((current_rook > 0 && board[row - i][col] < 0) || (current_rook < 0 && board[row - i][col] > 0)) {
				possibilities.add(new int[] { row - i, col });
			}
		}
		i = 1;

		// LOW STRAIGHT

		while (row + i < 8 && board[row + i][col] == 0) {
			possibilities.add(new int[] { row + i, col });
			i++;
		}
		if (row + i < 8) {
			if ((current_rook > 0 && board[row + i][col] < 0) || (current_rook < 0 && board[row + i][col] > 0)) {
				possibilities.add(new int[] { row + i, col });
			}
		}
		i = 1;

		// LEFT STRAIGHT

		while (col - i >= 0 && board[row][col - i] == 0) {
			possibilities.add(new int[] { row, col - i });
			i++;
		}
		if (col - i >= 0) {
			if ((current_rook > 0 && board[row][col - i] < 0) || (current_rook < 0 && board[row][col - i] > 0)) {
				possibilities.add(new int[] { row, col - i });
			}
		}
		i = 1;

		// RIGHT STRAIGHT

		while (col + i < 8 && board[row][col + i] == 0) {
			possibilities.add(new int[] { row, col + i });
			i++;
		}
		if (col + i < 8) {
			if ((current_rook > 0 && board[row][col + i] < 0) || (current_rook < 0 && board[row][col + i] > 0)) {
				possibilities.add(new int[] { row, col + i });
			}
		}
		i = 1;

		return possibilities;
	}

	// MOVES FOR QUEEN

	public static ArrayList<int[]> queenMoves(int[][] board, int row, int col) {

		ArrayList<int[]> possibilities = new ArrayList<>();

		// JUST CALL BISHOP AND ROOK METHODS HERE

		possibilities.addAll(bishopMoves(board, row, col));
		possibilities.addAll(rookMoves(board, row, col));

		return possibilities;
	}

	public static ArrayList<int[]> kingMoves(int[][] board, int row, int col) {
		int current_king = board[row][col];

		ArrayList<int[]> possibilities = new ArrayList<>();

		// UP LEFT
		if (row - 1 >= 0 && col - 1 >= 0) {
			if ((board[row - 1][col - 1] == 0) || (current_king > 0 && board[row - 1][col - 1] < 0)
					|| (current_king < 0 && board[row - 1][col - 1] > 0)) {
				possibilities.add(new int[] { row - 1, col - 1 });
			}
		}

		// UP STRAIGHT
		if (row - 1 >= 0) {
			if ((board[row - 1][col] == 0) || (current_king > 0 && board[row - 1][col] < 0)
					|| (current_king < 0 && board[row - 1][col] > 0)) {
				possibilities.add(new int[] { row - 1, col });
			}
		}
		// UP RIGHT
		if (row - 1 >= 0 && col + 1 < 8) {
			if ((board[row - 1][col + 1] == 0) || (current_king > 0 && board[row - 1][col + 1] < 0)
					|| (current_king < 0 && board[row - 1][col + 1] > 0)) {
				possibilities.add(new int[] { row - 1, col + 1 });
			}
		}
		// LEFT STRAIGHT
		if (col - 1 >= 0) {
			if ((board[row][col - 1] == 0) || (current_king > 0 && board[row][col - 1] < 0)
					|| (current_king < 0 && board[row][col - 1] > 0)) {
				possibilities.add(new int[] { row, col - 1 });
			}
		}
		// RIGHT STRAIGHT
		if (col + 1 < 8) {
			if ((board[row][col + 1] == 0) || (current_king > 0 && board[row][col + 1] < 0)
					|| (current_king < 0 && board[row][col + 1] > 0)) {
				possibilities.add(new int[] { row, col + 1 });
			}
		}
		// DOWN LEFT
		if (row + 1 < 8 && col - 1 >= 0) {
			if ((board[row + 1][col - 1] == 0) || (current_king > 0 && board[row + 1][col - 1] < 0)
					|| (current_king < 0 && board[row + 1][col - 1] > 0)) {
				possibilities.add(new int[] { row + 1, col - 1 });
			}
		}
		// DOWN STRAIGHT
		if (row + 1 < 8) {
			if ((board[row + 1][col] == 0) || (current_king > 0 && board[row + 1][col] < 0)
					|| (current_king < 0 && board[row + 1][col] > 0)) {
				possibilities.add(new int[] { row + 1, col });
			}
		}
		// DOWN RIGHT
		if (row + 1 < 8 && col + 1 < 8) {
			if ((board[row + 1][col + 1] == 0) || (current_king > 0 && board[row + 1][col + 1] < 0)
					|| (current_king < 0 && board[row + 1][col + 1] > 0)) {
				possibilities.add(new int[] { row + 1, col + 1 });
			}
		}

		return possibilities;
	}

	public static void main(String[] args) {
		int[][] f_board = makeBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(f_board[i][j] + "\t");
			}
			System.out.println();
		}
		f_board[5][0] = 4;
		f_board[1][0] = 0;
		ArrayList<int[]> moves = kingMoves(f_board, 5, 0);
		for (int[] move : moves) {
			System.out.println("(" + move[0] + "," + move[1] + ")");
		}

	}
}

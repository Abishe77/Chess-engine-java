package chess_game;

public class Piece {

	// White piece initialization
	public static final int w_pawn = 1;
	public static final int w_knight = 2;
	public static final int w_bishop = 3;
	public static final int w_rook = 4;
	public static final int w_queen = 5;
	public static final int w_king = 6;

	// Black piece initialization
	public static final int b_pawn = -1;
	public static final int b_knight = -2;
	public static final int b_bishop = -3;
	public static final int b_rook = -4;
	public static final int b_queen = -5;
	public static final int b_king = -6;

	// to check if a piece is White
	public static boolean isWhite(int piece) {
		// If the number is greater than 0, it's a white piece
		return piece > 0;
	}

	// to check if a piece is Black
	public static boolean isBlack(int piece) {
		// If the number is less than 0, it's a black piece
		return piece < 0;
	}

	// to check if two pieces are on opposite teams
	public static boolean isOpponent(int currentPiece, int targetPiece) {
		// An empty square
		if (targetPiece == 0) {
			return false;
		}

		// If one is white and the other is black, they are opponents
		if (isWhite(currentPiece) && isBlack(targetPiece)) {
			return true;
		}
		if (isBlack(currentPiece) && isWhite(targetPiece)) {
			return true;
		}

		return false; // same team
	}
}

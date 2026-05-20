
package chess_game;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Board board = new Board();
		Move_generator generator = new Move_generator();

		// Test a Knight in the middle of an empty board
		int testRow = 4, testCol = 4;
		ArrayList<int[]> knightMoves = generator.knightMoves(board, testRow, testCol);

		System.out.println("--- TESTING KNIGHT MOVEMENTS ---");
		for (int[] move : knightMoves) {
			System.out.println("Can move to: Row " + move[0] + ", Col " + move[1]);
		}
	}
}

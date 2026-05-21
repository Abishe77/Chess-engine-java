
package chess_game;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {

		Board board = new Board();
		board.setPieceAt(5, 3, -3);
		board.makeMove(board, 6, 4, 5, 3);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(board.getPieceAt(i, j) + " ");

			}
			System.out.println();
		}
	}
}

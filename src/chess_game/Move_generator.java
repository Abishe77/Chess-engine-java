package chess_game;

import java.util.ArrayList;

public class Move_generator {
	
	
	

	// Starting with knight because it is easier than other pieces

	/*
	 * Now optimized the code by creating a 2D vector offset where it contains all 8
	 * directions the knight can move and it reduce lines of code instead of 8 while
	 * loops we go with one loop
	 */

	private static final int[][] Knight_offsets = { { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { -2, 1 }, { 2, -1 },
			{ 2, 1 }, { -2, -1 } };

	public ArrayList<int[]> knightMoves(Board board, int row, int col) {

		int current_knight = board.getPieceAt(row, col);

		ArrayList<int[]> possibilities = new ArrayList<>();
		// Loop through all 8 possibilities
		for (int i = 0; i < 8; i++) {
			int target_row = row + Knight_offsets[i][0];
			int target_col = col + Knight_offsets[i][1];

			// Check boundary as usual then check if the piece is same color or different
			// color
			if (target_row >= 0 && target_col < 8 && target_row < 8 && target_col >= 0) {
				int target_piece = board.getPieceAt(target_row, target_col);

				if (target_piece == 0) {
					possibilities.add(new int[] { target_row, target_col });

				} else if (Piece.isWhite(current_knight) && Piece.isBlack(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });

				} else if (Piece.isBlack(current_knight) && Piece.isWhite(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
				}

			}

		}
		return possibilities;

	}

	// Now going with king because it has same 8 directions like knight
	private static final int[][] King_offsets = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 },
			{ 1, 0 }, { 1, 1 } };

	public ArrayList<int[]> kingMoves(Board board, int row, int col) {
		int current_king = board.getPieceAt(row, col);
		ArrayList<int[]> possibilities = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			int target_row = row + King_offsets[i][0];
			int target_col = col + King_offsets[i][1];

			if (target_row >= 0 && target_col < 8 && target_row < 8 && target_col >= 0) {
				int target_piece = board.getPieceAt(target_row, target_col);
				if (target_piece == 0) {
					possibilities.add(new int[] { target_row, target_col });
				}

				else if (Piece.isWhite(current_king) && Piece.isBlack(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
				}

				else if (Piece.isBlack(current_king) && Piece.isWhite(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
				}
			}
		}
		return possibilities;

	}

	// ROOK

	private static final int[][] Rook_offsets = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public ArrayList<int[]> rookMoves(Board board, int row, int col) {
		int current_rook = board.getPieceAt(row, col);

		ArrayList<int[]> possibilities = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int target_row = row + Rook_offsets[i][0];
			int target_col = col + Rook_offsets[i][1];

			while (true) { // This is here because so that the piece can slide next if empty
				if (target_row < 0 || target_col >= 8 || target_row >= 8 || target_col < 0) {

					/*
					 * 1.Instead of && , || is used because we have used while loop because its
					 * sliding so this || acts like break instead of total exit like &&
					 * 
					 * 2. Signs like >=0 and <8 is flip to >=8 and <0 Intuition : Think like a fence
					 * you don't keep it at the edge INSIDE of your house but at the first place
					 * OUSIDE of your house
					 */
					break;
				}
				int target_piece = board.getPieceAt(target_row, target_col);
				if (target_piece == 0) {
					possibilities.add(new int[] { target_row, target_col });

					// Slide if there is nothing to interrupt
					target_row += Rook_offsets[i][0];
					target_col += Rook_offsets[i][1];
				}

				else if (Piece.isWhite(current_rook) && Piece.isBlack(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
					break;
				}

				else if (Piece.isBlack(current_rook) && Piece.isWhite(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
					break;
				} else {
					break; // This is just to stop while and transfer the control if none of conditions
							// apply

				}
			}
		}

		return possibilities;
	}

	// BISHOP
	private static final int[][] Bishop_offsets = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public ArrayList<int[]> bishopMoves(Board board, int row, int col) {
		int current_bishop = board.getPieceAt(row, col);

		ArrayList<int[]> possibilities = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			int target_row = row + Bishop_offsets[i][0];
			int target_col = col + Bishop_offsets[i][1];

			// Same as Rook

			while (true) {
				if (target_row < 0 || target_col >= 8 || target_row >= 8 || target_col < 0) {
					break;
				}
				int target_piece = board.getPieceAt(target_row, target_col);
				if (target_piece == 0) {
					possibilities.add(new int[] { target_row, target_col });

					target_row += Bishop_offsets[i][0];
					target_col += Bishop_offsets[i][1];
				}

				else if (Piece.isWhite(current_bishop) && Piece.isBlack(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
					break;
				}

				else if (Piece.isBlack(current_bishop) && Piece.isWhite(target_piece)) {
					possibilities.add(new int[] { target_row, target_col });
					break;
				} else {
					break;

				}

			}

		}

		return possibilities;
	}

	// QUEEN
	public ArrayList<int[]> queenMoves(Board board, int row, int col) {

		ArrayList<int[]> possibilities = new ArrayList<>();

		possibilities.addAll(bishopMoves(board, row, col));
		possibilities.addAll(rookMoves(board, row, col));

		return possibilities;
	}

	// PAWN
	public ArrayList<int[]> pawnMoves(Board board, int row, int col) {
		int current_pawn = board.getPieceAt(row, col);
		ArrayList<int[]> possibilities = new ArrayList<>();

		// WHITE PAWN

		if (Piece.isWhite(current_pawn)) {
			if (row - 1 >= 0 && board.getPieceAt(row - 1, col) == 0) {
				if (row == 6) {
					if (board.getPieceAt(row - 2, col) == 0) {
						possibilities.add(new int[] { row - 2, col });
					}
				}
				possibilities.add(new int[] { row - 1, col });
			}

			// LEFT ATTACK
			if (row - 1 >= 0 && col - 1 >= 0) {
				if (Piece.isBlack(board.getPieceAt(row - 1, col - 1))) {
					possibilities.add(new int[] { row - 1, col - 1 });

				}
			}
			// RIGHT ATTACK
			if (row - 1 >= 0 && col + 1 < 8) {
				if (Piece.isBlack(board.getPieceAt(row - 1, col + 1))) {
					possibilities.add(new int[] { row - 1, col + 1 });
				}
			}
		}

		// BLACK PAWN

		if (Piece.isBlack(current_pawn)) {
			if (row + 1 < 8 && board.getPieceAt(row + 1, col) == 0) {
				if (row == 1) {
					if (board.getPieceAt(row + 2, col) == 0) {
						possibilities.add(new int[] { row + 2, col });
					}
				}
				possibilities.add(new int[] { row + 1, col });
			}

			// LEFT ATTACK
			if (row + 1 < 8 && col + 1 < 8) {
				if (Piece.isWhite(board.getPieceAt(row + 1, col + 1))) {
					possibilities.add(new int[] { row + 1, col + 1 });

				}
			}
			// RIGHT ATTACK
			if (row + 1 < 8 && col - 1 >= 0) {
				if (Piece.isWhite(board.getPieceAt(row + 1, col - 1))) {
					possibilities.add(new int[] { row + 1, col - 1 });
				}
			}
		}

		return possibilities;
	}

	public  ArrayList<int[]> getMoves(Board board, int row, int col) {

		// This method is piece specific move generation when a piece is selected it
		// just returns the moves for that piece
		ArrayList<int[]> piece_specific_possibilites = new ArrayList<>();
		if (board == null) return piece_specific_possibilites;
	    
	    
		int temp = board.getPieceAt(row, col);
		switch (Math.abs(temp)) {

		case 1:
			piece_specific_possibilites.addAll(pawnMoves(board, row, col));
			break;

		case 2:
			piece_specific_possibilites.addAll(knightMoves(board, row, col));
			break;

		case 3:
			piece_specific_possibilites.addAll(bishopMoves(board, row, col));
			break;

		case 4:
			piece_specific_possibilites.addAll(rookMoves(board, row, col));
			break;

		case 5:
			piece_specific_possibilites.addAll(queenMoves(board, row, col));
			break;

		case 6:
			piece_specific_possibilites.addAll(kingMoves(board, row, col));
			break;
		}
		return piece_specific_possibilites;
	}

	/*
	 * this method is used as radar for king just checking in all 8 directions up to
	 * the end of the board (more likely queen moves is copied to king verification)
	 * + knight jumps verification + pawn diagonal verification , ignoring if the
	 * piece is king of different color because a king cannot make another king
	 * check
	 */
	public boolean isKingSafe(int board[][], int king_row, int king_col, int current_flag) {
		int[][] ray_offsets = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for (int i = 0; i < 8; i++) {
			int row_offset = ray_offsets[i][0];
			int col_offset = ray_offsets[i][1]; // this two takes each one from ray_offsets

			int move_row = row_offset + king_row;
			int move_col = col_offset + king_col; // this two line checks 8 directions from kings coordinate
			int step = 1; // to look for pawn

			// Boundary check for ray scans
			while (move_row >= 0 && move_row < 8 && move_col >= 0 && move_col < 8) {
				int piece = board[move_row][move_col]; // keeps track of current scanning piece

				if (piece != 0) { // There exists a piece at current scanning position
					if ((current_flag > 0 && piece > 0) || (current_flag < 0 && piece < 0)) { // friendly piece check if
																								// yes just break
						break;
					}
					// If not a friendly piece must be enemy piece so check the piece value
					int abs_piece = Math.abs(piece); // Just to reduce complexity we neutralize the sign of pieces
					boolean isdiagonal = (Math.abs(row_offset) == 1 && Math.abs(col_offset) == 1);

					/*
					 * This boolean isdiagonal is created to check if there is any attack only from
					 * diagonal that is from bishop (3) or diagonally moving queen (5) because
					 * diagonal offsets are always in terms of 1 and 1 no zeros
					 */
					if (isdiagonal) {
						if (abs_piece == 3 || abs_piece == 5) { // queen or bishop is found
							return false;
						}

						// Now checking for pawn color is important for pawn
						if (abs_piece == 1 && step == 1) {
							if (current_flag > 0 && row_offset == -1 && (col_offset == 1 || col_offset == -1)) {
								// This 'if' is white king turn checking for black pawn check
								return false;
							}
							if (current_flag < 0 && row_offset == 1 && (col_offset == 1 || col_offset == -1)) {
								// this 'if' is black king turn checking for white pawn check
								return false;
							}
						}
						if(abs_piece == 6 && step ==1) {
							return false; //Checking opposite king one step diagonally
						}
						
					} else {
						// now checking if it is rook(4) or straight moving queen(5)
						if (abs_piece == 4 || abs_piece == 5) {
							return false;
						}
						else if(abs_piece ==6 && step ==1) {
							return false; //Checking for opposite straight
						}
					}
					break; // safety break for in case if it finds a knight

				}
				move_row += row_offset;
				move_col += col_offset;
				step++;

			}

		}

		// checking for knight separately because it is not a sliding piece but a
		// jumping piece so create separate one 
		int[][] jump_offsets = { { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { -2, 1 }, { 2, -1 }, { 2, 1 },
				{ -2, -1 } };
		for (int i = 0; i < 8; i++) {
			int row_offset = jump_offsets[i][0];
			int col_offset = jump_offsets[i][1];

			int move_row = row_offset + king_row;
			int move_col = col_offset + king_col;

			// Boundary check of existing piece , since it is not sliding use if instead of
			// while
			if (move_row >= 0 && move_row < 8 && move_col >= 0 && move_col < 8) {
				int piece = board[move_row][move_col];

				if (piece != 0) {
					if ((current_flag > 0 && piece > 0) || (current_flag < 0 && piece < 0)) {
						continue; //Skips if the piece is friendly just a safety check
					}
					int abs_piece = Math.abs(piece);

					if (abs_piece == 2) {
						if ((current_flag > 0 && piece < 0) || (current_flag < 0 && piece > 0)) {
							// same logic check the color difference
							return false;
						}
					}
				}
			}
		}
		return true;

	}
	

}

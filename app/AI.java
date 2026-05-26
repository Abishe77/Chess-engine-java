package chess_game;

public class AI {
	Move_generator move_gen = new Move_generator();

	public static class EvalTables {

		/*
		 * This is Piece Square board/table it contains certain values which will decide
		 * the best position for each piece on the board for example a knight at center
		 * will be better than a knight at corner because from center it can move in all
		 * 8 directions whereas at corner it is restricted to 2-3
		 * 
		 * This is done because we humans know by seeing the board that "Queen staying
		 * here is safe than staying infront of an attacking rook" , to make AI
		 * understand this spatial and positional awarness we use this piece table and
		 * evaluation function.
		 */

		public static final int[][] PAWN = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 50, 50, 50, 50, 50, 50, 50, 50 },
				{ 10, 10, 20, 30, 30, 20, 10, 10 }, { 5, 5, 10, 25, 25, 10, 5, 5 }, { 0, 0, 0, 20, 20, 0, 0, 0 },
				{ 5, -5, -10, 0, 0, -10, -5, 5 }, { 5, 10, 10, -20, -20, 10, 10, 5 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

		public static final int[][] KNIGHT = { { -50, -40, -30, -30, -30, -30, -40, -50 },
				{ -40, -20, 0, 0, 0, 0, -20, -40 }, { -30, 0, 10, 15, 15, 10, 0, -30 },
				{ -30, 5, 15, 20, 20, 15, 5, -30 }, { -30, 0, 15, 20, 20, 15, 0, -30 },
				{ -30, 5, 10, 15, 15, 10, 5, -30 }, { -40, -20, 0, 5, 5, 0, -20, -40 },
				{ -50, -40, -30, -30, -30, -30, -40, -50 } };

		public static final int[][] BISHOP = { { -20, -10, -10, -10, -10, -10, -10, -20 },
				{ -10, 0, 0, 0, 0, 0, 0, -10 }, { -10, 0, 5, 10, 10, 5, 0, -10 }, { -10, 5, 5, 10, 10, 5, 5, -10 },
				{ -10, 0, 10, 10, 10, 10, 0, -10 }, { -10, 10, 10, 10, 10, 10, 10, -10 },
				{ -10, 5, 0, 0, 0, 0, 5, -10 }, { -20, -10, -10, -10, -10, -10, -10, -20 } };

		public static final int[][] ROOK = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 5, 10, 10, 10, 10, 10, 10, 5 },
				{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 },
				{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 }, { 0, 0, 0, 5, 5, 0, 0, 0 } };

		public static final int[][] QUEEN = { { -20, -10, -10, -5, -5, -10, -10, -20 }, { -10, 0, 0, 0, 0, 0, 0, -10 },
				{ -10, 0, 5, 5, 5, 5, 0, -10 }, { -5, 0, 5, 5, 5, 5, 0, -5 }, { 0, 0, 5, 5, 5, 5, 0, -5 },
				{ -10, 5, 5, 5, 5, 5, 0, -10 }, { -10, 0, 5, 0, 0, 0, 0, -10 },
				{ -20, -10, -10, -5, -5, -10, -10, -20 } };

		public static final int[][] KING = { { -30, -40, -40, -50, -50, -40, -40, -30 },
				{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -30, -40, -40, -50, -50, -40, -40, -30 },
				{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -20, -30, -30, -40, -40, -30, -30, -20 },
				{ -10, -20, -20, -20, -20, -20, -20, -10 }, { 20, 20, 0, 0, 0, 0, 20, 20 },
				{ 20, 30, 10, 0, 0, 10, 30, 20 } };
	}

	public int evaluate(Board board) {
		/*
		 * This function calculates the score of the board for all 64 squares so that AI
		 * knows whether it is losing or winning
		 */

		int score = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int piece = board.getPieceAt(i, j);
				int value = 0; // inside the loop so that it will reassign again for every piece in the board
				int[][] table = null; // an 2d array to give AI the spatial awarness

				if (piece == 0)
					continue; // If empty square just skip

				/*
				 * The logic here is getting piece from board an setting values to each piece so
				 * that evaluation function can be calculated
				 */

				switch (Math.abs(piece)) {
				case 1:
					value = 100;
					table = EvalTables.PAWN;
					break; // PAWN
				case 2:
					value = 300;
					table = EvalTables.KNIGHT;
					break; // KNIGHT
				case 3:
					value = 300;
					table = EvalTables.BISHOP;
					break; // BISHOP
				case 4:
					value = 500;
					table = EvalTables.ROOK;
					break; // ROOK
				case 5:
					value = 900;
					table = EvalTables.QUEEN;
					break; // QUEEN
				case 6:
					value = 10000;
					table = EvalTables.KING;
					break; // KING
				// Giving king a big value is because of its importance if its lost game over so
				// AI tries to protect the piece
				}

				int bonus = (piece > 0) ? table[i][j] : table[7 - i][j];

				/*
				 * This bonus is a important factor because we initialized static importance
				 * value for each piece in above switch case , so basically the work of bonus is
				 * differentiating a good and a bad piece to the evaluation score by adding
				 * their static value + their positional value. Basically this is the heuristic
				 * function h(n) for this engine that all AI use to gain awarness
				 */
				if (piece > 0) {
					score += (value + bonus);
				} else if (piece < 0) {
					score -= (value + bonus);
				}
			}
		}

		return score;

	}

	public void simulateMove(Board board, int current_row, int current_col, int target_row, int target_col) {
		int piece = board.getPieceAt(current_row, current_col);

		// Pawn promotion logic
		// Pawn promotion logic
		if (Math.abs(piece) == 1) {

			// White pawn promotion
			if (piece > 0 && target_row == 0) {
				board.setPieceAt(target_row, target_col, 5); // Queen
			}

			// Black pawn promotion
			else if (piece < 0 && target_row == 7) {
				board.setPieceAt(target_row, target_col, -5); // Queen
			}

			else {
				board.setPieceAt(target_row, target_col, piece);
			}

		} else {
			board.setPieceAt(target_row, target_col, piece);
		}

		board.setPieceAt(current_row, current_col, 0);
		board.setPieceAt(current_row, current_col, 0);
	}

	public void undoMove(Board board, int current_row, int current_col, int target_row, int target_col, int moved_piece,
			int captured_piece) {
		board.setPieceAt(current_row, current_col, moved_piece);
		board.setPieceAt(target_row, target_col, captured_piece);
	}

	/*
	 * Now Creating the MINIMAX method with ALPHA BETA pruning, The process: It will
	 * start from root of the tree where at start alpha = most negative like
	 * -infinity and beta = most positive like +infinity and traverses the tree like
	 * DFS (until the very down upto leaf) first it will make a legal move for each
	 * depth and stores the score in two variables 1. maxEval = when the depth is
	 * max it does max(val , alpha) and returns alpha, prunes if beta<=alpha 2.
	 * minEval = when the depth is min it does min(val ,beta) and returns beta
	 * ,prunes if beta<=alpha the evaluate functions comes into action when the node
	 * is leaf that is further the depth ==0
	 * 
	 * First make a move using simulateMove then call Minimax again calculate
	 * max/min Eval then find max/min out of it and undo the move
	 */

	public int minimax(int alpha, int beta, Board board, int depth, boolean isMaximizing) {
		
		System.out.println("Depth: "+depth);

		// This isMaximizing is to keep track of node which is max and which is min
		if (depth == 0)
			return evaluate(board);
		
		int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE; //Line to check the alpha takes turn
		boolean foundMove = false;

					// Scan the board to know the pieces
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					int piece = board.getPieceAt(i, j);
						if((isMaximizing && piece <=0 )||(!isMaximizing && piece>=0) ){
							continue;
						}
						for (int[] target : move_gen.getMoves(board, i, j)) {
							Board tempBoard = board.copyBoard();
		                	if (!tempBoard.ghostCheck(i, j, target[0], target[1], move_gen)) {
		                	        continue;
		                	    }
							foundMove = true;
							int target_row = target[0];
							int target_col = target[1]; // These gets the piece where to land

							if (Math.abs(board.getPieceAt(target_row, target_col)) == 6) {
							    continue;
							}
							// these two lines is to save the piece value before changing and recurring it
							 simulateMove(tempBoard, i, j, target_row, target_col);

							// Main recursive part
							int eval = minimax(alpha, beta, tempBoard, depth - 1, !isMaximizing);// Flip the depth from max
																								// to min

							
                            if(isMaximizing) {
                            	bestValue = Math.max(bestValue, eval);
                            	alpha = Math.max(alpha, eval);
                            }
                            else {
                            	bestValue = Math.min(eval, bestValue);
                            	beta = Math.min(beta, eval);
                            }
                            if(beta<=alpha) //Pruning condition
                            {
                            	return bestValue;
                            }
							
						}

					}
				}
			if(!foundMove) {
				 return isMaximizing ? -100000 : 100000;//No moves 
			}
			return bestValue;
			
			

		} 

	
		/*
		 * This method acts like a score board for example minimax returns a score of
		 * +100 then we need to find out which move leads to that +100 without that
		 * knowledge we cant win so basically minimax is giving the best the work of
		 * this method is to get the best working
		 */
	 public void bestMove(Board board, int depth, boolean aiIsWhite) {
	        int bestVal = aiIsWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	        int[] bestMove = null;

	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                int piece = board.getPieceAt(i, j);

	                if ((aiIsWhite && piece <= 0) || (!aiIsWhite && piece >= 0)) {
	                    continue;
	                }

	                for (int[] target : move_gen.getMoves(board, i, j)) {
	                	Board tempBoard = board.copyBoard();
	                	if (!tempBoard.ghostCheck(i, j, target[0], target[1], move_gen)) {
	                	        continue;
	                	    }
	                    int target_row = target[0];
	                    int target_col = target[1];

	                    int moved_piece = board.getPieceAt(i, j);
	                    int captured_piece = board.getPieceAt(target_row, target_col);
	                    if (Math.abs(captured_piece) == 6) {
	                        continue;
	                    }

	             
	                    simulateMove(tempBoard, i, j, target_row, target_col);

	                    int moveVal = minimax(Integer.MIN_VALUE,Integer.MAX_VALUE,tempBoard, depth - 1 , !aiIsWhite);
	                   
	                    if (aiIsWhite) {
	                        if (moveVal > bestVal) {
	                            bestVal = moveVal;
	                            bestMove = new int[] { i, j, target_row, target_col };
	                        }
	                    } else {
	                        if (moveVal < bestVal) {
	                            bestVal = moveVal;
	                            bestMove = new int[] { i, j, target_row, target_col };
	                        }
	                    }
	                }
	            }
	        }

	        
	        	if (bestMove != null) {
	        	    board.executeMove(bestMove[0], bestMove[1], bestMove[2], bestMove[3]);
	        	}
	        }
	    }
	


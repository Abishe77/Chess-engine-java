# Chess engine in Java

# Completed:
1.Board representation
2.Move generation for all pieces
3.OOP refactor and optimization using vector/array offsets

# Working on:
1.Legal move validation
2.Check/Checkmate detection

# Future Goals:
1.GUI
2.Minimax AI (Alpha beta pruning)
3.Move Optimization

# Breakdown of each file
1.Chess_engine.java : It contains my first raw procedural type of code including all together - board , pieces , move generation , testing branch
2.Board.java : It contains the board of 8x8 chess board representation
3.Piece.java: It contains all pieces (white and black) and have them arranged in the board using Board class
4.Move_generator.java: (a)This contains all possible moves for all pieces - pawn , knight , bishop , rook , queen , king 
                       (b) This is the optimized file where I used vector offsets instead of repeating while loops implementing DRY principle
5.Test.java: It is the testing file where the whole code is tested

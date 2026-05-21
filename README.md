# Chess Engine in Java

## Completed

1. Board representation  
2. Move generation for all pieces  
3. OOP refactor and optimization using vector/array offsets  

## Working On

1. Legal move validation  
2. Check/checkmate detection  

## Future Goals

1. GUI  
2. Minimax AI (Alpha-beta pruning)  
3. Move optimization  

## Breakdown of Each File

1. Chess_engine.java  
   - Contains the first raw procedural version including board, pieces, move generation, and testing.

2. Board.java  
   - Contains the 8x8 chess board representation.

3. Piece.java  
   - Contains all pieces (white and black) and board arrangement logic.

4. Move_generator.java  
   - Contains move generation logic for pawn, knight, bishop, rook, queen, and king.
   - Optimized using vector offsets and DRY principle.

5. Test.java  
   - Testing file for validating engine behavior.

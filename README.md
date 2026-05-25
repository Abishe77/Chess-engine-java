Chess Engine — Java 

A scratch-built, object-oriented chess engine written entirely in Java — no external libraries, no premade engines, no shortcuts. Every system was implemented from first principles: board representation, legal move generation, king safety validation, checkmate detection, and a working Minimax AI with Alpha-Beta pruning.

Built from a blank file using handwritten planning and algorithm design before implementation.

Features
Core Chess Logic
Full move generation for all 6 pieces
Pawn
Knight
Bishop
Rook
Queen
King
Complete legal move validation
Check detection
Checkmate detection
Stalemate detection
Turn-based move enforcement
Pawn promotion system
Illegal move rejection
AI Engine
Minimax AI

Implemented a recursive Minimax search tree capable of evaluating future board states and selecting optimal moves.

Alpha-Beta Pruning

Optimized the Minimax traversal using Alpha-Beta pruning to eliminate unnecessary branches and drastically improve performance.

Heuristic Evaluation Function

Custom evaluation system using:

Material scoring
Piece-square tables
Positional bonuses
Spatial control awareness
Ghost Board Simulation

Every candidate move is simulated on a copied board before evaluation to avoid corrupting the real game state during recursion.

Engine Architecture
Vector Offset Optimization

Knight and king movement use precomputed 2D offset arrays instead of repetitive conditionals.

Sliding Piece Ray Traversal

Bishop, rook, and queen movement implemented using directional vectors and ray traversal loops.

King Safety Scanner

Custom attack detection system using:

8-directional ray casting
Knight jump verification
Pawn diagonal attack checks
Opposing king proximity detection
Legal Move Filtering

Moves that expose the king are automatically filtered using sandboxed ghost simulations before execution.

GUI

Built using Java Swing.

Features:

Interactive chessboard
Click-based movement
Valid move highlighting
Check indication
AI vs Player mode
Player vs Player mode
Real-time board updates
Promotion popup interface
Project Structure
src/chess_game/

├── AI.java
│   # Minimax AI, Alpha-Beta pruning, evaluation function

├── Board.java
│   # Board state, move execution, ghost simulation,
│   # checkmate/stalemate detection

├── Move_generator.java
│   # Piece move generation + king safety scanner

├── GUI.java
│   # Java Swing interface and event handling

├── Piece.java
│   # Piece constants and helper utilities

└── Test.java
    # Manual testing
Board Representation

The chessboard is represented using an 8x8 int[][] grid.

White Pieces  -> Positive Integers
Black Pieces  -> Negative Integers
Empty Square  -> 0
Pawn   = 1
Knight = 2
Bishop = 3
Rook   = 4
Queen  = 5
King   = 6
How Legal Move Validation Works
Generate pseudo-legal moves
Clone the current board
Simulate the move on the cloned board
Locate the current player's king
Run king safety verification
Reject the move if king becomes exposed

This prevents:

Moving into check
Ignoring checks
Illegal king movement
Pinned piece violations
Check Detection

The engine scans from the king’s position using directional rays.

Threats detected:

Rook attacks
Bishop attacks
Queen attacks
Pawn attacks
Knight jumps
Adjacent enemy king
Endgame Detection

checkDetection() determines game state after every move:

State	Return Value
Checkmate	0
Stalemate	1
Normal Play	2
AI Search Strategy

The engine recursively explores future positions using:

Minimax(depth)
├── Max Player (AI)
└── Min Player (Opponent)

Optimized with:

Alpha-Beta pruning
Move simulation
Recursive board evaluation
Built With
Java
Java Swing
Eclipse IDE
Handwritten algorithm planning
Design Philosophy

This project was intentionally built without chess libraries or engine frameworks to deeply understand:

Game tree search
Recursive algorithms
Board state management
AI heuristics
Spatial evaluation
Object-oriented engine design
Roadmap
 Board representation
 Move generation
 Legal move filtering
 Check/checkmate detection
 GUI interface
 Minimax AI
 Alpha-Beta pruning
 Castling
 En passant
 Opening book
 Move ordering optimization
 Transposition tables
 Iterative deepening
 Zobrist hashing
Preview
Player Move → Legal Validation → Ghost Simulation
→ Minimax Search → Alpha-Beta Pruning
→ Best Move Selection → Board Update
Author

Built entirely from scratch as a deep dive into chess engine architecture, recursive AI systems, and low-level game logic implementation in Java.
  
  


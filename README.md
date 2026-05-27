# Chess Engine in Java

A chess engine built from scratch in Java — covering everything from board representation and legal move generation to a fully playable game with a Swing GUI and an AI opponent powered by Minimax with Alpha-Beta pruning.

---

## Features

- **8×8 Board Representation** — 2D integer array with signed piece encoding (positive = white, negative = black)
- **Move Generation** — Full pseudo-legal move generation for all 6 piece types using vector/direction offsets
- **Legal Move Validation** — Ghost board simulation to filter out moves that leave the king in check
- **Check Detection** — Ray-scanning + knight-jump king safety checker covering all attack vectors
- **Pawn Promotion** — Auto-promotes to queen on reaching the back rank
- **AI Opponent (Minimax + Alpha-Beta Pruning)** — Searches the game tree to a configurable depth, pruning branches that can't affect the result
- **Piece-Square Tables** — Positional heuristics for all 6 piece types, giving the AI spatial awareness beyond raw material count
- **Swing GUI** — Fully playable interface with a dark-themed board, piece images, valid move dots, check highlighting, pawn promotion dialog, and checkmate/stalemate detection
- **Two Game Modes** — Player vs Player and Player vs AI, selectable at startup

---

## Project Structure

```
src/chess_game/
├── Chess_engine.java     # Original procedural version (board, pieces, move generation)
├── Board.java            # 8×8 board representation and board operations
├── Piece.java            # Piece definitions and color helpers
├── Move_generator.java   # Move generation for all pieces + king safety checks
├── AI.java               # Evaluation function, Minimax, Alpha-Beta pruning
├── GUI.java              # Swing GUI — board rendering, mouse input, game loop
└── Test.java             # Test harness for validating engine behavior
```

---

## How the AI Works

### Evaluation Function
Every board position is scored by summing material values and positional bonuses for each piece:

| Piece  | Value |
|--------|-------|
| Pawn   | 100   |
| Knight | 300   |
| Bishop | 300   |
| Rook   | 500   |
| Queen  | 900   |
| King   | 10000 |

Each piece also has a **Piece-Square Table** that rewards good squares (e.g. knights in the center, rooks on open files) and penalises bad ones (e.g. knights on the rim).

### Minimax with Alpha-Beta Pruning
The engine uses depth-limited Minimax search:
- **Maximising** nodes try to maximise the evaluation score (white's perspective)
- **Minimising** nodes try to minimise it (black's perspective)
- **Alpha-Beta pruning** cuts off branches where `beta <= alpha`, drastically reducing the number of nodes evaluated without affecting the result

Each move is simulated on a copied board, validated for legality (no leaving own king in check), scored recursively, then undone.

---

## GUI

Built with Java Swing. Features include:

- **Dark-themed board** — deep navy and steel blue squares with a glowing border
- **Valid move indicators** — white dots rendered on legal target squares when a piece is selected
- **Check highlight** — red glowing border around the king's square when in check
- **Pawn promotion dialog** — popup to choose Queen, Rook, Bishop, or Knight on reaching the back rank
- **Checkmate & stalemate detection** — dialog shown at end of game
- **Threaded AI moves** — AI runs on a background thread so the UI stays responsive

The AI plays as black at depth 4 in Player vs AI mode.

---

Move generation uses **direction offset arrays** for each piece type:

- **Knight & King** — Fixed jump offsets, single iteration
- **Bishop, Rook, Queen** — Sliding pieces use `while` loops along each direction, stopping on collision
- **Pawn** — Direction-aware (white moves up, black moves down), handles double push from starting rank and diagonal captures
- **King Safety** — A ray-scan from the king's position checks all 8 directions for sliding attackers + a separate knight-jump scan, used to validate legal moves

---

## Getting Started

### Prerequisites
- Java 11+
- Eclipse IDE (or any Java IDE)

### Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Abishe77/Chess-engine-java.git
   ```
2. Open in Eclipse as an existing Java project
3. Run `GUI.java` to launch the game, or `Test.java` to validate move generation

---

## What's Next

- [ ] GUI (Java Swing or JavaFX)
- [ ] Iterative deepening with time management
- [ ] Quiescence search (avoid horizon effect)
- [ ] Move ordering (killer moves, history heuristic)
- [ ] Transposition table with Zobrist hashing
- [ ] UCI protocol for Lichess integration

---

## Built With

- Java (100%)
- Eclipse IDE

---

*Built from scratch as a personal project — no external chess libraries used.*
  
  


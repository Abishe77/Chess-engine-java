# Chess Engine — Java from scratch

A scratch-built, object-oriented chess engine in Java. No libraries. No shortcuts. Pure logic.

Built from a blank file with handwritten planning — board representation, move generation, legal filtering, and checkmate detection all implemented from first principles.

---

## Features

- **Full Move Generation** — All 6 pieces: pawn, knight, bishop, rook, queen, king
- **Vector Offset Optimization** — Knight and king moves use 2D offset arrays instead of repetitive conditionals
- **Ghost Board Simulation** — Every move is tested on a sandboxed copy of the board before execution
- **Ray Casting (King Safety)** — 8-directional ray scan + knight jump pattern to detect check
- **Legal Move Filtering** — Moves that leave the king in check are silently rejected
- **Checkmate & Stalemate Detection** — Engine determines if the game is over after every move
- **Turn Management** — Flag-based system enforces alternating turns

---

## Project Structure

```
src/chess_game/
├── Board.java           # 8x8 grid state, makeMove(), ghostCheck(), checkDetection()
├── Move_generator.java  # Move logic for all pieces + isKingSafe() ray scanner
├── Piece.java           # Piece constants + isWhite(), isBlack(), isOpponent() helpers
└── Test.java            # Manual test cases
```

---

## How It Works

### Board Representation
The board is an 8x8 `int[][]` grid. Positive integers = white pieces, negative = black, zero = empty.

```
Pawn=1, Knight=2, Bishop=3, Rook=4, Queen=5, King=6
```

### Move Generation
Each piece has a dedicated method returning `ArrayList<int[]>` of valid target positions. Sliding pieces (bishop, rook, queen) use `while` loops with direction offsets. Non-sliding pieces (knight, king) use precomputed offset arrays.

### Legal Move Filtering
Before any move executes, `ghostCheck()` clones the board, simulates the move, locates the current player's king, and calls `isKingSafe()`. If the king is exposed, the move is rejected.

### Check Detection
`isKingSafe()` fires rays in all 8 directions from the king's position and checks for threatening pieces. Knights are handled separately using jump offsets.

### Checkmate / Stalemate
`checkDetection()` iterates over all friendly pieces, generates their legal moves, and runs each through `ghostCheck()`. If no legal move exists:
- King is in check → **Checkmate** (returns `0`)
- King is safe → **Stalemate** (returns `1`)
- Legal moves exist → **Continue** (returns `2`)

---

## Design Blueprints

All logic was planned by hand before coding. Notebook photos covering board layout, piece movement rules, attack patterns, ray casting, and ghost simulation are in [`/docs`](./docs).

---

## Roadmap

- [x] Board representation
- [x] Move generation (all 6 pieces)
- [x] OOP refactor with vector offsets
- [x] Legal move filtering
- [x] King safety (ray casting)
- [x] Checkmate & stalemate detection
- [ ] Minimax AI with Alpha-Beta pruning
- [ ] GUI (Java Swing or JavaFX)

---

## Built With

- Java (no external libraries)
- Eclipse IDE
- A notebook and a pen
  
##  Design Blueprints & Hand-Drawn Logic
* [View all original handwritten engineering notes and vector layout sheets directly in the repository gallery.](https://github.com/Abishe77/Chess-engine-java/tree/main/docs)

  


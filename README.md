# Chess Engine (Java) built from Scratch

A lightweight, object-oriented chess backend engine written in Java. The engine manages board state representation, alternates player turns sequentially, and evaluates move safety using a simulated sandbox pipeline.

## Core Features
* **Board State Matrix:** Built on a private 2D grid setup that handles piece tracking and coordinate validation.
* **Ghost Simulation Board (`ghostCheck`):** Duplicates the live game board layout into an isolated sandbox to test-fire potential player moves.
* **King Threat Radar:** Scans 8-directional sniper paths and knight jumping grids to ensure a player's move never leaves or puts their own King in check.
* **Sequential Turn Handler:** Alternates player control seamlessly while blocking out-of-turn execution.

## Project Structure
* `Board.java`: Manages the private grid state, executes final validated moves, and handles turn flags.
* `Move_generator.java`: Computes raw piece paths and acts as the threat radar scanning system for the King.
* `Piece.java`: Defines unique integer IDs for pieces and handles team color logic.
3. Piece.java  
   - Contains all pieces (white and black) and board arrangement logic.

4. Move_generator.java  
   - Contains move generation logic for pawn, knight, bishop, rook, queen, and king.
   - Optimized using vector offsets and DRY principle.

5. Test.java  
   - Testing file for validating engine behavior.
  
##  Design Blueprints & Hand-Drawn Logic
* [View all 18 original handwritten engineering notes and vector layout sheets directly in the repository gallery.](https://github.com/Abishe77/Chess-engine-java/tree/main)
  


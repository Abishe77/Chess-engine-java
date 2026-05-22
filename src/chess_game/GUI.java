package chess_game;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements MouseListener {
    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 8 * TILE_SIZE;
    
    private HashMap<Integer, Image> pieceImages = new HashMap<>();
    private Board board = new Board(); 

    public GUI() {
        JFrame frame = new JFrame("Chess Engine");
        this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        addMouseListener(this);
        loadImages();
    }

    private void loadImages() {
        String folderPath = "src/chess_game/Pieces/";
        for (int i = 1; i <= 6; i++) {
            pieceImages.put(i, new ImageIcon(folderPath + i + ".png").getImage());
            pieceImages.put(-i, new ImageIcon(folderPath + "-" + i + ".png").getImage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                g.setColor((row + col) % 2 == 0 ? new Color(173, 216, 230) : new Color(70, 130, 180));
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        if (board.getCheckKingRow() != -1) {
            g.setColor(new Color(255, 0, 0, 150));
            g.fillRect(board.getCheckKingCol() * TILE_SIZE, board.getCheckKingRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        g.setColor(new Color(0, 0, 0, 80));
        for (int[] move : board.getCurrentValidMoves()) {
            g.fillOval(move[1] * TILE_SIZE + 25, move[0] * TILE_SIZE + 25, 30, 30);
        }
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int piece = board.getPieceAt(row, col);
                if (piece != 0) {
                    g.drawImage(pieceImages.get(piece), col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        board.handleInput(e.getY() / TILE_SIZE, e.getX() / TILE_SIZE);
        repaint();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public static void main(String[] args) {
        new GUI();
    }
}

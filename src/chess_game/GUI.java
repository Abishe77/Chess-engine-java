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
        Color darkModeColor = new Color(15, 15, 15);
        frame.getContentPane().setBackground(darkModeColor);
        this.setBackground(darkModeColor);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
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
    	Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Now use g2 for all your drawing: g2.fillRect, g2.drawImage, etc.
        
        // 1. Draw Board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                g.setColor((row + col) % 2 == 0 ? new Color(20,25,60) : new Color(100,150,210));
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
       
            
            g2d.setColor(new Color(220, 220, 255)); // neon blue
            g2d.setStroke(new BasicStroke(6)); // border thickness
            g2d.drawRect(0, 0, BOARD_SIZE-1, BOARD_SIZE-1);
        }

        // 2. Draw Check (RED highlight)
        if (board.getCheckKingRow() != -1) {
            // Outer glow
            g2d.setColor(new Color(255, 0, 0, 100));
            g2d.setStroke(new BasicStroke(6));
            g2d.drawRect(board.getCheckKingCol() * TILE_SIZE + 3, board.getCheckKingRow() * TILE_SIZE + 3, TILE_SIZE - 6, TILE_SIZE - 6);
            
            // Inner bright border
            g2d.setColor(new Color(255, 0, 0, 220));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(board.getCheckKingCol() * TILE_SIZE + 5, board.getCheckKingRow() * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10);
        }

        // 3. Draw Dots (VALID MOVES)
        for (int[] move : board.getCurrentValidMoves()) {
            // Outer glow
            g2d.setColor(new Color(255, 255, 255, 80));
            g2d.fillOval(move[1] * TILE_SIZE + 28, move[0] * TILE_SIZE + 28, 24, 24);
            // Inner bright dot
            g2d.setColor(new Color(255, 255, 255, 200));
            g2d.fillOval(move[1] * TILE_SIZE + 32, move[0] * TILE_SIZE + 32, 16, 16);
        }
        
        // 4. Draw Pieces
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
        int row = e.getY() / TILE_SIZE;
        int col = e.getX() / TILE_SIZE;

        // 1. Handle Promotion
        if (board.pendingPromotionRow != -1) {
            String[] options = {"Queen", "Rook", "Bishop", "Knight"};
            int choice = JOptionPane.showOptionDialog(this, "Promote to:", "Promotion",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            // Map choice index to piece values: 
            // 0:Queen(1), 1:Rook(2), 2:Bishop(3), 3:Knight(4)
            int pieceCode = (choice == 0) ? 1 : (choice == 1) ? 2 : (choice == 2) ? 3 : 4;
            
            board.promotePawn(board.pendingPromotionRow, board.pendingPromotionCol, pieceCode);
            
            // Reset flags after promotion
            board.pendingPromotionRow = -1;
            board.pendingPromotionCol = -1;
            
            // Trigger check detection again to see if the promotion put the opponent in check
            board.checkDetection(); 
            repaint();
        } 
        // 2. Handle Normal Input
        else {
            board.handleInput(row, col);
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
   
}

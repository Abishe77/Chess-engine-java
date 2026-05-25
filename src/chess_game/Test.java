
package chess_game;

import javax.swing.SwingUtilities;

public class Test {
    public static void main(String[] args) {
        // This ensures the GUI is created on the correct thread
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }
}

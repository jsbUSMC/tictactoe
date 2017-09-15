import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameUI extends JFrame {
    // Window height and width set as constants
    private static final int WIDTH = 750;
    private static final int HEIGHT = 750;
    private static final int DISTANCE = 125;

    private Board board;
    private JPanel gamePanel;
    private char player1 = 'X';
    private char player2 = 'O';
    private Point[] gridLocs;

    public GameUI() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the GUI Components
        this.gamePanel = new JPanel();
        Container c = getContentPane();
        c.add(gamePanel);
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.addMouseListener(new ClickListener());

        setResizable(false);
        pack();
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Todo: implement the methods to mark a cell
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}

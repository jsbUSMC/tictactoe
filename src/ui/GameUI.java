package ui;

import game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;

public class GameUI extends JFrame {
    // Window height and width set as constants
    private static final int WIDTH = 750;
    private static final int HEIGHT = 750;
    private static final int DISTANCE = 125;

    private Board board;
    private GamePanel gamePanel;
    private char player1 = 'X';
    private char player2 = 'O';
    private Point[] gridLocs;

    private GameUI() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the GUI Components
        this.gamePanel = new GamePanel();
        this.gamePanel.registerCustomFont();
        Container c = getContentPane();
        c.add(gamePanel);
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.addMouseListener(new ClickListener());

        setResizable(false);
        pack();
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createGrid() {
        this.gridLocs = new Point[9];

        this.gridLocs[0] = new Point(125,125);
        this.gridLocs[1] = new Point(375,125);
        this.gridLocs[2] = new Point(625,125);
        this.gridLocs[3] = new Point(125,375);
        this.gridLocs[4] = new Point(375,375);
        this.gridLocs[5] = new Point(625,375);
        this.gridLocs[6] = new Point(125,625);
        this.gridLocs[7] = new Point(375,625);
        this.gridLocs[8] = new Point(625,625);
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            setPanelProps(g2);
            paintGrid(g2);
        }

        private void registerCustomFont() {
            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                InputStream inconsolataFont = GamePanel.class.getResourceAsStream("ui/Inconsolata.otf");
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, inconsolataFont));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }

        private void paintGrid(Graphics2D g) {
            String mark = "inconsolata";
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.getAllFonts();
            Font inconsolata = new Font("Inconsolata", Font.BOLD, 24);
            g.setFont(inconsolata);
            g.setColor(new Color(0,37,56));


            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    g.drawString(mark, (125 + (190 * x)), (125 + (190 * y)));
                }
            }
        }

        private void setPanelProps(Graphics2D g) {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawString("", 0, 0);
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}

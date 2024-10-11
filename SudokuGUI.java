import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI {
    private static final int SIZE = 9;
    private JFrame frame;
    private JTextField[][] grid;
    private JButton solveButton;

    public SudokuGUI() {
        frame = new JFrame("Sudoku Solver");
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(SIZE, SIZE));
        grid = new JTextField[SIZE][SIZE];
        
        // Initialize the grid of text fields
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new JTextField(2);
                grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                grid[i][j].setFont(new Font("Arial", Font.PLAIN, 24));
                panel.add(grid[i][j]);
            }
        }
        
        solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.BOLD, 18));
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solvePuzzle();
            }
        });
        
        frame.add(panel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Converts the user input into a 2D array of integers
    private int[][] getUserInput() {
        int[][] board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                try {
                    String text = grid[i][j].getText().trim();
                    if (!text.isEmpty()) {
                        board[i][j] = Integer.parseInt(text);
                    }
                } catch (NumberFormatException ex) {
                    board[i][j] = 0;  // Empty cell
                }
            }
        }
        return board;
    }

    // Displays the solved Sudoku grid
    private void displaySolution(int[][] solvedBoard) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j].setText(solvedBoard[i][j] == 0 ? "" : String.valueOf(solvedBoard[i][j]));
            }
        }
    }

    // Solves the puzzle and updates the GUI
    private void solvePuzzle() {
        int[][] board = getUserInput();
        if (SudokuSolver.solveSudoku(board)) {
            displaySolution(board);
        } else {
            JOptionPane.showMessageDialog(frame, "No solution exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI());
    }
}

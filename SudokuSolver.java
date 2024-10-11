public class SudokuSolver {

    public static final int SIZE = 9;
    
    // Method to check if it's safe to place a number at board[row][col]
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if the number is already in the row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if the number is already in the column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if the number is in the 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Backtracking function to solve the Sudoku puzzle
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {  // Check for empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0;  // Backtrack if placing num didn't work
                        }
                    }
                    return false;
                }
            }
        }
        return true;  // Sudoku is solved
    }

    // Helper function to print the board (used for testing purposes)
    public static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}



public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        // assume that board is 9X9 and char is only '.' or '1' ~ '9'
        helper(board, 0);
    }
    private boolean helper(char[][] board, int n) {
        // base case, 0 <= n <= 80
        if (n == 81) {
            return true;    // use a boolean to stop calculating when we've found the right answer
        }

        // general case
        int row = n / 9, col = n % 9;
        if (board[row][col] != '.') {
            return helper(board, n + 1);
        }

        for (int i = 1; i <= 9; i++) {
            board[row][col] = (char)(i + '0');
            if (valid(board, row, col) && helper(board, n + 1)) {
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }

    private boolean valid(char[][] board, int row, int col) {
        // check row
        for (int i = 0; i < 9; i++) {
            if (i != row && board[row][col] == board[i][col]) {
                return false;
            }
        }
        // check col
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][col] == board[row][i]) {
                return false;
            }
        }
        // check square
        int r0 = (row / 3) * 3;   // the current square:  most left-right corner row number
        int c0 = (col / 3) * 3;   // the current square:  most left-right corner col number
        for (int i = 0; i < 9; i++) {
            int curRow = r0 + (i / 3);
            int curCol = c0 + (i % 3);
            if (!(curRow == row && curCol == col) && board[row][col] == board[curRow][curCol]) {
                return false;
            }
        }
        return true;

        // the above three for loop could be written in on loop body
        /**
         int r = (row / 3) * 3 + (i / 3);
         int c = (col / 3) * 3 + (i % 3);
         for (int i = 0; i < 9; i++) {

            if ((i != row && board[row][col] == board[i][col]) ||        // row
                (i != col && board[row][col] == board[row][i]) ||        // col
                ((r != row || c != col) && board[row][col] == board[r][c])) {  // square
                return false;
            }
         }
        */
    }

    /////////////////// TEST //////////////////////
    public static void main(String[] args) {
        char[][] a = {
                "..9748...".toCharArray(),
                "7........".toCharArray(),
                ".2.1.9...".toCharArray(),
                "..7...24.".toCharArray(),
                ".64.1.59.".toCharArray(),
                ".98...3..".toCharArray(),
                "...8.3.2.".toCharArray(),
                "........6".toCharArray(),
                "...2759..".toCharArray()};

        new SudokuSolver().solveSudoku(a);
        int N = 9;
        for (int i = 0; i < N*N; i++) {
            System.out.print(a[i/N][i%N]);
            if (i%N == N-1) System.out.print('\n');
        }
    }
}


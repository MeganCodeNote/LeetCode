import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class NQueen {
    //--------------- Solution 1 --------------------//
    // use a board as a solution builder
    public List<String[]> solveNQueens(int n) {
        // input checking
        List<String[]> res = new ArrayList<String[]>();
        if (n <= 0) {
            return res;
        }
        // use a 2D array as solution builder
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        helper(res, board, n, 0);
        return res;
    }

    private void helper(List<String[]> res, char[][] board, int n, int row) {
        // base case
        if (row == n) {
            res.add(toResult(board, n));
            return;
        }

        // general case
        for (int col = 0; col < n; col++) {
            board[row][col] = 'Q';
            if (valid(board, n, row, col)) {
                helper(res, board, n, row + 1);
            }
            board[row][col] = '.';
        }
    }

    private String[] toResult(char[][] board, int n) {
        String[] solution = new String[n];
        for (int i = 0; i < n; i++) {
            solution[i] = String.valueOf(board[i]);
        }
        return solution;
    }

    private boolean valid(char[][] board, int n, int row, int col) {
        for (int offset = 1; offset <= row; offset++) {
            if (board[row - offset][col] == 'Q') {
                return false;
            }
            if (col - offset >= 0 && board[row - offset][col - offset] == 'Q') {
                return false;
            }
            if (col + offset < n && board[row - offset][col + offset] == 'Q') {
                return false;
            }
        }
        return true;
    }


    //--------------- Solution 2 --------------------//
    // interpreter this as a permutation problem
    // perm={1, 2, 4, 5, 3} -- index: row -- perm[index]: col
    public List<String[]> solveNQueens2(int n) {
        // input checking
        List<String[]> res = new ArrayList<String[]>();
        if (n <= 0) {
            return res;
        }
        // use a 1D-array to indicate solution
        helper(res, new ArrayList<Integer>(), n);
        return res;
    }

    private void helper(List<String[]> res, List<Integer> item, int n) {
        if (item.size() == n) {
            res.add(toResult(item, n));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (item.contains(i)) {
                continue;
            }
            item.add(i);
            if (valid(item)) {
                helper(res, item, n);
            }
            item.remove(item.size() - 1);
        }
    }

    private String[] toResult(List<Integer> item, int n) {
        String[] solution = new String[n];
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[item.get(i)] = 'Q';
            solution[i] = String.valueOf(row);
        }
        return solution;
    }

    private boolean valid(List<Integer> item) {
        int k = item.size() - 1;
        for (int i = 0; i < item.size() - 1; i++) {
            if (Math.abs(item.get(i) - item.get(k)) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    ////////////////////////   TEST   ///////////////////////////
    public static void main(String[] args) {
        List<String[]> results = (new NQueen()).solveNQueens(5);
        for (String[] sl: results) {
            for (String s: sl) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}


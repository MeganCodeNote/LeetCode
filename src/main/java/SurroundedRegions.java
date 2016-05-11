/* Created by meganlee on 11/15/14. */

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {
    public void solve(char[][] board) {
        // input checking
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        // initialize
        Queue<Integer>  queue = new LinkedList<Integer>();
        int rows = board.length, cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            enqueue(queue, board, i, 0);
            enqueue(queue, board, i, cols - 1);
        }
        for (int j = 1; j < cols - 1; j++) {
            enqueue(queue, board, 0, j);
            enqueue(queue, board, rows - 1, j);
        }

        // fill
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / cols, y = cur % cols;

            board[x][y] = '#';
            enqueue(queue, board, x - 1, y);
            enqueue(queue, board, x + 1, y);
            enqueue(queue, board, x, y - 1);
            enqueue(queue, board, x, y + 1);
        }

        // change
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = '0';
                }
            }
        }
    }

    public static void enqueue(Queue<Integer> queue, char[][] board, int x, int y) {
        int rows = board.length, cols = board[0].length;
        if (0 <= x && x < rows && 0 <= y && y < cols && board[x][y] == 'O'){
            queue.offer(x * cols + y);
        }
    }
}
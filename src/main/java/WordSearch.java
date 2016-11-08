public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int M = board.length, N = board[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (helper(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int cur, int i, int j) {
        // base case
        if (cur == word.length()) {
            return true;
        }

        // validate parameter
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(cur)) {
            return false;
        }

        // travel 4 directions
        board[i][j] = '#';
        if (helper(board, word, cur + 1, i - 1, j) || helper(board, word, cur + 1, i + 1, j) ||
            helper(board, word, cur + 1, i, j - 1) || helper(board, word, cur + 1, i, j + 1)
        ) {
            return true;
        }
        board[i][j] = word.charAt(cur);
        return false;
    }

    //////////////////    TEST   ////////////////////
    public static void main(String[] args) {
        char[][] board = {"bb".toCharArray(), "ab".toCharArray(), "ba".toCharArray()};
        System.out.println((new WordSearch()).exist(board, "abbbab"));
    }
}

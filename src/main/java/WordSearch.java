public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int M = board.length, N = board[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (helper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int i, int j, int cur) {
        // base case
        if (cur == word.length()) {
            return true;
        }

        // validate parameter
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
                board[i][j] != word.charAt(cur)) {
            return false;
        }

        // travel 4 directions
        board[i][j] = '#';
        if (helper(board, word, i - 1, j, cur + 1) ||
                helper(board, word, i + 1, j, cur + 1) ||
                helper(board, word, i, j - 1, cur + 1) ||
                helper(board, word, i, j + 1, cur + 1)) {
            return true;
        }
        board[i][j] = word.charAt(cur);
        return false;
    }

    //////////////////    TEST   ////////////////////
    public static void main(String[] args) {
        char[][] board = {"bb".toCharArray(),
                "ab".toCharArray(),
                "ba".toCharArray()};
        System.out.println((new WordSearch()).exist(board, "abbbab"));
    }
}

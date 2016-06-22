
public class EditDistance {
    public int minDistance(String word1, String word2) {
        // input validation
        if (word1 == null || word2 == null) {
            return Integer.MAX_VALUE;
        }

        // initialization
        int M = word1.length(), N = word2.length();
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= N; j++) {
            dp[0][j] = j;
        }

        // transition
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[M][N];
    }
}

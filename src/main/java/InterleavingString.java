/* Created by meganlee on 10/24/14. */

public class InterleavingString {
    //-------------------- Solution 1 --------------------//
    // dp, dp[i][j] -> put initalization inside for loop
    public boolean isInterleave(String s1, String s2, String s3) {
        // input validation
        if (s1 == null || s2 == null || s3 == null ||
            s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // define status and initialize
        // sometimes we could write the initialization inside fill in loop process
        int M = s1.length(), N = s2.length();
        boolean dp[][] = new boolean[M + 1][ N+ 1];
        dp[0][0] = true;
        for (int i = 1; i <= M; i++) {
            dp[i][0] = dp[i - 1][0] && (s3.charAt(i - 1) == s1.charAt(i - 1));
        }
        for (int j = 1; j <= N; j++) {
            dp[0][j] = dp[0][j - 1] && (s3.charAt(j - 1) == s2.charAt(j - 1));
        }

        // use transition function to fill in the dp table
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                           dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        return dp[M][N];
    }

    //-------------------- Solution 2 --------------------//
    // code optimization of solution1
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }

        int M = s1.length(), N = s2.length();
        boolean[][] dp = new boolean[M + 1][N + 1];
        dp[0][0] = true;
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                if (j > 0 && s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                } else if (i > 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[M][N];
    }
}

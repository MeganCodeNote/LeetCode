import java.util.Arrays;

public class ScrambleString {
    //----------------   Solution 1  ----------------------//
    // DP O(N^3)
    public boolean isScramble(String s1, String s2) {
        // input checking
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        // fast return and base case (len == 1)
        if (s1.equals(s2)) {
            return true;
        }

        // define status table and intial values
        int N = s1.length();
        boolean[][][] dp = new boolean[N][N][N + 1];  // [s1.start][s2.start][len], len == 0 is not used!!
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // start fill in the table len 2 --> N
        for (int len = 2; len <= N; len++) {
            for (int i = 0; i <= N - len; i++) {
                for (int j = 0; j <= N - len; j++) {
                    for (int split = 1; split <= len - 1; split++) {
                        boolean noSwap = dp[i][j][split] && dp[i + split][j + split][len - split];
                        boolean swap = dp[i][j + len - split][split] && dp[i + split][j][len - split];
                        if (noSwap || swap) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][N];
    }
    
    //----------------   Solution 1  ----------------------//
    // Recursion
    public boolean isScramble2(String s1, String s2) {
        // input checking
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        // fast return and base case (len == 1)
        if (s1.equals(s2)) {
            return true;
        }

        // add the pruning so that there won't be time limit exceeded
        // if two strings are not anagrams of each other, return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        if (!new String(str1).equals(new String(str2))) {
            return false;
        }

        // recursive calls
        int N = s1.length();
        for (int split = 1; split <= N - 1; split++) {
            boolean noSwap = isScramble(s1.substring(0, split), s2.substring(0, split)) &&
                             isScramble(s1.substring(split, N), s2.substring(split, N));
            boolean swap = isScramble(s1.substring(0, split), s2.substring(N - split, N)) &&
                           isScramble(s1.substring(split, N), s2.substring(0, N - split));
            if (swap || noSwap) {
                return true;
            }
        }
        return false;
    }
}


//NOTE  3:  We can use bit operator on booleans
//    boolean a = false;
//    boolean b = true;
//    boolean c = a & b; // false
//    boolean d = a | b; // true;

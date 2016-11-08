import java.util.Arrays;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        // input validation
        if (s1 == null || s2 == null || s3 == null ||
            s1.length() + s2.length() != s3.length() || isInvalid(s1 + s2, s3)) {
            return false;
        }

        if (s1.length() == 0) {      //--- base case
            return s2.equals(s3);
        } else if (s2.length() == 0) {
            return s1.equals(s3);

        } else {                    //--- general case
            boolean matchS1 = s3.charAt(0) == s1.charAt(0) && isInterleave(s1.substring(1), s2, s3.substring(1));
            boolean matchS2 = s3.charAt(0) == s2.charAt(0) && isInterleave(s1, s2.substring(1), s3.substring(1));
            return matchS1 || matchS2;
        }
    }

    private boolean isInvalid(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return !(new String(arr1).equals(new String(arr2)));
    }

    //-------------------- Solution 2 --------------------///
    // recursion with cache
    public boolean isInterleave2(String s1, String s2, String s3) {
        // input validation
        if (s1 == null || s2 == null || s3 == null ||
            s1.length() + s2.length() != s3.length() || isInvalid(s1 + s2, s3)) {
            return false;
        }

        // create cache
        int[][] cache = new int[s1.length() + 1][s2.length() + 1]; // NOTICE!! don't forget +1!
        // here we save initialization, we'll use 1 as true, -1 as false
        return helper(s1, s2, s3, 0, 0, cache);
    }

    private boolean helper(String s1, String s2, String s3, int i, int j, int[][] cache) {
        // update cache
        int M = s1.length(), N = s2.length(), k = i + j;
        if (cache[i][j] == 0) {
            if (i == M) {           //-- base case
                cache[i][j] = s2.substring(j).equals(s3.substring(k)) ? 1 : -1; // remember to set for false value (-1)
            } else if (j == N) {
                cache[i][j] = s1.substring(i).equals(s3.substring(k)) ? 1 : -1;

            } else {                //-- general case
                boolean matchS1 = s3.charAt(k) == s1.charAt(i) && helper(s1, s2, s3, i + 1, j, cache);
                boolean matchS2 = s3.charAt(k) == s2.charAt(j) && helper(s1, s2, s3, i, j + 1, cache);
                cache[i][j] = (matchS1 || matchS2) ? 1 : -1;
            }
        }
        // return cache
        return cache[i][j] == 1;
    }


    //-------------------- Solution 3 --------------------//
    // dp, dp[i][j] -> put initialization inside for loop
    public boolean isInterleave3(String s1, String s2, String s3) {
        // input validation
        if (s1 == null || s2 == null || s3 == null ||
            s1.length() + s2.length() != s3.length() || isInvalid(s1 + s2, s3)) {
            return false;
        }

        int M = s1.length(), N = s2.length();
        boolean dp[][] = new boolean[M + 1][ N+ 1];

        // base case
        for (int i = M; i >= 0; i--) {
            dp[i][N] = s3.substring(i + N).equals(s1.substring(i));
        }
        for (int j = N; j >= 0; j--) {
            dp[M][j] = s3.substring(M + j).equals(s2.substring(j));
        }

        // use transition function to fill in the dp table
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                dp[i][j] = s1.charAt(i) == s3.charAt(i + j) && dp[i + 1][j] || // match s1
                           s2.charAt(j) == s3.charAt(i + j) && dp[i][j + 1] ;  // match s2
            }
        }
        return dp[0][0];
    }


    //-------------------- Solution 4 --------------------//
    // Alternative dp:
    //      use a different state definition: dp[i][j]
    //      s1.substring(0, i), s2.substring(0, j) V.S. s3.substring(0, i + j);
    public boolean isInterleave4(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }

        int M = s1.length(), N = s2.length();
        boolean[][] dp = new boolean[M + 1][N + 1];
        dp[0][0] = true;
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                // we added guard: j > 0, and does the initialization here
                if (j > 0 && s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;

                    // we added guard: i > 0, and does the initialization here
                } else if (i > 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[M][N];
    }


    ///////////////////  TEST //////////////////////
    private static void test(InterleavingString solution, String s1, String s2, String s3) {
        System.out.println(String.format("s1: %s, s2: %s", s1, s2));
        System.out.println("s3: " + s3);
        System.out.println(solution.isInterleave4(s1, s2, s3) + "\n");
    }

    public static void main(String[] args) {
        InterleavingString solution = new InterleavingString();

        test(solution, "aabcc", "dbbca", "aadbbcbcac");
        test(solution, "aabcc", "dbbca", "aadbbbaccc");
        test(solution, "cacbbbaaabbacbbbbabbcaccccabaaacacbcaacababbaabbaccacbaabac",
                       "cbcccabbbbaaacbaccbabaabbccbbbabacbaacccbbcaabaabbbcbcbab",
                       "ccbcccacbabbbbbbaaaaabbaaccbabbbbacbcbcbaacccbacabbaccbaaabcacbbcabaabacbbcaacaccbbacaabababaabbbaccbbcacbbacabbaacb");
    }
}

import java.util.Arrays;

public class ScrambleString {
    //----------------   Solution 1  ----------------------//
    // Recursion
    public boolean isScramble(String s1, String s2) {
        // input checking
        if (s1 == null || s2 == null || s1.length() != s2.length() || isInvalid(s1, s2)) {
            return false;
        }

        // base case (len == 1) (fast return)
        if (s1.equals(s2)) {
            return true;
        }

        // recursive calls
        int N = s1.length();
        for (int split = 1; split < N; split++) {
            String s1A = s1.substring(0, split), s1B = s1.substring(split, N);
            String s2A = s2.substring(0, split), s2B = s2.substring(split, N);
            String s2C = s2.substring(0, N - split), s2D = s2.substring(N - split, N);

            // NOTE: in the following if exp (noSwap || swap)
            //       when noSwap is true, will the compiler does recursive call for swap?
            //       worth mentioning in interview, but the current coding is more readable
            boolean noSwap = isScramble(s1A, s2A) && isScramble(s1B, s2B);
            boolean swap   = isScramble(s1A, s2D) && isScramble(s1B, s2C);
            if (noSwap || swap) { // --- if matches return true
                return true;
            }
            // --- otherwise continues to next loop
        }
        return false;
    }

    /**
     * A util function to decide if 2 strings are anagrams
     */
    private boolean isInvalid(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1); // sort returns void!
        Arrays.sort(arr2);
        return !(new String(arr1).equals(new String(arr2)));
    }

    //----------------   Solution 2  ----------------------//
    // recursion with cache: using standard template!!
    public boolean isScramble2(String s1, String s2) {
        // input checking
        if (s1 == null || s2 == null || s1.length() != s2.length() || isInvalid(s1, s2)) {
            return false;
        }
        // fast return
        if (s1.equals(s2)) {
            return true;
        }

        // create a cache
        int N = s1.length();
        int[][][] cache = new int[N][N][N + 1];
        for (int[][] grid: cache) { // use int instead of boolean (true: 1, false: 0, default: -1)
            for (int[] row : grid) {
                Arrays.fill(row, -1);
            }
        }
        return helper(s1, 0, s2, 0, N, cache) == 1;
    }

    /**
     * We're computing isScramble(sub1, sub2) in helper
     *
     * sub1 = s1.substring(i, i + len)
     * sub2 = s2.substring(j, j + len)
     *
     * NOTE:
     * params in helper for recursion with cache has to
     * 1) contain the index of cache[i][j][len]
     * 2) original params (unchanged)
     *
     */
    private int helper(String s1, int i, String s2, int j, int len, int[][][] cache) {
        // update cache
        if (cache[i][j][len] == -1) {
            if (len == 1) {
                cache[i][j][len] = (s1.charAt(i) == s2.charAt(j)) ? 1 : 0;
            } else {
                for (int lenA = 1; lenA <= len - 1; lenA++) {
                    int lenB = len - lenA;
                    boolean noSwap = helper(s1, i, s2, j, lenA, cache) == 1 && helper(s1, i + lenA, s2, j + lenA, lenB, cache) == 1;
                    boolean swap   = helper(s1, i, s2, j + lenB, lenA, cache) == 1 && helper(s1, i + lenA, s2, j, lenB, cache) == 1;
                    if (noSwap || swap) { // ---- if matches return true
                        cache[i][j][len] = 1;
                        break;
                    } // --- otherwise continue to next loop
                }
                // don't forget to set it to 0 at the end of loop
                cache[i][j][len] = 0;
            }
        }
        // return value
        return cache[i][j][len];
    }

    //----------------   Solution 3  ----------------------//
    // DP O(N^3)
    public boolean isScramble3(String s1, String s2) {
        // input checking
        if (s1 == null || s2 == null || s1.length() != s2.length() || isInvalid(s1, s2)) {
            return false;
        }
        // fast return and base case (len == 1)
        if (s1.equals(s2)) {
            return true;
        }

        // define status table and initial values
        int N = s1.length();
        boolean[][][] dp = new boolean[N][N][N + 1];  // [s1.start][s2.start][len], len == 0 is not used!!

        // base case: len == 1
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // general case: len 2 --> N
        for (int len = 2; len <= N; len++) {
            for (int i = 0; i <= N - len; i++) { // i <= N - len (make sure the last substring is within bounds
                for (int j = 0; j <= N - len; j++) {
                    for (int subLen = 1; subLen <= len - 1; subLen++) {
                        boolean noSwap = dp[i][j][subLen] && dp[i + subLen][j + subLen][len - subLen];
                        boolean swap = dp[i][j + len - subLen][subLen] && dp[i + subLen][j][len - subLen];
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

    ///////////////////  TEST //////////////////////
    private static void test(ScrambleString solution, String s1, String s2) {
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(solution.isScramble2(s1, s2) + "\n");
    }

    public static void main(String[] args) {
        ScrambleString solution = new ScrambleString();

        test(solution, "great", "aterg");
        test(solution, "xxxyyy", "yyxxyx");
        test(solution, "xxxyyy", "yyxzyx");
        test(solution, "ab", "ba");
    }
}


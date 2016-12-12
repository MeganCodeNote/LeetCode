import java.util.Arrays;

public class DistinctSubsequences {
    //----------------   Solution 1  ----------------------//
    // Recursion (TIMEOUT)
    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        int M = s.length(), N = t.length();
        int res = 0;
        if (N == 0) {  //--- base case
            res = 1;
        } else if (M == 0) {
            res = 0;
        } else {       //--- general case
            res = s.charAt(0) == t.charAt(0) ? numDistinct(s.substring(1), t.substring(1)) : 0; // =
            res += numDistinct(s.substring(1), t); // +=
        }
        return res;
    }

    //----------------   Solution 2  ----------------------//
    // Recursion with cache (Stack Overflow)
    // cache[i][j] numDistinct(s.substring(i), t.substring(j))
    public int numDistinct2(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        // create cache
        int M = s.length(), N = t.length();
        int[][] cache = new int[M + 1][N + 1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return helper(s, t, 0, 0, cache);
    }

    private int helper(String s, String t, int i, int j, int[][] cache) {
        int M = s.length(), N = t.length();
        if (cache[i][j] == -1) {
            if (j == N) {  //--- base case
                cache[i][j] = 1;
            } else if (i == M) {
                cache[i][j] = 0;
            } else {       //--- general case
                cache[i][j] = s.charAt(i) == t.charAt(j) ? helper(s, t, i + 1, j + 1, cache) : 0;
                cache[i][j] += helper(s, t, i + 1, j, cache);
            }
        }
        return cache[i][j];
    }


    //----------------   Solution 3  ----------------------//
    // 2D-dp: s.substring(i) and t.substring(j)
    public int numDistinct3(String s, String t) {
        // input validation
        if (s == null || t == null) {
            return 0;
        }

        int M = s.length(), N = t.length();
        int[][] nums = new int[M + 1][N + 1];
        // initialize the the dp table
        for (int i = M; i >= 0; i--) {
            nums[i][N] = 1;  // if (lenT == 0), t is empty string, return 1
        }
        // if j != N && i == M, nums[i][j] == 0

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                nums[i][j] = s.charAt(i) == t.charAt(j) ? nums[i + 1][j + 1] : 0;
                nums[i][j] += nums[i + 1][j];
            }
        }
        return nums[0][0];
    }

    //----------------   Solution 4  ----------------------//
    // 2D-dp: s.substring(0, lenS) and t.substring(0, lenT)
    public int numDistinct4(String s, String t) {
        // input validation
        if (s == null || t == null) {
            return 0;
        }

        int M = s.length(), N = t.length();
        int[][] nums = new int[M + 1][N + 1];
        // initialize the the dp table
        for (int lenS = 0; lenS <= M; lenS++) {
            nums[lenS][0] = 1;  // if (lenT == 0), t is empty string, return 1
        }
        // if lenT != 0 && lenS == 0:  nums[lenS][lenT] == 0

        for (int lenS = 1; lenS <= M; lenS++) {
            for (int lenT = 1; lenT <= N; lenT++) {
                nums[lenS][lenT] = s.charAt(lenS - 1) == t.charAt(lenT - 1) ? nums[lenS - 1][lenT - 1] : 0;
                nums[lenS][lenT] += nums[lenS - 1][lenT];
            }
        }
        return nums[M][N];
    }


    ///////////////////  TEST //////////////////////
    private static void test(DistinctSubsequences solution, String s, String t) {
        System.out.println(s);
        System.out.println(t);
        System.out.println(solution.numDistinct3(s, t) + "\n");
    }

    public static void main(String[] args) {
        DistinctSubsequences solution = new DistinctSubsequences();

        test(solution, "rabbbit", "rabbit");
        test(solution, "ccc", "c");
        test(solution, "aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe",
                       "bddabdcae");
    }
}

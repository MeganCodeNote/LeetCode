import java.util.Arrays;

public class MinimumPathSum {
    //------------------   Solution 1   ----------------------//
    // pure recursion (TIMEOUT)
    public int minPathSum(int[][] grid) {
        /**
         * Do input checking, same as UniquePath2
         * assume that we could not change the original grid
         */
        int m = grid.length, n = grid[0].length;
        return helper(grid, m, n);
    }

    private int helper(int[][] grid, int m, int n) {
        // always remember to check the first row and col specifically!!
        if (m == 1 && n == 1) {     // -- base cases
            return grid[0][0];
        } else {                    // -- general case:
            int above = (m == 1) ? Integer.MAX_VALUE : helper(grid, m - 1, n);
            int left  = (n == 1) ? Integer.MAX_VALUE : helper(grid, m, n - 1);
            return grid[m - 1][n - 1] + Math.min(above, left);
        }
    }

    //------------------   Solution 2   ----------------------//
    // recursion with cache
    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // REMEMBER TO FILL CACHE WITH DEFAULT VALUE -1, IF THE DEFAULT 0 IS A POSSIBLE CELL VALUE
        int[][] cache = new int[m + 1][n + 1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return helper(grid, m, n, cache);
    }

    private int helper(int[][] grid, int m, int n, int[][] cache) {
        // update cache
        if (cache[m][n] == -1) {
            if (m == 1 && n == 1) {         // base cases
                cache[m][n] = grid[0][0];
            } else {                        // general cases
                int above = (m == 1) ? Integer.MAX_VALUE : helper(grid, m - 1, n, cache);
                int left  = (n == 1) ? Integer.MAX_VALUE : helper(grid, m, n - 1, cache);
                cache[m][n] = grid[m - 1][n - 1] + Math.min(above, left);
            }
        }
        // return value
        return cache[m][n];
    }


    //------------------   Solution 3   ----------------------//
    // dp with 2D cache
    public int minPathSum3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row: dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) { // -- base case
                    dp[i][j] = grid[i - 1][j - 1];
                } else {                // -- general case
                    dp[i][j] = grid[i - 1][j - 1] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    
	//-----------------    Solution 4  --------------------------//
    // space-saving dp
    public int minPathSum4(int[][] grid) {
        // use a 1D array to record intermediate result
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[j] = grid[0][0];
                } else {
                    dp[j] = grid[i - 1][j - 1] + Math.min(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[n];
    }


    ///////////////////  TEST //////////////////////
    private static void test(MinimumPathSum solution, int[][] grids) {
        ArrayUtils.print2D(grids);
        System.out.println(solution.minPathSum4(grids));
    }

    public static void main(String[] args) {
        MinimumPathSum solution = new MinimumPathSum();
        int[][] grids1 = {{0,1,2}, {0,1,0}, {4,3,5}};
        int[][] grids2 = {{0,3,0}, {0, 2, 1}};
        int[][] grids3 = {
            {5,0,1,1,2,1,0,1,3,6,3,0,7,3,3,3,1},
            {1,4,1,8,5,5,5,6,8,7,0,4,3,9,9,6,0},
            {2,8,3,3,1,6,1,4,9,0,9,2,3,3,3,8,4},
            {3,5,1,9,3,0,8,3,4,3,4,6,9,6,8,9,9},
            {3,0,7,4,6,6,4,6,8,8,9,3,8,3,9,3,4},
            {8,8,6,8,3,3,1,7,9,3,3,9,2,4,3,5,1},
            {7,1,0,4,7,8,4,6,4,2,1,3,7,8,3,5,4},
            {3,0,9,6,7,8,9,2,0,4,6,3,9,7,2,0,7},
            {8,0,8,2,6,4,4,0,9,3,8,4,0,4,7,0,4},
            {3,7,4,5,9,4,9,7,9,8,7,4,0,4,2,0,4},
            {5,9,0,1,9,1,5,9,5,5,3,4,6,9,8,5,6},
            {5,7,2,4,4,4,2,1,8,4,8,0,5,4,7,4,7},
            {9,5,8,6,4,4,3,9,8,1,1,8,7,7,3,6,9},
            {7,2,3,1,6,3,6,6,6,3,2,3,9,9,4,4,8}
        };

        test(solution, grids1);
        test(solution, grids2);
        test(solution, grids3);
    }
}
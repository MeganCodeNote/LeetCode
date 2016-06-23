import java.util.Arrays;

public class UniquePath2 {
    /**
     * Input Checking:
     * 1) each row [1 -> len-1] must be non-null
     * 2) all rows have the same len
     * 3) each element is either 0 or 1
     */

    //---------------------- Solution 1 ------------------------//
    // pure recursion (time limit exceed)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 ||
            obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        return helper(obstacleGrid, m, n);
    }

    private int helper(int[][] grid, int m, int n) {
        if (m == 0 || n == 0 || grid[m - 1][n - 1] == 1) { // case 1: out of boundary or blocker (order is important!!!)
            return 0;

        } else if (m == 1 && n == 1) {                     // case 2: non-blocker start point
            return 1;

        } else {                                           // case 3: general recursive call
            return helper(grid, m - 1, n) + helper(grid, m, n - 1);
        }
    }


    //---------------------- Solution 2 ------------------------//
    // pure recursion with cache
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 ||
            obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // REMEMBER TO FILL CACHE WITH DEFAULT VALUE -1, IF THE DEFAULT 0 IS A POSSIBLE CELL VALUE
        int[][] cache = new int[m + 1][n + 1];
        for (int[] row: cache) {
            Arrays.fill(row, -1);
        }
        return numOfpaths(obstacleGrid, m, n, cache);
    }

    private int numOfpaths(int[][] grid, int m, int n, int[][] cache) {
        // step 1: update cache
        if (cache[m][n] == -1) {
            if (m == 0 || n == 0 || grid[m - 1][n - 1] == 1) {  // case 1 BASE CASE: out of boundary OR blocker cell
                cache[m][n] = 0;

            } else if (m == 1 && n == 1) {                      // case 2 BASE CASE: non-blocker start point cell
                cache[m][n] = 1;

            } else {                                            // case 4 GENERAL CASE
                cache[m][n] = numOfpaths(grid, m - 1, n, cache) +
                              numOfpaths(grid, m, n - 1, cache);
            }
        }

        // step 2: return cached value
        return cache[m][n];
    }

    //---------------------- Solution 3 ------------------------//
    // dp with 2D cache
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 ||
            obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        // create a 2D table for storing intermediate results
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 0) {
                    if (i == 1 && j == 1) {  // non-blocker, start cell
                        dp[i][j] = 1;
                    } else {                 // non-blocker, other cells
                        dp[i][j]= dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }


    //---------------------- Solution 4 ------------------------//
    // space-saving dp
    public int uniquePathsWithObstacles4(int[][] obstacleGrid) {
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 ||
            obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        // use a 1D array to record intermediate result
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[j] = 0; // !!! need to CLEAR the value here !!
                } else {
                    if (i == 1 && j == 1) {   // non-blocker, start cell
                        dp[j] = 1;
                    } else {                  // non-blocker, other cells
                        dp[j] += dp[j - 1];
                    }
                }
            }
            ArrayUtils.print1D(dp);
        }
        return dp[n];
    }
    
    ///////////////////  TEST //////////////////////
    private static void test(UniquePath2 solution, int[][] grids) {
        ArrayUtils.print2D(grids);
        System.out.println(solution.uniquePathsWithObstacles4(grids));
    }

    public static void main(String[] args) {
        UniquePath2 solution = new UniquePath2();
        int[][] grids1 = {{0,0,0}, {0,1,0}, {0,0,0}};
        int[][] grids2 = {{0,0,0}};

        test(solution, grids1);
        test(solution, grids2);
    }
}

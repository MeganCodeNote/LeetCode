public class UniquePath2 {
    //---------------------- Solution 1 ------------------------//
    // pure recursion (time limit exceed)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /**
         * other checking: each row [1 -> len-1] must be non-null and has the same len with row 0
         * assume that each element is only 0 or 1
         */
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        // m > 0 && n >0
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        return helper(obstacleGrid, m, n);
    }

    private int helper(int[][] grid, int row, int col) {
        if (grid[row - 1][col - 1] == 1) {  // obstacle
            return 0;
        } else {
            // base case
            if (row == 0 || col == 0) { // non-obstacle
                return 1;
            }
            // general case
            return helper(grid, row - 1, col) + helper(grid, row, col - 1);
        }
    }


    //---------------------- Solution 2 ------------------------//
    // pure recursion with cache
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        // m > 0 && n >0
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] cache = new int[m][n];
        return numOfpaths(obstacleGrid, m, n, cache);
    }

    private int numOfpaths(int[][] grid, int row, int col, int[][] cache) {
        // base case
        if ((row < 0 || col < 0) || grid[row][col] == 1) {  // minus index || obstacle order is important!!!
            return 0;
        }
        if (row == 0 && col == 0) {  // non-obstacle and start point
            return 1;
        }

        // general cases
        if (cache[row][col] == -1){
            cache[row][col] = numOfpaths(grid, row - 1, col, cache) + numOfpaths(grid, row, col - 1, cache);
        }
        return cache[row][col];
    }


    //---------------------- Solution 3 ------------------------//
    // space-saving dp
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        // input checking
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }
        // other checking: each row [1 -> len-1] must be non-null and has the same len with row 0
        // assume that each element is only 0 or 1

        // use a 1D array to record intermediate result
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;  // this line is very important!!
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {  // obstacle
                    dp[j] = 0;
                } else if (j != 0) {            // non-obstacle and not first column
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
    
    ///////////////////  TEST //////////////////////
    public static void main(String[] args) {
        int[][] grids = {{0,0,0}, {0,1,0}, {0,0,0}};
        UniquePath2 up2 = new UniquePath2();
        System.out.println(up2.uniquePathsWithObstacles(grids));
    }
}

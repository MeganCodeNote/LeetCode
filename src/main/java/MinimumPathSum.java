import java.util.Arrays;

public class MinimumPathSum {

    //------------------   Solution 1   ----------------------//
    // pure recursion
    public int minPathSum(int[][] grid) {
        // do input checking, same as int unique path

        // assume that we could not change the original grid
        int m = grid.length, n = grid[0].length;
        return helper(grid, m - 1, n - 1);
    }

    private int helper(int[][] grid, int row, int col) {
        // base cases
        if (row == 0 && col == 0) {
            return grid[0][0];
        }

        // general case: always remember to check the first row and col specifically!!
        int increment = 0;
        if (row == 0) {  // first row
            increment = helper(grid, row, col - 1);
        } else if (col == 0) {  // first col
            increment = helper(grid, row - 1, col);
        } else {  // otherwise
            increment = Math.min(helper(grid, row - 1, col),
                                 helper(grid, row, col - 1));
        }
        return grid[row][col] + increment;
    }

    //------------------   Solution 2   ----------------------//
    // recursion with cache
    public int minPathSum1(int[][] grid) {
        // do input checking, same as int unique path

        // assume that we could not change the original grid
        int m = grid.length, n = grid[0].length;
        int[][] cache = new int[m][n];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return helper(grid, m - 1, n - 1, cache);
    }

    private int helper(int[][] grid, int row, int col, int[][] cache) {
        // base cases
        if (row == 0 && col == 0) {
            return grid[0][0];
        }

        // haven't cached
        if (cache[row][col] == -1) {
            int increment = 0;
            if (row == 0) { // first row
                increment = helper(grid, row, col - 1, cache);
            } else if (col == 0){  // first column
                increment = helper(grid, row - 1, col, cache);
            } else {  // otherwise
                increment = Math.min(helper(grid, row - 1, col, cache),
                        helper(grid, row, col - 1, cache));
            }
            cache[row][col] = grid[row][col] + increment;
        }
        return cache[row][col];
    }


    //------------------   Solution 3   ----------------------//
    // dp: time/space: m*n
    public int minPathSum3(int[][] grid) {
        // do input checking, same as int unique path

        // assume that we could not change the original grid
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];      // grid[0][0]
                } else if (i == 0) {
                    dp[i][j] += grid[i][j - 1]; // first row
                } else if (j == 0) {
                    dp[i][j] += grid[i - 1][j]; // first column
                } else {
                    dp[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]); // other cases
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    
	//-----------------    Solution 4  --------------------------//
	// dp: time/space: m*n
    // same with solution3, but change the logic a little
    public int minPathSum4(int[][] grid) {
        // do input checking, same as int unique path
        // assume that we could not change the original grid
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0];
                } else {
                    int above = (i == 0) ? Integer.MAX_VALUE : grid[i - 1][j];
                    int left = (j == 0) ? Integer.MAX_VALUE : grid[i][j - 1];
                    grid[i][j] += Math.min(above, left);
                }
            }
        }
        return grid[m - 1][n - 1];
    }
    
	//-----------------    Solution 5  --------------------------//
    // space saving dp --> time: m*n; space: N
    public int minPathSum5(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] table = new int[n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    table[0] = grid[0][0];
                    continue;
                }
                int above = (i == 0) ? Integer.MAX_VALUE : table[j];
                int left  = (j == 0) ? Integer.MAX_VALUE : table[j - 1];
                table[j] = grid[i][j] + Math.min(above, left);
            }
        return table[n - 1];
    }

}
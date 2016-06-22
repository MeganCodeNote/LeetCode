import java.util.ArrayList;
import java.util.List;

public class Triangle {
    //----------------- Solution 1 ---------------------//
    // recursion, divide and conquer
    // exponential time
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return Integer.MIN_VALUE;
        }

        return helper(triangle, 0, 0);
    }

    private int helper(List<List<Integer>> triangle, int row, int col) {
        // base case
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }

        // general case
        int left = helper(triangle, row + 1, col);
        int right = helper(triangle, row + 1, col + 1);
        return triangle.get(row).get(col) + Math.min(left, right);
    }

    //------------------- Solution 2 ----------------------//
    // bottom up: O(n^2)
    // similar to dp problem
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return Integer.MIN_VALUE;  // there might be negative values
        }

        // do not change the original triangle
        int N = triangle.size();
        int[] minSums = new int[N + 1];  // a dummy row filling with all 0
        for (int i = N - 1; i >= 0; i--) {
            List<Integer> curRow = triangle.get(i);
            for (int j = 0; j < curRow.size(); j++) {
                minSums[j] = curRow.get(j) + Math.min(minSums[j], minSums[j + 1]);
            }
        }
        return minSums[0];
    }
}

// 1. NOTE:  the values might be negative
// 2. ERROR: minSum.get(j) --> minSums.get(j) a misspelling
/* 10/24/2014 Megan Lee */

public class SearchA2DMatrix {
    //------------------ Solution 1 ---------------------//
    // twice binary : O(lgN) + O(lgM)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        // first find the row
        int lo = 0, hi = matrix.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (hi == -1) {
            return false;
        }
        int row = hi;

        // then search in the row
        lo = 0; hi = matrix[0].length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    //------------------ Solution 2 ---------------------//
    // treat the whole 2D matrix as a 1D array T = O(lg(N^2)) = O(lgN)
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int M = matrix.length, N = matrix[0].length;
        int lo = 0, hi = M * N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int row = mid / N, col = mid % N;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    //------------------ Solution 3 ---------------------//
    // single-step throw row or col -> O(M + N)
    public boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    //------------------ Solution 4 ---------------------//
    // binary search
    public boolean searchMatrix4(int[][] matrix, int target) {
        // input validation
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        return helper(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);
    }

    private boolean helper(int[][] matrix, int x1, int x2, int y1, int y2, int target) {
        // base case
        if (x1 > x2 || y1 > y2) {
            return false;
        }

        // step 1: find the pivot in the mid row
        int midRow = x1 + (x2 - x1) / 2;
        int midCol = findMidCol(matrix, midRow, y1, y2, target);
        if (midCol == Integer.MAX_VALUE) {
            return true;
        }

        // step 2: recursivelly call on 2 smaller matrix
        return helper(matrix, x1, midRow - 1, midCol + 1, y2, target) ||
                helper(matrix, midRow + 1, x2, y1, midCol, target);
    }

    private int findMidCol(int[][] matrix, int row, int y1, int y2, int target) {
        while (y1 <= y2) {
            int mid = y1 + (y2 - y1) / 2;
            if (matrix[row][mid] == target) {
                return Integer.MAX_VALUE;
            } else if (matrix[row][mid] < target) {
                y1 = mid + 1;
            } else {
                y2 = mid - 1;
            }
        }
        return y2;
    }
}

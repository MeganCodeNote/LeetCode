import java.util.Arrays;

public class SearchForARange {
    //--------------------- Solution 1 -----------------------//
    // search for a ceiling/floor, use search for insertion position as a
    // sub-routine
    public int[] searchRange(int[] A, int target) {
        // input checking
        int [] res = {-1, -1};
        if (A == null || A.length == 0) {
            return res;
        }

        // first search for low bound (target - 0.5)
        int lo = 0, hi = A.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        int left = lo; // lo: the first element > target - 0.5

        // then search for high bound (target + 0.5)
        lo = 0;
        hi = A.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        int right = hi; // hi: the first element < target + 0.5

        // really think about the relations between left and right
        // and whether there is a solution
        if (left <= right) {
            res[0] = left;
            res[1] = right;
        }
        return res;
    }

    //--------------------- Solution 2 -----------------------//
    // search for a leftmost and right most boundary
    public int[] searchRange2(int[] A, int target) {
        // input checking
        int [] res = {-1, -1};
        if (A == null || A.length == 0) {
            return res;
        }

        // look for leftmost
        int lo = 0, hi = A.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == A[mid]) {
                if (mid == 0 || A[mid - 1] < A[mid]) {
                    res[0] = mid;
                    break;
                } else {
                    hi = mid - 1;
                }
            } else if (target < A[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        // look for rightmost
        lo = 0;
        hi = A.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi)/2;
            if (target == A[mid]) {
                if (mid == A.length-1 || A[mid] < A[mid + 1]) {
                    res[1] = mid;
                    break;
                } else {
                    lo = mid + 1;
                }
            } else if (target < A[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    ///////////////////////  TEST /////////////////////////
    public static void main(String[] args) {
        SearchForARange solution = new SearchForARange();
        int[] A = {1,1,2};
        int[] results = solution.searchRange(A, 1);
        for (int i: results)
            System.out.println(i);
    }
}
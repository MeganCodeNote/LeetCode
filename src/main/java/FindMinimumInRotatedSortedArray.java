/**
 *  To understand the boundaries, use the following 3 examples:
 *  [2,1], [2,3,1], [3,1,2]
 */

public class FindMinimumInRotatedSortedArray {
    /**
     * Assumption 1: monotonically increasing
     * Assumption 2: no duplicates
     */

    //----------------  Solution 1  -----------------//
    // use examples to walk through the code logic
    // 1) [10]
    // 2) [10, 20]
    // 3) [20, 10]
    // 4) [10, 20, 30]
    // 5) [30, 10, 20]
    // 6) [20, 30, 10]
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {       // differ from binary search < instead of <=
            int mid = lo + (hi - lo)/2;
            if (nums[lo] < nums[mid]) {
                lo = mid + 1;   // [lo, mid] could not contain min
            } else {
                hi = mid;       // [mid + 1, hi] could not contain min
            }
        }
        return nums[lo];
    }


    //----------------  Solution 2  -----------------//
    public int findMin2(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int lo = 0, hi = num.length - 1;
        int min = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // [lo ~ mid] strictly sorted
            if (num[lo] <= num[mid]) {
                min = Math.min(min, num[lo]);
                lo = mid + 1;
            // [mid ~ hi] strictly sorted
            } else {
                min = Math.min(min, num[mid]);
                hi = mid - 1;
            }
        }
        return min;
    }
}

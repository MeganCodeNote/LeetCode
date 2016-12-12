
/**
 *  To understand the boundaries, use the following 3 examples:
 *  [2,1,1,1], [2,2,2,3,1], [3,1,1,1,2]
 */


public class FindMinimumInRotatedSortedArray2 {
    //----------------  Solution 1  -----------------//
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int lo = 0, hi = nums.length - 1;
        while (lo < hi){     // differ from binary search < instead of <=
            // base case return
            if (nums[lo] < nums[hi]) {
                return nums[lo];
            }
            // go to the split side
            int mid = lo + (hi - lo)/2;
            if (nums[lo] < nums[mid]) {
                lo = mid + 1;   // mid could not be the min value, skip mid
            } else if (nums[lo] > nums[mid]) {
                hi = mid;       // mid might be the min value, keep the mid
            } else {
                lo++;
            }
        }
        return -1;              // this return statement is non-reachable
    }


    //----------------  Solution 2  -----------------//
    public int findMin2(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int lo = 0, hi = num.length - 1;
        int min = Integer.MAX_VALUE;
        // go to the split side
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // [lo ~ mid] strictly sorted
            if (num[lo] < num[mid]) {
                min = Math.min(min, num[lo]);
                lo = mid + 1;

            // [mid ~ hi] strictly sorted
            } else if (num[lo] > num[mid]) {
                min = Math.min(min, num[mid]);
                hi = mid - 1;

            // not sure
            } else {
                min = Math.min(min, num[lo]);
                while (lo <= mid && num[lo] == num[mid]) {
                    lo++;
                }

            }
        }
        return min;
    }
}

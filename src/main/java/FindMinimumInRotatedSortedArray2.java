/*  Created by meganlee on 11/15/14.  */


public class FindMinimumInRotatedSortedArray2 {
    public int findMin(int[] num) {
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
                lo++;
            }
        }
        return min;
    }
}

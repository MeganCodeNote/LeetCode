/* Created by meganlee on 11/15/14.*/

public class FindMinimumInRotatedSortedArray {
    //----------------  Solution 1  -----------------//
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int lo = 0, hi = num.length - 1;
        int min = Integer.MAX_VALUE;
        while (lo <= hi) {
            // continuous range, return minimum
            if (num[lo] <= num[hi]) {
                return Math.min(min, num[lo]);
            }

            // go to the split side
            int mid = lo + (hi - lo) / 2;
            min = Math.min(min, num[mid]);
            if (num[lo] > num[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return min;
    }

    //----------------  Solution 2  -----------------//
    public int findMin2(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int lo = 0, hi = num.length - 1;
        int min = Integer.MAX_VALUE;
        while (lo <= hi) {
            // go to the split side
            int mid = lo + (hi - lo) / 2;
            if (num[lo] <= num[mid]) {
                min = Math.min(min, num[lo]);
                lo = mid + 1;
            } else {
                min = Math.min(min, num[mid]);
                hi = mid - 1;
            }
        }
        return min;
    }
}

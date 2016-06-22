
public class SearchInRotatedSortedArray2 {
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return false;
        }

        int lo = 0, hi = A.length - 1;
        while (lo <= hi) {
            // find the target
            int mid = lo + (hi - lo) / 2;
            if (target == A[mid]) {
                return true;
            }

            // [lo ~ mid] is strictly sorted
            if (A[lo] < A[mid]) {
                if (A[lo] <= target && target <= A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }

            // [mid ~ hi] is strictly sorted
            } else if (A[lo] > A[mid]) {
                if (A[mid] <= target && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }

            // not sure
            } else {
                lo++;
            }
        }
        return false;
    }
}

public class SearchInRotatedSortedArray {

    //------------------- Solution ----------------------//
    // a more concise way of developing the algorithm
    public int search(int[] A, int target) {
        // input checking
        if (A == null || A.length == 0) {
            return -1;
        }
        int lo = 0, hi = A.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // base case:
            if (target == A[mid]) {
                return mid;
            }
            // case 1: [lo, mid] is continuous part
            if (A[lo] <= A[mid]) {
                if (A[lo] <= target && target <= A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }

            // case 2: [mid, hi] is continuous part
            } else {
                if (A[mid] <= target && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }


    ////////////////////////  TEST  ////////////////////////
    private static void test(SearchInRotatedSortedArray solution, int[] a, int target) {
        int index = solution.search(a, target);
        System.out.println("Index of " + target + " is: " + index);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        int[] arr1 = {4, 5, 0, 1, 2, 3};
        int[] arr2 = {1, 2, 3, 4, 5, 6};
        test(solution, arr1, 3);
        test(solution, arr1, 5);
        test(solution, arr1, 6);

        test(solution, arr2, 3);
        test(solution, arr2, 5);
        test(solution, arr2, 6);
    }
}

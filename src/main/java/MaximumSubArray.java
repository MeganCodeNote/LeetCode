
public class MaximumSubArray {
    //-------------- Solution 1 ------------------//
    // sliding window
    public int maxSubArray(int[] A) {
        int max = Integer.MIN_VALUE;
        if (A == null || A.length == 0) {
            return max;
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            if (sum <= 0) {
                sum = 0;
            }
        }
        return max;
    }


    //----------------- Solution 2 ---------------------//
    // dp: space optimized dp[] --> one integer
    public int maxSubArray3(int[] A) {
        if (A == null || A.length == 0) {
            return Integer.MIN_VALUE;
        }

        int local = A[0], global = local;
        for (int i = 1; i < A.length; i++) {
            local = Math.max(A[i], A[i] + local);
            global = Math.max(global, local);
        }
        return global;
    }

    ////////////////////   TEST   /////////////////////
    public static void main(String[] args) {
        int[] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumSubArray m = new MaximumSubArray();
        System.out.println(m.maxSubArray(A));
    }
}

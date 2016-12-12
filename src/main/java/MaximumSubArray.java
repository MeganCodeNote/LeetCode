import java.util.Arrays;

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
                sum = 0; // clear content
            }
        }
        return max;
    }

    //----------------- Solution 2 ---------------------//
    // convert the problem "Best Time to Buy and Sell Stock" (TIME OUT)
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int N = nums.length;
        int[] sum = Arrays.copyOf(nums, N);
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int min = nums[0], global = min;
        for (int i = 1; i < nums.length; i++) {
            int local = sum[i] - Math.min(min, 0);
            global = Math.max(global, local);
            min = Math.min(min, sum[i]);
        }
        return global;
    }

    //----------------- Solution 3 ---------------------//
    // dp: space optimized dp[] --> one integer
    public int maxSubArray3(int[] A) {
        if (A == null || A.length == 0) {
            return Integer.MIN_VALUE;
        }

        int[] dp = new int[A.length];
        dp[0] = A[0];
        int global = 0;
        for (int i = 1; i < A.length; i++) {
            dp[i]  = Math.max(dp[i - 1], 0) + A[i];
            global = Math.max(global, dp[i]);
        }
        return global;
    }

    //----------------- Solution 4 ---------------------//
    // dp: space optimized dp[] --> one integer
    public int maxSubArray4(int[] A) {
        if (A == null || A.length == 0) {
            return Integer.MIN_VALUE;
        }

        int local = A[0], global = local;
        for (int i = 1; i < A.length; i++) {
            local  = Math.max(local, 0) + A[i];
            global = Math.max(global, local);
        }
        return global;
    }


    ////////////////////   TEST   /////////////////////
    private static void test(MaximumSubArray m, int[] A) {
        System.out.println(Arrays.toString(A));
        System.out.println(m.maxSubArray4(A) + "\n");
    }
    public static void main(String[] args) {
        int[] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumSubArray m = new MaximumSubArray();
        test(m, A);
    }
}

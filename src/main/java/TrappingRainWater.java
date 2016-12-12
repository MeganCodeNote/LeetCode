
public class TrappingRainWater {
    //----------------- Solution 1 ------------------//
    // incremental
    public int trap(int[] A) {
        // input checking
        if (A == null || A.length < 3) {  // in order to trap water, at least 3 bars
            return 0;
        }

        // parallel array: record the capacity of water each element hold
        int[] B = new int[A.length];
        int leftMax = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1]) {
                // update capacity array
                int bar = Math.min(A[i], leftMax);
                for (int j = i - 1; A[j] < bar; j--) {
                    B[j] =  bar - A[j];
                }
                // update max/maxIndex
                leftMax = Math.max(leftMax, A[i]);
            }
        }

        int sum = 0;
        for (int cap : B) {
            sum += cap;
        }
        return sum;
    }

    //----------------- Solution 2 ------------------//
    // current is the shortest-> center to two sides
    public int trap2(int[] A) {
        // input checking
        if (A == null || A.length < 3) {
            return 0;
        }

        // parallel arrays to record boundaries from both sides
        int N = A.length;
        int[] L = new int[N];
        int[] R = new int[N];
        int maxL = Integer.MIN_VALUE, maxR = Integer.MIN_VALUE;
        for (int i = 0, j = N - 1; i < N; i++, j--) {
            maxL = Math.max(maxL, A[i]);
            L[i] = maxL;
            maxR = Math.max(maxR, A[j]);
            R[j] = maxR;
        }

        // calculate sum
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.min(L[i], R[i]) - A[i];
        }
        return sum;
    }


    //----------------- Solution 3 ------------------//
    public int trap3(int[] height) {
        // input validation
        if (height == null || height.length < 3) {
            return 0;
        }

        // 2 pointers
        int sum = 0;
        for(int i = 0, j = height.length - 1, k = 0; i < j; ) {
            if (height[i] < height[j]) {
                for (k = i + 1; height[k] < height[i] && k <= j; k++) {
                    sum += height[i] - height[k];
                }
                i = k;
            } else {
                for (k = j - 1; height[k] < height[j] && k >= i; k--) {
                    sum += height[j] - height[k];
                }
                j = k;
            }
        }
        return sum;
    }


    ///////////////////  TEST //////////////////////
    private static void test(TrappingRainWater solution, int[] bars) {
        ArrayUtils.print1D(bars);
        System.out.println(solution.trap3(bars));
    }


    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        int[] bars = {9,6,8,8,5,6,3};
        test(solution, bars);
    }
}
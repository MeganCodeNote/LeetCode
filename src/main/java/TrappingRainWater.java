
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

    //----------------- Solution 1 ------------------//
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

    //---------------------  Test    -------------------------//
    public static void main(String[] args) {
        int[] A = {9,6,8,8,5,6,3};
        int sum = (new TrappingRainWater()).trap(A);
        int sum2 = (new TrappingRainWater()).trap2(A);
        System.out.println(sum);
        System.out.println(sum2);
    }
}
import java.util.Arrays;

public class MaximumProductSubarray {
    public int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return Integer.MIN_VALUE;
        }
        int global = A[0], lastMax = A[0], lastMin = A[0];
        for (int i = 1; i < A.length; i++) {
            int localMax = Math.max(Math.max(A[i], A[i] * lastMin), A[i] * lastMax);
            int localMin = Math.min(Math.min(A[i], A[i] * lastMin), A[i] * lastMax);
            global = Math.max(global, localMax);
            lastMax = localMax;
            lastMin = localMin;
        }
        return global;
    }

    ///////////////////  TEST //////////////////////
    private static void test(MaximumProductSubarray solution, int[] A, int expected) {
        int actual = solution.maxProduct(A);
        System.out.println(Arrays.toString(A));
        System.out.println("Expected: " + expected + "\nActual:   " + actual);
        System.out.println(expected == actual ? "Pass" : "Fail" + "\n");
    }

    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();
        int[] A  = {3, 1, -2, 5, 4};
        test(solution, A, 20);  // expected result is  1
    }
}



public class MaxProductSubarray {
    public int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return Integer.MIN_VALUE;
        }
        int global = A[0], localMax = A[0], localMin = A[0];
        for (int i = 1; i < A.length; i++) {
            int lastMax = localMax, lastMin = localMin;
            localMax = Math.max(Math.max(A[i], A[i] * lastMax), A[i] * lastMin);
            localMin = Math.min(Math.min(A[i], A[i] * lastMax), A[i] * lastMin);
            global = Math.max(global, localMax);
        }
        return global;
    }
}


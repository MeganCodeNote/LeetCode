public class RemoveDuplicates {
    //--------------- Solution 1 -------------------//
    // 2-pointers O(N)
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] != A[i]) { // or if (A[len - 1] != A[i]) {...}
                A[len++] = A[i];
            }
        }
        return len;
    }

    //--------------- Solution 2 -------------------//
    // move sub-array to the left
    // O(N^2) this version works, but could not pass LeetCode
    // for time limit excess
    public int removeDuplicates2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        for (int i = 0; i < len; ) {
            if (i == len - 1 || A[i] != A[i + 1]) {
                i++;
            } else {
                // find next different number
                int j = i + 1;
                while (j < len && A[j] == A[i]) {
                    j++;
                }
                // move elements to the left
                int offset = j - i - 1;
                for (int k = j; k < len; k++) {
                    A[k - offset] = A[k];
                }
                //decrease len
                len -= offset;
            }
        }
        return len;
    }

    //////////////////  TEST ////////////////////
    private static void test(int[] a, RemoveDuplicates solution) {
        int len = solution.removeDuplicates2(a);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < len ; i++) {
            if (i < len - 1) {
                sb.append(a[i] + ", ");
            } else {
                sb.append(a[i] + "]");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();

        int[] a1 = {1, 1, 2};
        test(a1, solution);

        int[] a2 = {1, 1};
        test(a2, solution);

        int[] a3 = {1, 2, 2, 2, 3, 4, 5, 5, 5};
        test(a3, solution);
    }
}

public class RemoveDuplicates2 {
    //--------------- Solution 1 -------------------//
    // 2-pointers O(N)
    public int removeDuplicates(int[] A) {
        // input checking / quick check return
        if (A == null) {
            return 0;
        }
        if (A.length <= 2) {
            return A.length;
        }

        // len indicate the length of valid subarray, j move char-by-char
        int len = 2;
        for (int j = 2; j < A.length; j++) {
            if (A[len - 2] != A[j]) {
                A[len++] = A[j];
            }
        }
        return len;
    }

    //--------------- Solution 2 -------------------//
    // move sub-array to the left
    // O(N^2) this version works, but could not pass LeetCode
    public int removeDuplicates2(int[] A) {
        // input checking / quick check return
        if (A == null) {
            return 0;
        }
        if (A.length <= 2) {
            return A.length;
        }

        // move tail sub array k steps to the front
        int len = A.length;
        for (int i = 0; i < len; ) {
            if (i >= len - 2 || A[i] != A[i + 1]) {
                i += 1;
            } else if (A[i] != A[i + 2]) {
                i += 2;
            } else {
                int j = i + 2;
                while (j < len && A[j] == A[i]) {
                    j++;
                }
                int offset = j - i - 2;
                for (int k = j; k < len; k++) {
                    A[k - offset] = A[k];
                }
                len -= offset;
            }
        }
        return len;
    }

    //////////////////  TEST ////////////////////
    private static void test(int[] a, RemoveDuplicates2 solution) {
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
        RemoveDuplicates2 solution = new RemoveDuplicates2();

        int[] a1 = {1, 1, 2};
        test(a1, solution);

        int[] a2 = {1, 1};
        test(a2, solution);

        int[] a3 = {1, 2, 2, 2, 3, 4, 5, 5, 5};
        test(a3, solution);
    }
}
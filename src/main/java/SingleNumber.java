/* Created by meganlee on 11/15/14. */

public class SingleNumber {
    public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;  // discuss this
        }

        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            res ^= A[i];
        }
        return res;
    }

    ///////////////////// TEST  ///////////////////////
    private static void test(SingleNumber solution, int[] a) {
        System.out.println(solution.singleNumber(a));
    }

    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();
        int[] a1 = {3, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 0};
        int[] a2 = {1, 1, 2, 2, 0, 0, 3435};

        test(solution, a1);
        test(solution, a2);
    }
}

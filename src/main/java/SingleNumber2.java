/* Created by meganlee on 11/15/14. */

public class SingleNumber2 {
    public int singleNumber(int[] A) {
        // assume A is non-null and there are at least 4 numbers in A
        int[] bits = new int[32];
        for (int num : A) {
            for (int i = 0; i < 32; i++) {
                bits[i] += (num >> i) & 1;
                bits[i] %= 3;
            }
        }

        // construct the missing number
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res |= bits[i] << i; // could also use +=
        }
        return res;
    }

    ///////////////////// TEST  ///////////////////////
    private static void test(SingleNumber2 solution, int[] a) {
        System.out.println(solution.singleNumber(a));
    }

    public static void main(String[] args) {
        SingleNumber2 solution = new SingleNumber2();
        int[] a1 = {3, 3, 3, Integer.MAX_VALUE};
        int[] a2 = {1, 1, 1, 0, 0, 0, 3435};

        test(solution, a1);
        test(solution, a2);
    }
}

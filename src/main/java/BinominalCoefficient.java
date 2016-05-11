/* Created by meganlee on 10/25/14. */

public class BinominalCoefficient {
    //------------ Solution works fine for small num ---------------//
    // when there is no overflow, the solution works fine
    public int binomialCoeff(int n, int k) {
        // Since C(n, k) = C(n, n-k)
        if (k > n - k) {
            k = n - k;
        }

        // starting from 1, there is a guarantee that the res is dividable: when i = 5
        // in numerator, there are 5 consecutive numbers, there must be a multiple of 5!
        int res = 1;
        for (int i = 1; i <= k; ++i) {
            res *= n - i + 1;
            res /= i;
        }
        return res;
    }

    //////////////////  TEST  /////////////////////
    private static void test(BinominalCoefficient solution, int n, int k) {
        System.out.println(solution.binomialCoeff(n, k));
    }

    public static void main(String[] args) {
        BinominalCoefficient solution = new BinominalCoefficient();
        test(solution, 6, 3);
        test(solution, 6, 4);
        test(solution, 6, 1);
        test(solution, 10, 3);
        test(solution, 10, 6);
        test(solution, 10, 5);
    }
}

import java.util.Arrays;


public class SpiralMatrix2 {
    //------------------ Solution ----------------------//
    // classic algorithm: use 4 bounds (for square, using 2 lo, hi)
    public int[][] generateMatrix(int n) {
        // input checking
        if (n < 0) {
            return null;
        }

        // start fill in the matrix (consider what happen when n == 0)
        // test case: input == 0; expect [];
        int[][] matrix = new int[n][n];
        int lo = 0, hi = matrix.length - 1;
        int num = 1;
        while (lo <= hi) {
            // pay attention to when lo == hi
            if (lo == hi) {
                matrix[lo][lo] = num;
                break;
            }
            // otherwise, fill this layer
            for (int i = lo; i < hi; i++) { // attention i < hi not i <= hi
                matrix[lo][i] = num++;
            }
            for (int i = lo; i < hi; i++) {
                matrix[i][hi] = num++;
            }
            for (int i = hi; i > lo; i--) {
                matrix[hi][i] = num++;
            }
            for (int i = hi; i > lo; i--) {
                matrix[i][lo] = num++;
            }
            lo++;
            hi--;
        }
        return matrix;
    }

    ///////////////////////  TEST  //////////////////////
    public static void test(SpiralMatrix2 solution, int n) {
        System.out.println("For n = " + n);
        ListPrinter.print2DArray(solution.generateMatrix(n));
        System.out.println();
    }

    public static void main(String[] args) {
        SpiralMatrix2 solution = new SpiralMatrix2();
        test(solution, -1);
        test(solution, 0);
        test(solution, 1);
        test(solution, 2);
        test(solution, 3);
        test(solution, 4);
        test(solution, 5);
    }
}

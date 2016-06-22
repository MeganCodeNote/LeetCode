
public class RotateImage {
    //------------------ Solution 1 ----------------------//
    // classic 4 bounds
    public void rotate(int[][] matrix) {
        // check null pointer or empty array
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        // check is the matrix is a NXN matrix ()

        // rotate layer by layer
        int lo = 0, hi = matrix[0].length - 1;
        while(lo < hi) {
            for (int offset = 0; lo + offset < hi; offset++) {
                int temp = matrix[lo][lo + offset];
                matrix[lo][lo + offset] = matrix[hi - offset][lo];
                matrix[hi - offset][lo] = matrix[hi][hi - offset];
                matrix[hi][hi - offset] = matrix[lo + offset][hi];
                matrix[lo + offset][hi] = temp;
            }
            lo++;
            hi--;
        }
    }

    //------------------ Solution 2 ----------------------//
    // find the transition function
    // (i, j) --> (j, len - 1 - i)
    // this is naive, used extra space ---> another matrix
    public void rotate2(int[][] matrix) {
        // check null pointer or empty array
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        // check is the matrix is a NXN matrix ()

        // put to new matrix
        int N = matrix.length;
        int[][] newMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMatrix[j][N - 1 - i] = matrix[i][j];
            }
        }

        // copy back
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }



    //------------------ Solution 2 ----------------------//
    // flip twice
    public void rotate3(int[][] matrix) {
        // check null pointer or empty array
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        // check is the matrix is a NXN matrix ()

        // step 1: first flip horizontally
        int N = matrix.length;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N - 1 - i][j];
                matrix[N - 1 - i][j] = temp;
            }
        }

        // step 2: flip diagonally
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /////////////////////////  Test  //////////////////////////
    public static void test(RotateImage r, int[][] m) {
        System.out.println("-------- Before ---------");
        ListPrinter.print2DArray(m);
        System.out.println("-------- After ---------");
        r.rotate(m);
        ListPrinter.print2DArray(m);
        System.out.println();
    }

    public static void main(String[] args) {
        RotateImage r = new RotateImage();

        int[][] m1 = {{1, 2}, {3, 4}};
        int[][] m2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test(r, m1);
        test(r, m2);
    }
}
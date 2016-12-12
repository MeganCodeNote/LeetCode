import java.util.Arrays;

public class SetMatrixZeros {
    //------------------ Solution ----------------------//
    public void setZeroes(int[][] matrix) {
        // check null pointer or empty array
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        // TODO: check is the matrix is a rect => each row has the same len

        // step 1: get 1st row and col
        boolean clearFirstRow = false, clearFirstCol = false;
        int M = matrix.length, N = matrix[0].length;
        for (int j = 0; j < N; j++) {
            if (matrix[0][j] == 0) {
                clearFirstRow = true;
                break;
            }
        }
        for (int i = 0; i < M; i++) {
            if (matrix[i][0] == 0) {
                clearFirstCol = true;
                break;
            }
        }

        // step 2: use the first row and col as the clear indicator
        // i, j index start from 0
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // step 3: actually clear the rows and cols
        // i, j index start from 1
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // set the first row and first col
        if (clearFirstRow) {
            for (int j = 0; j < N; j++) {
                matrix[0][j] = 0;
            }
        }
        if (clearFirstCol) {
            for (int i = 0; i < M; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    ///////////////////////// TEST  /////////////////////////
    private static void test(SetMatrixZeros solution, int[][] a) {
        System.out.println("-------- Before ---------");
        ListPrinter.print2DArray(a);
        System.out.println("-------- After ---------");
        solution.setZeroes(a);
        ListPrinter.print2DArray(a);
        System.out.println();
    }

    public static void main(String[] args) {
        SetMatrixZeros solution = new SetMatrixZeros();
        int[][] a = new int[4][6];
        for (int[] row : a) {
            Arrays.fill(row, 1);
        }
        a[1][3] = 0;
        a[2][2] = 0;
        a[3][5] = 0;
        test(solution, a);
    }
}

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    //--------------------  Solution 1 -------------------//
    // the most intuitive solution
    // need to consider when 1 row or 1 col
    public List<Integer> spiralOrder(int[][] matrix) {
        // input checking
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            // also needs to check all rows int matrix have the same length
            return res;
        }

        int x1 = 0, x2 = matrix.length - 1;
        int y1 = 0, y2 = matrix[0].length - 1;
        while (x1 <= x2 && y1 <= y2) {
            // one row or one col is special
            if (x1 == x2) {
                for (int i = y1; i <= y2; i++) {
                    res.add(matrix[x1][i]);
                }
                break;
            }
            if (y1 == y2) {
                for (int i = x1; i <= x2; i++) {
                    res.add(matrix[i][y1]);
                }
                break;
            }

            // top
            for (int i = y1; i < y2; i++) {
                res.add(matrix[x1][i]);
            }
            // right
            for (int i = x1; i < x2; i++) {
                res.add(matrix[i][y2]);
            }
            // bottom
            for (int i = y2; i > y1; i--) {
                res.add(matrix[x2][i]);
            }
            // left
            for (int i = x2; i > x1; i--) {
                res.add(matrix[i][y1]);
            }

            // update layer indicators
            x1++; x2--;
            y1++; y2--;

        }  // end of while
        return res;
    }
    
    //--------------------  Solution  2 -------------------//
    // don't have to deal with 1 row specific
    public List<Integer> spiralOrder2(int[][] matrix) {
        // input checking
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            // also needs to check all rows int matrix have the same length
            return order;
        }
        int x1 = 0, x2 = matrix.length - 1;
        int y1 = 0, y2 = matrix[0].length - 1;

        while (true) {
            for(int i = y1; i <= y2; i++) {
                order.add(matrix[x1][i]);
            }
            x1++;
            for(int i = x1; i <= x2; i++) {
                order.add(matrix[i][y2]);
            }
            y2--;
            if (x1 > x2 || y1 > y2) {
                break;
            }

            for(int i = y2; i >= y1; i--) {
                order.add(matrix[x2][i]);
            }
            x2--;
            for(int i = x2; i >= x1; i--) {
                order.add(matrix[i][y1]);
            }
            y1++;
            if (x1 > x2 || y1 > y2) {
                break;
            }
        }
        return order;
    }
    
    
    /////////////////////////     TEST    ////////////////////////
    private static void test(SpiralMatrix sp, int[][] matrix) {
        ListPrinter.printList(sp.spiralOrder(matrix));
    }

    public static void main(String[] args) {
        SpiralMatrix sp = new SpiralMatrix();
        // test case:
        // m > n; m < n; m == n; m == 1; n == 1
        int[][] matrix1 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] matrix2 = {{1,2,3}, {4,5,6}};
        int[][] matrix3 = {{1,2,3,4,5,6}};
        int[][] matrix4 = {{1},{2},{3},{4}};
        test(sp, matrix1);
        test(sp, matrix2);
        test(sp, matrix3);
        test(sp, matrix4);
    }
}

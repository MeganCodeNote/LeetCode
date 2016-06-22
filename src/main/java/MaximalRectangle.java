import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        // input checking
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        // assume matrix is a valid rectangle: only '0' '1'
        int N = matrix.length, M = matrix[0].length;
        int[] row = new int[M];
        int area = 0;
        for (int i = 0; i < N; i++) {
            // step 1, fill the row
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == '0') {
                    row[j] = 0;
                } else {
                    row[j]++;
                }
            }
            // step 2, use histogram to calculate the area
            area = Math.max(area, hist(row));
        }
        return area;
    }

    private int hist(int[] r) {
        Stack<Integer> s = new Stack<Integer>(); // stack store the index
        int[] row = Arrays.copyOf(r, r.length + 1);
        int area = 0;
        for (int i = 0; i < row.length; ) { // no ++ here
            if (s.isEmpty() || row[i] >= row[s.peek()]) { // > || >= both ok
                s.push(i++);  // i++ here

            } else { // no i++ here
                int h = row[s.pop()];
                int w = s.isEmpty() ? i : (i - s.peek() - 1);
                area = Math.max(area, h * w);
            }
        }
        return area;
    }
}

// NOTE: don't assume that matrix is a N X N square!
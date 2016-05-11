// TAGS: dp
import java.util.ArrayList;
import java.util.List;

public class PascalTriangle2 {
    // 0 --> [1]
    // 1 --> [1, 1]
    // 2 --> [1, 2, 1]
    // ...
    public List<Integer> getRow(int rowIndex) {
        // input checking
        List<Integer> res = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return res;
        }

        // compute row by row
        res.add(1);  // rowIndex == 0
        for (int row = 1; row <= rowIndex; row++) {
            res.add(1);
            for (int j = row - 1; j >= 1; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }
}


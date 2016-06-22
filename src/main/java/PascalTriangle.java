import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows <= 0) {
            return result;
        }

        List<Integer> firstRow = new ArrayList<Integer>();
        firstRow.add(1);
        result.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = result.get(i - 1);
            List<Integer> curRow  = new ArrayList<Integer>();
            curRow.add(1);
            for (int j = 0; j < i - 1; j++) {
                curRow.add(lastRow.get(j) + lastRow.get(j + 1));
            }
            curRow.add(1);
            result.add(curRow);
        }
        return result;
    }
}


import java.util.ArrayList;
import java.util.List;

public class GrayCode {
	
	//----------------- Solution 1 ---------------------//
    public List<Integer> grayCode(int n) {
        // input checking
        List<Integer> res = new ArrayList<Integer>();
        if (n < 0) {
            return res;
        }

        // incrementally generate next level's numbers
        res.add(0);
        for (int i = 0; i < n; i++) { // ATTENTION: off-by-one
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                int leftmostBit = 1 << i;
                res.add(leftmostBit + res.get(j));
            }
        }
        return res;
    }

    //----------------- Solution 2 ---------------------//
    // mathimetical
    public List<Integer> grayCode2(int n) {
        // input checking
        List<Integer> res = new ArrayList<Integer>();
        if (n < 0) {
            return res;
        }

        // binary -> gray
        for (int i = 0; i < (1 << n); i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    //--------------  Solution 1 ---------------------//
    // classic recursion (DFS + backtrack)
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (S == null || S.length == 0) {
            return res;
        }

        Arrays.sort(S);  // remember to sort!!
        helper(res, new ArrayList<Integer>(), S, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> subset, int[] S, int cur) {
        // base case
        if (cur == S.length) {  // ERROR: cur == s.length - 1
            res.add(new ArrayList<Integer>(subset));
            return;
        }

        // general case
        // case 1: exclude current element
        helper(res, subset, S, cur + 1);
        // case 2: include current element
        subset.add(S[cur]);
        helper(res, subset, S, cur + 1);
        subset.remove(subset.size() - 1);
    }
    

    //--------------  Solution 2 ---------------------//
    // incremental
    public List<List<Integer>> subsets2(int[] S) {
        // input checking
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());  // add empty subset
        if (S == null || S.length == 0) {
            return result;
        }

        Arrays.sort(S);	 // don't forget to sort!
        for (int i = 0; i < S.length; i++) {
            // 1. WITHOUT: all subsets without current elements are already in result (calculated from previous loops)
            // 2. WITH: add all subsets with current elements
            int size = result.size(); // need to record this, otherwise it will be changed
            for (int j = 0; j < size; j++) {
                List<Integer> item = new ArrayList<Integer>(result.get(j)); // add all previous elements
                item.add(S[i]);
                result.add(item);
            }
        }
        return result;
    }

    
    ///////////////////   TEST   ///////////////////////////
    public static void main(String[] args) {
        Subsets subsetter = new Subsets();
        int[] s = {1,3,4};
        List<List<Integer>> result = subsetter.subsets2(s);
        ListPrinter.print2DList(result);
    }
}

// NOTE  1: Arrays.sort(y); y should not be null --> RuntimeException
// ERROR 2: line 8 ==> S.length() ---> S.length 
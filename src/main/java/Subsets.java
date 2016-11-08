import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    //--------------------  Solution 1  ---------------------//
    // iterative
    public List<List<Integer>> subsets(int[] S) {
        // input checking
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (S == null || S.length == 0) {
            return result;
        }

        Arrays.sort(S); // sort first!
        result.add(new ArrayList<Integer>());  // add empty subset

        for (int num: S) {
            int size = result.size(); // need to record this, otherwise it will be changed
            for (int j = 0; j < size; j++) {
                List<Integer> item = new ArrayList<Integer>(result.get(j)); // add all previous elements
                item.add(num);
                result.add(item);
            }
        }
        return result;
    }


    //--------------------  Solution 2  ---------------------//
    // recursion (DFS + backtrack)
    public List<List<Integer>> subsets2(int[] S) {
        // input checking
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
        if (cur == S.length) {
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
    
    ///////////////////   TEST   ///////////////////////////
    public static void main(String[] args) {
        Subsets subsetter = new Subsets();
        int[] s = {1,3,4};
        List<List<Integer>> result = subsetter.subsets2(s);
        ListPrinter.print2DList(result);
    }
}


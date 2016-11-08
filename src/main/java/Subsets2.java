import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {
    //--------------  Solution 1 ---------------------//
    // incremental
    public List<List<Integer>> subsetsWithDup(int[] num) {
        // input checking
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return result;
        }

        Arrays.sort(num); // sort first!
        result.add(new ArrayList<Integer>());  // add the empty set

        for (int i = 0; i < num.length; i++) {
            // step 1: count duplicates && move to the last same num
            int counter = 1;
            while (i < num.length - 1 && num[i] == num[i + 1]) {
                i++;
                counter++;
            }
            // step 2: add new number to set,
            // add current number zero, once, then twice...
            int size = result.size();
            for (int j = 1; j <= counter; j++) {
                for (int k = (j - 1) * size; k < j * size; k++) {
                    List<Integer> item = new ArrayList<Integer>(result.get(k));
                    item.add(num[i]);
                    result.add(item);
                }
            }
        }
        return result;
    }

    //--------------  Solution 2 ---------------------//
    // classic recursion (DFS + backtrack)
    public List<List<Integer>> subsetsWithDup2(int[] num) {
        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        Arrays.sort(num);  // remember to sort!!
        populateResult(res, new ArrayList<Integer>(), num, 0);
        return res;
    }
    
    private void populateResult(List<List<Integer>> result,  List<Integer> subset, int[] num, int pos) {
        // base case
        if (pos >= num.length) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        
        // general case
        // 1. count duplicates, move index to the last num of a sequence of duplicates
        int count = 1;
        while (pos < num.length - 1 && num[pos] == num[pos + 1]) {
            count++;
            pos++;
        }
        // 2. add the current number to subset once, then twice ... until 'count' times
        populateResult(result, subset, num, pos + 1);
        for (int k = 1; k <= count; k++) {
            subset.add(num[pos]);
            populateResult(result, subset, num, pos + 1);
        }
        while (count-- > 0) {
            subset.remove(subset.size() - 1);
        }
    }
    
    ///////////////////     TEST     //////////////////////
    public static void main(String[] args) {
        Subsets2 solution = new Subsets2();
        int[] s = {1,4,4};
        List<List<Integer>> result = solution.subsetsWithDup(s);
        ListPrinter.print2DList(result);
    }
}

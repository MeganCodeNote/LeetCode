import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] num, int target) {
        // assume all nums in int[] num are positive
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0 || target <= 0) {
            return res;
        }
        Arrays.sort(num);   // sort to facilitate future calculation
        helper(res, new ArrayList<Integer>(), target, num, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> comb, int target, int[] num, int start) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(comb));
            return;
        }

        for (int i = start; i < num.length; i++) {
            if (i == start || num[i - 1] != num[i]) {
                comb.add(num[i]);
                helper(res, comb, target - num[i], num, i);
                comb.remove(comb.size() - 1);
            }
        }
    }

    //////////////////////// TEST ///////////////////////////
    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        int[] nums = {2, 1, 3};
        List<List<Integer>> combos = solution.combinationSum(nums,3);
        ListPrinter.print2DList(combos);
    }
}


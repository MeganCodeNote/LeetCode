import java.util.*;

public class CombinationSum {
    //----------------------  Solution 1 --------------------------//
    // Iterative
    public List<List<Integer>> combinationSum(int[] A, int target) {
        // Convert int[] --> Integer[]
        // since Arrays.asList() only takes Integer[] as parameter
        Integer[] num = new Integer[A.length];
        for (int i = 0; i < A.length; i++) num[i] = A[i];

        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        // Add the first level: when there is only 1 elem
        TreeSet<Integer> set = new TreeSet<Integer>(Arrays.asList(num)); // sorted unique elem
        List<List<Integer>> level1 = new ArrayList<List<Integer>>();
        List<List<Integer>> level2 = new ArrayList<List<Integer>>();
        for (int i: set) {
            List<Integer> item = new ArrayList<Integer>();;
            item.add(i);   // use first element to store sum
            item.add(i);   // the rest are the sorted combos
            level1.add(item);
        }

        // Compute level by level: increment the num of elems in each level
        while (level1.size() != 0) {
            for (List<Integer> combo: level1) {
                int sum = combo.get(0), lastNum = combo.get(combo.size() - 1);
                if (sum == target) {
                    combo.remove(0); // remove first elem sum
                    res.add(combo);  // add the combo to the result collector
                } else if (sum < target) {
                    Set<Integer> candidates = set.tailSet(lastNum);
                    for (int i: candidates) {
                        List<Integer> nextCombo = new ArrayList<Integer>(combo);
                        nextCombo.add(i);           // append new number
                        nextCombo.set(0, sum + i);  // update sum
                        level2.add(nextCombo);      // collect it
                    }
                } // otherwise sum > target, ignore
            }
            level1 = level2;
            level2 = new ArrayList<List<Integer>>();
        }
        return res;
    }


    //----------------------  Solution 2 --------------------------//
    // Recursive
    // According to problem requirements::
    // 1) all nums in int[] num are positive
    // 2) target >= 0
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // input checking
        if (num == null || num.length == 0) {
            return res;
        }
        Arrays.sort(num);   // sort to facilitate future calculation (might contain duplicates)
        helper(res, new ArrayList<Integer>(), target, num, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> comb, int target, int[] num, int start) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(comb));
        } else if (target > 0){
            for (int i = start; i < num.length; i++) {
                // find all the unique numbers left
                if (i == start || num[i - 1] != num[i]) {
                    comb.add(num[i]);
                    helper(res, comb, target - num[i], num, i);
                    comb.remove(comb.size() - 1);
                }
            }
        } // otherwise target < 0 ignore
    }

    //////////////////////// TEST ///////////////////////////
    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        int[] nums = {2, 1, 3};
        List<List<Integer>> combos1 = solution.combinationSum(nums, 3);
        ListPrinter.print2DList(combos1);
    }
}


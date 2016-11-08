import java.util.*;

public class CombinationSum2 {
    //----------------------  Solution 1 --------------------------//
    // Iterative
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        // Add the first level: when there is only 1 elem
        TreeMap<Integer, Integer> map = numCounter(num); // sorted unique elem
        List<List<Integer>> level1 = new ArrayList<List<Integer>>();
        List<List<Integer>> level2 = new ArrayList<List<Integer>>();
        for (int i: map.keySet()) {
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
                    // see if we still could consume the lastNum
                    Map<Integer, Integer> candidates = allConsumed(lastNum, combo, map) ? map.tailMap(lastNum, false) : map.tailMap(lastNum);
                    for (int i: candidates.keySet()) {
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

    /**
     * Return a number counter in a sorted manner
     */
    private TreeMap<Integer, Integer> numCounter(int[] num) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i: num) {
            int value = map.containsKey(i) ? map.get(i) + 1 : 1;
            map.put(i, value);
        }
        return map;
    }

    private Boolean allConsumed(int num, List<Integer> combo, Map<Integer, Integer> map) {
        int count = Collections.frequency(combo, num);
        if (combo.get(0) == num) {
            count -= 1;
        }
        return count == map.get(num);
    }

    //----------------------  Solution 2 --------------------------//
    // Recursive: Exactly same as Combination Sum, except for
    // helper(combos, combo, target - nums[i], nums, "start + 1"!!!);
    public List<List<Integer>> combinationSum2B(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // input checking
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);  // sort to facilitate future calculation (might contain duplicates)
        helper(res, new ArrayList<Integer>(), target,  nums, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> combos, List<Integer> combo, int target, int[] nums, int start) {
        if (target == 0) {
            combos.add(new ArrayList<Integer>(combo));
        } else if (target > 0) {
            for (int i = start; i < nums.length; i++) {
                // find all the unique numbers left
                if (i == start || nums[i - 1] != nums[i]) {
                    combo.add(nums[i]);
                    helper(combos, combo, target - nums[i], nums, i + 1);
                    combo.remove(combo.size() - 1);
                }
            }
        } // otherwise target < 0 ignore
    }


	// ------------------------- TEST --------------------------//
	public static void printList(List<Integer> list) {
		for (Integer i : list) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] nums = {10,1,1,1,2,7,6,5};
		List<List<Integer>> combos = (new CombinationSum2()).combinationSum2(nums,8);
		for (List<Integer> list : combos) {
			printList(list);
		}
	}
}

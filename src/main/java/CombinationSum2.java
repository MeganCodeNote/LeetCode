import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> combos = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i : nums) 
	        System.out.println(i);
        helper(combos, new ArrayList<Integer>(), nums, 0, target);
        return combos;
    }
    
    public void helper(List<List<Integer>> combos, List<Integer> combo, 
                        int[] nums, int start, int target) {
        // base case
        if (target < 0) return;
        if (target == 0) {
        		combos.add(new ArrayList<Integer>(combo)); 
        		return; 
        	}
        if (target > 0 && (start>=nums.length || nums[start]>target)) return;
        
        // general case
        for (int i = start; i < nums.length; i++) {
        		// skip duplicates 
        		if (i > 0 && nums[i] == nums[i-1]) continue;
            combo.add(nums[i]);
            helper(combos, combo, nums, start+1, target-nums[i]);
            combo.remove(combo.size()-1);
        }     
    }
    
	// ------------------------- TEST --------------------------//
	public static void printList(List<Integer> list) {
		for (Integer i : list) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] nums = { 10,1,1,2,7,6,5 };
		List<List<Integer>> combos = (new CombinationSum2()).combinationSum2(nums,8);
		for (List<Integer> list : combos) {
			printList(list);
		}
	}
}

// NOTE:
// draw a solution tree to understand the algorithm better
// use the solution tree to understand what happens when ther are duplicate
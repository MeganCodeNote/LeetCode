import java.util.ArrayList;
import java.util.List;


public class PermutationSequence {
    //----------------- Solution 1 ------------------//
    // use example, find pattern, math
    // O(N)
    public String getPermutation(int n, int k) {
        // check for n
        if (n <= 0) {
            return "";
        }
        // calculate n! and collect {1, 2, 3,..., n}
        int fact = 1;
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            fact *= i;
            nums.add(i);
        }
        // check for k
        if (k < 1 || k > fact) {
            return "";
        }
        k--;        // k is 1-based --> change to 0-based
        // build the string
        StringBuilder res = new StringBuilder();
        while (k != 0) { // if k == 0, we could stop early
            fact /= (n--);
            int index = k / fact;
            res.append(nums.get(index));
            nums.remove(index);
            k %= fact;
        }
        for (int i : nums) {
            res.append(i);
        }
        return res.toString();
    }

    //------------------ Solution 2 --------------------//
    // classic permutation solution, could not pass LeetCode for time exceed
    // worst case: n!
    public String getPermutation2(int n, int k) {
        // input checking
        // 1) n >= 1 2) 1 <= k <= n!
        if (!inputCheck(n, k)) {
            return "";
        }
        // both n and k are valid, fetch the kth item
        List<String> res = new ArrayList<String>();
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        int[] count = new int[1];
        helper(res, nums, new StringBuilder(), k, count);
        return res.get(0);
    }

    private void helper(List<String> res, List<Integer> nums, StringBuilder perm, int k, int[] count) {
        // base case
        if (nums.size() == 0) {
            count[0]++;
            if (k == count[0]) {
                res.add(perm.toString());
            }
            return;
        }
        // general case
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.remove(i);
            perm.append(num);
            helper(res, nums, perm, k, count);
            if (res.size() == 1) {
                return;
            }
            perm.deleteCharAt(perm.length() - 1);
            nums.add(i, num);
        }
    }

    private boolean inputCheck(int n, int k) {
        // check for n
        if (n <= 0) {
            return false;
        }
        // check for k
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        if (k < 1 || k > fact) {
            return false;
        }
        return true;
    }
    
    //////////////////  TEST ////////////////////
    public static void main(String[] args) {
        System.out.println((new PermutationSequence()).getPermutation2(9, 136371));
    }
}



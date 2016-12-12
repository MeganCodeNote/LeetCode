import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;


public class TwoSum {
    //-------------- Solution 1  ------------------//
    // 2 pointers
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;    // invalid input
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] res = {i, j};
                    return res;
                }
            }
        }

        return null;
    }

    //-------------- Solution 2  ------------------//
    // HashMap
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;    // invalid input
        }

        int[] res = new int[2];
        Map<Integer, Integer> table = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i], peerNum = target - num;
            if (table.containsKey(peerNum)) {
                res[0] = table.get(peerNum);
                res[1] = i;
                return res; // found an answer
            } else {
                table.put(num, i);
            }
        }

        return null;        // no valid answer
    }

    //-------------- Solution 3  ------------------//
    // Sort and solve:
    // T=O(nlgn) Space: decided on the sorting algorithm
    // NOTE:
    // here we return the actual number instead of the index
    // Could define an data structure {num, index} if we want to
    // return the index using this method
    public int[] twoSum3(int[] nums, int target) {
        // input validation
        if (nums == null || nums.length < 2) {
            return null;
        }

        // user a HashMap to keep the original index before sorting
        Map<Integer, Integer> table = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            table.put(nums[i], i);
        }

        // use two pointers to find the right solution
        int[] res = new int[2];
        Arrays.sort(nums);
        for(int l = 0, r = nums.length - 1; l < r; ) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                res[0] = table.get(nums[l]);
                res[1] = table.get(nums[r]);
                return res;
            } else if (sum < target) {
                l++;
            } else { // sum > target
                r--;
            }
        }

        // no solution found, return null;
        return null;
    }


    ////////////////  TEST  ///////////////////////
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] numbers = {12, 2, 11, 4, 6};
        int target = 8;
        int[] result = solution.twoSum2(numbers, target);
        for (int i : result) {
            System.out.println(i);
        }
    }
}

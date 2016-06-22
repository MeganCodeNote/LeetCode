import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;


public class TwoSum {

    //-------------- Solution 1  ------------------//
    // HashMap
    public int[] twoSum(int[] numbers, int target) {
        // input validation
        if (numbers == null || numbers.length < 2) {
            return null;
        }

        int[] res = new int[2];
        Map<Integer, Integer> table = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (table.containsKey(target - num)) {
                res[0] = table.get(target - num) + 1;
                res[1] = i + 1;
                return res; // found an answer
            }
            table.put(num, i);
        }
        return null; // no valid answer
    }

    //-------------- Solution 1  ------------------//
    // Sort and solve:
    // T=O(nlgn) Space: decided on the sorting algorithm
    // NOTE:
    // here we return the actual number instead of the index
    // Could define an data structure {num, index} if we want to
    // return the index using this method
    public int[] twoSum2(int[] numbers, int target) {
        // input validation
        if (numbers == null || numbers.length < 2) {
            return null;
        }

        // use two pointers to find the right solution
        int left = 0, right = numbers.length - 1;
        int[] res = new int[2];
        Arrays.sort(numbers);
        while (left < right) {
            int pairSum = numbers[left] + numbers[right];
            if (pairSum == target) {
                res[0] = numbers[left];
                res[1] = numbers[right];
                return res;
            } else if (pairSum < target) {
                left++;
            } else { // pairSum > target
                right--;
            }
        }
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



//=========  TAG: ==========//
// Array / Sort / Search
//
//
//=========  Design: =========//
// a.--- Brute-force, two pointers
//
// b.--- HashTable ==> make sure do not use one element multiple times
//          Input:  {3, 4, 2}, target 6
//          Error:  [1, 1] <-- return one element twice!
//          Output: [2, 3]
//
// c.--- Sort and Solve
//         Link: CTCI-P450-17.12
//
//=========  Error/Note: =========//
//      1) don't return one element twice!
//      2) index is 1-based and should be sorted
//      3) for solution 2: don't forget to sort!!!


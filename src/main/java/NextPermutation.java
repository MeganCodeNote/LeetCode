import java.util.Arrays;

public class NextPermutation {
    public static void nextPermutation(int[] num) {
        // input check and base case
        if (num == null) return;
        
        boolean done = helper(num, 0);
        if (!done)
            Arrays.sort(num);
    }
    
    public static boolean helper(int[] num, int index) {
        // base case
        int N = num.length;
        if (index >= N - 1) return false;
        
        // general case
        boolean done = helper(num, index + 1);
        if (done) {
            return true;
        } else {
            // find the next greater element in the range (index, N)
            int min_greater_value = Integer.MAX_VALUE;
            int min_greater_index = N;
            for (int i = index + 1; i < N; i++) {
                if (num[i] > num[index] && num[i] < min_greater_value) {
                    min_greater_value = num[i];
                    min_greater_index = i;
                }
            }
            
            if (min_greater_index != N) {
                int temp = num[min_greater_index];
                num[min_greater_index] = num[index];
                num[index] = temp;
                Arrays.sort(num, index + 1, N);
                return true;
            } else {
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
    		int[] nums = {3,2,1};
    		nextPermutation(nums);
    		for(int i: nums) {
    			System.out.print(i);
    			System.out.print(' ');
    		}
	}
}

// NOTE 1: There might be duplicates among all numbers
// NOTE 2: How to get all permutations for a list of elements (with duplicates)
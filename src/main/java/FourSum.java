import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FourSum {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length < 4) {
            return res;
        }

        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < num.length; j++) {
                if (j != i + 1 && num[j] == num[j - 1]) {
                    continue;
                }
                int start = j + 1, end = num.length - 1;
                while (start < end) {
                    int sum = num[i] + num[j] + num[start] + num[end];
                    if (sum == target) {
                        List<Integer> quad = Arrays.asList(num[i], num[j], num[start], num[end]);
                        res.add(quad);
                        start++;
                        end--;
                        while (start < end && num[start] == num[start - 1]) {
                            start++;
                        }
                        while (start < end && num[end] == num[end + 1]) {
                            end--;
                        }
                    } else if (sum < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return res;
    }
}

// same with 3 sum
// two pointer meeting each other
// O(n^3)
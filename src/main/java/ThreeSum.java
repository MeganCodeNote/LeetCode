import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length < 3) {
            return res;
        }

        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue;
            }
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int curSum = num[start] + num[end] + num[i];
                if (curSum == 0) {
                    List<Integer> triple = Arrays.asList(num[i], num[start], num[end]);
                    res.add(triple);
                    start++;
                    end--;
                    // skip all the duplicates
                    while (start < end && num[start] == num[start - 1]) {
                        start++;
                    }
                    while (start < end && num[end] == num[end + 1]) {
                        end--;
                    }
                } else if (curSum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return res;
    }
}

//=========  TAG: ==========//
// two pointers / kSum
//
//========= NOTE/ERROR: =========//
// 1) Don't forget to sort
// 2) pay attention to how to remove duplicates


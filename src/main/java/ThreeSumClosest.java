import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3) {
            return Integer.MAX_VALUE;
        }

        Arrays.sort(num);
        int closest = num[0] + num[1] + num[2];    // do NOT use Integer.MAX_VALUE might have overflow (closet-target)
        for (int i = 0; i < num.length - 1; i++) {
            for(int start = i + 1, end = num.length - 1; start < end; ) {
                // adjust pointers
                int sum = num[i] + num[start] + num[end];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    start++;
                } else {
                    end--;
                }

                // update solution
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
            }
        }
        return closest;
    }
}

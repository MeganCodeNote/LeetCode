import java.util.Arrays;

public class JumpGame2 {
    //------------------- Solution 1 ----------------------//
    // Similar to Graph BFS
    public int jump(int[] A) {
        // input validation
        if (A == null || A.length <= 1) {
            return 0;
        }

        // level by level
        int steps = 0;
        int start = 0, end = 0, nextEnd = end;
        while (start <= end) {  // level travesing
            for (int i = start; i <= end; i++) { // within each level, find max end for next level
                nextEnd = Math.max(nextEnd, i + A[i]);
                if (nextEnd >= A.length - 1) {
                    return steps + 1;
                }
            }
            start = end + 1;
            end = nextEnd;
            steps++;
        }
        return 0;
    }


    //------------------- Solution 2 ----------------------//
    // dp, O(N^2), not good for this problem
    public int jump2(int[] A) {
        // input validation
        if (A == null || A.length <= 1) {
            return 0;
        }

        // dp
        int[] steps = new int[A.length];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = Math.min(steps[i], steps[j] + 1);
                    break;
                }
            }
        }
        return steps[A.length - 1];
    }

    /////////////////////    TEST      //////////////////////
    private static void test(JumpGame2 solution, int[] a) {
        System.out.println(solution.jump(a));
    }

    public static void main(String[] args) {
        JumpGame2 solution = new JumpGame2();
        int[] A1 = {5, 3, 2, 1, 4};
        int[] A2 = {0};
        int[] A3 = {0, 1, 3};
        test(solution, A1);
        test(solution, A2);
        test(solution, A3);
    }
}

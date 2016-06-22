
public class JumpGame {
    //------------------- Solution 1 -------------------//
    // Range Expanding
    public boolean canJump(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }

        int start = 0, end = 0;
        for (int i = start; i <= end; i++) {
            end = Math.max(end, i + A[i]);
            if (end >= A.length - 1) {
                return true;
            }
        }
        return false;
    }


    //------------------- Solution 2 -------------------//
    // DP - time limits exceeded on LeetCode
    public boolean canJump2(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }

        int N = A.length;
        boolean[] dp = new boolean[N];  // dp[n] --> whether A[k] is reachable from A[0]
        dp[0] = true;
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && A[j] + j >= N - 1) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N - 1];

    }

    /////////////////////    TEST      //////////////////////
    private static void test(JumpGame solution, int[] a) {
        System.out.println(solution.canJump2(a));
    }

    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        int[] A1 = {5, 3, 2, 1, 4};
        int[] A2 = {0};
        int[] A3 = {0, 1, 3};
        test(solution, A1);
        test(solution, A2);
        test(solution, A3);
    }
}

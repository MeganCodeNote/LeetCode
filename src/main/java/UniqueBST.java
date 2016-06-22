
public class UniqueBST {
    //-------------- Solution 1 --------------------//
    // pure recursion
    public int numTrees(int n) {
        // input checking
        if (n < 0) {
            return 0;
        }

        // base case
        if (n <= 1) {
            return 1;
        }

        // general case
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += numTrees(i) * numTrees(n - 1 - i);
        }
        return res;
    }

    //-------------- Solution 2 --------------------//
    // DP: recursion formula (developed from solution 1)
    // Time: O(n^2), Space: O(n)
    public int numTrees2(int n) {
        // input checking
        if (n < 0) {
            return 0;
        }
        
        // for non-negative n
        int[] dp = new int[n + 1];  // n + 1 spaces
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j -1];
            }
        }
        return dp[n];
    }
    

    //-------------- Solution 3 --------------------//
    // Math: use Catalan num definition to calculate directly
    // C(n) = C(n-1) * 2(2n - 1) / (n + 1)
    // TIME: O(n); Space: O(1)
    public int numTrees3(int n) {
        // input checking
        if (n < 0) {
            return 0;
        }
        
        // for non-negative n
        int result = 1;
        for (int i = 1; i <= n; i++) {
            // ERROR: might lose precision here!! or range overflow
            // result *= (4 * i - 2) / (i + 1);

            // RIGHT SOLUTION:
            result *= (4 * i - 2);
            result /= i + 1;
        }
        return result;
    }


    //////////////////////   TEST   ////////////////////////
    public static void main(String[] args) {
        UniqueBST solution = new UniqueBST();
        for (int i = 0; i < 5; i++)
            System.out.println(solution.numTrees3(i));
    }
}


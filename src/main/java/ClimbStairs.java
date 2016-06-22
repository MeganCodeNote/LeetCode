import java.util.Arrays;

public class ClimbStairs {
    
    //----------------  Solution 1 --------------------//
    // Pure Recursion - Fibonacci (Time Limit Exceeded)
    public int climbStairs(int n) {
        // invalid input n
        if (n < 0) {
            return 0;
        }
        // n==0 || n==1 return 1
        if (n <= 1) {
            return 1;
        }

        // recursive call
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    //----------------  Solution 2 --------------------//
    // Recursion with cache
    public int climbStairs2(int n) {
        // invalid input n
        if (n < 0) {
            return 0;
        }

        // create a cache and initialize it
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);

        // call recusive function
        return helper(n, cache);
    }

    private int helper(int n, int[] cache) {
        // base: n == 0 || n == 1, return 1
        if (n <= 1) {
            return 1;
        }

        // miss: update cache
        if (cache[n] == -1) {
            cache[n] = helper(n - 1, cache) + helper(n - 2, cache);
        }

        // hit: return cache
        return cache[n];
    }

    //----------------  Solution 3 --------------------//
    // DP - O(n) space
    public int climbStairs3(int n) {
        // invalid input n
        if (n < 0) {
            return 0;
        }
        
        // Init: n == 0 || n == 1
        int[] numSteps = new int[n + 1];
        numSteps[0] = 1;
        numSteps[1] = 1;

        // Funct: dp[i] = dp[i - 1] + dp[i - 2]
        for (int i = 2; i <= n; i++) {
            numSteps[i] = numSteps[i - 1] + numSteps[i - 2];
        }

        // Answer
        return numSteps[n];
    }

    
    //----------------  Solution 4 --------------------//
    // DP - O(1) space
    public int climbStairs4(int n) {
        // negative steps
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }

        // n >= 2
        int f1 = 1;  // count of 2 step previous
        int f2 = 1;  // count of 1 steps previous
        for (int i = 2; i <= n; i++) {
            int f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f2;
    }

    //----------------  Solution 5 --------------------//
    // DP - O(1) space: carousel
    public int climbStairs5(int n) {
        // negative steps
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }

        int[] numSteps = new int[3];
        numSteps[0] = 1;
        numSteps[1] = 1;
        for (int i = 2; i <= n; i++) {
            numSteps[i % 3] = numSteps[(i - 1) % 3] + numSteps[(i - 2) % 3];
        }
        return numSteps[n % 3];
    }
    
    //////////////////////// TEST /////////////////////////
    public static void main(String[] args) {
        ClimbStairs cs = new ClimbStairs();
        System.out.println(cs.climbStairs(44));
        System.out.println(cs.climbStairs2(44));
        System.out.println(cs.climbStairs3(44));
    }
}

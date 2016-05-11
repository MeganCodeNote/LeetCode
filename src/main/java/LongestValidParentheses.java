import java.util.Stack;

public class LongestValidParentheses {
    //-------------- Solution 1 --------------------//
    // dynamic programming (a hard dp problem)
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == ')') {
            int left = i - dp[i - 1] - 1;
            if (left >= 0 && s.charAt(left) == '(') {
                dp[i] = dp[i - 1] + 2;
                if (left >= 1) {
                    dp[i] += dp[left - 1];
                }
            }
            res = Math.max(res, dp[i]);
        }
    }
    return res;
}

    //------------------ Solution 2 ----------------------//
    // use stack to solve
    // some test cases:
    // () (()
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;   // global max
        Stack<Integer> stack = new Stack<Integer>();
        int start = -1; // boundary of the beginning of cur valid parens
                        // start + 1 is the index for the first char of valid parens substring
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);  // put the index in stack
            } else {
                if (stack.empty()) {
                    start = i;
                } else {
                    stack.pop(); // pops out the matching '('
                    int len = i - (stack.empty() ? start : stack.peek());
                    res = Math.max(res, len);
                }
            }
        }
        return res;
    }
}

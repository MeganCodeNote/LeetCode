

public class LongestPalindromicSubstring {
    //------------------ Solution 1. ---------------------//
    //  Naive Solution O(n^3)
    //  Time Limit Exceeded on Leetcode
    public String longestPalindrome(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return s;
        }

        int N = s.length();
        int start = 0, end = 0; // inclusive
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (isPalindrome(s, i, j) && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    //-------------------- Solution 2 ----------------------//
    // Improved O(n^2)
    // fixed on each char as the center as palindrome
    public String longestPalindrome2(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return s;
        }

        int[] borders = {0, 0}; // inclusive
        for (int i = 0; i < s.length() - 1; i++) { // i < s.length() - 1
            expand(s, i, i, borders);
            expand(s, i, i + 1, borders);
        }
        return s.substring(borders[0], borders[1] + 1);
    }

    private void expand(String s, int i, int j, int[] borders) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        if ((j - 1) - (i + 1) > borders[1] - borders[0]) {
            borders[0] = i + 1;
            borders[1] = j - 1;
        }
    }


    //-------------------- Solution 3 ----------------------//
    //  Dynamic Time/Space: O(n^2)
    public String longestPalindrome3(String s) {
        // Define:   dp[i][j] = whether substring(i, j + 1) is a palindrome
        // Function: dp[i][j] == (char.i == char.j) &&
        //            (j - i == [0,1] || dp[i+1][j-1] == true)
        // Initial:  dp[0][0] = true;
        // Return:   global best

        // input validation
        if (s == null || s.length() == 0) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int bestStart = 0, bestEnd = 0; // inclusive
        for (int end = 0; end < s.length(); end++) {
            for (int start = 0; start <= end; start++) {
                // the most clever statement in the solution:
                if (s.charAt(start) == s.charAt(end) && (end - start <= 1 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                    // update global best
                    if (end - start > bestEnd - bestStart) {
                        bestStart = start;
                        bestEnd = end;
                    }
                }
            }
        }
        return s.substring(bestStart, bestEnd + 1);
    }


    ////////////////// TEST ///////////////////////
    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        String result = solution.longestPalindrome("sdjfeirusdhhdskj");
        System.out.println(result);
    }
}


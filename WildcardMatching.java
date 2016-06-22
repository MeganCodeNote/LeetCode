
public class WildcardMatching {
    //-------------- Solution 1 -------------------//
    // brute-force (Leetcode  Time Limit Exceeded)
    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*+", "*");  // THIS COULD OPTIMIZE TIME, but still timeout on Leetcode!!!
        if (p.isEmpty()) {
            return s.isEmpty();
        } else {
            if (p.charAt(0) == '*') {
                return isMatch(s, p.substring(1)) ||                // match 0 chars in s
                    (!s.isEmpty() && isMatch(s.substring(1), p));  // match >=1 chars in s
            } else {
                return sameFirstChar(s, p) && isMatch(s.substring(1), p.substring(1));
            }
        }
    }

    private boolean sameFirstChar(String s, String p) {
        return !s.isEmpty() && (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0));
    }

    //-------------- Solution 2 -------------------//
    // dp
    public boolean isMatch2(String s, String p) {
        int S = s.length(), P = p.length();
        boolean[][] dp = new boolean[P + 1][S + 1];
        dp[0][0] = true;
        for (int pi = 1; pi <= P; pi++) {
            for (int si = 0; si <= S; si++) {
                if (p.charAt(pi - 1) != '*') {
                    // CASE 1: L ( letter || ? )
                    dp[pi][si] = same(s, p, si, pi) && dp[pi - 1][si - 1];

                } else {
                    // CASE 2: *
                    dp[pi][si] = dp[pi - 1][si] || (si != 0 && dp[pi][si - 1]);
                              // match 0 chars in s    match >= 1 chars in s
                }
            }
        }
        return dp[P][S];
    }

    private boolean same(String s, String p, int si, int pi) {
        return si != 0 && (p.charAt(pi - 1) == '?' || p.charAt(pi - 1) == s.charAt(si - 1));
    }


    ////////////////////////////       Test      ///////////////////////////////////
    private static void test(WildcardMatching solution, boolean expected, String s, String p) {
        boolean actual = solution.isMatch2(s, p);
        System.out.println("Expected: " + expected + " Actual: " + actual);
        System.out.println("--- s: " + s);
        System.out.println("--- p: " + p);
        System.out.println(actual == expected ? "PASS\n" : "ERROR!!\n");
    }

    public static void main(String[] args) {
        WildcardMatching solution = new WildcardMatching();
        test(solution, true, "", "*");
        test(solution, false, "abcad", "a*cb**?");
        test(solution, true, "abbbbm", "*?");
        test(solution, false, "babbbbaabababaabbababaababaabbaabababbaaababbababaaaaaabbabaaaabababbabbababbbaaaababbbabbbbbbbbbbaabbb", "b**bb**a**bba*b**a*bbb**aba***babbb*aa****aabb*bbb***a");
        test(solution, true, "ab", "*");
        test(solution, true, "ab", "?*");
        test(solution, false, "aab", "c?a*b*");
        test(solution, true, "cabab", "*ab");
        test(solution, false, "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b");
        test(solution, false, "bbaabbbabbbbabbbaaabababbbabbababbbabaaabbbbaabaabaaaa", "*b**b*a**abbaab*aba***");
    }
}
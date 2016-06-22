
public class RegularExpressionMatching {
    //------------------ Solution 1 ---------------------//
    // brute-force (recursion)
    // NOTE: the way we resursively call isMatch make sure that
    //     1) p ALWAYS starts with a letter or dot(.)
    //     2) p NEVER starts with a *
    public boolean isMatch(String s, String p) {
        // case A: p is empty
        if (p.isEmpty()) {
            return s.isEmpty();

        // case B-1: len(p) >= 1 && first_char_of_p is L (letter || .)
        } else if (p.length() == 1 || p.charAt(1) != '*') {
            // if first_char_of_s matches first_char_of_p, recursive call
            return sameFirstChar(s, p) && isMatch(s.substring(1), p.substring(1));

        // case B-2: len(p) >= 2 && first_char_of_p is L* (letter* || .*)
        } else {
            return isMatch(s, p.substring(2)) ||    // match 0 chars in s
                   sameFirstChar(s, p) && isMatch(s.substring(1), p);    // match >=1 chars in s
        }
    }

    private boolean sameFirstChar(String s, String p) {
        return !s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
    }


    //------------------ Solution 2 ---------------------//
    // dp
    public boolean isMatch2(String s, String p) {
        int P = p.length(), S = s.length();
        boolean[][] dp = new boolean[P + 1][S + 1];
        dp[0][0] = true;

        // NOTE 1: pi corresponse to p.charAt(pi - 1)
        //         si corresponse to s.charAt(si - 1)
        //
        // NOTE 2: pi could point to 3 types of chars:
        //          a) * itself                          <-- pick this as L*
        //          b) letter || . (next char is NOT *)  <-- pick this as L
        //          c) letter || . (next char     IS *)

        for (int pi = 1; pi <= P; pi++) {       // row-by-row
            for (int si = 0; si <= S; si++) {   // col-by-col
                // case 1: L* (current char is *)
                if (p.charAt(pi - 1) == '*') {
                    // compare current_char_in_s and previous_char_in_p
                    dp[pi][si] = dp[pi - 2][si] ||  // match 0   chars in s
                                 sameChar(s, p, si, pi - 1) && dp[pi][si - 1];    // match >=1 chars in s

                // case 2: L (last_char_in_p || next char is not *)
                } else if (pi == P || p.charAt(pi) != '*') {
                    dp[pi][si] = sameChar(s, p, si, pi) && dp[pi - 1][si - 1];
                }
            }
        }
        return dp[P][S];
    }

    private boolean sameChar(String s, String p, int si, int pi) {
        return si != 0 && (p.charAt(pi - 1) == '.' || s.charAt(si - 1) == p.charAt(pi - 1));
    }
}
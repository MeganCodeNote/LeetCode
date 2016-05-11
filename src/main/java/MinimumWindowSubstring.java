import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        // assume S, T are non-null and there is a solution
        Map<Character, Integer> needs = new HashMap<Character, Integer>();
        Map<Character, Integer> found = new HashMap<Character, Integer>();
        for (char ch : T.toCharArray()) { // not (char ch: T) !!
            needs.put(ch, needs.containsKey(ch) ? needs.get(ch) + 1 : 1);
        }

        String res = S + " ";       // notice why we use S + " " here, see line 38
        int count = 0;
        for (int start = 0, end = start; end < S.length(); end++) {
            // skip invalid chars
            char ch = S.charAt(end);
            if (!needs.containsKey(ch)) {
                continue;
            }
            // update found
            found.put(ch, found.containsKey(ch) ? found.get(ch) + 1 : 1);
            if (found.get(ch) <= needs.get(ch)) {
                count++;
            }
            // if a valid window found, shrink start and update solution
            if (count == T.length()) {
                // shrink start
                while (!needs.containsKey(S.charAt(start)) || found.get(S.charAt(start)) > needs.get(S.charAt(start))) {
                    if (found.containsKey(S.charAt(start))) { // don't forget this if!!
                        found.put(S.charAt(start), found.get(S.charAt(start)) - 1);
                    }
                    start++;
                }
                // update solution
                if (end - start + 1 < res.length()) {
                    res = S.substring(start, end + 1);
                }
            }
        }
        return res.length() > S.length() ? "" : res;
    }
}
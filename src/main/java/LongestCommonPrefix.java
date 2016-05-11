// http://oj.leetcode.com/problems/longest-common-prefix/

public class LongestCommonPrefix {
    //------------------ Solution 1 -----------------------//
    // string-by-string
    public String longestCommonPrefix(String[] strs) {
        // input checking
        if(strs == null) return null;
        if(strs.length == 0) return "";
        
        // check char by char, using the first string as benchmark
        for(int i = 0; i < strs[0].length(); i++) {
            for(int j = 1; j < strs.length; j++)  { // check each string
                if ((strs[j].length() < i + 1) || 
                		(strs[j].charAt(i) != strs[0].charAt(i)) )
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    //------------------ Solution 2 -----------------------//
    // char-by-char: faster to terminate that solution 1
    public String longestCommonPrefix3(String[] strs) {
        // input checking
        if (strs == null || strs.length == 0) {
            return "";
        }
        // check char-by-char
        for (int i = 0; i < strs[0].length(); i++) { // for each char
            for (int j = 0; j < strs.length; j++) { // for each string
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}

/*
 * NOTE for char-by-char, it's faster to terminate
 *   0: hello
 *   1: hello
 *   2: hello
 *   3: p
 */


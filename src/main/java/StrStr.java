
public class StrStr {
    //-----------------  Solution 1 ------------------//
    // brute-force
    // ERROR: !Line 18: error: cannot find symbol: variable j
    public String strStr(String haystack, String needle) {
        // input checking
        if (haystack == null || needle == null || needle.length() == 0) {
            return haystack;
        }
        if (haystack.length() < needle.length()) {
            return null;
        }

        // sliding window
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                } else {
                    if (j == needle.length() - 1) {
                        return haystack.substring(i);
                    }
                }
            }
        }
        return null;
    }

    //-----------------  Solution 2 ------------------//
    // KMP - please refer to class KMP
    // explanation could be found on Leetcode Summary

    //----------------- Solution 3 -------------------//
    // Rolling Hash - Rabin-Karp: pleas refer to code ganker's website
}


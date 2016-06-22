import java.util.Set;

public class WordBreak {
    //---------- A very dynamic solution -------------//
    public boolean wordBreak(String s, Set<String> dict) {
        // input validation
        if (s == null || s.length() == 0) {
            return true;
        }

        // prepare for the calculation
        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        for (String word: dict) {
            int wordLen = word.length();
            minLen = Math.min(minLen, wordLen);
            maxLen = Math.max(maxLen, wordLen);
        }

        // start calculation
        boolean[] res = new boolean[s.length() + 1]; // res[i], can prefix with len i be segmented
        res[0] = true;
        for (int i = minLen; i <= s.length(); i++) {
            for (int j = Math.max(i - maxLen, 0); j <= i - minLen; j++) {
                String curWord = s.substring(j, i);
                if (dict.contains(curWord) && res[j]) {
                    res[i] = true;
                    break;
                }
            }
        }
        return res[s.length()];
    }

    ////////////////  TEST  /////////////////////
    public static void main(String[] args) {

    }
}

// 1. Discuss about what should be returned when s == null s is empty string
//          according to test cases, we should return new ArrayList<String>();
// 2. Discuss about whether words in the dict could be used multiple times
//          it seems that dict words could be used multiple times
// 3. Optimization: minLen and maxLen
// 4. Error:      for (int j = i - maxLen; ...)
//            --> for (int j = Math.max(i - maxLen, 0); ...)

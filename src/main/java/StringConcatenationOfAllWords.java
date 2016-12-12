import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringConcatenationOfAllWords {
    //-------------------  Solution 1 --------------------//
    // Brute Force: check all substring with length of (numWords * wordLen)
    public List<Integer> findSubstring(String S, String[] L) {
        // assume S and L are non-null and non-empty, L doesn't contain empty string
        List<Integer> res = new ArrayList<Integer>();
        Map<String, Integer> needs = new HashMap<String, Integer>();
        for (String word: L) {
            needs.put(word, needs.containsKey(word) ? needs.get(word) + 1 : 1);
        }
        int numWords = L.length, wordLen = L[0].length();
        for (int start = 0; start <= S.length() - numWords * wordLen; start++) {
            if (isValid(S, start, needs, numWords, wordLen)) {
                res.add(start);
            }
        }
        return res;
    }

    private boolean isValid(String S, int start, Map<String, Integer> needs, int numWords, int wordLen) {
        Map<String, Integer> found = new HashMap<String, Integer>();
        for (int i = 0; i < numWords; i++) {
            int left = start + i * wordLen;
            String word = S.substring(left, left + wordLen);
            if (needs.containsKey(word)) {
                if (found.containsKey(word) && found.get(word) >= needs.get(word)) {
                    return false;
                } else {
                    found.put(word, found.containsKey(word) ? found.get(word) + 1 : 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }


    //-------------------  Solution 2 --------------------//
    // sliding window: no backwards, reuse pre-computed results
    public List<Integer> findSubstring2(String S, String[] L) {
        // assume S and L are non-null and non-empty, L doesn't contain empty string
        List<Integer> res = new ArrayList<Integer>();
        Map<String, Integer> needs = new HashMap<String, Integer>();
        for (String word: L) {
            needs.put(word, needs.containsKey(word) ? needs.get(word) + 1 : 1);
        }

        int numWords = L.length, wordLen = L[0].length();
        for (int i = 0; i < wordLen; i++) {
            populateResult(S, i, needs, numWords, wordLen, res);
        }
        return res;
    }

    private void populateResult(String S, int i, Map<String, Integer> needs, int numWords, int wordLen, List<Integer> res) {
        Map<String, Integer> found = new HashMap<String, Integer>();
        for (int start = i, end = start; end <= S.length() - wordLen; end += wordLen) {
            String word = S.substring(end, end + wordLen);
            // adjust start to satisfy the invariant
            if (!needs.containsKey(word)) {
                found.clear(); // don't forget to clear the cache table
                start = end + wordLen;
            } else {
                while (found.get(word) != null && found.get(word) >= needs.get(word)) {
                    String removeWord = S.substring(start, start + wordLen);
                    found.put(removeWord, found.get(removeWord) - 1);
                    start += wordLen;
                }
                found.put(word, found.containsKey(word) ? found.get(word) + 1 : 1);
                if (end + wordLen - start == numWords * wordLen) {
                    res.add(start);
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "helo";
        for (char ch : s.toCharArray()) {

        }
    }
}



//=========  TAG: ==========//
// Sliding Window
//
//=========  Design: =========//
// a.--- Brute-force, all substring of length (numWords * wordLen)
// b.--- classify into wordLen number of strings, and use cache to optimize
//
//=========  Error/Note: =========//
//       1) if (found.get(word) >= needs.get(word))  ==> NullPointer
//  --->  if (found.containsKey(word) && found.get(word) >= needs.get(word))
//       2) don't forget to clear cache table


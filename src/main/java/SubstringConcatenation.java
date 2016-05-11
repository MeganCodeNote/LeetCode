import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatenation {
    public static List<Integer> findSubstring(String S, String[] L) {
        // input checking
        List<Integer> results = new ArrayList<Integer>();
        if (S == null || L == null || S.length() == 0 || L.length == 0)
            return results;
        
        // define frequently used variables
        int strLen = S.length();
        int numWords = L.length;
        int wordLen = L[0].length();
        int substrLen = wordLen * numWords;
        if (strLen < substrLen) return results; // another input checking
        
        // store the word counts into a map
        Map<String, Integer> wordCount = new HashMap<String, Integer>();
        for (String word : L) {
            if (wordCount.containsKey(word))
                wordCount.put(word, wordCount.get(word) + 1);
            else
                wordCount.put(word, 1); // error: initially put 0 here!!
        }
        
        // use 2 pointers to indicate a sliding window to find all answers
        Map<String, Integer> wordsLeft = new HashMap<String, Integer>();
        for (int left = 0; left <= strLen - substrLen; left++) {
            wordsLeft.putAll(wordCount); // overwrite the old values
            int right; // do remember to put right outside of for loop
            for (right = 0; right < numWords; right++) {
                 String curWord = S.substring(left+right*wordLen, left+(right+1)*wordLen);
                 if (wordsLeft.containsKey(curWord)) {
                    wordsLeft.put(curWord, wordsLeft.get(curWord)-1);
                    if (wordsLeft.get(curWord) == 0) wordsLeft.remove(curWord);
                 } else {
                     break;
                 }
            }
            if (right == numWords) // we find the substring, record the start index
                results.add(left);
        }
        
        return results; // don't forget to return the results!
    }
}

// NOTE:
// 1) Be familiar with how to use the Map
//      map.containsKey, map.get(key), map.put(key, value), 
//      map.putAll(anotherMap), map.remove(key), map.clear()
// 2) Do remember to RETURN!


// 1) attention: duplicates in L
// 2) brute-force: fist find all combinations of list of words, then use
//              string matching algorithms to find the results

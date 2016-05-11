import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;


//---------------------  Solution 1 ----------------------//
// normal recursion
public class WordBreak2 {
    public List<String> wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return new ArrayList<String>();
        }

        return wordBreakHelper(s, dict, new HashMap<String, ArrayList<String>>());
    }

    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> cache){
        if(cache.containsKey(s)) {
            return cache.get(s);
        }

        ArrayList<String> result = new ArrayList<String>();
        for(int len = 1; len <= s.length(); ++len){
            String word = s.substring(0,len);
            if(dict.contains(word)){
                // base case, last word
                if(len == s.length()){
                    result.add(word);

                // general case, recursive call
                } else {
                    String suffix = s.substring(len);
                    ArrayList<String> tmp = wordBreakHelper(suffix, dict, cache);
                    for(String item: tmp){
                        item = word + " " + item;
                        result.add(item);
                    }
                }
            }
        }
        cache.put(s, result);
        return result;
    }

    //---------------------  Solution 1 ----------------------//
//  normal recursion
    public List<String> wordBreak2(String s, Set<String> dict) {
        // input validation
        if (s == null || s.length() == 0) {
            return new ArrayList<String>();
        }

        //-- for passing online judge: use the following
        //-- method canBreak is the solution of the problem WordBreak
        // if (s == null || s.length() == 0 || !canBreak(s, dict)) {
        //     return new ArrayList<String>();
        // }

        // get the minLen and maxLen of the words in the dict
        int minLen = 0, maxLen = 0;
        for (String word: dict) {
            minLen = Math.min(minLen, word.length());
            maxLen = Math.max(maxLen, word.length());
        }

        // construct the cache
        // 1. empty List means no solution
        // 2. base case is the first element, set it to be empty sting "" (pay attention to line 31)
        Map<Integer, List<String>> cache = new HashMap<Integer, List<String>>();
        for (int i = 0; i <= s.length(); i++) {
            cache.put(i, new ArrayList<String>());
        }
        cache.get(0).add("");

        // start calculating
        for (int i = 1; i <= s.length(); i++) {
            List<String> results = new ArrayList<String>();
            for (int j = Math.max(0, i - maxLen); j <= i - minLen; j++) {
                String word = s.substring(j, i);
                if (dict.contains(word)) {
                    for (String prefix: cache.get(j)) {
                        results.add(prefix.length() == 0 ? word : prefix + " " + word);
                    }
                }
            }
            cache.put(i, results);
        }

        return cache.get(s.length());
    }

}


// 1 NOTE:  for the cache part in solution 1, we could either use the prefix as Key
//          or we could use the index as key (as we did in word break)
//          please think carefully about the differences, edge cases
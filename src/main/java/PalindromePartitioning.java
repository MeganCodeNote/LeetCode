import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    //--------------------- Solution --------------------------//
    // a combination problem
    // Naive Approach: replace buildCache method with isPalindrome, others are the same
    public List<List<String>> partition(String s) {
        // input validation
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) {
            return res;
        }
        boolean[][] cache = buildCache(s);
        populateResult(s, 0, new ArrayList<String>(), res, cache);
        return res;
    }

    private void populateResult(String s, int start, List<String> partition, List<List<String>> res, boolean[][] cache) {
        // base case
        if (start >= s.length()) {
            res.add(new ArrayList<String>(partition));
        }

        // general case
        for (int i = start; i < s.length(); i++) {
            if (cache[start][i]) {
                partition.add(s.substring(start, i + 1));
                populateResult(s, i + 1, partition, res, cache);
                partition.remove(partition.size() - 1);
            }
        }
    }

    private boolean[][] buildCache(String s) {
        boolean[][] cache = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 1 || cache[j + 1][i - 1])) {
                    cache[j][i] = true;
                }
            }
        }
        return cache;
    }
}

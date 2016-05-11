import java.util.ArrayList;
import java.util.List;

public class Solution {
    static String[] table = {"",   "",    "abc",  "def", "ghi", 
                            "jkl", "mno", "pqrs", "tuv", "wxyz"};
                            
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<String>();
        
        // input checking
        if (digits == null || digits.length() == 0) {
            results.add("");
            return results;
        }
        
        // call the helper to fill in the results array
        char[] prefix = new char[digits.length()];
        helper(digits, 0, prefix, results);
        return results;
    }
    
    void helper(String digits, int curIndex, char[] prefix, List<String> results) {
        // base case
        if (curIndex == digits.length()) {
            results.add(String.valueOf(prefix));
            return;
        }
        
        // general case
        int curDigit = digits.charAt(curIndex) - '0';
        for (int i = 0; i < table[curDigit].length(); i++) {
            prefix[curIndex] = table[curDigit].charAt(i);
            helper(digits, curIndex + 1, prefix, results);
        }
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().letterCombinations("23"));
	}
}
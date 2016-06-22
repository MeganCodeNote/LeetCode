import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    //-------------- Solution StringBuilder --------------//
    // could also use a char[] instead of a StringBuilder!
    public List<String> letterCombinations1(String digits) {
        // input checking
        List<String> results = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            results.add("");
            return results;
        }

        // normal case
        String[] table = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", };
        helper(results, new StringBuilder(), 0, digits, table);
        return results;
    }

    public void helper(List<String> results, StringBuilder prefix,
                        int curIndex, String digits, String[] table) {
        // base case
        if (curIndex == digits.length()) {
            results.add(prefix.toString());
            return;  // don't forget to return in base case!!!
        }

        // general case
        int i = digits.charAt(curIndex) - '0';
        for (char letter : table[i].toCharArray()) {
            prefix.append(letter);
            helper(results, prefix, curIndex + 1, digits, table);
            prefix.deleteCharAt(prefix.length() - 1); // delete last char
        }
    }
}

// NOTE: 1) Could also use a char[] instead of StringBuilder
//          char[] --> String: String.valueOf(char[])
//
//       2) letter table starts from '2'!, be careful
//
//       3) don't forget to return at BASE CASE;

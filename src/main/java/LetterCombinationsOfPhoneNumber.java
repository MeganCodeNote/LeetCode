import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {
    //-------------- Solution 1 --------------//
    // iterative:
    public List<String> letterCombinations(String digits) {
        // input checking
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }

        // normal cases
        String[] buttons = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<String>();
        List<String> resLast = new ArrayList<String>(); // last level
        resLast.add("");
        for (char digit: digits.toCharArray()) {  // NOTE: illegal to write --> for (char c: string)
            for (char letter: buttons[digit - '0'].toCharArray()) {
                for (String s: resLast) {
                    res.add(s + letter);   // it's ok to do "String" + 'x' <-- char
                }
            }
            resLast = res;
            res = new ArrayList<String>();
        }
        return resLast;
    }

    //-------------- Solution 2 --------------//
    // recursive:
    // could also use a char[] instead of a StringBuilder!
    // char[] --> String: String.valueOf(char[])
    public List<String> letterCombinations2(String digits) {
        // input checking
        List<String> results = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            results.add("");
            return results;
        }

        // normal case: (starts from index 2, pls see the phone keyboard)
        String[] buttons = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", };
        helper(results, new StringBuilder(), digits, buttons, 0);
        return results;
    }

    public void helper(List<String> results, StringBuilder prefix,
                       String digits, String[] table, int curIndex) {
        if (curIndex == digits.length()) {      // base case
            results.add(prefix.toString());
        } else {                                // general case
            int i = digits.charAt(curIndex) - '0';
            for (char letter : table[i].toCharArray()) {
                prefix.append(letter);
                helper(results, prefix, digits, table, curIndex + 1);
                prefix.deleteCharAt(prefix.length() - 1); // delete last char
            }
        }
    }

    ///////////////////  TEST ///////////////////
    public static void test(LetterCombinationsOfPhoneNumber solution, String digits) {
        System.out.println(digits);
        for (String s: solution.letterCombinations2(digits)) {
            System.out.print(String.format("\"%s\"  ", s));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();
        test(solution, "23");
    }
}

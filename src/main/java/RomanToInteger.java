import java.util.HashMap;
import java.util.Map;


public class RomanToInteger {
    public int romanToInt(String s) {
        // input checking
        if (s == null || s.length() == 0) {
            return 0;
        }
        // assume that the String s is a valid Roman Number all in capital
        // 1. build the table
        char[] keys = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] vals = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < vals.length; i++) {
            table.put(keys[i], vals[i]);
        }

        // 2. convert Roman to Integer
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // if is the last letter, or if this letter is >= next letter
            if (i == s.length() -1 || table.get(s.charAt(i)) >= table.get(s.charAt(i + 1))) {
                res += table.get(s.charAt(i));
            } else {
                res -= table.get(s.charAt(i));
            }
        }
        return res;
    }
    
    //////////////// Test ///////////////////
    public static void test(String s) {
        RomanToInteger solution = new RomanToInteger();
        System.out.println(s + " = " + solution.romanToInt(s));
    }

    public static void main(String[] argv) {
        test("MMMCMXCIX");
        test("VIII");
        test("XXVII");
    }
}

// ERROR:
// 1) -1  2) >=
//     if (i == s.length() || table.get(s.charAt(i)) > table.get(s.charAt(i + 1))) {
//     if (i == s.length() -1 || table.get(s.charAt(i)) >= table.get(s.charAt(i + 1))) {


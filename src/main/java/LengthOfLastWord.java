import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LengthOfLastWord {
    //--------------------  Solution 1 ------------------------//
    // sliding window, could be used to
    // 1. find kth word's length
    // 2. return the index of the last wo
    public int lengthOfLastWord(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return 0;
        }

        // use a start and a end pointer to point to the last seen word
        int start = -1, end = -1;
        for (int i = 0; i < s.length(); i++) {
            // if it's a whitespace, do nothing
            if (s.charAt(i) == ' ') {
                continue;
            } else if (Character.isLetter(s.charAt(i))) {
                if (i == 0 || s.charAt(i - 1) == ' ') {
                    start = i;
                }
                if (i == s.length() - 1 || s.charAt(i + 1) == ' ') {
                    end = i;
                }
            }
        }
        return (end == -1) ? 0 : (end - start + 1);
    }

    //--------------------  Solution 2 ------------------------//
    // search from end to front
    public int lengthOfLastWord2(String s) {
        // find the last alphabetic letter's index
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        
        // find the immediate previous space's index before the last word
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;     // if no words end==start==-1
    }

    //---------------------- Solution 2 ----------------------//
    // build-in string related methods and regex
    public int lengthOfLastWord4(String s) {
        // input validation
        if (s == null || s.trim().length() == 0) {
            return 0;
        }

        // at lease one word exists
        String[] words = s.split("\\s+");
        return words[words.length - 1].length(); // str.split(String regex)
    }
    
    public int lengthOfLastWord5(String s) {
        if (s == null) {
            return 0;
        }
        s = new StringBuilder(s).reverse().toString();
        Matcher m = Pattern.compile("\\w+").matcher(s);
        m.find();
        return m.group().length();
    }

    ///////////////  TEST ///////////////
    public static void main(String[] args) {
        LengthOfLastWord len = new LengthOfLastWord();
        System.out.println(len.lengthOfLastWord2("      "));
        System.out.println(len.lengthOfLastWord2(" 3"));
    }
}
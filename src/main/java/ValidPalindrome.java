
public class ValidPalindrome {
    //------------------- Solution 1 ------------------------//
    // two pointers meeting each other
    public boolean isPalindrome(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return true;
        }
        String str = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            while (i <= j && !isValid(str.charAt(i))) {
                i++;
            }
            while (i <= j && !isValid(str.charAt(j))) {
                j--;
            }
            if (i <= j && str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch);
    }


    //------------------- Solution 2 ------------------------//
    // equals its reverse
    public boolean isPalindrome2(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return true;
        }
        StringBuilder strBuilder = new StringBuilder();
        for (char ch : s.toLowerCase().toCharArray()) {
            if (isValid(ch)) {
                strBuilder.append(ch);
            }
        }
        return strBuilder.toString().equals(strBuilder.reverse().toString());
    }

    //-------------- Appendix ----------------//
    // if we could not use toLowerCase() or isLetter() or isDigit()
    private boolean isLetterOrDigit(char c) {
        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private boolean isSame(char c1, char c2) {
        // convert upper letter to lower letter
        // lowercase and digit stay the same
        if(c1 >= 'A' && c1 <= 'Z') {
            c1 = (char) (c1 - 'A' + 'a');
        }
        if(c2 >= 'A' && c2 <= 'Z') {
            c2 = (char) (c2 - 'A' + 'a');
        }
        return c1 == c2;
    }
}

// ERROR:
//    First check the index range, then access it
//    if (str.charAt(i) != str.charAt(j) && i <= j) {  <--- Wrong
//    if (i <= j && str.charAt(i) != str.charAt(j)) {  <--- right

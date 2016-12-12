
public class PalindromeNumber {
    //------------- Solution 1 ---------------//
    // use StringBuilder library
    // space = O(n)
    public boolean isPalindrome(int x) {
        // pay attention to negative numbers
        if (x < 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder().append(x);
        return sb.toString().equals(sb.reverse().toString());
    }

    //------------- Solution 2 ---------------//
    // original == reversed
    // space = 2 long type variables
    public boolean isPalindrome2(int x) {
        // pay attention to negative numbers
        if (x < 0) {
            return false;
        }
        // use long type to prevent reversed overflow
        long reversed = 0; // could also use StringBuilder
        long xCopy = x;
        while (x > 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return reversed == xCopy;
    }

    //------------- Solution 3 ---------------//
    // from two sides to middle
	  public boolean isPalindrome3(int x) {
        // pay attention to negative numbers
        if (x < 0) {
            return false;
        }

        int div = 1, temp = x;
        while (temp / div >= 10) { // pay attention not to over flow
            div *= 10;
        }
        while (x > 0) {
            if (x / div != x % 10) {    // compare highest with lowest digits
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
	}


    ////////////////// TEST ///////////////////////
    public static void main(String[] args) {
        int n1 = 0;
        int n2 = -0;
        int n3 = -1221;
        int n4 = 1221;
        int n5 = 12212;
        int n6 = +1221;

        PalindromeNumber solution = new PalindromeNumber();
        System.out.println(solution.isPalindrome(n1));
        System.out.println(solution.isPalindrome(n2));
        System.out.println(solution.isPalindrome(n3));
        System.out.println(solution.isPalindrome(n4));
        System.out.println(solution.isPalindrome(n5));
        System.out.println(solution.isPalindrome(n6));
    }
}

public class StringToInteger {
	
	//------------- Solution 1. Use casting ---------------//
    // use long to prevent overflow
    public static int atoi(String str) {
        // input checking
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        str = str.trim();

        // get the sign
        int index = 0;
        boolean isNeg = false;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            index++;
            isNeg = (str.charAt(0) == '-') ? true : false;
        } else if (!Character.isDigit(str.charAt(0))) {
            return 0;
        }

        // convert the string to integer
        long res = 0;
        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            res = res * 10 + (str.charAt(index) - '0');
            if (res > Integer.MAX_VALUE) { // if next res is 2147483648 and isNeg --> would return MIN
                // treat MIN differently, but MIN fits in the logic here
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            index++;
        }
        return (int)(isNeg ? -res : res);
    }
    
    
	//-------------   Solution 2 No casting   ---------------//
    public int atoi2(String str) {
        // input checking
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        str = str.trim();

        // get the sign
        int index = 0;
        boolean isNeg = false;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            index++;
            isNeg = (str.charAt(0) == '-') ? true : false;
        } else if (!Character.isDigit(str.charAt(0))) {
            return 0;
        }

        // convert the string to integer
        int res = 0;
        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            int digit = str.charAt(index) - '0';
            if (res > (Integer.MAX_VALUE - digit) / 10) { // if next res is 2147483648 --> think of MAX and MIN
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + digit;
            index++;
        }
        return isNeg ? -res : res;
    }
    
    
    ////////////////// TEST ///////////////////////

    public static void main(String[] args) {
        StringToInteger solution = new StringToInteger();
        System.out.println(solution.atoi(""));
        System.out.println(solution.atoi("  "));
        System.out.println(solution.atoi(null));
        String s1 = " a-00df ";
        System.out.println(solution.atoi(s1));
        String s2 = " -000124  ";
        System.out.println(solution.atoi(s2));
        String s3 = " +000124a ";
        System.out.println(solution.atoi(s3));
        String s4 = " 2147483650  ";
        System.out.println(solution.atoi(s4));
        String s5 = " -2147483648   ";
        System.out.println(solution.atoi(s5));
    }
}


public class AddBinary {
    //--------------- Solution 1  ----------------//
    // recursion
    final static String empty = "";

    public String addBinary(String a, String b) {
        return helper(a, b, 0).toString();
    }

    private StringBuilder helper(String a, String b, int carry) {
        // base case
        if (empty.equals(a) && empty.equals(b) && carry == 0) {
            return new StringBuilder();
        }

        // general case
        int digitA = empty.equals(a) ? 0 : a.charAt(a.length() - 1) - '0';
        int digitB = empty.equals(b) ? 0 : b.charAt(b.length() - 1) - '0';
        int sum = digitA + digitB + carry;
        char curChar = (char) ('0' + sum % 2);

        String aRest = empty.equals(a) ? empty : a.substring(0, a.length() - 1);
        String bRest = empty.equals(b) ? empty : b.substring(0, b.length() - 1);
        StringBuilder res = helper(aRest, bRest, sum / 2);
        return res.append(curChar);
    }

    //--------------- Solution 2  ----------------//
    // iterative 1
    public String addBinary2(String a, String b) {
        // input checking (could pass without this checking)
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        
        // both a and b are non-empty strings
        int carry = 0;
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            // sum the current digit
            int digitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.charAt(j) - '0' : 0;
            int sum = digitA + digitB + carry;
            carry = sum / 2;
            res.append(sum % 2);

            // update iterator
            i = (i >= 0) ? i - 1 : -1;
            j = (j >= 0) ? j - 1 : -1;
        }
        return res.reverse().toString();
    }

    //--------------- Solution 3  ----------------//
    // iterative 2
    public String addBinary3(String a, String b) {
        // assume a and b are non-null, no-leading 0s, valid binary
        // switch parameters, make sure a is shorter
        int carry = 0;
        StringBuilder res = new StringBuilder();
        int pa = a.length() - 1, pb = b.length() - 1;

        // add two digits
        while (pa >= 0 && pb >= 0) {
            int sum = carry + (a.charAt(pa) - '0') + (b.charAt(pb) - '0');
            carry = sum / 2;
            res.append(sum % 2);
            pa--;
            pb--;
        }
        // add 1 digit
        int pLeftover = (pa >= 0) ? pa : pb;
        String sLeftOver = (pa >= 0) ? a : b;
        while (pLeftover >= 0) {
            int sum = carry + (sLeftOver.charAt(pLeftover) - '0');
            carry = sum / 2;
            res.append(sum % 2);
            pLeftover--;
        }
        // add the carry
        if (carry == 1) {
            res.append(carry);
        }
        return res.reverse().toString();
    }
}


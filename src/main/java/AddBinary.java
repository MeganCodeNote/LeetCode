
public class AddBinary {
    //--------------- Solution 1  ----------------//
    // iterative 1
    public String addBinary(String a, String b) {
        // input checking (could pass without this checking)
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        
        // both a and b are non-empty strings
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || carry == 1) {
            // sum the current digit
            int digitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.charAt(j) - '0' : 0;
            int digitSum = digitA + digitB + carry;
            res.append(digitSum % 2);
            carry = digitSum / 2;
            // update iterator
            i = (i >= 0) ? i - 1 : -1;
            j = (j >= 0) ? j - 1 : -1;
        }
        return res.reverse().toString();
    }

    //--------------- Solution 2  ----------------//
    // iterative 2
    public String addBinary2(String a, String b) {
        // assume a and b are non-null, no-leading 0s, valid binary
        // switch parameters, make sure a is shorter
        if (a.length() > b.length()) {
            return addBinary(b, a);
        }

        StringBuilder res = new StringBuilder();
        int carry = 0;
        int pa = a.length() - 1, pb = b.length() - 1;
        // add two digits
        while (pa >= 0) {
            int sum = carry + (a.charAt(pa) - '0') + (b.charAt(pb) - '0');
            res.append(sum % 2);
            carry = sum / 2;
            pa--;
            pb--;
        }
        // add 1 digit
        while (pb >= 0) {
            int sum = carry + (b.charAt(pb) - '0');
            res.append(sum % 2);
            carry = sum / 2;
            pb--;
        }
        // add the carry
        if (carry == 1) {
            res.append(carry);
        }
        return res.reverse().toString();
    }
}


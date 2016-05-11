public class PlusOne {
    public int[] plusOne(int[] digits) {
        // input validation
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) {
                return digits; // ATTENTION 1: fast break when carry is 0
            }
        }

        // ATTENTION 2: when finally carry is 1, the rest must be 0
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    /////////////// TEST ////////////////
    public static void main(String[] args) {
        int[] digits = {9,9,9,9};
        PlusOne solution = new PlusOne();
        int[] sum = solution.plusOne(digits);

        for(int i: sum) {
            System.out.print(i + ", ");
        }
    }
}

// Question 1: what is the base? 2 or 10?
// Question 2: can I modify the original digits
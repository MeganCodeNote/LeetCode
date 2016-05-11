public class IntegerToRoman {
    //-----------------  Solution 1 ---------------------//
    // use enum for dictionary
    public String intToRoman(int num) {
        // input checking
        if (num <= 0 || num > 3999) {
            return null;
        }
        // from the largest digits to the lowest digits
        StringBuilder res = new StringBuilder();
        for (Roman digit: Roman.values()) {
            while (num >= digit.value) {
                res.append(digit);
                num -= digit.value;
            }
        }
        return res.toString();
    }

    enum Roman {
        M(1000), CM(900), D(500), CD(400),
        C(100), XC(90), L(50), XL(40),
        X(10), IX(9), V(5), IV(4), I(1);  // arrange them from largest to smallest
                                          // used in for (Roman digit : Roman.values())

        int value;
        Roman(int value) { // no public decorator
            this.value = value;
        }
    }

    //-----------------  Solution 1 ---------------------//
    // use parallel array
    public String intToRoman2(int num) {
        // input checking
        if (num <= 0 || num > 3999) {
            return null;
        }

        // build the dictionary (descending)
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}; // magic 1954
        String[] digits = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                res.append(digits[i]);
                num -= values[i];
            }
        }
        return res.toString();
    }


    /////////////////// Test //////////////////////
    static void test(int n, IntegerToRoman solution) {
        System.out.println(n + " = " + solution.intToRoman(n));
    }
    public static void main(String[] args) {
        IntegerToRoman solution = new IntegerToRoman();
        test(3999, solution);
        test(0, solution);
        test(944, solution);
        test(8, solution);
    }
}
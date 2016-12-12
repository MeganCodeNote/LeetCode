
public class MultiplyStrings {
    //-----------------   SOLUTION 1  ---------------------//
    // 1. put digit product in bucket
    // 2. take care of carry
    public String multiply(String num1, String num2) {
        //======= could pass without this part ======//
        // input checking
        if (num1 == null || num2 == null || num1.length() == 0  || num2.length() == 0) {
            return "";
        }
        // quick return
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        //======== multiplication =========//
        int N1 = num1.length(), N2 = num2.length();
        int[] product = new int[N1 + N2];
        for (int i = N1 - 1; i >= 0; i--) {
            for (int j = N2 - 1; j >= 0; j--) {
                // accumulate in bucket without taking care of carry
                product[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        // work on carry and build the result string
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = N1 + N2 - 1; i >= 0; i--) {
            int digitSum = carry + product[i];
            carry = digitSum / 10;
            sb.append(digitSum % 10);
        }  // after the loop the carry must be 0

        String res = sb.reverse().toString().replaceFirst("^0+", "");
        return res.isEmpty() ? "0" : res;
    }


    //-----------------   SOLUTION 2  ---------------------//
    // mimic the multiplication of math 
    public String multiply2(String num1, String num2) {
        // input checking
        if (num1 == null || num2 == null || num1.length() == 0  || num2.length() == 0) {
            return "";
        }
        // quick return
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        // mock the multiply process
        //   num2 <--- j
        // * num1 <--- i
        //-------------
        //    sb
        int N1 = num1.length(), N2 = num2.length();
        int[] product = new int[N1 + N2];
        for (int i = N1 - 1; i >= 0; i--) {
            for (int j = N2 - 1; j >= 0; j--) {
                int digitProd = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int bucketSum = product[i + j + 1] + digitProd;
                product[i + j + 1] = bucketSum % 10;    // current bucket:  "=" not "+="
                product[i + j] += bucketSum / 10;       // carry:           "+=" not "="
            }
        }

        // build the result
        StringBuilder sb = new StringBuilder();
        for (int digit: product) {
            sb.append(digit);
        }
        String res = sb.toString().replaceFirst("^0+", "");
        return res.isEmpty() ? "0" : res;
    }
    
    /////////////////////////   TEST   /////////////////////////////
    private static void test(String num1, String num2, MultiplyStrings solution) {
//        String product = solution.multiply(num1, num2);
        String product = solution.multiply2(num1, num2);
        System.out.println(num1 + " * " + num2 + " = " + product);
    }

    public static void main(String[] args) {
        MultiplyStrings solution = new MultiplyStrings();
        test("8", "9", solution);
        test("009", "013456", solution);
        test("0", "2345", solution);
        test("0", "0000", solution);
    }
}

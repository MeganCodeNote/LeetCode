
public class MultiplyStrings {
    //-----------------   SOLUTION 1  ---------------------//
    // 1. put digit product in butcet
    // 2. take care of carry
    public String multiply(String num1, String num2) {
        //======= could pass without this part ======//
        // input validation
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
             return "";
        }

        // special cases to end the calculation earlier
        if (num1.equals("0") || num2.equals("0")) {
             return "0";
        }

        //======== multiplication =========//
        // reverse the input strings for better manipulation
        // but impact performance (legibility V.S. performance)
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        // calculate each digit in num1 with each digit in num2 (not consider carries)
        int[] product = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                product[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        // handle the carries
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < product.length; i++) {
            int sum = carry + product[i];
            carry = sum / 10;
            sb.append(sum % 10);
        }

        // remove leading '0's
        // replaceFirst could only be used for String, not StrnigBuilder
        String res = sb.reverse().toString().replaceFirst ("0*", ""); 
        return res.isEmpty() ? "0" : res;
    }


    //-----------------   SOLUTION 2  ---------------------//
    // mimic the multiplication of math 
    public String multiply2(String num1, String num2) {
        //======= could pass without this part ======//
        // input cheching
        if (num1 == null || num2 == null || num1.isEmpty() || num2.isEmpty()) {
            return null;
        }
        // quick result
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        //======= could pass without this part ======//
        // calculation:
        //   num2 <--- j
        // * num1 <--- i
        //-------------
        //    res
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = res[i + j + 1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                res[i + j + 1] = product % 10;  // current digit
                res[i + j] += product / 10;     // carry ERROR:  = --> +=
            }
        }
        
        //======= result formatting ======//
        // strip leading "0"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i]);
        }
        String resStr = sb.toString().replaceFirst("^0+", "");
        return resStr.isEmpty() ? "0" : resStr;
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

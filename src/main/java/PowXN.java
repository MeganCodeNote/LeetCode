
public class PowXN {
    //------------------  Solution 1 --------------------//
    // divide and conquer - recursion
    public double myPow(double x, int n) {
        /****** Could pass if the following block is removed ******/
        // validation
        if (x == 0 && n > 0) return 0;
        if (x == 0 && n < 0) throw new ArithmeticException("Could not raise 0 to a negative number!");
        // quick return
        if (x == 1) return 1;
        if (x == -1) return (n % 2 == 0) ? 1 : -1;
        /**********************************************************/

        // base case
        if (n == 0) {
            return 1;
        }
        // general case
        double remainder = 1;
        if (n % 2 != 0) {
            remainder = (n > 0) ? x : 1 / x;
        }
        double half = myPow(x, n / 2);
        return half * half * remainder;
    }

    //------------------  Solution 2 --------------------//
    // bitwise operation - recursion
    public double myPow2(double x, int n) {
        boolean negPow = n < 0;
        long absN = Math.abs((long)n);  // use long to prevent overflow
        double res = 1;
        for (int i = 31; i >= 0; i--) { // absN is long, start from the sign bit
            res = res * res;
            res *= (absN & (1 << i)) == 0 ? 1 : x;
        }
        return negPow ? 1 / res : res;
    }

    ////////////////////  TEST  /////////////////////
    public static void main(String[] args) {
        try {
            PowXN p = new PowXN();

            // n > 0
            System.out.println(p.myPow(-3, 2));
            System.out.println(p.myPow(1, 2));
            System.out.println(p.myPow(0.5, 3));
            System.out.println(p.myPow(-0.5, 3));

            // n < 0
            System.out.println(p.myPow(-3, -1));
            System.out.println(p.myPow(3, -2));
            System.out.println(p.myPow(0.5, -3));
            System.out.println(p.myPow(-0.5, -3));

            // n == 0
            System.out.println(p.myPow(1, 0));
            System.out.println(p.myPow(-0.5, 0));

            // x == 0
            System.out.println(p.myPow(0, 0));
            System.out.println(p.myPow(0, 1));
            System.out.println(p.myPow(0, -1));

            // x == 1
            System.out.println(p.myPow(1, Integer.MIN_VALUE));


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


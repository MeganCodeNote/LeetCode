
public class PowXN {
    //------------------  Solution 1 --------------------//
    // divide and conquer - recursion
    public double pow(double x, int n) {
        // cases for 0
        if (n == 0) return 1;
        if (x == 0 && n > 0) return 0;
        // if (x == 0 && n < 0) throw new Exception("could not raise 0 to a negative power");

        // optimization for 1 and -1 (could be removed from the solution)
        if (x == 1) return 1;
        if (x == -1) return (n % 2 == 0) ? 1 : -1;

        // general case
        double remainder = 1;
        if (n % 2 != 0) {
            remainder = (n > 0) ? x : (1 / x);
        }
        double half = pow(x, n / 2);
        return half * half * remainder;
    }

    //------------------  Solution 2 --------------------//
    // divide and conquer - recursion

    public static void main(String[] args) {
        try {
            PowXN p = new PowXN();

            // n > 0
            System.out.println(p.pow(-3, 2));
            System.out.println(p.pow(1, 2));
            System.out.println(p.pow(0.5, 3));
            System.out.println(p.pow(-0.5, 3));

            // n < 0
            System.out.println(p.pow(-3, -1));
            System.out.println(p.pow(3, -2));
            System.out.println(p.pow(0.5, -3));
            System.out.println(p.pow(-0.5, -3));

            // n == 0
            System.out.println(p.pow(1, 0));
            System.out.println(p.pow(-0.5, 0));

            // x == 0
            System.out.println(p.pow(0, 0));
            System.out.println(p.pow(0, 1));
            System.out.println(p.pow(0, -1));

            // x == 1
            System.out.println(p.pow(1, Integer.MIN_VALUE));


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

//
//NOTE test cases: (0, 1, -1, 0.5, MIN, MAX)
//for x --> -3, -1, -0.5, 0, 0.5, 1, 3
//for n --> -2, -1, 0, 1, 2
//           Integer.MIN_VALUE  even number, odd number
//
//******** power(x, n): Power Logic ***********
//deal with 0
//-- if (n == 0) return 1;            // (for all types of x: < 0, == 0, > 0)
//-- if (x == 0 && n > 0) return 0;
//-- if (x == 0 && n < 0) Exception;
//
//deal with when n is negative
//-- if (n < 0) return 1/power(x, -n)
//-- else return power(x, n/2) * power(x, n/2) * remainder
//


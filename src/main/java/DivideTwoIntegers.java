
public class DivideTwoIntegers {
    //------------------ Solution 1 ---------------------//
    // each time find shift from 1
    public int divide(int dividend, int divisor) {
        // divide-by-0 validation
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        boolean isNeg = ((dividend ^ divisor) >>> 31 == 1) ? true : false; // >>> ≠ >>
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        long res = 0;
        while (a >= b) {
            long shift = 1;
            long temp = b;
            while (a >= temp) {  // long, won't overflow
                shift <<= 1;
                temp <<= 1;
            }
            res += (shift >> 1);
            a -= (temp >> 1);
        }
        return (int)(isNeg ? -res : res); // don't forget the casting (int)
    }

    //------------------ Solution 2 ---------------------//
    // first find the largest shift
    // then do it bit by bit
    public int divide2(int dividend, int divisor) {
        // divide-by-0 validation
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        boolean isNeg = ((dividend ^ divisor) >>> 31 == 1) ? true : false; // >>> ≠ >>
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        // find the largest shift
        long shift = 1;
        long temp = b;
        while (a >= temp) {  // long, won't overflow
            shift <<= 1;
            temp <<= 1;
        }

        // subtract from largest shift to 1
        long res = 0;
        while (shift >= 1) {
            if (a >= temp) {
                a -= temp;
                res += shift;
            }
            shift >>= 1;
            temp >>= 1;
        }
        return (int)(isNeg ? -res : res);
    }

    //////////////////   TEST   /////////////////////
    public static void main(String[] args) {
        DivideTwoIntegers solution = new DivideTwoIntegers();
        int a = solution.divide(-10, 3);
        System.out.println(a);

        int b = solution.divide(-1, -1);
        System.out.println(b);

        int c = solution.divide(Integer.MIN_VALUE, 1);
        System.out.println(c);

        int d = solution.divide(Integer.MIN_VALUE, Integer.MIN_VALUE);
        System.out.println(d);

        int e = solution.divide(0, Integer.MAX_VALUE);
        System.out.println(e);

        int f = solution.divide(Integer.MAX_VALUE, 0);
        System.out.println(f);
    }
}

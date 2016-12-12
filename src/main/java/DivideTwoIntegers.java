
public class DivideTwoIntegers {
    //------------------ Solution 1 ---------------------//
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        boolean isNeg = ((dividend ^ divisor) >>> 31) == 1;
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int res = 0;
        while (a >= b) {
            a -= b;
            res += 1;
        }
        return isNeg ? -res : res;
    }

    //------------------ Solution 2 ---------------------//
    public int divide2(int dividend, int divisor) {
        // the only 2 cases of overflow: 1) divide by 0 2) -214743648 / -1
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }

        boolean isNeg = ((dividend ^ divisor) >>> 31) == 1; //get sign:shift 31 ! not 32
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int res = 0;    // res won't overflow, use int; res is positive,

        while (a >= b) {
            long shift = 1;
            long multiple = b;
            while (a >= (multiple << 1)) {
                shift <<= 1;
                multiple <<= 1;
            }
            a -= multiple;
            res += shift;
        }
        return isNeg ? -res : res;
    }

    //------------------ Solution 3 ---------------------//
    // first find the largest shift
    // then do it bit by bit
    public int divide3(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        boolean isNeg = ((dividend ^ divisor) >>> 31) == 1;
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int res = 0;

        // find the largest shift
        long shift = 1;
        long multiple = b;
        while (a >= (multiple << 1)) {
            shift <<= 1;
            multiple <<= 1;
        }

        // accumulate res bit by bit
        while (shift >= 1) {
            if (a >= multiple) {
                a -= multiple;
                res += shift;
            }
            shift >>= 1;
            multiple >>= 1;
        }

        return isNeg ? -res : res;
    }

    //////////////////   TEST   /////////////////////
    public static void test(DivideTwoIntegers solution, int dividend, int divisor, int expected) {
        int actual = solution.divide(dividend, divisor);
        System.out.println(String.format("\nActual: %s / %s = %s \nExpected: %s", dividend, divisor, actual, expected));
        System.out.println(actual == expected);
    }
    public static void main(String[] args) {
        DivideTwoIntegers solution = new DivideTwoIntegers();
        test(solution, -10, 3, -3);
        test(solution, Integer.MIN_VALUE, -1, Integer.MAX_VALUE);
        test(solution, Integer.MIN_VALUE, Integer.MIN_VALUE, 1);
        test(solution, 0, Integer.MAX_VALUE, 0);
        test(solution, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        test(solution, Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
    }
}

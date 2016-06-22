
public class ReverseInteger {
    //----------------- Solution 1 --------------------//
    // ultimate: a very classic reverse process
    public int reverse(int x) {
        // 1. Assume that there is no overflow after reverse
        // 2. the following works even if x < 0
        //      in Java -123 / 10 = -12  (python: -13)
        //              -123 % 10 = -3   (python: 7)
        int res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    public long reverse2(int x) {
        int sign = (x > 0) ? 1 : -1; // get the sign
        long absX = x * sign;        // use long (-Integer.MIN_VALUE overflow)
        long absXReverse = 0;
        while(absX != 0) {
            absXReverse = absXReverse * 10 + absX % 10;
            absX /= 10;
        }
        return absXReverse * sign;
    }

    //----------------- Solution 2 --------------------//
    // string method
    public long reverse3(int x) {
        int sign = (x < 0) ? -1 : 1;
        String xStr = String.valueOf((long)x * sign);
        String xStrR = new StringBuilder(xStr).reverse().toString();
        xStrR.replace("^0+","");
        return Long.parseLong(xStrR) * sign;
    }


    //----------------- Solution 3 --------------------//
    // if overflow, return Integer.MIN_VALUE || Integer.MAX_VALUE
    // this is the most strict solution for the problem
    public int reverse4(int x) {
        if (x == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        int abs = Math.abs(x);
        int res = 0;
        while (abs != 0) {
            if ((Integer.MAX_VALUE - abs % 10) / 10 < res) {
                return (x > 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + abs % 10;
            abs /= 10;
        }
        return (x > 0) ? res : -res;
    }
    
    ////////////////// TEST ///////////////////////

    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();
        int[] numbers = {-1200, 2344, 0, 2345, 1220010};
        System.out.println("----------------");
        for (int x : numbers) {
            System.out.println(solution.reverse(x));
        }

        System.out.println("----------------");
        for (int x : numbers) {
            System.out.println(solution.reverse2(x));
        }
        System.out.println(-123/10);
    }
}

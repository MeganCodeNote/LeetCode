
public class Sqrt {
    //-----------  Binary Search --------------//
    public int sqrt(int x) {
        if (x < 0) {
            return -1; // discuss this with interviewer, what answer do they want
        }
        if (x == 0) {
            return 0;
        }

        int lo = 1, hi = x / 2 + 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (x / mid == mid) {   // do not use mid * mid < x, might have overflow
                return mid;
            } else if (x / mid < mid) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }


    //-----------  Newton's method --------------//
    public int sqrt2(int x) {
        if (x < 0) {
            return -1; // discuss this with interviewer, what answer do they want
        }
        if (x == 0) {
            return 0;
        }

        // use Newton's method to solve the problem
        double lastGuess = 0;
        double guess = 1;
        while (lastGuess != guess) {    // in java if abs(double1 - doubl2) <= epsilon, they are the same (not in C++ or Python)
            lastGuess = guess;
            guess = (guess + x / guess) / 2; // magic update derived from x - f(x)/f'(x)
        }
        return (int) guess;
    }

    public static void main(String[] args) {
        long x = (long)2147483647 * 2147483647;
        System.out.println(x);
    }
}

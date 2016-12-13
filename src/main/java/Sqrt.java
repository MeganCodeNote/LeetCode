
public class Sqrt {
    //-----------  Binary Search --------------//
    public int sqrt(int x) {
        if (x < 0)  return -1; // discuss this case with interviewer
        if (x == 0) return 0;

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
        if (x < 0)  return -1; // discuss this case with interviewer
        if (x == 0) return 0;

        // use Newton's method to solve the problem
        double lastGuess = 0, guess = 1;     // use double to avoid looping for ever
        while (lastGuess != guess) {         // in Java, equivalent to: abs(a-b) <= epsilon  (not so in C++ or Python)
            lastGuess = guess;
            guess = (guess + x / guess) / 2; // magic update derived from x - f(x)/f'(x)
        }
        return (int) guess;                  // if return type is double, do not need to do the type casting
    }

    public static void main(String[] args) {
        int x = 32;
        System.out.println(new Sqrt().sqrt(x));
    }
}

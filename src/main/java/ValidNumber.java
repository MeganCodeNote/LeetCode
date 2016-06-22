
public class ValidNumber {
    //------------------- Solution 1 ------------------//
    // use regular expression
    public boolean isNumber(String s) {
        return s.trim().matches("[-+]?([0-9]+\\.?|[0-9]*\\.[0-9]+)([eE][-+]?[0-9]+)?");
    }

    //------------------- Solution 2 ------------------//
    // check string one-by-one
    public boolean isNumber2(String s) {
        // input validation
        if (s == null) {
            return false;
        }
        s = s.trim().toLowerCase();
        boolean hasNum = false;
        boolean hasE = false;
        boolean hasDot = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                hasNum = true;
            } else if (ch == '.') {
                if (hasE || hasDot) {
                    return false;
                }
                hasDot = true;
            } else if (ch == '+' || ch == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else if (ch == 'e') {
                if (!hasNum || hasE) {
                    return false;
                }
                hasE = true;
                hasNum = false;
            } else {
                return false;
            }
        }
        return hasNum;
    }


    public static void test() {
        int[] a = new int[20];
        a[0] = +000;
        a[1] = -0;
        a[2] = 0;
        a[3] = +130;
        a[4] = -130;
        a[5] = 00130;
        a[6] = Integer.MAX_VALUE;
        a[6] = Integer.MIN_VALUE;

        long[] b = new long[20];
        b[0] = -0l;
        b[1] = +0l;
        b[2] = 0l;
        b[3] = -15l;
        b[4] = +15l;
        b[5] = 15l;

        double[] c = new double[20];
        c[0] = 177.1e+00f;
        c[1] = 00.;
        c[1] = .0000f;

        System.out.println(a.toString());
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        System.out.println("1ex ee".split("e").length);
    }

    public static void main(String[] args) {
        System.out.println(new ValidNumber().isNumber2("1.0e+"));
    }
}

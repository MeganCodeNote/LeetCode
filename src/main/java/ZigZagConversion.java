
public class ZigZagConversion {
    public String convert(String s, int nRows) {
        // input validation
        if (s == null || s.length() == 0 || nRows <= 1) {
            return s;   // null || "" || s
        }

        int len = 2 * (nRows - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = i; j < s.length(); j += len) {
                // 1. append j
                sb.append(s.charAt(j));

                // 2. optional append next (exclude first and last row)
                int next = j + 2 * (nRows - 1 - i);
                if (i != 0 && i != nRows - 1 &&  next < s.length()) {
                    sb.append(s.charAt(next));
                }
            }
        }
        return sb.toString();
    }
    
    
    ////////////////// TEST ///////////////////////
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int rows = 3;
        System.out.println(new ZigZagConversion().convert(s, rows));
    }
}

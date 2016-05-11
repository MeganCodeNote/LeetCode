
public class CountAndSay {
    //--------------------- Solution 1 ----------------------//
    // add the count and say, when we meet the beginning of new number
    public String countAndSay(int n) {
        // input validation
        if (n <= 0) {
            return "";
        }

        // for n >= 1
        String res = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char ch = res.charAt(0);
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) != res.charAt(j - 1)) {
                    sb.append(count + "" + ch);
                    count = 1;
                    ch = res.charAt(j);
                } else {
                    count++;
                }
            }
            sb.append(count + "" + ch);
            res = sb.toString();
        }
        return res;
    }

    //--------------------- Solution 2 ----------------------//
    // add the count and say, when we meet the last repetitive number
    public String countAndSay2(int n) {
        // input validation
        if (n <= 0) {
            return "";
        }

        // for n >= 1
        String res = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int lo = 0, hi = 0; hi < res.length(); hi++) {
                if (hi == res.length() - 1 || res.charAt(hi) != res.charAt(hi + 1)) {
                    sb.append((hi - lo + 1) + "" + res.charAt(hi));
                    lo = hi + 1;
                }
            }
            res = sb.toString();
        }
        return res;
    }

    /////////////////////   TEST   ////////////////////////
    public static void main(String[] args) {
        // Expected output:
        // 1  2   3   4     5       6       7
        // 1, 11, 21, 1211, 111221, 312211, 13112221, ...
        CountAndSay solution = new CountAndSay();
        for (int i = -1; i <= 10; i++) {
            String s = solution.countAndSay(i);
            System.out.println("The " + i + "th: " + s);
        }
    }
}

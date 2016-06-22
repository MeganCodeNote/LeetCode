
public class DecodeWays {
    //------------------  Solution 1 ----------------//
    // dp, use N space
    public int numDecodings(String s) {
        // input checking: first char is '0', no way to decode!!!
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int N = s.length();
        int[] counts = new int[N + 1];  // index indicates len
        counts[0] = 1;  // num of decodes s.substring(0,1) <- first 0 char(s) of s
        counts[1] = 1;  // num of decodes s.substring(0,2) <- first 1 char(s) of s
        for (int i = 1; i < N; i++) {
            // case 1. use a single digit to decode
            if (s.charAt(i) != '0') {
                counts[i + 1] += counts[i];  // i'th char in s, stored in i + 1's place in counts
            }
                
            // case 2. use two digits to decode
            if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                counts[i + 1] += counts[i - 1];
            }
        }
        return counts[N];
    }
    
    //------------------  Solution 2 ----------------//
    // dp, use constant space
    public int numDecodings2(String s) {
        // input checking: first char is '0', no way to decode!!!
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // dp[i] --> prefix with length i
        int num1 = 1;  // dp[0] = 1
        int num2 = 1;  // dp[1] = 1
        for (int i = 1; i < s.length(); i++) {
            // case 1. use a single digit to decode
            int num3 = 0;
            if (s.charAt(i) != '0') {
                num3 += num2;
            }
            // case 2. use two digits to decode
            int val = Integer.valueOf(s.substring(i - 1, i + 1));
            if (10 <= val && val <= 26) {
                num3 += num1;
            }
            // update
            num1 = num2;
            num2 = num3;
        }
        return num2;
    }
}


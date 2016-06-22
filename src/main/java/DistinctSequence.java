/* Created by meganlee on 9/14/14. */

public class DistinctSequence {
    public int numDistinct(String S, String T) {
        // input validation
        if (S == null || T == null || S.length() < T.length()) {
            return 0;
        }

        // initialize the the dp table
        int lenS = S.length(), lenT = T.length();
        int[][] nums = new int[lenS + 1][lenT + 1];
        for (int i = 0; i <= lenS; i++) {
            nums[i][0] = 1;  // if lenT == 0, empty string, return 1
            // if lenS == 0, return 0
        }

        // calculate the number row by row
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenT; j++) {
                nums[i][j] = nums[i - 1][j];
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    nums[i][j] += nums[i - 1][j - 1];
                }
            }
        }
        return nums[lenS][lenT];
    }
}

// NOTE 1: see the Design Sketch page 11
/* Created by meganlee on 11/15/14. */

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        // input checking
        if (num == null || num.length == 0) {
            return 0;
        }

        // put original array into a set
        Set<Integer> set = new HashSet<Integer>();
        for (int n : num) {
            set.add(n);
        }

        int res = 0;
        for (int i = 0; i < num.length && !set.isEmpty(); i++) {
            // go down
            int count = 0;
            for (int n = num[i]; set.contains(n); n--) {
                set.remove(n);
                count++;
            }
            // go up
            for (int n = num[i] + 1; set.contains(n); n++) {
                set.remove(n);
                count++;
            }
            res = Math.max(count, res);
        }
        return res;
    }
}

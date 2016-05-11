import java.util.*;


public class LengthOfLongestSubstring {
    /*---------- Solution :  Sliding Window--------------*/
    // HashSet or boolean[256]
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> seenChars = new HashSet<Character>();
        int max = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            char curChar = s.charAt(end);
            if (seenChars.contains(curChar)) {
                while (s.charAt(start) != curChar) {
                    seenChars.remove(s.charAt(start));
                    start++;
                }
                start++; // DO NOT remove s.charAt(char) == curChar from seeChars
            } else {
                seenChars.add(curChar);
                max = Math.max(max, end - start + 1);
            }
        }
        return max;
    }
    

    /*---------- Solution 2:  Sliding Window--------------*/
    // use the cache to remember the last index
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] table = new int[256];
        Arrays.fill(table, -1);
        int res = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            int lastIndex = table[ch];
            if (lastIndex < start) {    // unseen char in current substring
                res = Math.max(res, end - start + 1);
            } else {                    // seen char in current substring
                start = lastIndex + 1;
            }
            table[ch] = end;
        }
        return res;
    }


    ////////////////// TEST ///////////////////////
    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();

        String s1 = "bbmqbwkkyh";
        int len1 = solution.lengthOfLongestSubstring(s1);
        System.out.println(len1);

        String s2 = "qopubjguxhxdipfzwswybgfylqvjzhar";
        int len2 = solution.lengthOfLongestSubstring(s2);
        System.out.println(len2);

        String s3 = "opubpubxyz";
        int len3 = solution.lengthOfLongestSubstring(s3);
        System.out.println(len3);
    }
}

//=========  TAG: ==========//
// String / Sliding window / two pointers
//
//=========  Design: =========//
// a.--- Brute-force, two pointers
// b.--- HashTable / boolean[256] solution 1
// c.--- use hashtable to record the last index solution 2
//
//=========  Error/Note: =========//
//      1) pay attention to update the cache
//         update the pointer after the last appear of the same char
//         but do not remove the char from cache
//      2) HashSet: add(), remove(), isEmpty(), size(), contains()


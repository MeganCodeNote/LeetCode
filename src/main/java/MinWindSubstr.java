
public class MinWindSubstr {
    public String minWindow(String S, String T) {
        // assume both S and T are not null
        if (S.length() < T.length()) return ""; // fast input checking
        
        // char frequency for T
        int foundTotal = 0, targetTotal = T.length();
        int R = 256;
        int[] foundTable  = new int[R];
        int[] targetTable = new int[R];
        for (char ch: T.toCharArray())  targetTable[ch]++;
        
        // slide window
        int bestStart = -1, bestEnd = S.length();
        int start = 0;
        for (int end = 0; end < S.length(); end++) {
            char ch = S.charAt(end);
            if (targetTable[ch] > 0) {
                foundTable[ch]++;   // update counters
                if (foundTable[ch] <= targetTable[ch]) foundTotal++; // update total
                if (foundTotal == targetTotal) { // encounter a valid window
                		// increment start index as much as possible
                    char chs = S.charAt(start);
                    while (targetTable[chs] == 0 || targetTable[chs] < foundTable[chs]) {
                        if (targetTable[chs] < foundTable[chs]) foundTable[chs]--;
                        start++;
                        chs = S.charAt(start);
                    }
                    
                    // update best solution
                    if (end - start < bestEnd - bestStart) {
                    		bestEnd = end;
                    		bestStart = start;
                    }
                }
            }
        }// end of for loop
        
        if (bestStart == -1) return "";
        else return S.substring(bestStart, bestEnd + 1);  
    }
    
    ////////////////////////  TEST  /////////////////////////////
    
    public static void main(String[] args) {
		String S = "acbxxxa";
		String T = "ab";
		System.out.println((new MinWindSubstr()).minWindow(S, T));
	}
}

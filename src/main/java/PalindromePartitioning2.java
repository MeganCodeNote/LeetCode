
public class PalindromePartitioning2 {
    public int minCut(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return 0;
        }

        int N = s.length();
        boolean[][] cache = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || cache[j + 1][i - 1])) {
                    cache[j][i] = true;
                }
            }
        }

        int[] cuts = new int[N + 1];
        cuts[0] = -1;           // think about this carefully
        for (int i = 0; i < N; i++) {
            cuts[i + 1] = i;   // initialization, at most i cuts
            for (int j = 0; j <= i; j++) {
                if (cache[j][i]) {
                    cuts[i + 1] = Math.min(cuts[i + 1], cuts[j] + 1);
                }
            }
        }
        return cuts[N];
    }
}
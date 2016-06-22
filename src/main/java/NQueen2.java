import java.util.ArrayList;
import java.util.List;

public class NQueen2 {
    //--------------- Solution 1 --------------------//
    // use a 1D-array as a solution builder
    public int totalNQueens(int n) {
        // input checking
        int[] res = new int[1];
        if (n <= 0) {
            return res[0];
        }
        // use a 1D-array to indicate solution
        helper(res, new ArrayList<Integer>(), n);
        return res[0];
    }

    private void helper(int[] res, List<Integer> item, int n) {
        if (item.size() == n) {
            res[0]++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (item.contains(i)) {
                continue;
            }
            item.add(i);
            if (valid(item)) {
                helper(res, item, n);
            }
            item.remove(item.size() - 1);
        }
    }

    private boolean valid(List<Integer> item) {
        int k = item.size() - 1;
        for (int i = 0; i < item.size() - 1; i++) {
            if (Math.abs(item.get(i) - item.get(k)) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    //--------------- Solution 2 --------------------//
    // use bitmap to solve the problem!! cool!!
    public int totalNQueens2(int n) {
        int target = (1 << n) - 1;  // for n = 9: 00000000,00000000,00000001,11111111
        int[] count = {0};
        helper(0, 0, 0, target, count);
        return count[0];
    }

    private void helper(int up, int urd, int uld, int target, int[] count) {
        // Arguments indicate positions taken from 3 direction
        // 1) up: go up vertically
        // 2) urd: go up-right diagonally
        // 3) uld: go up-left diagonally
        if (up == target) {
            count[0] += 1;
            return;
        }

        // for each of available position
        int pos = target & ~(up | urd | uld);
        while(pos != 0) {
            int p = pos & (~pos + 1);  // get the righmost 1: 0110 --> 0010
            helper(up | p, (urd | p) << 1, (uld | p) >> 1, target, count);
            pos -= p;                  // remove the rightmost 1: 0110 --> 0100
        }
    }

    /////////////////////  TEST  ///////////////////////
    public static void main(String[] args) {
        NQueen2 queen = new NQueen2();
        System.out.println(queen.totalNQueens(4));
    }
}

import java.util.Arrays;

public class BestTimeBuyAndSellStock3 {
    //----------------- Solution 1 -------------------//
    // brute force, 4 pointers (Exceed Time Limit)
    public int maxProfit(int[] prices) {
        int N = prices.length;
        int global = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                for (int m = j; m < N; m++) {
                    for (int n = m; n < N; n++) {
                        int gain = (prices[j] - prices[i]) + (prices[n] - prices[m]);
                        global = Math.max(gain, global);
                    }
                }
            }
        }
        return global;
    }

    //----------------- Solution 2 -------------------//
    // DC(divide and conquer) + DP
    // DC: FIND the solution
    // DP: OPTIMIZE the solution
    public int maxProfit2(int[] prices) {
        // input checking
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int N = prices.length;
        int[] maxFromStart = new int[N];
        int[] maxToEnd = new int[N];
        
        // DP: start --> end
        int min = prices[0];
        for (int i = 1; i < N; i++) {
            min = Math.min(min, prices[i]);
            maxFromStart[i] = Math.max(maxFromStart[i - 1], prices[i] - min);
        }
        
        // DP: end --> start
        int max = prices[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            maxToEnd[i] = Math.max(maxToEnd[i + 1], max - prices[i]);
        }
        
        // find the solution of 2 trades
        int maxProfit = 0;
        for (int i = 0; i < N; i++) {
            maxProfit = Math.max(maxProfit, maxFromStart[i] + maxToEnd[i]);
        }
        return maxProfit;
    }

    //-------------- Solution 4 -------------------//
    // space-saving dp
    public int maxProfit4(int[] prices) {
        // input checking
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] global = new int[3];  // [num of trades]
        int[] local  = new int[3];

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int trades = 2; trades >= 1; trades--) {  // NOTE: have to count from 2 --> 1
                local[trades]  = Math.max(local[trades] + diff, global[trades - 1] + (diff > 0 ? diff : 0));
                global[trades] = Math.max(local[trades], global[trades]);
            }
        }

        return global[2];
    }

    //-------------- Solution 4 -------------------//
    // DP - Space saving
    public int maxProfit3(int[] prices) {
        // input checking
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int N = prices.length;
        int[][] global = new int[N][3];  // [index][num of trades]
        int[][] local  = new int[N][3];

        // initial: global[*][0] = 0, local[*][0] = 0. (0 trades, profit is 0)
        //          global[0][*] = 0, local[0][*] = 0, (day 0, no matter how many trades, profit is 0)

        for (int i = 1; i < N; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int trades = 1; trades <= 2; trades++) {
                local[i][trades]  = Math.max(local[i - 1][trades] + diff,
                    global[i - 1][trades - 1] + (diff > 0 ? diff : 0));  // NOTE: MUST HAVE () AROUND TERNARY EXPRESSION!!
                // X + Y > 0 ? Y : 0 SAME AS ==> (X + Y) > 0 ? Y : 0
                global[i][trades] = Math.max(local[i][trades], global[i - 1][trades]);
            }
        }

        return global[N - 1][2];
    }

    ////////////////////   TEST   /////////////////////
    private static void test(BestTimeBuyAndSellStock3 m, int[] A) {
        System.out.println(Arrays.toString(A));
        System.out.println(m.maxProfit3(A) + "\n");
    }
    public static void main(String[] args) {
        BestTimeBuyAndSellStock3 m = new BestTimeBuyAndSellStock3();
        int[] A1 = {397,6621,4997,7506,8918,1662,9187,3278,3890,514,18,9305,93,5508};
        int[] A2 = {1,2,0,1};
        test(m, A1);
        test(m, A2);
    }
}


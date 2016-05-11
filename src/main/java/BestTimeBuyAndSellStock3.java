
public class BestTimeBuyAndSellStock3 {
    //-------------- Solution 1 -------------------//
    // DAC(divide and conquer) + DP
    // DAC: FIND the solution
    // DP:  OPTIMIZE the solution
    public int maxProfit(int[] prices) {
        // input checking
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int N = prices.length;
        int[] maxFromStart = new int[N];
        int[] maxFromEnd = new int[N];
        
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
            maxFromEnd[i] = Math.max(maxFromEnd[i + 1], max - prices[i]);
        }
        
        // find the solution of 2 trades
        int maxProfit = 0;
        for (int i = 0; i < N; i++) {
            maxProfit = Math.max(maxProfit, maxFromStart[i] + maxFromEnd[i]);
        }
        return maxProfit;
    }

    //-------------- Solution 2 -------------------//
    // DP - could be generalized to k times of trades
    
}


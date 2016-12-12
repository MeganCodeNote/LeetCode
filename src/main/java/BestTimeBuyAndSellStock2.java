
public class BestTimeBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        // input checking
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // diff[] --> sum up all non-negative number in it
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {  // ATTENTION: off-by-one error
            maxProfit += Math.max(0, prices[i + 1] - prices[i]);
        }
        return maxProfit;
    }
}

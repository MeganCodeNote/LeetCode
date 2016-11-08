
public class BestTimeBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        // input checking
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int N = prices.length;
        int res = 0;
        if (k > N) {    // unlimited number of trades
            for (int i = 1; i < N; i++) {
                int diff = prices[i] - prices[i - 1];
                res += diff > 0 ? diff : 0;
            }
        } else {        // otherwise, use dp
            int[] local  = new int[k + 1];
            int[] global = new int[k + 1];
            for (int i = 1; i < N; i++) {
                int diff = prices[i] - prices[i - 1];
                for (int trades = k; trades >= 1; trades--) {
                    local[trades]  = Math.max(local[trades] + diff, global[trades - 1] + (diff > 0 ? diff : 0));
                    global[trades] = Math.max(local[trades], global[trades]);
                }
                res = global[k];
            }
        }
        return res;
    }
}


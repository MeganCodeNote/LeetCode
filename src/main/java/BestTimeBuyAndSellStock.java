
public class BestTimeBuyAndSellStock {
    //--------------  Solution 1  -------------------//
    // most intuitive solution
    public int maxProfit(int[] prices) {
        // input validation
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // record the min up util now
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i : prices) {
            min = Math.min(min, i);
            res = Math.max(res, i - min);
        }
        return res;
    }

    //--------------  Solution 2  -----------------//
    // == maximal sub-array problem (diff)
    public int maxProfit2(int[] prices) {
        // input validation
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // calculate the result
        int sum = 0;
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];  // get the diff
            sum = Math.max(0, sum + diff);
            res = Math.max(res, sum);
        }
        return res;
    }

    //----------------   Solution 3  -------------------//
    // dp: Global and local
    public int maxProfit3(int[] prices) {
        // input validation
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // calculate the result
        int local = 0;
        int global = 0;
        for (int i = 1; i < prices.length; i++) {
            local = Math.max(0, local + prices[i] - prices[i - 1]);
            global = Math.max(global, local);
        }
        return global;
    }
    
    ////////////////////  TEST ////////////////////
    public static void main(String[] args) {
        int[] prices = {1, 2, 5, 3, 4, 6, 2, 8};
        BestTimeBuyAndSellStock solution = new BestTimeBuyAndSellStock();
        System.out.println(solution.maxProfit2(prices));
    }
}
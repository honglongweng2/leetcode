package Top.ArrayOrString;

public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit main = new MaxProfit();
        System.out.println(main.maxProfitDP(new int[] {1, 3, 2, 8, 4, 9}, 2));
    }

    /**
     * No.121 只买卖一次
     *
     * 找每一步和当前最小值的差值
     */
    public int maxProfitOnce(int[] prices) {
        int result = 0;
        int lowest = Integer.MAX_VALUE, tempProfit = 0;
        for (int price : prices) {
            if (lowest > price) {
                lowest = price;
            }
            tempProfit = price - lowest;
            if (result < tempProfit) {
                result = tempProfit;
            }
        }
        return result;
    }

    /**
     * No.122 可以无数次买卖
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) return 0;
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                result += prices[i] - prices[i-1];
            }
        }
        return result;
    }

    /**
     * No.714 每次交易有手续费
     * （这个思路可以不考虑，过多if不易理解）
     * 情况一 低于当前buy的价格，则买入价还可以更低
     * 情况二 高于buy，可能交易。需要判断收益是否大于fee，若大于则可以成为一次成功的交易；若小于则往后找到可以大于fee的sell值
     * 情况三 [1, 4, 3, 6, 3], fee = 2时，虽然1-4和2-5两次可成功的交易，但最佳还是6-1-fee=3。也既prices[sell]-prices[step]
     */
    public int maxProfit(int[] prices, int fee) {
        int result = 0;
        int buy = 0, sell = 0;
        // 上次交易sell的位置
        int step = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[buy]) {
                // 出现更低买入价
                buy = sell = i;
            }
            if (prices[i] > prices[sell]) {
                sell = i;
                int trade = prices[sell] - prices[buy] - fee;
                if (trade > 0) {
                    // 一次有效的交易
                    // step == 0时一个交易也还没产生，prices[sell]-prices[step]还没-fee，所以要排除掉
                    result += Math.max(trade, step > 0 ? prices[sell] - prices[step] : 0);
                    buy = step = i;
                }
            }
            if (prices[i] > prices[step] && step > 0) {
                // 前一次交易已经成功，价格又涨了，重置前一次交易
                result += prices[sell] - prices[step];
                step = i;
            }
        }
        return result;
    }

    /**
     * No.714 每次交易有手续费
     * 贪心版本
     *
     * 思路：前面的情况一和情况二就是简单的单次交易时最大值最小值的差
     * 情况三，若某次跌幅超过fee，也既下一次交易必独立于前一次（已知 sell_first - buy_first = a +fee
     *                                                  sell_first - buy_next = b + fee
     *                                                  sell_next - sell_first = c
     *                                                  => Profit(sell_next - buy_next + sell_first - buy_first) = a+b+c
     *                                                     Profit(sell_next - buy_first) = a+c。
     * 于是贪心，出现跌幅超过fee时，计算profit并开始新一轮curProfit的计算
     */
    public int greed(int[] prices, int fee) {
        int profit=0;
        int curProfit=0;
        int minP=prices[0];
        int maxP=prices[0];
        for(int i=1; i<prices.length; i++) {
            minP = Math.min(minP, prices[i]);
            maxP = Math.max(maxP, prices[i]);
            curProfit = Math.max(curProfit, prices[i] - minP - fee);
            if ((maxP - prices[i]) >= fee) {
                profit += curProfit;
                curProfit = 0;
                minP = prices[i];
                maxP = prices[i];
            }
        }
        return profit + curProfit;
    }

    /**
     * No.714 每次交易有手续费
     * 思路：dp，两个数组（优化后只留一个数即可），一个存持有股票，一个存不持有股票。
     * 只能从持有股票处卖，从不持有处买
     */
    public int maxProfitDP(int[] prices, int fee) {
        int hold = -prices[0], sell = 0;
        for (int price : prices) {
            // 可继续持有/从未持有买入
            hold = Math.max(hold, sell - price);
            // 可卖/继续未持有
            sell = Math.max(price + hold - fee, sell);
        }
        return sell;
    }
}

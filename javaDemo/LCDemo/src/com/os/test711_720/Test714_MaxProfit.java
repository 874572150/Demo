package com.os.test711_720;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 9:52
 * @Description: 买卖股票的最佳时机含手续费 ※
 */
public class Test714_MaxProfit {
    @Test
    public void test() {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));   //8
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6;
        System.out.println(maxProfit(new int[]{1, 5, 9}, 2));   //6
        System.out.println(maxProfit(new int[]{1, 4, 6, 2, 8, 3, 10, 14}, 3));   //13
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // [i][0] : 第i天交易后未持有股票最大利润
        // [i][1] : 第i天交易后持有股票的最大利润
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}

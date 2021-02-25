package com.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/15 14:46
 * @Description: 整数反转
 */
public class Test07_Reverse {

    @Test
    public void test() {
//        int maxValue = 2147483647;
//        int minValue = -2147483648;
        int x = -1147483648;
        System.out.println(reverse(x));
    }

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            res = res * 10 + pop;
            x = x / 10;
        }
        return res;
    }
}

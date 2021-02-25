package com.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/15 15:15
 * @Description: 字符串转换整数 (atoi)
 */
public class Test08_MyAtoi {
    @Test
    public void test() {
        System.out.println(myAtoi("000042"));
    }

    public int myAtoi(String s) {
        long res = 0;
        int sign = 1;
        boolean start = false;
        for (int i = 0; i < s.length(); i++) {
            if (!start && s.charAt(i) == ' ') continue;
            if (!start && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                sign = s.charAt(i) == '-' ? -1 : 1;
                start = true;
                continue;
            }
            if (Character.isDigit(s.charAt(i))) {
                start = true;
                res = res * 10 + sign * (s.charAt(i) - '0');
                if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                    return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            } else {
                return (int) res;
            }
        }
        return (int) res;
    }
}

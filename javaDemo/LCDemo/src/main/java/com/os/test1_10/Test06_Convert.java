package com.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/15 10:21
 * @Description: Z 字形变换
 */
public class Test06_Convert {
    @Test
    public void test() {
        String str = "PAYPALISHIRING";
        System.out.println(convert(str, 4));
    }

    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        if (numRows == 1) {
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            int interval, intervalSum;
            if (i == 0 || i == numRows - 1) {
                interval = (numRows - 1) * 2 - 1;
                intervalSum = interval * 2;
            } else {
                interval = (numRows - 2 - i) * 2 + 1;// 中间行间隔数;
                intervalSum = 2 * numRows - 4;  // 一圈中非本行总数
            }
            for (int j = i; j < s.length(); j = interval + j + 1, interval = intervalSum - interval) {
                sb.append(s.charAt(j));
            }
        }
        return sb.toString();
    }
}

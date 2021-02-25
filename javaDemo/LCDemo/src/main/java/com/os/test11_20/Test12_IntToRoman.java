package com.os.test11_20;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 14:32
 * @Description: 整数转罗马数字
 */
public class Test12_IntToRoman {
    @Test
    public void test() {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }

    public String intToRoman(int num) {
        int[] numbers = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            while (num >= numbers[i]) {
                sb.append(romans[i]);
                num -= numbers[i];
            }
        }
        return sb.toString();
    }


}

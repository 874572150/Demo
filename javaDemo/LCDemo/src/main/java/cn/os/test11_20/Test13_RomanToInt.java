package cn.os.test11_20;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 15:24
 * @Description: 罗马数字转整数
 */
public class Test13_RomanToInt {
    @Test
    public void test() {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));

    }

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char c1 = s.charAt(0);
        int romanValue = 0;
        int bfNum = getValueByRomanChar(c1);
        for (int i = 1; i < s.length(); i++) {
            int afNum = getValueByRomanChar(s.charAt(i));
            if (bfNum < afNum) {
                romanValue -= bfNum;
            } else {
                romanValue += bfNum;
            }
            bfNum = afNum;
        }
        romanValue += bfNum;
        return romanValue;
    }

    public int getValueByRomanChar(Character c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
}

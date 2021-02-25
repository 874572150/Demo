package com.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/14 16:56
 * @Description: 最长回文子串
 */
public class Test05_LongestPalindrome {
    @Test
    public void test() {
        String str = "bb";
        System.out.println(longestPalindrome(str));
    }

    public String longestPalindrome(String s) {
        String maxStr = "" + s.charAt(0);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                String tempStr = getPalindrome(s, i, i + 1);
                maxStr = tempStr.length() > maxStr.length() ? tempStr : maxStr;
            }
            if (i > 0 && i + 1 < s.length() && s.charAt(i - 1) == s.charAt(i + 1)) {
                String tempStr = getPalindrome(s, i - 1, i + 1);
                maxStr = tempStr.length() > maxStr.length() ? tempStr : maxStr;
            }
        }
        return maxStr;
    }

    private String getPalindrome(String str, int left, int right) {
        while (true) {
            if (str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
            } else {
                return str.substring(left + 1, right);
            }
            if (left < 0) {
                return str.substring(0, right);
            }
            if (right >= str.length()) {
                return str.substring(left + 1);
            }
        }
    }
}

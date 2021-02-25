package com.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/16 14:35
 * @Description: 正则表达式匹配 ※
 */
public class Test10_IsMatch {
    @Test
    public void test() {
//        System.out.println(isMatch("ab", ".*c"));
//        System.out.println(isMatch("aaa", "aaaa"));
//        System.out.println(isMatch("aab", "c*a*b"));
//        System.out.println(isMatch("aaa", "a*a"));
//        System.out.println(isMatch("aaa", "ab*a*c*a"));
        System.out.println(isMatch("baa", "a*c"));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // f[i][j] 标识s的前i个字符与p的前j个字符是否匹配
        boolean[][] f = new boolean[m + 1][n + 1];
        // s与p均为空字符串时,匹配成功
        f[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (isMatch(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (isMatch(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean isMatch(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}


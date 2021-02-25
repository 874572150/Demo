package com.os.test11_20;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 15:53
 * @Description: 最长公共前缀
 */
public class Test14_LongestCommonPrefix {
    @Test
    public void test() {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"flower"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{"cir", "car"}));
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int minLength = -1;
        for (int i = 0; i < strs.length; i++) {
            minLength = minLength == -1 ? strs[i].length() : Math.min(strs[i].length(), minLength);
        }
        for (int i = 0; i < minLength; i++) {
            char c = strs[0].charAt(i);
            int j = 1;
            for (; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return sb.toString();
                }
            }
            if (j == strs.length) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

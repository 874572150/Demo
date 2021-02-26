package cn.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/14 15:22
 * @Description: 无重复字符的最长子串
 */
public class Test03_LengthOfLongestSubstring {
    @Test
    public void test() {
        String str = "bbbbb";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int beginIndex = -1;
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (str.contains(String.valueOf(ch))) {
                // 未重复的最大字串长度
                maxLength = str.length() > maxLength ? str.length() : maxLength;
                int subIndex = str.indexOf(ch);
                beginIndex = beginIndex + subIndex + 1;
                str = s.substring(beginIndex + 1, i);
            }
            str += ch;
        }
        maxLength = str.length() > maxLength ? str.length() : maxLength;
        return maxLength;
    }
}

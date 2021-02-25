package com.os.test281_290;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Oushuo
 * @Date: 2020/12/16 14:22
 * @Description: 单词规律
 */
public class Test290_WordPattern {
    @Test
    public void test() {
        String pattern = "abba", str = "dog dog dog dog";
        System.out.println(wordPattern(pattern, str));
    }

    public boolean wordPattern(String pattern, String s) {
        if (s == null || s.length() == 0) return false;
        String[] strs = s.split(" ");
        if (pattern.length() != strs.length) return false;
        // 每个pattern对应一个单词
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if ((map.containsKey(c) && !map.get(c).equals(strs[i])) ||
                    (!map.containsKey(c) && map.values().contains(strs[i]))) {
                return false;
            } else {

                map.put(c, strs[i]);
            }
        }
        return true;
    }
}

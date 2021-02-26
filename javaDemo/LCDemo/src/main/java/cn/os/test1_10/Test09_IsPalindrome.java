package cn.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/15 17:29
 * @Description: 回文数
 */
public class Test09_IsPalindrome {
    @Test
    public void test() {
        System.out.println(isPalindrome(-121));
    }

    public boolean isPalindrome(int x) {
        int temp = x;
        int y = 0;
        if (x < 0) return false;
        while (temp != 0) {
            y = y * 10 + temp % 10;
            temp = temp / 10;
        }
        return x == y;
    }
}

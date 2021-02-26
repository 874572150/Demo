package cn.os.test11_20;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2021/01/21 15:39
 * @Description: 有效的括号
 */
public class Test20_IsValid {
    @Test
    public void test() {
        System.out.println(isValid("{}}{"));
        System.out.println(isValid("()"));
        System.out.println(isValid("([])"));
        System.out.println(isValid("([)"));
        System.out.println(isValid("([))"));
    }

    public boolean isValid(String s) {
        int length = s.length();
        if (length < 2) {
            return false;
        }
        // 左边界
        int leftBorder = 0;
        int left = 0;
        int right = 1;
        while (right < length) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (bracketMatch(leftChar, rightChar)) {
                right--;
                left++;
            }
            // 小于左边界，更新左边界值
            if (left < leftBorder) {
                leftBorder = right;
                left = right;
                right++;
            }
            // 到达边界，判断可以消除完毕
            if (right == length - 1 && left == leftBorder && bracketMatch(leftChar, rightChar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看括号是否匹配
     *
     * @param leftChar
     * @param rightChar
     * @return
     */
    private boolean bracketMatch(char leftChar, char rightChar) {
        if ((leftChar == '(' && rightChar == ')') ||
                (leftChar == '[' && rightChar == ']') ||
                (leftChar == '{' && rightChar == '}')) {
            return true;
        }
        return false;
    }

    /**
     * 括号锁死
     *
     * @return
     */
    private boolean bracketLock(char leftChar, char rightChar) {
        char[] leftChars = new char[]{'(', '{', '['};
        char[] rightChars = new char[]{')', '}', ']'};
        return true;
    }
}

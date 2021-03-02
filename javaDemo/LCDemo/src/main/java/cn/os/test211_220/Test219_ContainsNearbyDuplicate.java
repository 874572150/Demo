package cn.os.test211_220;

import org.junit.Test;

/**
 * @author ou shuo
 * @description: 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * @date 2021/03/01 10:35
 */
public class Test219_ContainsNearbyDuplicate {
    @Test
    public void test() {
        System.out.println(containsNearbyDuplicate(new int[]{99, 99}, 2));
    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

package com.os.test11_20;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 17:34
 * @Description: 最接近的三个数之和
 */
public class Test16_ThreeSumClosest {
    @Test
    public void test() {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4, -8, 7}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        int sum = nums[0] + nums[1] + nums[2];
        int closestDistance = sum - target;
        for (int i = 0; i < nums.length; i++) {

        }
        return sum;
    }
}

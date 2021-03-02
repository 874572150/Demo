package cn.os.test621_630;

import java.util.Arrays;

/**
 * @author ou shuo
 * @description: 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * @date 2021/03/01 9:27
 */
public class Test628_MaximumProduct {
    /**
     * @param nums
     * @return 时间复杂度：O(NlogN)
     * 空间复杂度：O(logN)
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length;
        return Math.max(nums[0] * nums[1] * nums[l - 1],
                nums[l - 1] * nums[l - 2] * nums[l - 3]);
    }

    /**
     * @param nums
     * @return
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public int maximumProduct1(int[] nums) {
        // 最小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}

package cn.os.test1_10;

import java.util.Arrays;

/**
 * @Author: Oushuo
 * @Date: 2020/12/14 13:54
 * @Description: 两数之和
 */
public class Test01_TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 13};
        int target = 13;
        Test01_TwoSum test01_twoSum = new Test01_TwoSum();
        int[] res = test01_twoSum.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (temp == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}

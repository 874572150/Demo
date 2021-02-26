package cn.os.test11_20;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 16:06
 * @Description: 三数之和
 */
public class Test15_ThreeSum {

    @Test
    public void test() {
//        System.out.println(threeSum(new int[]{-1, 0, 0, 0, 0, 1, 2, -1, -4}));
//        System.out.println(threeSum(new int[]{1, -1, -1, 0}));
//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}));
//        System.out.println(threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
        System.out.println(threeSum(new int[]{0, 0, 0}));
    }

    // 排序 + 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] + nums[i + 1] > 0) {
                return lists;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    lists.add(Arrays.asList(new Integer[]{nums[i], nums[left], nums[right]}));
                    left++;
                    right--;
                    while (true) {
                        if (right <= left) {
                            break;
                        }
                        if (nums[left] == nums[left - 1]) {
                            left++;
                        } else if (nums[right] == nums[right + 1]) {
                            right--;
                        } else {
                            break;
                        }
                    }
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return lists;
    }
}

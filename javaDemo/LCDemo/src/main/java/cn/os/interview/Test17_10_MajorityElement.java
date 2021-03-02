package cn.os.interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test17_10_MajorityElement {
    @Test
    public void test() {

        System.out.println(majorityElement(new int[]{1, 2, 2, 2, 4, 5}));
        System.out.println(majorityElement2(new int[]{1, 5, 6, 6, 6, 6, 5}));
    }

    // 时间复杂度: O(n)
    // 空间复杂度: O(n)
    public int majorityElement(int[] nums) {
        int res = -1;
        int half = nums.length / 2;
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int l = 1;
            if (maps.containsKey(nums[i])) {
                l = maps.get(nums[i]) + 1;
            }
            if (l > half) {
                res = nums[i];
                break;
            }
            maps.put(nums[i], l);
        }
        return res;
    }

    // 时间复杂度: O(nlogn)
    // 空间复杂度: O(1)
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i + nums.length / 2 < nums.length; i++) {
            if (nums[i] == nums[i + nums.length / 2]) {
                return nums[i];
            }
        }
        return -1;
    }
}

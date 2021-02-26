package cn.os.interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test17_10_MajorityElement {
    @Test
    public void test() {
        System.out.println(majorityElement(new int[]{1, 2, 2, 2, 4, 5}));
    }

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

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[nums.length / 2]) {
                return nums[i];
            }
        }
        return -1;
    }
}

package cn.os.test971_980;

import org.junit.Test;

import java.util.Arrays;

public class Test977_SortedSquares {
    @Test
    public void test() {
        System.out.println(Arrays.toString(sortedSquares(new int[]{1, 7, 6, 3})));
        System.out.println(Arrays.toString(sortedSquares2(new int[]{-9, -3, -11, 0, 3, 2})));
        System.out.println(Arrays.toString(sortedSquares3(new int[]{-9, -3, -11, 0, 3, 2})));
    }

    // 时间复杂度: O(nlogn)
    // 空间复杂度: O(logn)
    public int[] sortedSquares(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        for (int i = 0; i < l; i++) {
            res[i] = nums[i] * nums[i];
        }

        Arrays.sort(res);
        return res;
    }

    public int[] sortedSquares2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    public int[] sortedSquares3(int[] nums) {
        return Arrays.stream(nums).map(n -> n * n).sorted().toArray();
    }


}

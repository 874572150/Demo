package cn.os.test1_10;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Oushuo
 * @Date: 2020/12/14 16:07
 * @Description: 寻找两个正序数组的中位数
 */
public class Test04_FindMedianSortedArrays {
    @Test
    public void test() {
        int[] nums1 = new int[]{1, 2, 7};
        int[] nums2 = new int[]{3, 4, 6};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < nums1.length; ) {
            if (j < nums2.length && nums2[j] < nums1[i]) {
                list.add(nums2[j++]);
            } else {
                list.add(nums1[i++]);
            }
        }
        for (int i = j; i < nums2.length; i++) {
            list.add(nums2[i]);
        }
        int total = nums1.length + nums2.length;
        if (total % 2 == 0) {
            int mid1 = list.get(total / 2 - 1);
            int mid2 = list.get(total / 2);
            return (double) (mid1 + mid2) / 2;
        }
        return (double) list.get(total / 2);
    }
}

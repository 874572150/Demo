package com.os.test11_20;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/17 14:02
 * @Description: 盛最多水的容器
 */
public class Test11_MaxArea {

    @Test
    public void test() {
        System.out.println(maxArea(new int[]{1, 1}));
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxArea(new int[]{1, 2, 1}));
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea1(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));

    }

    public int maxArea(int[] height) {
        int n = height.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                if (w * h > maxArea) {
                    maxArea = w * h;
                }
            }
        }
        return maxArea;
    }

    // 双指针
    public int maxArea1(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left) {
            maxArea = Math.max((right - left) * Math.min(height[left], height[right]), maxArea);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }
}

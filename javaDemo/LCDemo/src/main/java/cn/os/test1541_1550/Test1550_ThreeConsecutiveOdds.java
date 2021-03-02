package cn.os.test1541_1550;

/**
 * @author ou shuo
 * @description: 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：
 * 如果存在，请返回 true ；否则，返回 false 。
 * @date 2021/03/01 10:07
 */
public class Test1550_ThreeConsecutiveOdds {
    /**
     * @param arr
     * @return
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        int oddLength = 0;
        for (int i = 0; i < arr.length; i++) {
            oddLength = arr[i] % 2 != 0 ? oddLength + 1 : 0;
            if (oddLength == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param arr
     * @return
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean threeConsecutiveOdds1(int[] arr) {
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] % 2 != 0 && arr[i - 1] % 2 != 0 && arr[i - 2] % 2 != 0) {
                return true;
            }
        }
        return false;
    }
}

package cn.os.test1581_1590;

import org.junit.Test;

public class Test1588_SumOddLengthSubarrays {
    @Test
    public void test() {
        System.out.println(sumOddLengthSubarrays3(new int[]{1, 4, 2, 5}));
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            sum += arr[i] + sumOddLengthSubarrays(arr, i);
        }
        return sum;
    }

    public int sumOddLengthSubarrays(int[] arr, int index) {
        int sum = 0;
        int bfSum = arr[index];
        int left = index - 1;
        int right = index + 1;
        while (left >= 0 && right < arr.length) {
            bfSum = arr[left] + bfSum + arr[right];
            sum += bfSum;
            left--;
            right++;
        }
        return sum;
    }

    public int sumOddLengthSubarrays3(int[] arr) {
        int arrSize = arr.length;
        int res = 0;
        int lEven, lOdd, rEven, rOdd;
        for (int i = 0; i < arrSize; i++) {
            lOdd = (i + 1) / 2;
            lEven = i / 2 + 1;
            rOdd = (arrSize - i) / 2;
            rEven = (arrSize - i + 1) / 2;
            res += (lOdd * rOdd + lEven * rEven) * arr[i];
        }
        return res;

    }
}

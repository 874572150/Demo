package cn.os.test861_870;

import org.junit.Test;

import java.util.Arrays;

public class Test867_Transpose {

    @Test
    public void test() {
        System.out.println(Arrays.toString(transpose(new int[][]{{1, 3, 2}, {8, 4, 9}})));
        System.out.println(Arrays.deepToString(transpose(new int[][]{{1, 3, 2}, {8, 4, 9}})));

    }

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] k = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                k[i][j] = matrix[j][i];
            }
        }
        return k;
    }
}
